package com.example.converter.fragments;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.converter.R;
import com.example.converter.contract.AppContract;

public class BaseFragment extends Fragment {
    private AppContract appContract;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.appContract = (AppContract) context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.appContract = null;
        }

    void setupCancelButton(View view) {
        view.findViewById(R.id.cancelButton)
                .setOnClickListener(v -> {
                    getAppContract().cancel();
                });
    }


    final AppContract getAppContract() {
        return appContract;
    }

}
