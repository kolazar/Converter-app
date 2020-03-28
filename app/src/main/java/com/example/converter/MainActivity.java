package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


import com.example.converter.activities.LengthActivity;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    public static final String APP_PREFERENCES = "com.example.converter.mysettings";
    private final String[] lengthTypes = {  "cm",  "m", "km", "in", "ft", "yd","mi"};
    private final double[] lengthConversionFactors = {  0.01, 1.0, 1000.0, 0.0254, 0.3048, 0.9144, 1609.34};
    private final String[] weightTypes = {  "g",  "kg", "t", "ct", "funt", "lb"};
    private final double[] weightConversionFactors = {  0.001, 1.0, 1000.0, 0.0002, 0.409517, 16.3807};


    public SharedPreferences getPrefs() {
        return prefs;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu);
        setSharedPreferences();
    }

    public void onToLengthScreen(View view) {
        Intent intent = new Intent(this, LengthActivity.class);
        startActivity(intent);
    }

    public void onToTemperatureScreen(View view) {
        Intent intent = new Intent(this,LengthActivity.class);
        startActivity(intent);
    }

    public void onToWeightScreen(View view) {
        Intent intent = new Intent(this,LengthActivity.class);
        startActivity(intent);
    }

    public void onQuit(View view) {
       cancel();
    }

    @Override
    public void onBackPressed() {
        cancel();
    }

    public void cancel() {
        int count = getSupportFragmentManager()
                .getBackStackEntryCount();
        if (count <= 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void setSharedPreferences(){
        prefs = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        for (int i=0;i<lengthTypes.length;i++){
            editor.putString(lengthTypes[i],String.valueOf(lengthConversionFactors[i]));
        }

        for (int i=0;i<weightTypes.length;i++){
            editor.putString(weightTypes[i],String.valueOf(weightConversionFactors[i]));
        }

        editor.apply();

    }
}


