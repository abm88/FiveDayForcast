package com.example.fivedayforecast.home.mvp;

import android.support.annotation.NonNull;

import com.example.fivedayforecast.model.Day;

import java.util.List;


public interface HomeContract {

    interface View {
        void showForecast(@NonNull List<Day> days);
        void showError(Throwable throwable);
        void showProgress(boolean shouldShow);
        void showTryAgain(boolean shouldShow);
        void setActivityTitle(String title);
    }

    interface Presenter {
        void getForecast(boolean isOnline);
        void start();
        void stop();
    }
}
