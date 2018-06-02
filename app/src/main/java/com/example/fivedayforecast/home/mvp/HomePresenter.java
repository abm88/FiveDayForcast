package com.example.fivedayforecast.home.mvp;

import android.util.Log;

import com.example.fivedayforecast.Constants;
import com.example.fivedayforecast.db.ForecastDatabase;
import com.example.fivedayforecast.model.Day;
import com.example.fivedayforecast.model.Forecast;
import com.example.fivedayforecast.net.ForecastService;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter{
    private final ForecastService forecastService;
    private final CompositeDisposable compositeDisposable;
    private final HomeContract.View view;
    private final ForecastDatabase forecastDatabase;

    public HomePresenter(ForecastService forecastService, ForecastDatabase forecastDatabase,
                         HomeContract.View view) {
        this.forecastService = forecastService;
        this.forecastDatabase = forecastDatabase;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void getForecast(boolean isOnline) {
        Observable<Forecast> observable = isOnline ? getForecastFromAPI() : getForecastFromDb();
        compositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(forecast -> view.setActivityTitle(forecast.city.name))
                .map(forecast -> forecast.days)
                .doOnSubscribe(disposable -> view.showProgress(true))
                .doOnTerminate(() -> view.showProgress(false))
                .subscribe(this::handleResult, view::showError));
        }

    private Observable<Forecast> getForecastFromDb() {
        return forecastDatabase.forecastDao()
                .getForecast()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::handleEmptyDb) //Maybe completes without emitting anything when the table is empty
                .toObservable();
    }

    private Observable<Forecast> getForecastFromAPI() {
        return forecastService.getFiveDayForecast(Constants.CITY_ID, Constants.API_KEY)
                .doOnNext(this::addToDb);
    }

    @Override
    public void start() { }

    @Override
    public void stop() {
        compositeDisposable.clear();
    }

    private void handleEmptyDb() {
        handleResult(Collections.emptyList());
    }

    private void handleResult(List<Day> days) {
        if (days.isEmpty()) {
            view.showTryAgain(true);
        } else {
            view.showTryAgain(false);
            view.showForecast(days);
        }
    }

    private void addToDb(Forecast forecast) {
        forecastDatabase.forecastDao()
                .insertForecasts(forecast);
    }
}
