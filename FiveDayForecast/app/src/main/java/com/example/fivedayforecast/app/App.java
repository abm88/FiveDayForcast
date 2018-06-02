package com.example.fivedayforecast.app;

import android.app.Application;

import com.example.fivedayforecast.dagger.AppComponent;
import com.example.fivedayforecast.dagger.AppModule;
import com.example.fivedayforecast.dagger.DaggerAppComponent;
import com.example.fivedayforecast.dagger.NetworkModule;
import net.danlew.android.joda.JodaTimeAndroid;



public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                            .appModule(new AppModule(this))
                            .build();
        JodaTimeAndroid.init(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
