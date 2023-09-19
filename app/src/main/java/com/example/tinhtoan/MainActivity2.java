package com.example.tinhtoan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    EditText edtMin, edtMax;
    Button btnGenerate;
    TextView tvResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtMin = findViewById(R.id.edtMin);
        edtMax = findViewById(R.id.edtMax);

        btnGenerate = findViewById(R.id.btnGenerate);

        tvResult = findViewById(R.id.tvResult);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = Integer.parseInt(edtMin.getText().toString().trim());
                int max = Integer.parseInt(edtMax.getText().toString().trim());

                Random generator = new Random();
                //random 1 số trong khoảng từ min -> max
                int random = generator.nextInt((max - min) + 1) + min;

                tvResult.setText(random + "");
            }
        });
    }
}