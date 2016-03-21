package calculator.johnmurphy.com.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import calculator.johnmurphy.com.calculator.R;
import calculator.johnmurphy.com.implementation.Calculator;
import calculator.johnmurphy.com.implementation.TypeCheck;


// TODO
public class CalculatorActivity extends Activity implements View.OnClickListener {

    private boolean bracketOpen = false;
    private boolean buttonIsPressed = false;
    private String currentNumber = "";
    private ArrayList<String> calculationArray = new ArrayList<>();

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPoint, btnBrackets,
            btnAdd, btnSubtract, btnMultiply, btnDivide, btnEquals, btnClear;
    ImageButton btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Instantiating buttons and adding onClickListeners
        btn0 = (Button) findViewById(R.id.buttonZero);
        btn0.setOnClickListener(this);
        btn1 = (Button) findViewById(R.id.buttonOne);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.buttonTwo);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.buttonThree);
        btn3.setOnClickListener(this);
        btn4 = (Button) findViewById(R.id.buttonFour);
        btn4.setOnClickListener(this);
        btn5 = (Button) findViewById(R.id.buttonFive);
        btn5.setOnClickListener(this);
        btn6 = (Button) findViewById(R.id.buttonSix);
        btn6.setOnClickListener(this);
        btn7 = (Button) findViewById(R.id.buttonSeven);
        btn7.setOnClickListener(this);
        btn8 = (Button) findViewById(R.id.buttonEight);
        btn8.setOnClickListener(this);
        btn9 = (Button) findViewById(R.id.buttonNine);
        btn9.setOnClickListener(this);
        btnPoint = (Button) findViewById(R.id.buttonPoint);
        btnPoint.setOnClickListener(this);
        btnBrackets = (Button) findViewById(R.id.buttonBrackets);
        btnBrackets.setOnClickListener(this);
        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(this);
        btnSubtract = (Button) findViewById(R.id.buttonSubtract);
        btnSubtract.setOnClickListener(this);
        btnMultiply = (Button) findViewById(R.id.buttonMultiply);
        btnMultiply.setOnClickListener(this);
        btnDivide = (Button) findViewById(R.id.buttonDivide);
        btnDivide.setOnClickListener(this);
        btnEquals = (Button) findViewById(R.id.buttonEquals);
        btnEquals.setOnClickListener(this);
        btnClear = (Button) findViewById(R.id.buttonClear);
        btnClear.setOnClickListener(this);
        btnDelete = (ImageButton) findViewById(R.id.buttonDelete);
        btnDelete.setOnClickListener(this);
        /*btnDelete.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        System.out.println("Button is released!");
                        btnDelete.setPressed(false);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        System.out.println("Button is pressed!");
                        EditText display = (EditText) findViewById(R.id.outputDisplay);
                        System.out.println(display.getText());
                        System.out.println(display.getSelectionStart());
                        btnDelete.setPressed(true);

                        while (btnDelete.isPressed() && display.getText().length() > 0) {
                            display.getText().delete(display.getSelectionStart()-1, display.getSelectionStart());

                        }
                        break;
                }
                return true;
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        EditText display = (EditText) findViewById(R.id.outputDisplay);
        TypeCheck tc = new TypeCheck();

        switch (v.getId()) {
            /* Adds numbers to the display */
            case R.id.buttonZero:
            case R.id.buttonOne:
            case R.id.buttonTwo:
            case R.id.buttonThree:
            case R.id.buttonFour:
            case R.id.buttonFive:
            case R.id.buttonSix:
            case R.id.buttonSeven:
            case R.id.buttonEight:
            case R.id.buttonNine:
                String buttonText = ((Button) v).getText().toString();
                // Insert digit at current cursor position.
                display.getText().insert(display.getSelectionStart(), buttonText);
                // TODO: 11/03/2016 currentNumber variable is now obsolete using this method of insertion. Rethink this.
                currentNumber += buttonText;
                break;

            /* Adds decimal point */
            case R.id.buttonPoint:
                // TODO Add decimal point to number only
                display.getText().insert(display.getSelectionStart(), ((Button) v).getText());
                break;

            /* Adds brackets to current equation. */
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

            /* Adds mathematical operators */
            case R.id.buttonAdd:
            case R.id.buttonSubtract:
            case R.id.buttonMultiply:
            case R.id.buttonDivide:
                if (canAddOperator(display, tc)) {
                    display.append(((Button) v).getText());
                    currentNumber = "";
                }
                break;

            /* Deletes the character at the current cursor position */
            case R.id.buttonDelete:
                // If display is not empty
                if (display.length() != 0) {
                    int cursorPosition = display.getSelectionStart();
                    //If multiple characters are selected.
                    if (display.getSelectionEnd() > display.getSelectionStart()) {
                        display.getText().replace(cursorPosition, display.getSelectionEnd(), "");
                        display.setSelection(cursorPosition);
                    } // There is no selection only a cursor
                    else {
                        // TODO: 11/03/2016 Keep deleting when button is held down.
                        display.getText().delete(cursorPosition-1, cursorPosition);
                    }
                }
                break;

            /* Clears the equation from the display */
            case R.id.buttonClear:
                display.getText().clear();
                currentNumber = "";
                bracketOpen = false;
                break;

            /* Calculates the equation */
            case R.id.buttonEquals:
                // TODO Calculate equation
                break;
        }


    }

    private Boolean canAddOperator(EditText display, TypeCheck typeCheck) {
        // Checking the end of the display for an existing operator and return true if found.
        if (typeCheck.isOperator(display.getText().charAt(display.length() - 1))) {
            System.out.println("There is already an operator here!");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Calculates the equation currently in the display.
     *
     * @param view
     */
    public void sendEquation(View view) {
        TypeCheck tc = new TypeCheck();
        Calculator calc = new Calculator();
        EditText display = (EditText) findViewById(R.id.outputDisplay);
        String displayText = display.getText().toString();

        // Checks if there are any trailing operators and excludes them.
        if (tc.isOperator(displayText.charAt(displayText.length() - 1))) {
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
