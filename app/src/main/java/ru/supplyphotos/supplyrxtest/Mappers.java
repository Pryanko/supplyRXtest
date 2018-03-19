package ru.supplyphotos.supplyrxtest;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.supplyphotos.supplyrxtest.data.ImageFile;
import ru.supplyphotos.supplyrxtest.data.PhotoIdFile;
import ru.supplyphotos.supplyrxtest.data.order_id.OrderId;
import ru.supplyphotos.supplyrxtest.data.photo_id.PhotoId;

/**
 * @author libgo (14.03.2018)
 */

public class Mappers {

    public  static Integer mapPhotoId(PhotoId photoId){
        return  photoId.getData().getPhotoId();

    }

    public static Integer mapOrderId(OrderId orderId){
        return orderId.getData().getOrderId();
    }

    public static String getRealPathFromURI (Uri contentUri, ContentResolver contentResolver) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = contentResolver.query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    public static List<ImageFile> mapImageFile(List<String> listPath){
        List<ImageFile> imageFiles = new ArrayList<>();
        for(String s : listPath){
            ImageFile imageFile = new ImageFile();
            File file = new File(s);
            imageFile.setFile(file);
            imageFile.setPath(s);
            imageFile.setNameImage(file.getName());
            imageFiles.add(imageFile);
        }
        return imageFiles;
    }

    public static PhotoIdFile zipPhotoIdFile (ImageFile imageFile, Observable<PhotoId> photoId){
        PhotoIdFile photoIdFile = new PhotoIdFile();
        photoIdFile.setFile(imageFile.getFile());
        photoIdFile.setPhoto_id(photoId.getData().getPhotoId());
        return photoIdFile;

    }


}
