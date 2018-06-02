package com.example.fivedayforecast.db;

import android.arch.persistence.room.TypeConverter;

import com.example.fivedayforecast.model.Day;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DayTypeConverter {


    @TypeConverter
    public static List<Day> stringToListOfDays(String data) {
        final Gson gson= new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type type = new TypeToken<List<Day>>() {}.getType();
        return gson.fromJson(data, type);
    }

    @TypeConverter
    public static String listOfDaysToString(List<Day> days) {
        return new Gson().toJson(days);
    }
}
