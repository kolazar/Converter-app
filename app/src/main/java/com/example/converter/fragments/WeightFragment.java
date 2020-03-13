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

public class WeightFragment extends BaseFragment {

    private EditText printWeight;
    private final String[] weightTypes = {  "g",  "kg", "t", "ct", "funt", "lb"};
    private final double[] conversionFactors = {  0.001, 1.0, 1000.0, 0.0002, 0.409517, 16.3807};
    private TextView result;
    private int item1;
    private int item2;

    public static WeightFragment newInstance() {
        WeightFragment fragment = new WeightFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        printWeight = view.findViewById(R.id.printValue);
        Spinner firstUnitSpinner = view.findViewById(R.id.weight_spinner1);
        Spinner secondUnitSpinner = view.findViewById(R.id.weight_spinner2);
        result = view.findViewById(R.id.yourResult);

        firstUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                item1 = selectedItemPosition;
                if (printWeight.getText() != null){
                    calculateWeight();
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        secondUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                item2 = selectedItemPosition;
                if (printWeight.getText() != null){
                    calculateWeight();
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        printWeight.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                calculateWeight();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        setupCancelButton(view);
    }


    private void calculateWeight(){

        double conversionRate = conversionFactors[item1] / conversionFactors[item2];

        String temp = printWeight.getText().toString();
        double value = 0;
        if (!"".equals(temp)) {
            value = Double.parseDouble(temp);
        }

        double computation = value * conversionRate;
        String computation_s = String.format(Locale.getDefault(),"%.2f",computation);
        String abbr = " " + weightTypes[item2];
        result.setText(computation_s.concat(abbr));
    }



}
