package com.example.tinhtoan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtSoThu1, edtSoThu2;
    Button btnCong, btnTru, btnNhan, btnChia;
    TextView tvKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ánh xạ code java với view
        refView();

        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int so1 = Integer.parseInt(edtSoThu1.getText().toString().trim());
                int so2 = Integer.parseInt(edtSoThu2.getText().toString().trim());

                int tong = so1 + so2;

                tvKetQua.setText(tong + "");
            }
        });

    }

    private void refView() {
        edtSoThu1 = findViewById(R.id.edtSoThu1);
        edtSoThu2 = findViewById(R.id.edtSoThu2);

        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);

        tvKetQua = findViewById(R.id.tvKetQua);
    }
}