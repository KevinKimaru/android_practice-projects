package emobilis.com.call;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = (Button) findViewById(R.id.call);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    EditText editt=(EditText)findViewById(R.id.pnoneNumber);
                    String str=editt.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+str));
                    startActivity(intent);
                }
                catch (android.content.ActivityNotFoundException e){

                    Toast.makeText(getApplicationContext(),"App failed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}
