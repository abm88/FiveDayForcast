package com.example.fivedayforecast;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Observable;

public class Utils {
    private static final double KELVIN_CONSTANT =   273.15;

    public static String getCelsiusFromKelvin(double value) {
        double tempInCelsius = value - KELVIN_CONSTANT;
        return String.valueOf(roundDoubleToTwoDecimalPoints(tempInCelsius)) + Constants.CELSIUS_EXTENSION;
    }

    public static String getDateForLocaleFromUtc(long value) {
        DateTime dateTime = new DateTime(value*1000L, DateTimeZone.getDefault()); //Converting to milliseconds
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("d MMM E h:m a");
        return dateTimeFormatter.print(dateTime);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static String roundDoubleToTwoDecimalPoints(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        return decimalFormat.format(value);
    }
}
