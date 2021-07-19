package com.example.b2.cet_mca.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b2.cet_mca.R;
import com.example.b2.cet_mca.pojo.Image;
import com.example.b2.cet_mca.viewholder.GalleryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class gallery extends Fragment {
    private View v;
    private AppCompatActivity a;
    private RecyclerView list;
    private FirebaseRecyclerAdapter<Image,GalleryViewHolder> f;
    private DatabaseReference galleryRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_gallery, container, false);
        a= (AppCompatActivity) getActivity();
        a.getSupportActionBar().setTitle("Gallery");
        NavigationView navigationView =  a.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_gallery);
        list=v.findViewById(R.id.gallery_list);
        galleryRef=FirebaseDatabase.getInstance().getReference().child("gallery");
        FirebaseRecyclerOptions<Image> options=new FirebaseRecyclerOptions.Builder<Image>().setQuery(galleryRef,Image.class).build();
        f=new FirebaseRecyclerAdapter<Image, GalleryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull GalleryViewHolder holder, int position, @NonNull Image model) {
                holder.setImage(model.getImage(),getActivity());
            }

            @NonNull
            @Override
            public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v=LayoutInflater.from(getActivity()).inflate(R.layout.gallery_row,viewGroup,false);
                return  new GalleryViewHolder(v);
            }
        };
        list.setAdapter(f);
        list.setHasFixedSize(true);
        list.setLayoutManager(new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false));
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        f.startListening();
    }
}
