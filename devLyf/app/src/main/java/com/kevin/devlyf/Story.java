package com.kevin.devlyf;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 6/26/2017.
 */

public class Story {
    private String mName;
    private String mStory;

    public Story(String name, String story) {
        mName = name;
        mStory = story;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public char getLabel() {
        char label = getName().charAt(0);
        return label;
    }

    public String getStory() {
        return mStory;
    }

    public void setStory(String story) {
        mStory = story;
    }
}
