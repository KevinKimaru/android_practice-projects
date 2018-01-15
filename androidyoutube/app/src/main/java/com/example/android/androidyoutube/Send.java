package com.example.android.androidyoutube;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Send extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    TextView question, test;
    Button returnData;
    RadioGroup selection;
    String gotBread, setText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        initialize();

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(this);
        String et = getData.getString("name", "Kevin is...");
        String values = getData.getString("list", "4");
        if (values.contentEquals("1")) {
            question.setText(et);
        }

//        Bundle gotBasket = getIntent().getExtras();
//        gotBread = gotBasket.getString("key");
//        question.setText(gotBread);
    }

    private void initialize() {
        question = (TextView) findViewById(R.id.tvQues);
        test = (TextView) findViewById(R.id.tvText);
        returnData = (Button) findViewById(R.id.bReturn);
        selection = (RadioGroup) findViewById(R.id.rgAnswers);

        returnData.setOnClickListener(this);
        selection.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent person = new Intent();
        Bundle backpack = new Bundle();
        backpack.putString("key", setText);
        person.putExtras(backpack);
        setResult(RESULT_OK, person);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rCrazy:
                setText = "Maybe, dont know";
                break;
            case R.id.rWise:
                setText = "Definitely";
                break;
            case R.id.rBoth:
                setText = "Spot on";
                break;
        }
        test.setText(setText);
    }
}
