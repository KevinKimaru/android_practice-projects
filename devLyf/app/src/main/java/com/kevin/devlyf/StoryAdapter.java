package com.kevin.devlyf;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 6/26/2017.
 */

public class StoryAdapter extends ArrayAdapter<Story> {
    public StoryAdapter(@NonNull Context context,@NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listStoryView = convertView;

        if(listStoryView == null){
            listStoryView = LayoutInflater.from(getContext()).inflate(R.layout.story_list_item, parent, false);
        }

        Story currentStory = getItem(position);

        TextView nameTag = (TextView) listStoryView.findViewById(R.id.tv_story_name_tag);
        nameTag.setText(currentStory.getLabel() + "");
        TextView name = (TextView) listStoryView.findViewById(R.id.tv_story_name);
        name.setText(currentStory.getName());
        TextView story = (TextView) listStoryView.findViewById(R.id.tv_story);
        story.setText(currentStory.getStory());

        return listStoryView;
    }
}
