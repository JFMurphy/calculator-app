package calculator.johnmurphy.com.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import calculator.johnmurphy.com.calculator.R;
import calculator.johnmurphy.com.implementation.Calculator;
import calculator.johnmurphy.com.implementation.TypeCheck;

public class CalculatorActivity extends AppCompatActivity {

    private boolean bracketOpen = false;
    private String numberValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void addElement(View view) {
        TypeCheck tc = new TypeCheck();
        EditText display = (EditText) findViewById(R.id.outputDisplay);
        Button button = (Button) view;

        String buttonText = button.getText().toString();

        /**
         * If numberValue is empty start new number.
         * Add current number to display.
         * If button is a point '.' check if there is already a point in the current number, if not
         * add one.
         * If button is an operator check if there is an operator before it,
         * if not add the operator.
         */
        if (Character.isDigit(buttonText.charAt(0))) {
            numberValue += buttonText;
            display.append(button.getText());
        } else if (buttonText.charAt(0) == '.') {
            if (numberValue.contains("."))
                System.out.println("Error!");
            else {
                display.append(buttonText);
                numberValue += buttonText;
            }
        } else if (tc.isOperator(buttonText.charAt(0)) && display.length() > 0) {
            if (tc.isOperator(display.getText().charAt(display.length()-1)))
                System.out.println("There's an operator here!");
            else {
                display.append(buttonText);
                numberValue = "";
            }
        }
    }

    public void clearDisplay(View view) {
        EditText display = (EditText) findViewById(R.id.outputDisplay);

        display.setText("");
        bracketOpen = false;
    }

    public void addBrackets(View view) {
        EditText display = (EditText) findViewById(R.id.outputDisplay);

        if (!bracketOpen) {
            bracketOpen = true;
            display.append("(");
        } else {
            bracketOpen = false;
            display.append(")");
        }
    }

    public void deleteElement(View view) {
        String temp;
        int displayTextLen;
        EditText display = (EditText) findViewById(R.id.outputDisplay);
        displayTextLen = display.getText().length();

        if (displayTextLen > 0) {
            temp = display.getText().toString();
            display.setText(temp.substring(0, displayTextLen - 1));
        }
    }

    /**
     * Calculates the equation currently in the display.
     * @param view
     */
    public void sendEquation(View view) {
        TypeCheck tc = new TypeCheck();
        Calculator calc = new Calculator();
        EditText display = (EditText) findViewById(R.id.outputDisplay);
        String displayText = display.getText().toString();

        // Checks if there are any trailing operators and excludes them.
        if (tc.isOperator(displayText.charAt(displayText.length()-1))) {
            display.setText(displayText.substring(0, displayText.length() - 1));
            displayText = display.getText().toString();
        }

        double result = calc.calculate(displayText);
        // Return a whole number if the double has no decimal places.
        if (result % 1 == 0) {
            int iResult = (int) result;
            display.setText(Integer.toString(iResult));
        }
        // Return the double.
        else {
            String strResult = Double.toString(result);
            display.setText(strResult);
        }
    }
}
