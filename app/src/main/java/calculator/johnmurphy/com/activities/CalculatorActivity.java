package calculator.johnmurphy.com.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import calculator.johnmurphy.com.calculator.R;
import calculator.johnmurphy.com.implementation.Calculator;
import calculator.johnmurphy.com.implementation.TypeCheck;

public class CalculatorActivity extends AppCompatActivity {

    private boolean bracketOpen = false;
    private String currentNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void buttonPress(View view) {
        TypeCheck tc = new TypeCheck();
        EditText display = (EditText) findViewById(R.id.outputDisplay);
        Button button;
        ImageButton imgButton;
        String buttonText;

        /* Check if this view is an ImageButton and cast it.
           If not cast as Button.
         */
        if (view instanceof ImageButton) {
            imgButton = (ImageButton) view;

            switch (imgButton.getId()) {
                case R.id.buttonDelete:
                    /**
                     * TODO
                     * Implement functionality to delete characters from the current position of the cursor.
                     */
                    String currentDisplayContents = display.getText().toString();
                    String newDisplayContents = "";

                    if (display.length() != 0) {
                        //If multiple characters are selected.
                        if (display.getSelectionEnd() > display.getSelectionStart() ) {
                            newDisplayContents = currentDisplayContents.substring(0, display.getSelectionStart());
                            newDisplayContents += currentDisplayContents.substring(display.getSelectionEnd());

                            display.setText(newDisplayContents);
                        } else {
                            // There is no selection but there is a cursor.
                            int cursorPosition = display.getSelectionStart();
                            newDisplayContents = currentDisplayContents.substring(0, cursorPosition)
                                    + currentDisplayContents.substring(cursorPosition);

                            display.setText(newDisplayContents);
                        }
                    }
                    break;
            }
        }
        else {
            button = (Button) view;
            buttonText = button.getText().toString();

            switch(button.getId()) {

            /* Adds numbers to the calculation. */
                // TODO Improve number adding method so numbers can be added at any position.
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
                    currentNumber += buttonText;
                    display.append(buttonText);
                    break;

            /* Checks for a decimal point and adds one if there isn't one in the current number. */
                case R.id.buttonPoint:
                    if (currentNumber.contains(".")) {
                        System.out.println("Decimal point already exists.");
                    } else {
                        display.append(buttonText);
                        currentNumber += buttonText;
                    }
                    break;

            /* Performs the actions for adding brackets to the calculation. */
                case R.id.buttonBrackets:
                    // TODO improve bracket adding functionality
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
                        currentNumber = "";
                    }
                    break;

            /* Actions for clearing the display and deleting characters */
                case R.id.buttonClear:
                    display.setText("");
                    currentNumber = "";
                    bracketOpen = false;
                    break;

            /* Calculating the equation. */
                case R.id.buttonEquals:
                    // TODO make it calculate.
            }
        }
        //Button button = (Button) view;

        //String buttonText = button.getText().toString();



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
