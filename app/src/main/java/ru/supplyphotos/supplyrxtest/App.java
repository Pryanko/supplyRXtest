package ru.supplyphotos.supplyrxtest;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;

import ru.supplyphotos.supplyrxtest.di.AppComponent;
import ru.supplyphotos.supplyrxtest.di.DaggerAppComponent;


/**
 * @author libgo (18.03.2018)
 */

public class App extends Application {



    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setResizeAndRotateEnabledForNetwork(true)
                .setDownsampleEnabled(true)
                .build();

        Fresco.initialize(this, config);


        appComponent = DaggerAppComponent.builder()
                .build();


    }


    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
