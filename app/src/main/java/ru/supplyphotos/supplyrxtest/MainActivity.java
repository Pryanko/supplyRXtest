package ru.supplyphotos.supplyrxtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.supplyphotos.supplyrxtest.data.cloud_upload_url.UploadUrl;

public class MainActivity extends AppCompatActivity {


    private int serviceId = 9;
    private int orderId = 31;
    private int orderItemId = 13;
    private String fileName = "file123123.jpg";
    private String tokenUser = "abacdafb6e91f52697e958d2c668733f";


    Button button;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.url_text);
        button.setOnClickListener(v -> startRxShow());

    }

    private void startRxShow(){
        Log.d("Start", "OK");
        ApiService apiService = ApiService.retrofit.create(ApiService.class);

        Disposable disposable = apiService.getPhotoId(tokenUser, orderItemId, fileName)
                .doOnNext(photoId -> Log.d("Start",
                        String.valueOf(photoId.toString())))
                .map(Mappers::mapPhotoId)
                .flatMap(i -> apiService.getUploadUrl(tokenUser, i))
                .doOnNext(uploadUrl -> Log.d("Start", uploadUrl.getData().toString()))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::readUrlResponse, this::onError);


    }

    private void readUrlResponse(UploadUrl uploadUrl){
        Log.d("OK", uploadUrl.getData().toString());
        textView.setText(uploadUrl.getData().getUrl());

    }

    private void onError(Throwable throwable){
        Log.d("Error", throwable.getMessage());

    }
}
