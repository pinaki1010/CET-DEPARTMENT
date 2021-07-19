package com.example.b2.cet_mca.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.b2.cet_mca.R;

public class PlacementViewHolder extends RecyclerView.ViewHolder {
    private TextView nameText,batchText,companyText;
    public PlacementViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText=itemView.findViewById(R.id.placement_name);
        batchText=itemView.findViewById(R.id.placement_batch);
        companyText=itemView.findViewById(R.id.placement_company);
    }
    public void setName(String name){
        nameText.setText(name);
    }
    public void setBatch(String batch){
        batchText.setText(batch);
    }
    public void setCompany(String company){
        companyText.setText(company);
    }
}
