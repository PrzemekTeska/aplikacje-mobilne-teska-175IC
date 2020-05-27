package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] arraySpinner = new String[]{
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    private double firstDigit;
    private double secondDigit;

    private String result;

    private TextView resultTextView;
    private Button addButton;
    private Button substractButton;
    private Button multiplyButton;
    private Button divideButton;
    private Spinner firstDigitSpinner;
    private Spinner secondDigitSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        addButton = findViewById(R.id.addButton);
        substractButton = findViewById(R.id.substractButton);
        multiplyButton = findViewById(R.id.multiplyButton);
        divideButton = findViewById(R.id.divideButton);

        firstDigitSpinner = findViewById(R.id.firstDigitSpinner);
        secondDigitSpinner = findViewById(R.id.secondDigitSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        firstDigitSpinner.setAdapter(adapter);
        secondDigitSpinner.setAdapter(adapter);

        addButton.setOnClickListener(this);
        substractButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addButton:
                firstDigit = Double.parseDouble(firstDigitSpinner.getSelectedItem().toString());
                secondDigit = Double.parseDouble(secondDigitSpinner.getSelectedItem().toString());

                result = String.valueOf(firstDigit + secondDigit);

                resultTextView.setText(result);
                break;
            case R.id.substractButton:
                firstDigit = Double.parseDouble(firstDigitSpinner.getSelectedItem().toString());
                secondDigit = Double.parseDouble(secondDigitSpinner.getSelectedItem().toString());

                result = String.valueOf(firstDigit - secondDigit);

                resultTextView.setText(result);
                break;
            case R.id.divideButton:
                firstDigit = Double.parseDouble(firstDigitSpinner.getSelectedItem().toString());
                secondDigit = Double.parseDouble(secondDigitSpinner.getSelectedItem().toString());

                if (secondDigit == 0) result = "DON'T DO IT";
                else result = String.valueOf(firstDigit / secondDigit);
                resultTextView.setText(result);
                break;
            case R.id.multiplyButton:
                firstDigit = Double.parseDouble(firstDigitSpinner.getSelectedItem().toString());
                secondDigit = Double.parseDouble(secondDigitSpinner.getSelectedItem().toString());

                result = String.valueOf(firstDigit * secondDigit);

                resultTextView.setText(result);
                break;
        }
    }
}
