package ru.supplyphotos.gallery_fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vistrav.ask.Ask;
import com.vistrav.ask.annotations.AskDenied;
import com.vistrav.ask.annotations.AskGranted;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.supplyphotos.supplyrxtest.R;

/**
 * @author libgo (20.03.2018)
 */

public class GalleryActivity extends AppCompatActivity{


    private List<String> imageUrls;
    private RecyclerView recyclerView;
    private GalleryAdapter galleryAdapter;
    private int count;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        recyclerView = (RecyclerView) findViewById(R.id.image);
        galleryAdapter = new GalleryAdapter();


        Ask.on(this)
                .id(2) // in case you are invoking multiple time Ask from same activity or fragment
                .forPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withRationales("In order to save file you will need to grant storage permission") //optional
                .go();


    }

    //optional
    @AskGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void fileAccessGranted(int id) {
        Log.i("TAG", "ok");
        getImageUri();


    }

    //optional
    @AskDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void fileAccessDenied(int id) {
        Log.i("TAG", "FILE  DENiED");
    }

    private  void getImageUri() {


       /* final String[] columns = { MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID };
        final String orderBy = MediaStore.Images.Media._ID;
        Cursor imagecursor = managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
        null, orderBy);
        int image_column_index = imagecursor
                .getColumnIndex(MediaStore.Images.Media._ID);
        this.count = imagecursor.getCount();
        this.thumbnails = new Bitmap[this.count];
        this.arrPath = new String[this.count];
        for (int i = 0; i < this.count; i++) {
            imagecursor.moveToPosition(i);
            int id = imagecursor.getInt(image_column_index);
            int dataColumnIndex = imagecursor
                    .getColumnIndex(MediaStore.Images.Media.DATA);
            thumbnails[i] = MediaStore.Images.Thumbnails.getThumbnail(
                    getApplicationContext().getContentResolver(), id,
                    MediaStore.Images.Thumbnails.MICRO_KIND, null);
            arrPath[i] = imagecursor.getString(dataColumnIndex);
        }
        imagecursor.close();
        addImage();
    }     */


        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        Cursor imagecursor = managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy + " DESC");

        this.imageUrls = new ArrayList<>();

        for (int i = 0; i < imagecursor.getCount(); i++) {
            
            imagecursor.moveToPosition(i);
            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
            imageUrls.add(imagecursor.getString(dataColumnIndex));
            Log.d("TAG", imagecursor.getString(dataColumnIndex));
            //Log.d("Path file", imageUrls.get(i));
        }
        imagecursor.close();


    }

    private void StartRx(){
        Disposable disposable = Flowable.just(imageUrls)
                .subscribeOn(Schedulers.newThread())
                .flatMap(Flowable::fromIterable)
                .flatMap(s -> Utils.addImage(s))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addImage, this::onError);
    }

    private void onError(Throwable throwable) {
    }

    private void addImage(StateFile stateFile) {
        recyclerView.setLayoutManager( new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(galleryAdapter);
        galleryAdapter.updateImageList(file);

    }


    }



