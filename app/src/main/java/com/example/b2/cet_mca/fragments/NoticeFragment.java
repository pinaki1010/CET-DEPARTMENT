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
import android.widget.Toast;

import com.example.b2.cet_mca.R;
import com.example.b2.cet_mca.pojo.Notice;
import com.example.b2.cet_mca.viewholder.NoticeViewHolder;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class NoticeFragment extends Fragment {

    private View v;
    private AppCompatActivity a;
    private DatabaseReference noticeRef;
    private RecyclerView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=  inflater.inflate(R.layout.fragment_notifaction, container, false);
        a= (AppCompatActivity) getActivity();
        a.getSupportActionBar().setTitle("Notifications");
        NavigationView navigationView = (NavigationView) a.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_Notification);
        list=v.findViewById(R.id.notice_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        noticeRef=FirebaseDatabase.getInstance().getReference().child("notice");
        refresh();
        return v;
    }

    private void refresh() {
        FirebaseRecyclerOptions<Notice> options=new FirebaseRecyclerOptions.Builder<Notice>()
                                                                    .setQuery(noticeRef,Notice.class)
                                                                    .build();
        FirebaseRecyclerAdapter<Notice,NoticeViewHolder> f=new FirebaseRecyclerAdapter<Notice, NoticeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull NoticeViewHolder holder, int position, @NonNull Notice model) {
                holder.setBody(model.getBody());
                holder.setTitle(model.getTitle());
                holder.setTime(model.getTime());
                holder.setClick(a,model.getLink());
            }

            @NonNull
            @Override
            public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new NoticeViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.notice_row,viewGroup,false));
            }
        };
        f.startListening();
        list.setAdapter(f);
    }


}

