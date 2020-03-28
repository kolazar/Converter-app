package com.example.converter.sync;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import org.mariuszgromada.math.mxparser.*;



import androidx.annotation.Nullable;



import java.util.Locale;

public class ConverterService extends Service {

    public static final String TAG =
            ConverterService.class.getSimpleName();

    private CustomBinder binder =
            new CustomBinder();


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: " + hashCode());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
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

    public String weightLengthCalculation(String printedValue,
                                          String conversionFactor1, String conversionFactor2, String unit) {

        double conversionRate;
        String computation_s;
        String abbr;
        double computation;


        conversionRate = Double.parseDouble(conversionFactor1) / Double.parseDouble(conversionFactor2);
        double value = 0;
        if (!"".equals(printedValue)) {
            value = Double.parseDouble(printedValue);
        }

        computation = value * conversionRate;
        computation_s = String.format(Locale.getDefault(), "%.2f", computation);
        abbr = " " + unit;


        return computation_s.concat(abbr);

    }

    public String tempCalculation(String printedValue,
                                  String formula, String unit) {

        String computation_s = "";
        String abbr = "";
        String replaceString;
        Expression expression = new Expression();

        if (!printedValue.isEmpty()) {
            switch (unit) {
                case "CF":
                    abbr = " F";
                    replaceString = formula.replace("C", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s =  String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;
                case "CK":
                    abbr = " K";
                    replaceString = formula.replace("C", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s =  String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;
                case "FC":
                    abbr = " C";
                    replaceString = formula.replace("F", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s =  String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;
                case "FK":
                    abbr = " K";
                    replaceString = formula.replace("F", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s =  String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;
                case "KC":
                    abbr = " C";
                    replaceString = formula.replace("C", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s =  String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;
                case "KF":
                    abbr = " F";
                    replaceString = formula.replace("K", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s = String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;

                case "CC":
                    abbr = " C";
                    computation_s = printedValue;
                    break;
                case "KK":
                    abbr = " K";
                    computation_s = printedValue;
                    break;
                case "FF":
                    abbr = " F";
                    computation_s = printedValue;
                    break;
                default:
                    abbr = "";
                    break;
            }
            return computation_s.concat(abbr);

        } else {
            return computation_s.concat(abbr);
        }
    }

}
