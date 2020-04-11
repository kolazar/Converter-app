package com.example.converter.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.converter.MainActivity;
import com.example.converter.MainStateListener;
import com.example.converter.MainViewState;
import com.example.converter.R;
import com.example.converter.sync.ConverterService;
import com.example.converter.tasks.ConvertLengthWeightCallable;
import com.example.converter.tasks.ConvertTemperatureCallable;
import com.example.converter.tasks.LooperThreadTask;
import com.example.converter.tasks.Task;
import com.example.converter.tasks.TaskListener;


public class TemperatureActivity extends MainActivity implements MainStateListener {

    private TextView resultTextView;
    private EditText input;
    private Spinner firstUnitSpinner;
    private Spinner secondUnitSpinner;
    private ProgressBar progress;

    private String formula,tempConversionType;
    private String item1 ="";
    private String item2 = "";
    private boolean onInput;


    private SharedPreferences prefs;

    public static final String TAG = LengthActivity.class.getSimpleName();
    private Task<String> currentTask;
    private MainViewState mainViewState = new MainViewState();
    private MainStateListener listener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_temperature);

        input = findViewById(R.id.printValue);
        resultTextView = findViewById(R.id.yourResult);
        firstUnitSpinner = findViewById(R.id.temp_spinner1);
        secondUnitSpinner = findViewById(R.id.temp_spinner2);
        progress = findViewById(R.id.progress);

        setListener(this);

        prefs = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);

        firstUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                item1 = firstUnitSpinner.getSelectedItem().toString();
                tempConversionType = item1.concat(item2);
                formula = prefs.getString(tempConversionType,"0.0");
                onInput = false;

                if (input.getText() != null){
                    String printedTemp = input.getText().toString();
                    convertTemp(printedTemp,formula,tempConversionType,onInput);
                }
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
                onInput = false;

                if (input.getText() != null){
                    String printedTemp = input.getText().toString();
                    convertTemp(printedTemp,formula,tempConversionType,onInput);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        input.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                String printedTemp = input.getText().toString();
                onInput = true;

                if (input.getText() != null) {
                    convertTemp(printedTemp,formula,tempConversionType,onInput);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        findViewById(R.id.cancelButton).setOnClickListener(v ->{
            cancel();
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(currentTask !=null){
            currentTask.cancel();
        }
        setListener(null);
    }

    public void setListener(MainStateListener listener){
        this.listener = listener;
        if(listener != null){
            listener.onNewState(mainViewState);
        }
    }


    public void onNewState(MainViewState state) {
        input.setEnabled(state.isInputEnabled);
        firstUnitSpinner.setEnabled(state.isSpinner1Enabled);
        secondUnitSpinner.setEnabled(state.isSpinner2Enabled);
        progress.setVisibility(state.showProgress ? View.VISIBLE : View.GONE);
        resultTextView.setVisibility(state.showResultText ? View.VISIBLE : View.GONE);
        resultTextView.setText(state.result);
    }



    public void convertTemp(String printedLength,
                              String formula, String item2,boolean onInput){
        currentTask = convertTempTask(printedLength,formula,item2);

        if(!onInput) {
            mainViewState.showProgress = true;
            mainViewState.isSpinner1Enabled = false;
            mainViewState.isSpinner2Enabled = false;
            mainViewState.showResultText = false;
            mainViewState.isInputEnabled = false;
            mainViewState.result = "";
            updateState();
        }

        currentTask.execute(new TaskListener<String>() {
            @Override
            public void onSuccess(String result) {
                mainViewState.showProgress = false;
                mainViewState.isSpinner1Enabled = true;
                mainViewState.isSpinner2Enabled = true;
                mainViewState.showResultText = true;
                mainViewState.isInputEnabled = true;
                mainViewState.result = result;
                updateState();
            }

            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error",error);
                mainViewState.showProgress = false;
                mainViewState.isSpinner1Enabled = true;
                mainViewState.isSpinner2Enabled = true;
                mainViewState.showResultText = false;
                mainViewState.isInputEnabled = true;
                mainViewState.result ="";
                updateState();
            }
        });


    }

    private void updateState(){
        if(listener != null){
            listener.onNewState(mainViewState);
        }
    }

    private Task<String> convertTempTask(String printedTemp,
                                           String formula, String item2){

        return new LooperThreadTask<>(new ConvertTemperatureCallable(printedTemp,
                formula, item2));
    }


}
