package com.example.b2.cet_mca;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Signup_Activity extends AppCompatActivity {
                private EditText nametext, emailtext, rollnotext, addresstext, phonetext, passtext, agetext;
                private RadioGroup genderSelect;
                private FirebaseAuth mAuth;
                private DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);
        emailtext = findViewById(R.id.signup_input_email);
        nametext = findViewById(R.id.signup_input_name);
        rollnotext = findViewById(R.id.signup_input_rollno);
        addresstext = findViewById(R.id.signup_input_address);
        phonetext = findViewById(R.id.signup_input_phone);
        passtext = findViewById(R.id.signup_input_password);
        agetext = findViewById(R.id.signup_input_age);
        genderSelect = findViewById(R.id.gender_radio_group);
        mAuth=FirebaseAuth.getInstance();
        userRef=FirebaseDatabase.getInstance().getReference().child("users");
    }

    public void signup(View view) {
        final String email = emailtext.getText().toString().trim();
        final String pass = passtext.getText().toString().trim();
        final String roll = rollnotext.getText().toString().trim();
        final String address = addresstext.getText().toString().trim();
        final String phone = phonetext.getText().toString().trim();
        final String name = nametext.getText().toString().trim();
        final String age = agetext.getText().toString().trim();
        RadioButton rb = findViewById(genderSelect.getCheckedRadioButtonId());
        final String gender = rb.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(roll) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(gender)) {
            Toast.makeText(this, "You must fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            final ProgressDialog p = new ProgressDialog(this);
            p.setMessage("Please wait while we are Signing you in");
            p.setTitle("Please wait");
            p.setCancelable(false);
            p.setCanceledOnTouchOutside(false);
            p.show();
            mAuth.createUserWithEmailAndPassword(email, pass).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    p.dismiss();
                    Toast.makeText(Signup_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
              String uid=authResult.getUser().getUid();
                    Map<String,Object> m=new HashMap<>();
                    m.put("name",name);
                    m.put("email",email);
                    m.put("phone",phone);
                    m.put("roll",roll);
                    m.put("address",address);
                    m.put("age",age);
                    m.put("gender",gender);
                    userRef.child(uid).updateChildren(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                       p.dismiss();
                            Toast.makeText(Signup_Activity.this, "Account Sucessfully created", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            p.dismiss();
                            Toast.makeText(Signup_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });




                }
            });
        }

    }
}
