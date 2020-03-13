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

public class LengthFragment extends BaseFragment {

    private EditText printLength;
    private final String[] lengthTypes = {  "cm",  "m", "km", "in", "ft", "yd","mi"};
    private final double[] conversionFactors = {  0.01, 1.0, 1000.0, 0.0254, 0.3048, 0.9144, 1609.34};
    private TextView result;
    private int item1;
    private int item2;

    public static LengthFragment newInstance() {
        LengthFragment fragment = new LengthFragment();
                return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_length,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        printLength = view.findViewById(R.id.printValue);
        Spinner firstUnitSpinner = view.findViewById(R.id.weight_spinner1);
        Spinner secondUnitSpinner = view.findViewById(R.id.weight_spinner2);
        result = view.findViewById(R.id.yourResult);

        firstUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                                item1 = selectedItemPosition;
                                if (printLength.getText() != null){
                                    calculateLength();
                                }
                }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

       secondUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                      item2 = selectedItemPosition;
                if (printLength.getText() != null){
                    calculateLength();
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        printLength.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                calculateLength();
                }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        setupCancelButton(view);
    }


    private void calculateLength(){

        double conversionRate = conversionFactors[item1] / conversionFactors[item2];

        String temp = printLength.getText().toString();
        double value = 0;
        if (!"".equals(temp)) {
            value = Double.parseDouble(temp);
        }

        double computation = value * conversionRate;
        String computation_s = String.format(Locale.getDefault(),"%.2f",computation);
        String abbr = " " + lengthTypes[item2];
        result.setText(computation_s.concat(abbr));
    }



}
