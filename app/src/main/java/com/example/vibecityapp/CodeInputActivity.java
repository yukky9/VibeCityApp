package com.example.vibecityapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CodeInputActivity extends AppCompatActivity {

    private static final String TG_BOT_URL = "https://t.me/vibecity_expresspe_bot";
    private static final String VALID_CODE = "123456"; // В реальном приложении проверяйте через API

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_input);

        EditText codeInput = findViewById(R.id.codeInput);
        Button verifyButton = findViewById(R.id.verifyButton);
        Button openTgButton = findViewById(R.id.openTgButton);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Кнопка проверки кода
        verifyButton.setOnClickListener(v -> {
            String code = codeInput.getText().toString().trim();

            if (code.isEmpty()) {
                showError("Введите код подтверждения");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            verifyButton.setEnabled(false);

            // Имитация проверки кода через API
            new android.os.Handler().postDelayed(() -> {
                progressBar.setVisibility(View.GONE);
                verifyButton.setEnabled(true);

                if (code.equals(VALID_CODE)) {
                    startMainActivity();
                } else {
                    showError("Неверный код. Попробуйте снова");
                }
            }, 1500);
        });

        // Кнопка открытия Telegram бота
        openTgButton.setOnClickListener(v -> openTelegramBot());
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void openTelegramBot() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(TG_BOT_URL));
            startActivity(intent);
        } catch (Exception e) {
            // Если Telegram не установлен, открываем в браузере
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(TG_BOT_URL.replace("t.me", "web.telegram.org")));
            startActivity(intent);
        }
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}