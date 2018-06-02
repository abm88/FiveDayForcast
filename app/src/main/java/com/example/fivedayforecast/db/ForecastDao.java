package com.example.fivedayforecast.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.fivedayforecast.model.Forecast;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForecasts(Forecast... forecasts);

    @Query("SELECT * FROM forecast")
    Maybe<Forecast> getForecast();
}
