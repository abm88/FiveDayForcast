package com.example.fivedayforecast.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.example.fivedayforecast.db.WeatherTypeConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Day {
    @SerializedName("dt")
    @Expose
    public long dateAndTime;

    @SerializedName("main")
    @Expose
    public Main main;

    @TypeConverters(WeatherTypeConverter.class)
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;

    @Embedded
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;

    @Embedded
    @SerializedName("wind")
    @Expose
    public Wind wind;

    public Day() {
    }
}
