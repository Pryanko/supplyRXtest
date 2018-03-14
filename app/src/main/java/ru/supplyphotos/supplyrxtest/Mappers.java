package ru.supplyphotos.supplyrxtest;

import android.util.Log;

import ru.supplyphotos.supplyrxtest.data.photo_id.PhotoId;

/**
 * @author libgo (14.03.2018)
 */

public class Mappers {

    public  static Integer mapPhotoId(PhotoId photoId){
        Log.d("ANSWER", String.valueOf(photoId.getData().getPhotoId()) );
        return  photoId.getData().getPhotoId();

    }
}
