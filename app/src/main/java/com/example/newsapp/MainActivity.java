package com.example.newsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);
        login = findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pw = password.getText().toString();

                if ((name.equals("pakjoko"))&&(pw.equals("yangpentingcuan"))){
                    Toast.makeText(MainActivity.this,"BERHASIL MASUK!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, DetailData.class);
                    startActivity(intent);
                }

                else if (TextUtils.isEmpty(username.getText().toString())) {
                    Toast.makeText(MainActivity.this, "ISI USERNAME TERLEBIH DAHULU!",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(MainActivity.this, "ISI PASSWORD TERLEBIH DAHULU!",Toast.LENGTH_SHORT).show();
                }
                else {
                    showAlertDialog();
                }
            }
        });
    }

    public void showAlertDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Konfirmasi");
        alertBuilder.setMessage("Username/password salah, silahkan coba lagi!!!");

        alertBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "USERNAME/PASSWORD SALAH!", Toast.LENGTH_SHORT).show();
                username.getText().clear();
                password.getText().clear();
            }
        });

        alertBuilder.show();
    }

}