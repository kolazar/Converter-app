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


public class TemperatureActivity extends MainActivity {

    private TextView result;
    private String item1 ="";
    private String item2 = "";
    private EditText printTemp;
    private String tempConversionType;
    private String formula;


    private SharedPreferences prefs;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_temperature);

        printTemp = findViewById(R.id.printValue);

        Spinner firstUnitSpinner = findViewById(R.id.temp_spinner1);
        Spinner secondUnitSpinner = findViewById(R.id.temp_spinner2);

        result = findViewById(R.id.yourResult);
        prefs = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);


        firstUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                item1 = firstUnitSpinner.getSelectedItem().toString();
                tempConversionType = item1.concat(item2);
                formula = prefs.getString(tempConversionType,"0.0");


//                if (printTemp.getText() != null){
//                    if (getService() == null) return;
//                    String printedTemp = printTemp.getText().toString();
//                    String resultValue = getService().tempCalculation(printedTemp,
//                            formula,tempConversionType);
//                    result.setText(resultValue);
//                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        secondUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {


                item2 = secondUnitSpinner.getSelectedItem().toString();
                tempConversionType = item1.concat(item2);
                formula = prefs.getString(tempConversionType,"0.0");

//                if (printTemp.getText() != null){
//                    if (getService() == null) return;
//                    String printedLength = printTemp.getText().toString();
//                    String resultValue = getService().tempCalculation(printedLength,
//                            formula,tempConversionType);
//                    result.setText(resultValue);
//
//                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        printTemp.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

//                String printedLength = printTemp.getText().toString();
//                if (printTemp.getText() != null) {
//                    if (getService() == null) return;
//                    String resultValue = getService().tempCalculation(printedLength,
//                            formula, tempConversionType);
//                    result.setText(resultValue);
//                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        findViewById(R.id.cancelButton).setOnClickListener(v ->{
            cancel();
        });

    }



}
