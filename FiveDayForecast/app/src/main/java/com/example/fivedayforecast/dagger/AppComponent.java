package com.example.fivedayforecast.dagger;

import com.example.fivedayforecast.db.ForecastDatabase;
import com.example.fivedayforecast.home.mvp.HomeActivity;
import com.example.fivedayforecast.net.ForecastService;

import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    ForecastService forecastService();
    ForecastDatabase forecastDatabase();
}
