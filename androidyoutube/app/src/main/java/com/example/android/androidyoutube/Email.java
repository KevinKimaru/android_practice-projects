package com.example.android.androidyoutube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends AppCompatActivity implements View.OnClickListener{

    EditText personsEmail, intro, personsName, stupidThings, hatefulAction, outro;
    String emailAdd, beginning, name, stupidAction, hatefulAct, out;
    Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        initializeVars();
        sendEmail.setOnClickListener(this);
    }

    private void initializeVars() {
        personsEmail = (EditText) findViewById(R.id.etEmails);
        intro = (EditText) findViewById(R.id.etIntro);
        personsName = (EditText) findViewById(R.id.etNames);
        stupidThings = (EditText) findViewById(R.id.etThings);
        hatefulAction = (EditText) findViewById(R.id.etAction);
        outro = (EditText) findViewById(R.id.etOutro);
        sendEmail = (Button) findViewById(R.id.bSentEmail);

    }

    private void convEditToStr() {
        emailAdd = personsEmail.getText().toString().trim();
        beginning = intro.getText().toString().trim();
        name = personsName.getText().toString().trim();
        stupidAction = stupidThings.getText().toString().trim();
        hatefulAct = hatefulAction.getText().toString().trim();
        out = outro.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        convEditToStr();
        String emailaddress[] = {emailAdd};
        String message = "Well Hello "
                + name
                + " I just wanted to say "
                + beginning
                + ". Not only that but i also hate it when you "
                + stupidAction
                + ", that just really makes me crazy. I just want to make you "
                + hatefulAct
                + ". Well thats what i wanted to chit chatter about, oh and "
                + out
                + ". Oh also if you get bored you should check out my website "
                + '\n' + "PS. I think I love you....";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailaddress);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Helllo!!!");
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(emailIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
