package com.kevin.dothemath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import android.content.SharedPreferences;

import java.util.Random;

public class PlayGame extends AppCompatActivity implements View.OnClickListener {

    private int level = 0, answer = 0, operator = 0, operand1 = 0, operand2 = 0;

    private static final int ADD_OPERATOR = 0, SUBTRACT_OPERATOR = 1, MULTIPLY_OPERATOR = 2,
            DIVIDE_OPERATOR = 3;

    private String[] operators = {"+", "-", "*", "/"};

    private int[][] levelMin = {
            {1, 11, 21},
            {1, 5, 10},
            {2, 5, 10},
            {2, 3, 5}};
    private int[][] levelMax = {
            {10, 25, 50},
            {10, 20, 30},
            {5, 10, 15},
            {10, 50, 100}};

    private Random random;

    private TextView question, answerTxt, scoreTxt;
    private ImageView response;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, enterBtn, clearBtn;

    //variables for application shared prefrences and file na,me
    private SharedPreferences gamePrefs;
    public static final String GAME_PREFS = "ArithmeticFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //instanciate shared prefrences object
        gamePrefs = getSharedPreferences(GAME_PREFS, 0);

        question = (TextView) findViewById(R.id.question);
        answerTxt = (TextView) findViewById(R.id.answer);
        scoreTxt = (TextView) findViewById(R.id.score);
        response = (ImageView) findViewById(R.id.response);

        response.setVisibility(View.INVISIBLE);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        enterBtn = (Button) findViewById(R.id.enter);
        clearBtn = (Button) findViewById(R.id.clear);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        enterBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);

        //We've saved the score and level data, now let's restore them.
        if(savedInstanceState!=null){
            //restore state
            level=savedInstanceState.getInt("level");
            int exScore = savedInstanceState.getInt("score");
            scoreTxt.setText("Score: "+exScore);
        }
        else{
            //retrieve random number
            Bundle extras = getIntent().getExtras();
            if(extras !=null)
            {
                int passedLevel = extras.getInt("level", -1);
                if(passedLevel>=0) level = passedLevel;
            }
        }

        random = new Random();

        chooseQuestion();

    }

    private void chooseQuestion() {
        //reset answer each time a new question is generated
        answerTxt.setText("=?");

        operator = random.nextInt(operators.length);

        operand1 = getOperand();
        operand2 = getOperand();

        //To ensure no negative answers
        if (operator == SUBTRACT_OPERATOR) {
            while (operand2 > operand1) {
                operand1 = getOperand();
                operand2 = getOperand();
            }
        }else if (operator == DIVIDE_OPERATOR) {
            while (((double)operand1/ (double)operand2) % 1 > 0 || (operand1 == operand2)) {
                operand1 = getOperand();
                operand2 = getOperand();
            }
        }

        switch (operator) {
            case ADD_OPERATOR:
                answer = operand1 + operand2;
                break;
            case SUBTRACT_OPERATOR:
                answer = operand1 - operand2;
                break;
            case MULTIPLY_OPERATOR:
                answer = operand1 * operand2;
                break;
            case DIVIDE_OPERATOR:
                answer = operand1 / operand2;
                break;
            default:
                break;
        }

        question.setText(operand1 + " " + operators[operator] + " " + operand2);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.enter) {
            String answerContent = answerTxt.getText().toString();

            //is answer present?
            if (!answerContent.endsWith("?")) {
                //we have an answer
            }

            int enteredAnswer = Integer.parseInt(answerContent.substring(2));

            int exScore = getScore();

            if (enteredAnswer == answer){
                scoreTxt.setText("Score: " + (exScore + 1));
                response.setImageResource(R.drawable.tick);
                response.setVisibility(View.VISIBLE);
            } else {
                setHighScore();
                scoreTxt.setText("Score: 0");
                response.setImageResource(R.drawable.cross);
                response.setVisibility(View.VISIBLE);
            }
            chooseQuestion();

        } else if (view.getId() == R.id.clear) {
            answerTxt.setText("=?");
        } else {
            response.setVisibility(View.INVISIBLE);

            int enteredNum = Integer.parseInt(view.getTag().toString());

            //first or last digit entered?
            if (answerTxt.getText().toString().endsWith("?")) answerTxt.setText("= " + enteredNum);
            else answerTxt.append("" + enteredNum);
        }
    }

    private int getScore() {
        String scoreStr = scoreTxt.getText().toString();
        return Integer.parseInt(scoreStr.substring(scoreStr.lastIndexOf(" ") + 1));
    }

    private int getOperand() {
        return random.nextInt(levelMax[operator][level] - levelMin[operator][level] + 1)
                + levelMin[operator][level];
    }

    private void setHighScore(){
        //retrieve the score
        int exScore = getScore();
        //check if score is greater than 0
        if(exScore>0){
            //get shared preferences editor
            SharedPreferences.Editor scoreEdit = gamePrefs.edit();
            //Create a date format object
            DateFormat dateForm = new SimpleDateFormat("dd MMMM yyyy");
            //get the date and format it
            String dateOutput = dateForm.format(new Date());

            //Retrieve any existing scores from the Shared Preferences, using a variable name we
            // will also use when setting the new high scores:
            String scores = gamePrefs.getString("highScores", "");

            //check ehether there are any existing scores
            if(scores.length()>0){//we have existing scores

                //create a list of Score objects:
                List<Score> scoreStrings = new ArrayList<Score>();

                //We will be storing the high scores in the Shared Preferences as one
                // pipe-delimited string, so split that now:
                String[] exScores = scores.split("\\|");

                // loop through, creating a Score object for each high score by splitting
                // each one into its date and number, then adding it to the list:
                for(String eSc : exScores){
                    String[] parts = eSc.split(" - ");
                    scoreStrings.add(new Score(parts[0], Integer.parseInt(parts[1])));
                }

                //create a Score object for the current score and add it to the list:
                Score newScore = new Score(dateOutput, exScore);
                scoreStrings.add(newScore);

                //Sort the Scores:
                Collections.sort(scoreStrings);

                //dd the first ten to a pipe-delimited string and write it to the Shared Preferences:
                StringBuilder scoreBuild = new StringBuilder("");
                for(int s=0; s<scoreStrings.size(); s++){
                    if(s>=10) break;//only want ten
                    if(s>0) scoreBuild.append("|");//pipe separate the score strings
                    scoreBuild.append(scoreStrings.get(s).getScoreText());
                }
                //write to prefs
                //save the new scores using the variable name we used to retrieve them from the
                // Shared Preferences earlier in the method, as we did when there were no existing
                // scores.
                scoreEdit.putString("highScores", scoreBuild.toString());
                scoreEdit.commit();

            }
            else{  //no existing scores
                //simply add the new score along with the date, with a dash character between
                // them - each score in the list will use this format.
                scoreEdit.putString("highScores", ""+dateOutput+" - "+exScore);
                scoreEdit.commit();
            }

        }

    }
    //set highscore when activity is destroyed
    protected void onDestroy(){
        setHighScore();
        super.onDestroy();
    }

    //save the instance state so that the game data persists through state changes

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        int exScore = getScore();
        savedInstanceState.putInt("score", exScore);
        savedInstanceState.putInt("level", level);
        super.onSaveInstanceState(savedInstanceState);
    }
}
