package com.example.fivedayforecast.dagger;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.fivedayforecast.db.ForecastDatabase;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.converter.gson.GsonConverterFactory;



@Module
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    public ForecastDatabase provideForecastDatabase(Context context) {
        return Room.databaseBuilder(context, ForecastDatabase.class, "forecast").build();
    }

}
