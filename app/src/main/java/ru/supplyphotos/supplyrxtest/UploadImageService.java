package ru.supplyphotos.supplyrxtest;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

/**
 * @author libgo (19.03.2018)
 */

public class UploadImageService {

    private String string;

    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://photo.it33.ru/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                //Для всех запросов используется шедулер созданный выше.
                // .client(TestInterceptor.getInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }





}
