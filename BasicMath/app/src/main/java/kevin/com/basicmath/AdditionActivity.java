package kevin.com.basicmath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdditionActivity extends AppCompatActivity {

    EditText mNumber1;
    EditText mNumber2;
    Button mEquals;
    TextView mAnswer;
    Button question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        mNumber1 = (EditText) findViewById(R.id.number1);
        mNumber2 = (EditText) findViewById(R.id.number2);
        mEquals = (Button) findViewById(R.id.equals);
        mAnswer = (TextView) findViewById(R.id.answer);
        question = (Button) findViewById(R.id.questions);


        mEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num1 = mNumber1.getText().toString().trim();
                String num2 = mNumber2.getText().toString().trim();

                if (num1 == null) {
                    mAnswer.setText("Field required");
                }

                int number2 = Integer.parseInt(num2);
                int number1 = Integer.parseInt(num1);

                int number3 = number1 + number2;
                mAnswer.setText(String.valueOf(number3));
            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdditionActivity.this, AdditionPracticeActivity.class);
                startActivity(intent);
            }
        });

    }
}
