package com.example.b2.cet_mca.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageView;

import com.example.b2.cet_mca.R;
import com.squareup.picasso.Picasso;

public class GalleryDialog extends Dialog {
    public GalleryDialog( Context context,String url) {
        super(context);
        setContentView(R.layout.dialog_galley);
        ImageView img=findViewById(R.id.gallery_image);
        Picasso.with(context).load(url).into(img);

    }
}
