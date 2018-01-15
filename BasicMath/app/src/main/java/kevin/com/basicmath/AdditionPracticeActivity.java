package kevin.com.basicmath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AdditionPracticeActivity extends AppCompatActivity {

    TextView number1;
    TextView number2;
    EditText answer;
    Button submit;
    TextView score;
    TextView winLost;
    Button restart;

    private static final Random rand = new Random();

    private static int instances = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_practice);

        number1 = (TextView) findViewById(R.id.number1);
        number2 = (TextView) findViewById(R.id.number2);
        answer = (EditText) findViewById(R.id.answer);
        submit = (Button) findViewById(R.id.submit);
        score = (TextView) findViewById(R.id.score);
        winLost = (TextView) findViewById(R.id.winLose);
        restart = (Button) findViewById(R.id.restart);

        final int num = rand.nextInt(100) + 1;
        final int num1 = rand.nextInt(100) + 1;
        number1.setText(String.valueOf(num));
        number2.setText(String.valueOf(num1));


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count = 0;

                String ans = answer.getText().toString().trim();
                int answ = Integer.parseInt(ans);
                int num3 = num + num1;
                generateRandomNumbers();
                answer.setText("");

                if (instances == 4) {
                    finish();
                    Toast.makeText(AdditionPracticeActivity.this, "You won", Toast.LENGTH_SHORT).show();
                }

                score.setText(String.valueOf(instances));


            }
        });
    }

    private void generateRandomNumbers() {
        final int num = rand.nextInt(100) + 1;
        final int num1 = rand.nextInt(100) + 1;
        number1.setText(String.valueOf(num));
        number2.setText(String.valueOf(num1));

        instances++;
    }

}