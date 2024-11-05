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

public class FormLogin extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private ImageView imgHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        imgHome = findViewById(R.id.home);

        View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    imgHome.setVisibility(View.GONE);
                } else if (!etUsername.hasFocus() && !etPassword.hasFocus()) {
                    imgHome.setVisibility(View.VISIBLE);
                }
            }
        };

        etUsername.setOnFocusChangeListener(focusChangeListener);
        etPassword.setOnFocusChangeListener(focusChangeListener);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = etUsername.getText().toString();
                String passwordInput = etPassword.getText().toString();

                // Ambil data dari SharedPreferences
                SharedPreferences sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String savedUsername = sharedPref.getString("username", null);
                String savedPassword = sharedPref.getString("password", null);

                if (savedUsername != null && savedPassword != null) {
                    if (usernameInput.equals(savedUsername) && passwordInput.equals(savedPassword)) {
                        Toast.makeText(FormLogin.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // Arahkan ke halaman utama
                        Intent intent = new Intent(FormLogin.this, FormUtama.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(FormLogin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FormLogin.this, "Please register first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
