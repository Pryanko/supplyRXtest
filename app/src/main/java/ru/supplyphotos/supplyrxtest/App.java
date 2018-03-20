package ru.supplyphotos.supplyrxtest;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

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

        Fresco.initialize(this);


        appComponent = DaggerAppComponent.builder()
                .build();


    }


    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
