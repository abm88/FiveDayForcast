package com.example.fivedayforecast.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.fivedayforecast.model.Forecast;

@Database(entities = {Forecast.class}, version = 1)
public abstract class ForecastDatabase extends RoomDatabase{
    public abstract ForecastDao forecastDao();
}
