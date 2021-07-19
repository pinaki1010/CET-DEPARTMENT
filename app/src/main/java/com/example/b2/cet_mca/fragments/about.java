package com.example.b2.cet_mca.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b2.cet_mca.R;


public class about extends Fragment {


    private View v;
    private AppCompatActivity a;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_about, container, false);
        a= (AppCompatActivity) getActivity();
        a.getSupportActionBar().setTitle("About-Us");
        NavigationView navigationView = (NavigationView) a.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_About);
        return v;
    }



}
