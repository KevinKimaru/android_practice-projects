package emobilis.com.login;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button logInBtn, cancelBtn;
    EditText username, pass;
    TextView attempts;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logInBtn = (Button) findViewById(R.id.login);
        cancelBtn = (Button) findViewById(R.id.cancel);
        username = (EditText) findViewById(R.id.username);
        pass  = (EditText) findViewById(R.id.pass);
        attempts = (TextView) findViewById(R.id.attempts);
        attempts.setVisibility(View.GONE);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") &&
                        pass.getText().toString().equals("password")){
                    Toast.makeText(MainActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                    //CREATE AN INTENT TO INVESTIGATE TO ANOTHER ACTIVITY HERE
                }else {
                    Toast.makeText(MainActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                    attempts.setVisibility(View.VISIBLE);
                    attempts.setBackgroundColor(Color.RED);
                    counter--;
                    attempts.setText(Integer.toString(counter) + " attempts left");

                    if(counter == 0){
                        logInBtn.setEnabled(false);
                    }
                }
            }
        });

    }
}
