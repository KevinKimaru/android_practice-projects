package com.example.android.apis;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class DisplayActivity extends AppCompatActivity {
    ListView list;
    CustomAdapter adapter;
    ArrayList<User> arrayUsers;
    SwipeRefreshLayout refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        list = (ListView) findViewById(R.id.usersList);
        refresh = (SwipeRefreshLayout) findViewById(R.id.swiper);
        arrayUsers = new ArrayList<>();
        adapter = new CustomAdapter(this, arrayUsers);
        list.setAdapter(adapter);
        refresh.setRefreshing(true);
        fetch();
    }

    public void fetch() {
        String url = "http://kevinkimaru.pythonanywhere.com//all";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                refresh.setRefreshing(false);
                Toast.makeText(DisplayActivity.this, "Failed to Fetch. Try again", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                refresh.setRefreshing(false);
                Log.d("RESPONSE", s);

                try {
                    JSONArray array = new JSONArray(s);
                    for (int x = 0; x < array.length(); x++) {
                        JSONObject person = array.getJSONObject(x);
                        int id = person.getInt("id");
                        String names = person.getString("names");
                        String email = person.getString("email");
                        int age = person.getInt("age");

                        User u = new User(id, names, email, age);
                        arrayUsers.add(u);
                        log.d("PERSON", id + " " + names + " " + email + " " + age);

                        Log.d("PERSON ", id + "  " + names + "  " + email + "  " + age);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

