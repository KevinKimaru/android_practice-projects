package com.kevin.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kevin Kimaru Chege on 5/2/2017.
 */

public class Lm_Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //inflate the layout of this fragment
        View rootView;
        rootView = inflater.inflate(R.layout.pm_fragment, container, false);

        return rootView;
    }
}
