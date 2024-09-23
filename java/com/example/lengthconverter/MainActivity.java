package com.example.lengthconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputLength;
    private Spinner inputUnit;
    private TextView outputLength;

    private static final String[] UNITS = {"Meters", "Kilometers", "Miles", "Feet"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputLength = findViewById(R.id.input_length);
        inputUnit = findViewById(R.id.input_unit);
        outputLength = findViewById(R.id.output_length);
        Button convertButton = findViewById(R.id.convert_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UNITS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputUnit.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertLength();
            }
        });
    }

    private void convertLength() {
        String inputValue = inputLength.getText().toString();
        if (inputValue.isEmpty()) {
            outputLength.setText("Please enter a value.");
            return;
        }

        double length = Double.parseDouble(inputValue);
        String selectedUnit = inputUnit.getSelectedItem().toString();
        double convertedLength;

        switch (selectedUnit) {
            case "Meters":
                convertedLength = length; // stays the same
                break;
            case "Kilometers":
                convertedLength = length / 1000; // convert to kilometers
                break;
            case "Miles":
                convertedLength = length * 0.000621371; // convert to miles
                break;
            case "Feet":
                convertedLength = length * 3.28084; // convert to feet
                break;
            default:
                convertedLength = length; // fallback
                break;
        }

        outputLength.setText(String.format("Converted Length: %.4f", convertedLength));
    }
}
