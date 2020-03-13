package com.example.converter.fragments;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.converter.R;


import java.util.Locale;

public class TemperatureFragment extends BaseFragment {

    private EditText printTemp;
    private TextView result;
    private String item1 = "";
    private String item2 = "";

    public static TemperatureFragment newInstance() {
        TemperatureFragment fragment = new TemperatureFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        printTemp = view.findViewById(R.id.printValue);
        Spinner firstUnitSpinner = view.findViewById(R.id.temp_spinner1);
        Spinner secondUnitSpinner = view.findViewById(R.id.temp_spinner2);
        result = view.findViewById(R.id.yourResult);

        firstUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                item1 = firstUnitSpinner.getSelectedItem().toString();
                if (printTemp.getText() != null) {
                   convertTemps();
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        secondUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                item2 = secondUnitSpinner.getSelectedItem().toString();;
                if (printTemp.getText() != null) {
                    convertTemps();
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        printTemp.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                convertTemps();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        setupCancelButton(view);
    }


    private void convertTemps() {

        double computation;
        String abbr = " ";
        String computation_s = " ";
        String enteredVal = printTemp.getText().toString();

        if(!enteredVal.isEmpty()) {


            switch (item1) {
                case "C":
                    if (item2.equals("F")) {
                        computation = (Double.parseDouble(enteredVal) * (9.0 / 5.0)) + 32;
                        abbr = " F";
                        computation_s = String.format(Locale.getDefault(), "%.2f", computation);
                    } else if (item2.equals("K")) {
                        computation = Double.parseDouble(enteredVal) + 273.15;
                        abbr = " K";
                        computation_s = String.format(Locale.getDefault(), "%.2f", computation);
                    } else {
                        abbr = " C";
                        computation_s = enteredVal;
                    }
                    break;
                case "F":
                    if (item2.equals("C")) {
                        computation = (Double.parseDouble(enteredVal) - 32) * (5.0 / 9.0);
                        abbr = " C";
                        computation_s = String.format(Locale.getDefault(), "%.2f", computation);
                    } else if (item2.equals("K")) {
                        computation = (Double.parseDouble(enteredVal) - 32) * (5.0 / 9.0) + 273.15;
                        abbr = " K";
                        computation_s = String.format(Locale.getDefault(), "%.2f", computation);
                    } else {
                        abbr = " F";
                        computation_s = enteredVal;
                    }
                    break;
                case "K":
                    if (item2.equals("C")) {
                        computation = (Double.parseDouble(enteredVal) - 273.15);
                        abbr = " C";
                        computation_s = String.format(Locale.getDefault(), "%.2f", computation);
                    } else if (item2.equals("F")) {
                        computation = (Double.parseDouble(enteredVal) - 273.15) * (9.0 / 5.0) + 32;
                        abbr = " F";
                        computation_s = String.format(Locale.getDefault(), "%.2f", computation);
                    } else {
                        abbr = " K";
                        computation_s = enteredVal;
                    }
                    break;

            }
        }

        result.setText(computation_s.concat(abbr));
    }
}