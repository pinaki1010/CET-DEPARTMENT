package com.example.b2.cet_mca.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b2.cet_mca.R;

public class NoticeViewHolder extends RecyclerView.ViewHolder {
    private View v;
    private TextView titleText,bodyText,timeText;
    public NoticeViewHolder(@NonNull View itemView) {
        super(itemView);
        v=itemView;
        titleText=v.findViewById(R.id.notice_title);
        bodyText=v.findViewById(R.id.notice_body);
        timeText=v.findViewById(R.id.notice_time);
    }
    public void setTitle(String title){
        titleText.setText(title);
    }
    public void setBody(String body){
        bodyText.setText(body);
    }
    public void setTime(String time){
        timeText.setText(time);
    }
    public void setClick(final AppCompatActivity a, final String url){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(url.equalsIgnoreCase("na"))
                    Toast.makeText(a, "No Link Provided", Toast.LENGTH_SHORT).show();
                else {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    a.startActivity(i);
                }
            }
        });
    }
}
