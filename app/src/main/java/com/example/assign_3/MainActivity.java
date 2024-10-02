package com.example.assign_3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, idEditText, emailEditText, phoneEditText, passwordEditText;
    private String name, id, email, phone, password, dept;
    private Spinner deptSpinner;
    private Button submit, back, edit;
    private Pattern namePattern = Pattern.compile("^[A-Za-z\\s]{2,}$");
    private Pattern idPattern = Pattern.compile("^[0-9]{2,}$");
    private Pattern emailPattern = Pattern.compile("^[a-z]+_[0-9]{5,}@[a-z]+\\.[a-z]{2,}\\.[a-z]{2,}$");
    private Pattern phonePattern = Pattern.compile("^[0-9]{11}$");
    private Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&+=]).{8,}$");

    LinearLayout inputLayout, outputLayout;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.et_username);
        idEditText = findViewById(R.id.et_id);
        emailEditText = findViewById(R.id.et_email);
        phoneEditText = findViewById(R.id.et_number);
        passwordEditText = findViewById(R.id.et_password);
        deptSpinner = findViewById(R.id.spinner);
        submit = findViewById(R.id.btn_submit);
        back = findViewById(R.id.btn_return);
        edit = findViewById(R.id.btn_edit);
        inputLayout = findViewById(R.id.input_layout);
        outputLayout = findViewById(R.id.output_layout);
        outputText = findViewById(R.id.output_text);

        String[] items = new String[]{"Select Department", "CSE", "EEE", "ARCH", "CE", "BuA", "ENG", "LAW", "IS", "BNG", "THM", "PH"};
        deptSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items));
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dept = deptSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEditText.getText().toString();
                id = idEditText.getText().toString();
                email = emailEditText.getText().toString();
                phone = phoneEditText.getText().toString();
                password = passwordEditText.getText().toString();

                if (name.isEmpty()) {
                    nameEditText.setError("Name is required!");
                    nameEditText.requestFocus();
                } else if (!namePattern.matcher(name).matches()) {
                    nameEditText.setError("Only alphabets are allowed!");
                    nameEditText.requestFocus();
                } else if (id.isEmpty()) {
                    idEditText.setError("ID is required!");
                    idEditText.requestFocus();
                } else if (!idPattern.matcher(id).matches()) {
                    idEditText.setError("Only numbers are allowed!");
                    idEditText.requestFocus();
                } else if (email.isEmpty()) {emailEditText.setError("Email is required!");
                    emailEditText.requestFocus();
                } else if (!emailPattern.matcher(email).matches()) {
                    emailEditText.setError("Enter a valid email!");
                    emailEditText.requestFocus();
                } else if (phone.isEmpty()) {
                    phoneEditText.setError("Phone number is required!");
                    phoneEditText.requestFocus();
                }  else if (!phonePattern.matcher(phone).matches()) {
                    phoneEditText.setError("Invalid number!");
                    phoneEditText.requestFocus();
                } else if (password.isEmpty()) {
                    passwordEditText.setError("Password is required!");
                    passwordEditText.requestFocus();
                }  else if (!passwordPattern.matcher(password).matches()) {
                    passwordEditText.setError("Invalid password!");
                    passwordEditText.requestFocus();
                } else if (Objects.equals(dept, "Select Department")) {
                    Toast.makeText(getApplicationContext(), "Please select department!", Toast.LENGTH_SHORT).show();
                } else {
                    inputLayout.setVisibility(View.GONE);
                    outputLayout.setVisibility(View.VISIBLE);
                    String s = "Name: " + name + "\nId: " + id + "\nEmail: " + email + "\nPhone: "+phone+ "\nDepartment: "+dept ;
                    outputText.setText(s);

                }

            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                outputLayout.setVisibility(View.GONE);
                inputLayout.setVisibility(View.VISIBLE);

                nameEditText.setText("");
                idEditText.setText("");
                emailEditText.setText("");
                phoneEditText.setText("");
                passwordEditText.setText("");
                deptSpinner.setSelection(0);

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputLayout.setVisibility(View.GONE);
                inputLayout.setVisibility(View.VISIBLE);
            }
        });

    }

}