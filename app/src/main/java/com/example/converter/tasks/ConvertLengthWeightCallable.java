package com.example.converter.tasks;


import java.util.Locale;
import java.util.concurrent.Callable;

public class ConvertLengthWeightCallable implements Callable<String> {

    private String printedValue, conversionFactor1,conversionFactor2,unit;

    public ConvertLengthWeightCallable(String printedValue, String conversionFactor1, String conversionFactor2, String unit) {
        this.printedValue = printedValue;
        this.conversionFactor1 = conversionFactor1;
        this.conversionFactor2 = conversionFactor2;
        this.unit = unit;
    }

    @Override
    public String call() throws Exception {

        double conversionRate;
        String computation_s;
        String abbr;
        double computation;

        Thread.sleep(1000);

        conversionRate = Double.parseDouble(conversionFactor1) / Double.parseDouble(conversionFactor2);
        double value = 0;
        if (!"".equals(printedValue)) {
            value = Double.parseDouble(printedValue);
        }

        computation = value * conversionRate;
        computation_s = String.format(Locale.getDefault(), "%.2f", computation);
        abbr = " " + unit;


        return computation_s.concat(abbr);

    }
}
