package com.example.converter.sync;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.EditText;


import androidx.annotation.Nullable;



import java.util.Locale;

public class ConverterService extends Service {

    public static final String TAG =
            ConverterService.class.getSimpleName();

    private CustomBinder binder =
            new CustomBinder();


    public EditText printWeight;

    private final String[] weightTypes = {  "g",  "kg", "t", "ct", "funt", "lb"};
    private final double[] weightConversionFactors = {  0.001, 1.0, 1000.0, 0.0002, 0.409517, 16.3807};


    public static final String EXTRA_VALUE = "VALUE";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: " + hashCode());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"onStartCommand");
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    public class CustomBinder extends Binder {
        public ConverterService getService() {
            return ConverterService.this;
        }
    }

    public String conversionCalculation(String printedValue, boolean isTemperature,
                                        String conversionFactor1, String conversionFactor2, String unit){

        double conversionRate = 0.0;

        if(isTemperature){

        }
        else {
            conversionRate =  Double.parseDouble(conversionFactor1) /  Double.parseDouble(conversionFactor2);
        }

        double value = 0;
        if (!"".equals(printedValue)) {
            value = Double.parseDouble(printedValue);
        }

        double computation = value * conversionRate;
        String computation_s = String.format(Locale.getDefault(),"%.2f",computation);
        String abbr = " " + unit;

        return computation_s.concat(abbr);

    }


}
