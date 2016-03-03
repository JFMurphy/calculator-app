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
    /* Stores the value of a whole number.
     Will reset once an operator is added, equation is calculated or display is cleared.
      */
    private String numberValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void buttonPress(View view) {
        TypeCheck tc = new TypeCheck();
        EditText display = (EditText) findViewById(R.id.outputDisplay);
        Button button = (Button) view;

        String buttonText = button.getText().toString();

        switch(button.getId()) {

            /* Adds numbers to the calculation. */
            case R.id.buttonOne:
            case R.id.buttonTwo:
            case R.id.buttonThree:
            case R.id.buttonFour:
            case R.id.buttonFive:
            case R.id.buttonSix:
            case R.id.buttonSeven:
            case R.id.buttonEight:
            case R.id.buttonNine:
            case R.id.buttonZero:
                numberValue += buttonText;
                display.append(buttonText);
                break;

            /* Checks for a decimal point and adds one if there isn't one in the current number. */
            case R.id.buttonPoint:
                if (numberValue.contains(".")) {
                    System.out.println("Decimal point already exists.");
                } else {
                    display.append(buttonText);
                    numberValue += buttonText;
                }
                break;

            /* Performs the actions for adding brackets to the calculation. */
            case R.id.buttonBrackets:
                // TODO implement bracket adding functionality
                if (!bracketOpen) {
                    bracketOpen = true;
                    display.append("(");
                } else {
                    display.append(")");
                    bracketOpen = false;
                }
                break;

            /* Adding mathematical operators */
            case R.id.buttonMultiply:
            case R.id.buttonDivide:
            case R.id.buttonAdd:
            case R.id.buttonSubtract:
                if (canAddOperator(display, tc)) {
                    display.append(buttonText);
                    numberValue = "";
                }
                break;

            /* Actions for removing parts of the equation and clearing the display */
            case R.id.buttonClear:
                display.setText("");
                numberValue = "";
                bracketOpen = false;
                break;
            case R.id.buttonDelete:
                /**
                 * TODO
                 * Implement functionality to delete characters from the current position of the cursor.
                 */
                break;

            /* Calculating the equation. */
            case R.id.buttonEquals:
                // TODO make it calculate.
        }

    }

    private Boolean canAddOperator(EditText display, TypeCheck typeCheck) {
        // Checking the end of the display for an existing operator and return true if found.
        if (typeCheck.isOperator(display.getText().charAt(display.length()-1))) {
            System.out.println("There is already an operator here!");
            return false;
        } else {
            return true;
        }
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
