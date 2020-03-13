package com.example.converter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;


import com.example.converter.contract.AppContract;

import com.example.converter.fragments.LengthFragment;
import com.example.converter.fragments.MenuFragment;
import com.example.converter.fragments.TemperatureFragment;
import com.example.converter.fragments.WeightFragment;


import java.util.UUID;

public class MainActivity extends AppCompatActivity implements AppContract {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            launchFragment(null, new MenuFragment());
        }
    }

    @Override
    public void onBackPressed() {
        cancel();
    }

    private void launchFragment(@Nullable Fragment target,
                Fragment fragment) {
            if (target != null) {
                fragment.setTargetFragment(target, 0);
            }
            String tag = UUID.randomUUID().toString();
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, fragment, tag)
                    .commit();
    }

    @Override
    public void toLengthScreen(Fragment target) {
        launchFragment(target,
                LengthFragment.newInstance());
    }

    @Override
    public void toTemperatureScreen(Fragment target) {
        launchFragment(target,
                TemperatureFragment.newInstance());
    }

    @Override
    public void toWeightScreen(Fragment target) {
        launchFragment(target,
                WeightFragment.newInstance());
    }


        @Override
    public void cancel() {
        int count = getSupportFragmentManager()
                .getBackStackEntryCount();
        if (count <= 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

}
