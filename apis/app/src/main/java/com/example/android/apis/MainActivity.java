package com.example.android.apis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    EditText inputNames, inputEmail, inputAge;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNames = (EditText) findViewById(R.id.inputNames);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputAge = (EditText) findViewById(R.id.inputAge);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Saving User....");
    }

    public void submit(View view) {
        String url = "http://kevinkimaru.pythonanywhere.com/save";
        String names = inputNames.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String age = inputAge.getText().toString().trim();
        RequestParams params = new RequestParams();
        params.put("names", names);
        params.put("email", email);
        params.put("age", Integer.parseInt(age));

        AsyncHttpClient client = new AsyncHttpClient();
        dialog.show();
        client.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Failed to save. Try again", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                Toast.makeText(MainActivity.this, "Server said " + s, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }

    public void display(View view) {
        Intent x = new Intent(this, DisplayActivity.class);
        startActivity(x);
    }
}
