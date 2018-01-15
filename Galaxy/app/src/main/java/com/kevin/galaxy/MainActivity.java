package com.kevin.galaxy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);


        String url = "https://api.nasa.gov/planetary/apod?api_key=2DvZffMBhr4ky7Lsa1h9FKllCZXAnhktUdN7yxGk";
        OkHttpClient client = new OkHttpClient();
        client.retryOnConnectionFailure();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v(TAG, "======================================" + e + "===================================");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.v(TAG, response.body().string());
                if (!response.equals(null)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                textView.setText(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        });

    }
}
