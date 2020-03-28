package com.example.converter.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.converter.MainActivity;
import com.example.converter.R;
import com.example.converter.sync.ConverterService;


public class WeightActivity extends MainActivity {

    private TextView result;
    private String item1;
    private String conversionRate1;
    private String item2;
    private String conversionRate2;
    private EditText printWeight;


    private SharedPreferences prefs;

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(this,ConverterService.class);
        bindService(intent,getServiceConnection(), Context.BIND_AUTO_CREATE);

    }

    @Override
    public void onStop() {
        super.onStop();
        unbindService(getServiceConnection());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_weight);

        printWeight = findViewById(R.id.printValue);

        Spinner firstUnitSpinner = findViewById(R.id.weight_spinner1);
        Spinner secondUnitSpinner = findViewById(R.id.weight_spinner2);

        result = findViewById(R.id.yourResult);
        prefs = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);


        firstUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                item1 = firstUnitSpinner.getSelectedItem().toString();
                conversionRate1 = prefs.getString(item1,"0.0");

                if (printWeight.getText() != null){
                    if (getService() == null) return;
                    String printedLength = printWeight.getText().toString();
                    String resultValue = getService().conversionCalculation(printedLength,
                            false,conversionRate1,conversionRate2,item2);
                    result.setText(resultValue);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        secondUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {


                item2 = secondUnitSpinner.getSelectedItem().toString();
                conversionRate2 = prefs.getString(item2,"0.0");
                if (printWeight.getText() != null){
                    if (getService() == null) return;
                    String printedLength = printWeight.getText().toString();
                    String resultValue = getService().conversionCalculation(printedLength,
                            false,conversionRate1,conversionRate2,item2);
                    result.setText(resultValue);

                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        printWeight.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String printedLength = printWeight.getText().toString();
                String resultValue = getService().conversionCalculation(printedLength,
                        false,conversionRate1,conversionRate2,item2);
                result.setText(resultValue);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        findViewById(R.id.cancelButton).setOnClickListener(v ->{
            cancel();
        });

    }



}
