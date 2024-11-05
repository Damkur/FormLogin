package com.damkur.formlogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FormRegister extends AppCompatActivity {
    private EditText etEmail, etUsername, etPassword;
    private Button btnRegister;
    private ImageView homeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);

        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        homeImageView = findViewById(R.id.home);


        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    homeImageView.setVisibility(View.GONE);
                }else if (!etUsername.hasFocus() && !etPassword.hasFocus()) {
                    homeImageView.setVisibility(View.VISIBLE);
                }
            }
        };

        etEmail.setOnFocusChangeListener(onFocusChangeListener);
        etUsername.setOnFocusChangeListener(onFocusChangeListener);
        etPassword.setOnFocusChangeListener(onFocusChangeListener);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (!email.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                    // Simpan data ke SharedPreferences
                    SharedPreferences sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("email", email);
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();

                    Toast.makeText(FormRegister.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                    // Arahkan ke halaman login
                    Intent intent = new Intent(FormRegister.this, FormLogin.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(FormRegister.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
