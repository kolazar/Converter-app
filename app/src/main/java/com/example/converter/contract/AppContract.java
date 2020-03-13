package com.example.converter.contract;



import androidx.fragment.app.Fragment;


public interface AppContract {
    /**
     * Launch length screen
     * @param target fragment that launches length screen
     */
    void toLengthScreen(Fragment target);

    /**
     * Launch temperature screen
     * @param target fragment that launches temperature screen
     */
    void toTemperatureScreen(Fragment target);

    /**
     * Launch weight screen
     * @param target fragment that launches weight screen
     */
    void toWeightScreen(Fragment target);

    /**
     * Exit from the current screen
     */
    void cancel();




}

