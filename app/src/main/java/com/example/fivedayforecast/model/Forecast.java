package com.example.fivedayforecast.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.example.fivedayforecast.db.DayTypeConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity
public class Forecast {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @SerializedName("cod")
    @Expose
    public String cod;

    @SerializedName("message")
    @Expose
    public double message;

    @SerializedName("cnt")
    @Expose
    public int cnt;

    @TypeConverters(DayTypeConverter.class)
    @SerializedName("list")
    @Expose
    public java.util.List<Day> days = null;

    @Embedded
    @SerializedName("city")
    @Expose
    public City city;

    public Forecast() {
    }
}
