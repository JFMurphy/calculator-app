package calculator.johnmurphy.com.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void addNumber(View view) {
        TextView textView = (TextView) findViewById(R.id.outputDisplay);
        Button button = (Button) view;

        textView.append(((Button) view).getText());
    }
}
