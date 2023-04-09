package com.example.mantaiaja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    EditText etemail,etpassword,etusername;
    Button SignUpButton;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        etusername = (EditText) findViewById(R.id.et_username);
        etemail = (EditText) findViewById(R.id.et_email);
        etpassword = (EditText) findViewById(R.id.et_password);
        TextView textView = findViewById(R.id.text_view);
        String text = getResources().getString(R.string.no_account_text);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Aksi yang akan dijalankan saat teks "Create now" di-klik
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        };

        spannableStringBuilder.setSpan(clickableSpan, 24, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        // Set onClickListener untuk SignUpButton
        SignUpButton = findViewById(R.id.signup_button);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = etusername.getText().toString();
                String b = etemail.getText().toString();
                String c = etpassword.getText().toString();
                editor.putString("kunci01",a);
                editor.putString("kunci02",b);
                editor.putString("kunci03",c);
                editor.commit();
                Toast.makeText(SignUp.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

