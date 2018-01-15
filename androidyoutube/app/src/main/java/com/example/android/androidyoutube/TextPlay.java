package com.example.android.androidyoutube;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

public class TextPlay extends AppCompatActivity implements View.OnClickListener {

    Button btnCmd;
    ToggleButton togCmd;
    EditText edCmd;
    TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_play);

        baconAndEggs();
        togCmd.setOnClickListener(this);
        btnCmd.setOnClickListener(this);
    }

    private void baconAndEggs() {
        btnCmd = (Button) findViewById(R.id.btnCmd);
        togCmd = (ToggleButton) findViewById(R.id.togCmd);
        edCmd = (EditText) findViewById(R.id.edCmd);
        tvRes = (TextView) findViewById(R.id.tvRes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCmd:
                String check = edCmd.getText().toString().trim();
                tvRes.setText(check);
                if (check.contentEquals("left")) {
                    tvRes.setGravity(Gravity.LEFT);
                } else if (check.contentEquals("center")) {
                    tvRes.setGravity(Gravity.CENTER);
                } else if (check.contentEquals("right")) {
                    tvRes.setGravity(Gravity.RIGHT);
                } else if (check.contentEquals("blue")) {
                    tvRes.setTextColor(Color.BLUE);
                } else if (check.contains("WIC")) {
                    Random crazy = new Random();
                    tvRes.setText("WIC!!!!");
                    tvRes.setTextSize(crazy.nextInt(255));
                    tvRes.setTextColor(Color.rgb(crazy.nextInt(255), crazy.nextInt(255),
                            crazy.nextInt(255)));
                    switch (crazy.nextInt(3)) {
                        case 0:
                            tvRes.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            tvRes.setGravity(Gravity.CENTER);
                            break;
                        case 2:
                            tvRes.setGravity(Gravity.RIGHT);
                            break;
                    }
                } else {
                    tvRes.setText("invalid");
                    tvRes.setGravity(Gravity.CENTER);
                    tvRes.setTextColor(Color.BLACK);
                }
                break;
            case R.id.togCmd:
                if (togCmd.isChecked()) {
                    edCmd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    edCmd.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;
        }
    }
}
