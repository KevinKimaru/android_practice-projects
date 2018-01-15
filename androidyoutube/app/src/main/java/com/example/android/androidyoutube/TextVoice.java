package com.example.android.androidyoutube;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Locale;
import java.util.Random;

public class TextVoice extends AppCompatActivity {

    Button b;

    static final String[] texts = {
            "Hello, I am Kevin",
            "I am an android Developer",
            "Programming for life"
    };

    TextToSpeech mTextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_voice);

        b = (Button) findViewById(R.id.bTextToVoice);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                String random = texts[r.nextInt(3)];
                mTextToSpeech.speak(random, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        mTextToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        if (mTextToSpeech != null) {
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
        }
        super.onPause();
    }
}
