package me.nathanfallet.appmonday.controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.nathanfallet.appmonday.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompetitionsFragment extends Fragment {


    public CompetitionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_competitions, container, false);
    }

}
