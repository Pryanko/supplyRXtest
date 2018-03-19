package ru.supplyphotos.supplyrxtest;

import android.Manifest;
import android.content.Intent;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import com.vistrav.ask.Ask;
import com.vistrav.ask.annotations.AskDenied;
import com.vistrav.ask.annotations.AskGranted;

import java.io.IOException;
import java.util.List;
import java.util.function.BiFunction;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import ru.supplyphotos.supplyrxtest.data.ImageFile;
import ru.supplyphotos.supplyrxtest.data.PhotoIdFile;
import ru.supplyphotos.supplyrxtest.data.cloud_upload_url.UploadUrl;
import ru.supplyphotos.supplyrxtest.data.order_item_id.OrderItemId;
import ru.supplyphotos.supplyrxtest.data.photo_id.PhotoId;
import ru.supplyphotos.supplyrxtest.di.AppRepository;


@SuppressWarnings("unused")
public class MainActivity extends AppCompatActivity {


    private int serviceId = 9;
    private int orderId = 31;
    private int orderItemId = 13;
    private String fileName = "file123123.jpg";
    private String tokenUser = "abacdafb6e91f52697e958d2c668733f";

    private CompositeDisposable compositeDisposable;
    private AppRepository appRepository;
    private Button buttonSend;
    private Button buttonOrder;
    private Button buttonGallery;
    private TextView textView;
    private ApiService apiService;
    private List<String> pathImageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appRepository = App.getAppComponent().getAppRepository();


        buttonSend = (Button) findViewById(R.id.button_send);
        buttonOrder = (Button) findViewById(R.id.button_order);
        buttonGallery = (Button) findViewById(R.id.button_gallery);
        textView = (TextView) findViewById(R.id.url_text);
        compositeDisposable = new CompositeDisposable();
        apiService = ApiService.retrofit.create(ApiService.class);
        buttonSend.setOnClickListener(v -> sendPhoto());
        buttonGallery.setOnClickListener(v -> openGallery());
        buttonOrder.setOnClickListener(v -> createOrder());

    }

    private void createOrder(){

        compositeDisposable.add(apiService.createOrder(tokenUser)
                           .map(Mappers::mapOrderId)
                .doOnNext(integer -> appRepository.setOrderId(integer))
                .flatMap(integer -> apiService.createOrderItem(tokenUser, integer, serviceId))
                .doOnNext(orderItemId1 -> appRepository.setOrderItemId(orderItemId1.getData()
                        .getOrderItemId()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::readOrderItemId, this::onError));


    }


    private void sendPhoto(){



        compositeDisposable.add(appRepository.getObservableListPathImage()

                .map(Mappers::mapImageFile)
                .flatMap(Observable::fromIterable)
                .flatMap(this::setCasheFile)
               /* .doOnNext(imageFile ->
                {appRepository.setFileImage(imageFile.getFile());
                               Log.d("ImageFile", imageFile.getNameImage());})*/
                .flatMap(imageFile -> apiService.getPhotoId(tokenUser, appRepository.getOrderItemId(),
                        imageFile.getNameImage()))
                .flatMap(photoId -> apiService.getUploadUrl(tokenUser, photoId.getData().getPhotoId()))
                .map(uploadUrl -> Mappers.mapPhotoUrlFile(uploadUrl, appRepository.getFileImage()))
                .flatMap(this::sendPhoto)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::createPhoto, this::onError));

    }

    private Observable<ImageFile> setCasheFile(ImageFile imageFile){
        appRepository.setFileImage(imageFile.getFile());
        Log.d("ImageFile", imageFile.getNameImage());
        return Observable.just(imageFile);
    }

    private Observable<ResponseBody> sendPhoto(PhotoIdFile photoIdFile){

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), photoIdFile.getFile());
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", photoIdFile.getFile().getName(), requestFile);
        return apiService.uploadPhoto(photoIdFile.getUploadUrl(), body);

    }

 /*   private void upLoadPhoto(PhotoIdFile photoIdFile){
        UploadImageService uploadImageService = new UploadImageService();
        ApiService apiService1 = uploadImageService.getRetrofit().create(ApiService.class);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), photoIdFile.getFile());

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", "file.jpg", requestFile);
       // String path = photoIdFile.getUploadUrl().substring(9);
        compositeDisposable.add(apiService1.uploadPhoto(photoIdFile.getUploadUrl(), body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::createPhoto));

    }*/

    private void createPhoto(ResponseBody responseBody) throws IOException {
        textView.setText(responseBody.toString());
    }


    private void readOrderItemId(OrderItemId orderItemId){
        textView.setText("Создан order_id:" + String.valueOf(appRepository.getOrderId())
                + "  " + "Создан order_item_id:" + String.valueOf(orderItemId.getData().getOrderItemId()));
    }



    private void startRxShow(){
        Log.d("Start", "OK");

        compositeDisposable.add(apiService.getPhotoId(tokenUser, orderItemId, fileName)
                .doOnNext(photoId -> Log.d("Start",
                        String.valueOf(photoId.toString())))
                .map(Mappers::mapPhotoId)
                .flatMap(i -> apiService.getUploadUrl(tokenUser, i))
                .doOnNext(uploadUrl -> Log.d("Start", uploadUrl.getData().toString()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::readUrlResponse, this::onError));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        appRepository.addItemListImage(Mappers.getRealPathFromURI(uri, getContentResolver()));
        textView.setText("Отобрано фотографий: " + String.valueOf(appRepository.getListImageSize()));
    }

    private void openGallery(){


        Ask.on(this)
                .id(1) // in case you are invoking multiple time Ask from same activity or fragment
                .forPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withRationales("In order to save file you will need to grant storage permission") //optional
                .go();





    }

    //optional
    @AskGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void fileAccessGranted(int id) {

        Log.i("TAG", "FILE  GRANTED");
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 200);


    }

    //optional
    @AskDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void fileAccessDenied(int id) {
        Log.i("TAG", "FILE  DENiED");
    }



    private void readUrlResponse(UploadUrl uploadUrl){
        Log.d("OK", uploadUrl.getData().toString());
        textView.setText(uploadUrl.getData().getUrl());

    }

    private void onError(Throwable throwable){
        Log.d("Error", throwable.getMessage());

    }
}
