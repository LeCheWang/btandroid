package com.example.tinhtoan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    SeekBar sb1, sb2, sb3;
    Button btnStart, btnReset;

    CheckBox cb1, cb2, cb3;

    EditText edtMoney;
    TextView tvBalance;

    int progress1 = 0;
    int progress2 = 0;
    int progress3 = 0;

    int balance = 1000;

    int money = 0;
    int total_money = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        refView();
        sb1.setMax(100);
        sb2.setMax(100);
        sb3.setMax(100);

        tvBalance.setText("Banlance: " + balance + " $");

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    money = Integer.parseInt(edtMoney.getText().toString().trim());
                } catch (Exception e) {
                    Toast.makeText(MainActivity2.this, "Hãy nhập số tiền hợp le", Toast.LENGTH_SHORT).show();
                    return;
                }
                final Handler handler = new Handler();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (progress1 < 100 && progress2 < 100 && progress3 < 100) {
                            Random rand = new Random();
                            int r1 = rand.nextInt(10) + 1;
                            int r2 = rand.nextInt(10) + 1;
                            int r3 = rand.nextInt(10) + 1;

                            progress1 += r1;
                            progress2 += r2;
                            progress3 += r3;

                            // Cập nhật giao diện người dùng bằng Handler
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    updateProgress();
                                    boolean isWin = false;
                                    if (progress1 >= 100 && cb1.isChecked()) {
                                        balance += money * 3;
                                        tvBalance.setText("Banlance: " + balance + " $");
                                        isWin = true;
                                        openDialog(money*3, "Con ngựa số 1", isWin);
                                    }

                                    if (progress2 >= 100 && cb2.isChecked()) {
                                        balance += money * 3;
                                        tvBalance.setText("Banlance: " + balance + " $");
                                        isWin = true;
                                        openDialog(money*3, "Con ngựa số 2", isWin);
                                    }

                                    if (progress3 >= 100 && cb3.isChecked()) {
                                        balance += money * 3;
                                        tvBalance.setText("Banlance: " + balance + " $");
                                        isWin = true;
                                        openDialog(money*3, "Con ngựa số 3", isWin);
                                    }

                                    if ((progress1 >= 100 || progress2 >= 100 || progress3 >= 100) && !isWin){
                                        openDialog(total_money, "Chúc bạn thành công lần sau", isWin);
                                    }
                                }


                            });

                            // Lặp lại công việc sau 1 giây
                            handler.postDelayed(this, 1000);
                        }
                    }
                };

                if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked()) {

                    if (cb1.isChecked()) {
                        total_money += money;
                    }
                    if (cb2.isChecked()) {
                        total_money += money;
                    }
                    if (cb3.isChecked()) {
                        total_money += money;
                    }

                    if (total_money <= balance) {
                        balance -= total_money;
                        tvBalance.setText("Banlance: " + balance + " $");
                        // Bắt đầu công việc ban đầu
                        handler.post(runnable);
                    } else {
                        Toast.makeText(MainActivity2.this, "Số dư không đủ", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity2.this, "Hãy chọn 1 con ngựa", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress1 = 0;
                progress2 = 0;
                progress3 = 0;
                updateProgress();
                edtMoney.setText("");
                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);
                total_money = 0;
                money = 0;
            }
        });
    }

    private void openDialog(int money, String s, boolean isWin) {
        Dialog dialog = new Dialog(MainActivity2.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_finished);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtributes = window.getAttributes();
        windowAtributes.gravity = Gravity.CENTER_VERTICAL;
        window.setAttributes(windowAtributes);

        TextView tvWIn = dialog.findViewById(R.id.tvWin);
        TextView tvMoneyWin = dialog.findViewById(R.id.tvMoneyWin);
        TextView tvDes = dialog.findViewById(R.id.tvDes);

        if (!isWin){
            tvWIn.setText("LOSS");
            tvMoneyWin.setText("-" + money + "$");
            tvDes.setText(s);
        }else {
            tvMoneyWin.setText("+" + money + "$");
            tvDes.setText("Chúc mừng bạn thắng " + s);
            // phát nhạc
            MediaPlayer mPlayer = MediaPlayer.create(MainActivity2.this, R.raw.win);
            mPlayer.start();
        }


        dialog.setCancelable(true);

        dialog.show();
    }

    private void refView() {
        sb1 = findViewById(R.id.sb1);
        sb2 = findViewById(R.id.sb2);
        sb3 = findViewById(R.id.sb3);

        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);

        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);

        edtMoney = findViewById(R.id.edtMoney);
        tvBalance = findViewById(R.id.tvBalance);
    }

    private void updateProgress() {
        sb1.setProgress(progress1);
        sb2.setProgress(progress2);
        sb3.setProgress(progress3);
    }



}