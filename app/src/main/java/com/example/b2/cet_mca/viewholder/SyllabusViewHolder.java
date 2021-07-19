package com.example.b2.cet_mca.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.b2.cet_mca.R;

public class SyllabusViewHolder extends RecyclerView.ViewHolder {
    private Button downloadbutton;
    private TextView nameText;
    public SyllabusViewHolder(View itemView)
    {
        super(itemView);
        nameText=itemView.findViewById(R.id.syllabus_name);
        downloadbutton=itemView.findViewById(R.id.syllabus_download);
    }
    public void setName(String name){nameText.setText(name);}
    public void setDownload(final AppCompatActivity a, final String url){
        downloadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.parse(url),"application/pdf");
                a.startActivity(i);
            }
        });
    }


}
