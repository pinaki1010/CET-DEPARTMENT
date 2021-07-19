package com.example.b2.cet_mca.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b2.cet_mca.R;
import com.example.b2.cet_mca.pojo.Faculty;
import com.example.b2.cet_mca.viewholder.FacultyViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class facaulty extends Fragment {
    private View v;
    private AppCompatActivity a;
    private RecyclerView list;
    private FirebaseRecyclerAdapter<Faculty,FacultyViewHolder> f;
    private DatabaseReference facultyRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_facaulty, container, false);
        a= (AppCompatActivity) getActivity();
        a.getSupportActionBar().setTitle("Faculty Details");
        NavigationView navigationView = (NavigationView) a.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_faculty);
        list=v.findViewById(R.id.faculty_list);
        facultyRef=FirebaseDatabase.getInstance().getReference().child("faculty");
        FirebaseRecyclerOptions<Faculty> options=new FirebaseRecyclerOptions.Builder<Faculty>().setQuery(facultyRef,Faculty.class).build();
        f=new FirebaseRecyclerAdapter<Faculty, FacultyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FacultyViewHolder holder, int position, @NonNull Faculty model) {
                holder.sendMail(model.getMail(),(AppCompatActivity)getActivity());
                holder.setCall(model.getPhone(),(AppCompatActivity) getActivity());
                holder.setDp(model.getImage(),getActivity());
                holder.setName(model.getName());
                holder.setPost(model.getPost());
                holder.setQualification(model.getQualification());
                holder.setSubject(model.getSubject());
            }

            @NonNull
            @Override
            public FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v=LayoutInflater.from(getActivity()).inflate(R.layout.faculty_row,viewGroup,false);
                return new FacultyViewHolder(v);
            }
        };
        list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        list.setHasFixedSize(true);
        list.setAdapter(f);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        f.startListening();
    }
}
