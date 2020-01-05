package com.samples.chopchop.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samples.chopchop.Interface.ItemClickListerner;
import com.samples.chopchop.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView food_name;
    public ImageView food_image;

    private ItemClickListerner itemClickListerner;

    public void setItemClickListerner(ItemClickListerner itemClickListerner) {
        this.itemClickListerner = itemClickListerner;
    }

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        food_name = (TextView)itemView.findViewById(R.id.food_name);
        food_image = (ImageView)itemView.findViewById(R.id.food_img);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListerner.onClick(v,getAdapterPosition(),false);

    }
}
