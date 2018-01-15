package emobilis.com.facebookintegration;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImg;
    Button mShareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImg = (ImageView) findViewById(R.id.imageView);
        mShareBtn = (Button) findViewById(R.id.share);

        mShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                Uri screenShotUri = Uri.parse("android.resource://comexample.theta.myapplication/");

                sharingIntent.setType("image/png");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, screenShotUri);
                startActivity(Intent.createChooser(sharingIntent, "Share Image"));
            }
        });
    }
}
