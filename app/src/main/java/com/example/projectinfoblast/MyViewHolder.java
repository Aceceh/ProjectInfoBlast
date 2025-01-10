package com.example.projectinfoblast;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView , profileView;
    TextView usernameView,descriptionView,statusView;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        usernameView = itemView.findViewById(R.id.username);
        descriptionView = itemView.findViewById(R.id.description);
        profileView = itemView.findViewById(R.id.post_profile_image);
        statusView = itemView.findViewById(R.id.status);

    }
}
