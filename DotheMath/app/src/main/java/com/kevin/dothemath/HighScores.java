package com.kevin.dothemath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.TextView;

public class HighScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        TextView scoreView = (TextView)findViewById(R.id.high_scores_list);

        //retrieve the Shared Preferences using the constant defined in the gameplay class:
        SharedPreferences scorePrefs = getSharedPreferences(PlayGame.GAME_PREFS, 0);

        //Split the string into an array of high scores:
        String[] savedScores = scorePrefs.getString("highScores", "").split("\\|");

        //Iterate through the scores, appending them into a single string with new lines between them:
        StringBuilder scoreBuild = new StringBuilder("");
        for(String score : savedScores){
            scoreBuild.append(score+"\n");
        }

        scoreView.setText(scoreBuild.toString());

    }
}
