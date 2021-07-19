package com.example.b2.cet_mca.fragments;

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
import com.example.b2.cet_mca.pojo.Syllabus;
import com.example.b2.cet_mca.viewholder.SyllabusViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Syllabus_fragment extends Fragment {

    private View v;
    private AppCompatActivity a;
    private RecyclerView list;
    private FirebaseRecyclerAdapter<Syllabus,SyllabusViewHolder> f;
    private DatabaseReference syllabusRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_syllabus, container, false);
        a= (AppCompatActivity) getActivity();
        a.getSupportActionBar().setTitle("Syllabus");
        NavigationView navigationView = (NavigationView) a.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_syllabus);
        list=v.findViewById(R.id.syllabus_list);
        syllabusRef=FirebaseDatabase.getInstance().getReference().child("syllabus");
        FirebaseRecyclerOptions<Syllabus> options=new FirebaseRecyclerOptions.Builder<Syllabus>().setQuery(syllabusRef,Syllabus.class).build();
        f=new FirebaseRecyclerAdapter<Syllabus, SyllabusViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SyllabusViewHolder holder, int position, @NonNull Syllabus model) {
                holder.setName(model.getName());
                holder.setDownload((AppCompatActivity) getActivity(),model.getPdf());
            }
            @Override
            public SyllabusViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v=LayoutInflater.from(getActivity()).inflate(R.layout.syllabus_row,viewGroup,false);
                return new SyllabusViewHolder(v);
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
