package com.example.converter.tasks;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.Locale;
import java.util.concurrent.Callable;

public class ConvertTemperatureCallable implements Callable<String> {

    private String printedValue, formula,unit;

    public ConvertTemperatureCallable(String printedValue, String formula, String unit) {
        this.printedValue = printedValue;
        this.formula  = formula;
        this.unit = unit;
    }

    @Override
    public String call() throws InterruptedException {

        String computation_s = "";
        String abbr = "";
        String replaceString;
        Expression expression = new Expression();

        Thread.sleep(1000);

        if (!printedValue.isEmpty()) {
            switch (unit) {
                case "CF":
                    abbr = " F";
                    replaceString = formula.replace("C", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s =  String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;
                case "CK":
                    abbr = " K";
                    replaceString = formula.replace("C", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s =  String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;
                case "FC":
                    abbr = " C";
                    replaceString = formula.replace("F", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s =  String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;
                case "FK":
                    abbr = " K";
                    replaceString = formula.replace("F", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s =  String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;
                case "KC":
                    abbr = " C";
                    replaceString = formula.replace("C", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s =  String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;
                case "KF":
                    abbr = " F";
                    replaceString = formula.replace("K", printedValue);
                    expression.setExpressionString(replaceString);
                    computation_s = String.format(Locale.getDefault(), "%.2f", expression.calculate());
                    break;

                case "CC":
                    abbr = " C";
                    computation_s = printedValue;
                    break;
                case "KK":
                    abbr = " K";
                    computation_s = printedValue;
                    break;
                case "FF":
                    abbr = " F";
                    computation_s = printedValue;
                    break;
                default:
                    abbr = "";
                    break;
            }
            return computation_s.concat(abbr);

        } else {
            return computation_s.concat(abbr);
        }

    }
}