package emobilis.com.sendmail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton;

        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

    }

    protected void sendEmail(){
        Log.i("Send email", "");
        String[] TO = {"kevinkimaru99@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto"));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email was here");

        try{
            startActivity(Intent.createChooser(emailIntent, "Send email.."));
            finish();
            Log.i("Finished sending email.", "");
        } catch(android.content.ActivityNotFoundException ex){
            Toast.makeText(this, "There is no email client installed on your device", Toast.LENGTH_SHORT).show();
        }


    }
}
