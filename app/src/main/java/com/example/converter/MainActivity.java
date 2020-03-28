package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;


import com.example.converter.activities.LengthActivity;
import com.example.converter.activities.WeightActivity;
import com.example.converter.sync.ConverterService;
import com.example.converter.sync.ConverterService.CustomBinder;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    public static final String APP_PREFERENCES = "com.example.converter.mysettings";

    private final String[] lengthTypes = {  "cm",  "m", "km", "in", "ft", "yd","mi"};
    private final double[] lengthConversionFactors = {  0.01, 1.0, 1000.0, 0.0254, 0.3048, 0.9144, 1609.34};

    private final String[] weightTypes = {  "g",  "kg", "t", "ct", "funt", "lb"};
    private final double[] weightConversionFactors = {  0.001, 1.0, 1000.0, 0.0002, 0.409517, 16.3807};

    private ConverterService service;

    public ConverterService getService() {
        return service;
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

    //TODO
//    public void onToTemperatureScreen(View view) {
////        Intent intent = new Intent(this,LengthActivity.class);
////        startActivity(intent);
////    }

    public void onToWeightScreen(View view) {
        Intent intent = new Intent(this, WeightActivity.class);
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

    private void setSharedPreferences(){
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

    private ServiceConnection serviceConnection =
            new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name,
                                               IBinder binder) {
                    service = ((CustomBinder) binder)
                            .getService();
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    service = null;
                }
            };

    public ServiceConnection getServiceConnection() {
        return serviceConnection;
    }
}


