package com.example.b2.cet_mca.fragments;

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
import com.example.b2.cet_mca.pojo.Placement;
import com.example.b2.cet_mca.viewholder.PlacementViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PlacementFragment extends Fragment {

    private View v;
    private AppCompatActivity a;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v =inflater.inflate(R.layout.fragment_placement, container, false);
        a= (AppCompatActivity) getActivity();
        a.getSupportActionBar().setTitle("Placement Record");
        NavigationView navigationView = (NavigationView) a.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_placement);
        RecyclerView list=v.findViewById(R.id.placement_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(a,LinearLayoutManager.VERTICAL,false));
        DatabaseReference placementRef=FirebaseDatabase.getInstance().getReference().child("placement");
        FirebaseRecyclerOptions<Placement> options=new FirebaseRecyclerOptions.Builder<Placement>()
                                                                                .setQuery(placementRef,Placement.class)
                                                                                        .build();
        FirebaseRecyclerAdapter<Placement,PlacementViewHolder> f=new FirebaseRecyclerAdapter<Placement, PlacementViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PlacementViewHolder holder, int position, @NonNull Placement model) {
                holder.setBatch(model.getBatch());
                holder.setCompany(model.getCompany());
                holder.setName(model.getName());
            }

            @NonNull
            @Override
            public PlacementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new PlacementViewHolder(LayoutInflater.from(a).inflate(R.layout.placement_row,viewGroup,false));
            }
        };
        f.startListening();
        list.setAdapter(f);
       return v;
    }



}
