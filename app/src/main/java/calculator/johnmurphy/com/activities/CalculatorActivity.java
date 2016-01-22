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
    private boolean newNumber = false;
    private String numberValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void addElement(View view) {
        TypeCheck tc = new TypeCheck();
        EditText input = (EditText) findViewById(R.id.outputDisplay);
        Button button = (Button) view;

        String buttonText = button.getText().toString();

        if (Character.isDigit(buttonText.charAt(0))) {
            if (numberValue.isEmpty())
                newNumber = true;
            numberValue += buttonText;
        }

        if (tc.isOperator(buttonText.charAt(0))) {

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

    public void sendEquation(View view) {
        // TODO Send display text to be solved.
    }
}
