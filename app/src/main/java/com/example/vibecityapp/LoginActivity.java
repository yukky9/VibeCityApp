package com.example.vibecityapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText codeInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        codeInput = findViewById(R.id.code_input);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            String code = codeInput.getText().toString().trim();
            if (code.isEmpty()) {
                Toast.makeText(this, "Please enter the code", Toast.LENGTH_SHORT).show();
            } else {
                // In a real app, you would verify this code with your Telegram bot API
                // For this example, we'll just proceed with any non-empty code
                ((VibeCityApp) getApplication()).setAuthToken("dummy_token_" + code);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });
    }
}