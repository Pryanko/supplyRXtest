package ru.supplyphotos.supplyrxtest;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import ru.supplyphotos.supplyrxtest.data.cloud_upload_url.UploadUrl;
import ru.supplyphotos.supplyrxtest.data.order_id.OrderId;
import ru.supplyphotos.supplyrxtest.data.order_item_id.OrderItemId;
import ru.supplyphotos.supplyrxtest.data.photo_id.PhotoId;

/**
 * @author libgo (13.03.2018)
 */

public interface ApiService {


    @GET("order-create")
    Observable<OrderId> createOrder(@Query("access_token") String token);

    @GET("order-item-create")
    Observable<OrderItemId> createOrderItem(@Query("access_token") String token,
                                            @Query("order_id") Integer order_id,
                                            @Query("service_id") Integer service_id);

    @GET("photo-add")
    Observable<PhotoId> getPhotoId(@Query("access_token") String token,
                                 @Query("order_item_id") Integer order_item,
                                 @Query("filename") String filename);

    @GET("photo-get-upload-url")
    Observable<UploadUrl> getUploadUrl(@Query("access_token") String token,
                                       @Query("photo_id") Integer photo_id);

    @Multipart
    @POST()
    Observable<ResponseBody> uploadPhoto(@Url String url, @Part MultipartBody.Part partImage);






    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://photo.it33.ru/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            //Для всех запросов используется шедулер созданный выше.
           // .client(TestInterceptor.getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();


}
