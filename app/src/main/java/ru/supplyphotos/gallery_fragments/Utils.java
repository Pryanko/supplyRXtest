package ru.supplyphotos.gallery_fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Libgo on 21.03.2018.
 */

public class Utils {

    private static int page = 0;

    public static StateFile addImage(String s) {
        Log.d("IMAGE LOAD", "Start    "  + String.valueOf(page));

        File file = new File(s);
        StateFile stateFile = new StateFile();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = null;
      /*  try {


            bitmap = Bitmap.createScaledBitmap(cropBitmap(BitmapFactory
                    .decodeStream(new FileInputStream(file),
                            null, options)), 400,400, false);

            Log.d("isERROR", "notERROR");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("ERROR", "ERROR");
        }  */
        page++;

        stateFile.setBitmap(bitmap);

        return stateFile;







        //  gridAdapter = new GridAdapter(getSystemService(Context.LAYOUT_INFLATER_SERVICE), count);
        //  gridView.setAdapter(gridAdapter);
        //  gridAdapter.setThumbnails(imageUrls);


        //recyclerView.setLayoutManager(gridLayoutManager);
        //recyclerView.setAdapter(galleryAdapter);
        //galleryAdapter.updateImageList(imageUrls);
        //simpleDraweeView.setImageURI("file://" + s);

    }

    private static Bitmap cropBitmap(Bitmap bitmap){
        Bitmap newBitmap = null;
        if(bitmap.getWidth() >= bitmap.getHeight()){
            newBitmap = Bitmap.createBitmap(bitmap, bitmap.getWidth()/2 - bitmap.getHeight()/2,
                    0,
                    bitmap.getHeight(),
                    bitmap.getHeight()
            );

        }else{
            newBitmap = Bitmap.createBitmap(
                    bitmap,
                    0,
                    bitmap.getHeight()/2 - bitmap.getWidth()/2,
                    bitmap.getWidth(),
                    bitmap.getWidth()
            );}
        return newBitmap;
    }


}


