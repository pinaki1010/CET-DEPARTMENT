package com.example.b2.cet_mca;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private TextView nameText,emailText,phoneText,rollText,ageText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth=FirebaseAuth.getInstance();
        nameText=findViewById(R.id.profile_name);
        emailText=findViewById(R.id.profile_email);
        phoneText=findViewById(R.id.profile_phone);
        rollText=findViewById(R.id.profile_roll);
        ageText=findViewById(R.id.profile_age);
        userRef=FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nameText.setText(dataSnapshot.child("name").getValue().toString());
                emailText.setText(dataSnapshot.child("email").getValue().toString());
                phoneText.setText(dataSnapshot.child("phone").getValue().toString());
                rollText.setText(dataSnapshot.child("roll").getValue().toString());
                ageText.setText(dataSnapshot.child("age").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


}
    public void logOut(View view) {
        mAuth.signOut();
        startActivity(new Intent(this,LoginActivity.class));
        finishAffinity();
        finish();

    }

}

