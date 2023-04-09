package com.example.mantaiaja;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText etEmail, etPassword;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        TextView textView = findViewById(R.id.text_view);
        String text = getResources().getString(R.string.have_account_text);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Aksi yang akan dijalankan saat teks "Create now" di-klik
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        };

        spannableStringBuilder.setSpan(clickableSpan, 19, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        // Set onClickListener untuk loginButton
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String b = pref.getString("kunci02", null);
                String c = pref.getString("kunci03", null);

//                 Validasi email dan password
                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Email harus diisi");
                    etEmail.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Password harus diisi");
                    etPassword.requestFocus();
                    return;
                }

                if (email.equals(b) && password.equals(c)) {
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }}


