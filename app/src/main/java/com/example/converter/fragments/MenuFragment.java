package com.example.converter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.converter.R;

public class MenuFragment extends BaseFragment {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
            }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_menu,
                container,
                false
        );
    }
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.convertLengthButton)
                .setOnClickListener(v -> {
                    getAppContract().toLengthScreen(this);
                });


        view.findViewById(R.id.convertWeightButton)
                .setOnClickListener(v -> {
                    getAppContract().toWeightScreen(this);
                });

        view.findViewById(R.id.convertTempButton)
                .setOnClickListener(v -> {
                    getAppContract().toTemperatureScreen(this);
                });



        view.findViewById(R.id.quitButton)
                .setOnClickListener(v -> {
                    getAppContract().cancel();
                });


    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
