package com.example.validation;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextEmail, editTextPhone, editTextDOB, editTextPassword, editTextConfirmPassword;
    RadioGroup radioGroupGender;
    Spinner spinnerState;
    Button buttonSubmit;

    String[] states = {"Select State", "Andra pradesh", "Goa", "Hariyana", "Jammu kashmir", "kerala","Punjab"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        editTextDOB = findViewById(R.id.editTextDOB);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        spinnerState = findViewById(R.id.spinnerState);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Setup spinner with states
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(adapter);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String dob = editTextDOB.getText().toString().trim();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                // Name validation
                if (TextUtils.isEmpty(name)) {
                    editTextName.setError("Name is required");
                    return;
                }

                // Email validation
                if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Valid email is required");
                    return;
                }

                // Phone validation (simple 10 digit check)
                if (TextUtils.isEmpty(phone) || !phone.matches("\\d{10}")) {
                    editTextPhone.setError("Enter valid 10-digit phone number");
                    return;
                }

                // Gender validation
                if (radioGroupGender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Please select gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                // DOB validation
                if (TextUtils.isEmpty(dob)) {
                    editTextDOB.setError("Date of Birth is required");
                    return;
                }

                // Password validation
                if (TextUtils.isEmpty(password) || password.length() < 6) {
                    editTextPassword.setError("Password must be at least 6 characters");
                    return;
                }

                // Confirm password validation
                if (!password.equals(confirmPassword)) {
                    editTextConfirmPassword.setError("Passwords do not match");
                    return;
                }

                // State validation
                if (spinnerState.getSelectedItemPosition() == 0) {
                    Toast.makeText(MainActivity.this, "Please select a state", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(MainActivity.this, "All validations passed! Form submitted.", Toast.LENGTH_LONG).show();
            }
        });
    }
}


