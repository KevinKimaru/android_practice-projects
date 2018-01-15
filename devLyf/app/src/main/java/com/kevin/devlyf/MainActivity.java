package com.kevin.devlyf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner storySpinner = (Spinner) findViewById(R.id.spinner_story);
        ArrayAdapter storySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_story, android.R.layout.simple_spinner_item);
        storySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        storySpinner.setAdapter(storySpinnerAdapter);

        ListView storyListView = (ListView) findViewById(R.id.lvStory);

        ArrayList<Story> stories = new ArrayList<Story>();
        stories.add(new Story("kevinkimaru", "I have just added python to my skill set. Check out my profile page"));
        stories.add(new Story("kevinkimaru", "I have just added android to my skill set. Check out my profile page"));
        stories.add(new Story("kevinkimaru", "I have just added java to my skill set. Check out my profile page"));
        stories.add(new Story("kevinkimaru", "I have just added javascript to my skill set. Check out my profile page"));
        stories.add(new Story("kevinkimaru", "I have just added html5 my skill set. Check out my profile page"));
        stories.add(new Story("liz94", "I have just added html5 to my skill set. Check out my profile page"));
        stories.add(new Story("liz94", "I have just added nodejs to my skill set. Check out my profile page"));
        stories.add(new Story("erickariuki", "I have just added python to my skill set. Check out my profile page"));
        stories.add(new Story("erickariuki", "I have just added android to my skill set. Check out my profile page"));
        stories.add(new Story("kevin99", "I have just added python to my skill set. Check out my profile page"));

        StoryAdapter storyAdapter = new StoryAdapter(this, stories);

        storyListView.setAdapter(storyAdapter);
    }
}
