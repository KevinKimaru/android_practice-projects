package com.kevin.emailintents;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button send;
    EditText phoneNum;
    EditText message;
    Button sendMess;
    Button sendMessIntent;
    Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNum = (EditText) findViewById(R.id.phone);
        message = (EditText) findViewById(R.id.message);

        call = (Button) findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callIntent();
            }
        });
        sendMessIntent = (Button) findViewById(R.id.sendMessageIntent);
        sendMessIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSmsViaIntent();
            }
        });
        sendMess = (Button) findViewById(R.id.sendMessage);
        sendMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSmsViaSmsManager();
            }
        });
        send = (Button) findViewById(R.id.email);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void callIntent() {
        String phoneNo = phoneNum.getText().toString();

        Intent callIntent = new Intent(Intent.ACTION_CALL);//use ACTION_DIAL, if you want to modify number before calling
        callIntent.setData(Uri.parse("tel:" + phoneNo ));

        try {
            startActivity(callIntent);
            finish();
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Call failed, please try again later" + e, Toast.LENGTH_SHORT).show();
        }
    }

    private void sendEmail() {
        String[] TO = {"vanqschege@gmail.com"};
        String[] CC = {"kevinkimaru140@gmail.com"};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email from application, Amazing!");

        try {
            //startActivity(Intent.createChooser(emailIntent, "Send email..."));
            startActivity(emailIntent);
            finish();
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSmsViaSmsManager() {
        String phoneNo = phoneNum.getText().toString();
        String messageSms = message.getText().toString();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, messageSms, null, null);
            Toast.makeText(this, "Sms sent.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Sms failed, please try again: %n" + e, Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSmsViaIntent() {

        String phoneNo = phoneNum.getText().toString();
        String messageSms = message.getText().toString();

        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");

        smsIntent.putExtra("address", phoneNo);
        smsIntent.putExtra("sms_body", messageSms);

        try {
            startActivity(smsIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Sms failed: %n" + e, Toast.LENGTH_SHORT).show();
        }
    }

}
