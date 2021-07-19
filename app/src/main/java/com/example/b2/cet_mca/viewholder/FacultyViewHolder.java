package com.example.b2.cet_mca.viewholder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.b2.cet_mca.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class FacultyViewHolder extends RecyclerView.ViewHolder {
    private Button callButton,mailButton;
    private CircularImageView dp;
    private TextView nameText,postText,subText,qualificationText;
    public FacultyViewHolder(@NonNull View itemView) {
        super(itemView);
        callButton=itemView.findViewById(R.id.faculty_call);
        mailButton=itemView.findViewById(R.id.faculty_mail);
        nameText=itemView.findViewById(R.id.faculty_name);
        postText=itemView.findViewById(R.id.faculty_designation);
        subText=itemView.findViewById(R.id.faculty_subject);
        qualificationText=itemView.findViewById(R.id.faculty_qualification);
        dp=itemView.findViewById(R.id.faculty_dp);
    }
    public void setName(String name){nameText.setText(name);}
    public void setPost(String post){postText.setText(post);}
    public void setSubject(String sub){subText.setText(sub);}
    public void setQualification(String qualification){qualificationText.setText(qualification);}
    public void setDp(String url,Context c){
        Picasso.with(c).load(url).into(dp);
    }
    public void setCall(final String number, final AppCompatActivity a){
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:+91"+number));
                a.startActivity(i);
            }
        });
    }
    public void sendMail(final String mail, final AppCompatActivity a){
        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
                i.setType("message/rfc822");
                a.startActivity(Intent.createChooser(i,"Send mail"));
            }
        });

    }
}
