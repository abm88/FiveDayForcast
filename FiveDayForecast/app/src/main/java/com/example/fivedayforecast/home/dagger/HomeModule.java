package com.example.fivedayforecast.home.dagger;

import com.example.fivedayforecast.db.ForecastDatabase;
import com.example.fivedayforecast.home.mvp.HomeContract;
import com.example.fivedayforecast.home.mvp.HomePresenter;
import com.example.fivedayforecast.net.ForecastService;

import dagger.Module;
import dagger.Provides;
@Module
public class HomeModule {

    private final HomeContract.View homeView;

    public HomeModule(HomeContract.View homeView) {
        this.homeView = homeView;
    }

    @Provides
    @HomeScope
    public HomeContract.Presenter provideHomePresenter(ForecastService forecastService,
                                                       ForecastDatabase forecastDatabase) {
        return new HomePresenter(forecastService, forecastDatabase, homeView);
    }
}
