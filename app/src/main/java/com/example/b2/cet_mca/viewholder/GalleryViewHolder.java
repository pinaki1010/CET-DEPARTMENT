package com.example.b2.cet_mca.viewholder;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.b2.cet_mca.Dialog.GalleryDialog;
import com.example.b2.cet_mca.R;
import com.squareup.picasso.Picasso;

public class GalleryViewHolder extends RecyclerView.ViewHolder {
    private ImageView img;
    public GalleryViewHolder(@NonNull View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.gallery_image);
    }
    public void setImage(final String url, final Context c){
        Picasso.with(c).load(url).into(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GalleryDialog(c,url).show();
            }
        });
    }
}
