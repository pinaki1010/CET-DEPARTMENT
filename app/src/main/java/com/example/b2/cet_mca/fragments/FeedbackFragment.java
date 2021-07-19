package com.example.b2.cet_mca.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b2.cet_mca.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class FeedbackFragment extends Fragment {
    private View v;
    private AppCompatActivity a;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_feedback, container, false);
        a= (AppCompatActivity) getActivity();
        a.getSupportActionBar().setTitle("Feed Back");
        NavigationView navigationView = (NavigationView) a.findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_Feedback);
        final EditText subjectText=v.findViewById(R.id.feedback_input_subject);
        final EditText feedBackText=v.findViewById(R.id.feedback_input_feedback);
        final Button addButton=v.findViewById(R.id.btn_feedback);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton.setEnabled(false);
                String subject=subjectText.getText().toString();
                String feedback=feedBackText.getText().toString();
                if(subject.isEmpty() || feedback.isEmpty()){
                    Toast.makeText(a, "You must fill both the fields", Toast.LENGTH_SHORT).show();
                }else{
                    Map<String,Object> m=new HashMap<>();
                    m.put("uid",FirebaseAuth.getInstance().getCurrentUser().getUid());
                    m.put("subject",feedback);
                    m.put("body",feedback);
                    SimpleDateFormat s=new SimpleDateFormat("dd/MM/YY hh.mm");
                    m.put("time",s.format(new Date()));
                    FirebaseDatabase.getInstance().getReference().child("feedback").push().updateChildren(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            addButton.setEnabled(true);
                            if(task.isSuccessful()){
                                Toast.makeText(a, "Feedback successfully submitted", Toast.LENGTH_SHORT).show();
                                subjectText.setText("");
                                feedBackText.setText("");
                            }else{
                                Toast.makeText(a, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        return v;
    }


}
