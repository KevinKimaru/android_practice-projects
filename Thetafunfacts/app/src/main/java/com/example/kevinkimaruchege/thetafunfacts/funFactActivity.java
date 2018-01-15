package com.example.kevinkimaruchege.thetafunfacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class funFactActivity extends AppCompatActivity {
    private FactBook mFactBook = new FactBook();
    // Declare our View Variables
    private TextView mFactTextView;
    private Button mShowFactButton;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_fact);

        //Assign views from the layout files to the corresponding variables
        mFactTextView = (TextView) findViewById(R.id.factTextView);
        mShowFactButton = (Button) findViewById(R.id.showFactButton);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String fact = mFactBook.getFacts();
                //update the screen with our dynamic fact
                mFactTextView.setText(fact);
                //mRelativeLayout.setBackgroundColor(color.RED);

            }
        };
        mShowFactButton.setOnClickListener(listener);
    }
}
