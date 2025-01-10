package com.example.projectinfoblast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.all_posts_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.usernameView.setText(items.get(position).getUsername());
        holder.descriptionView.setText(items.get(position).getDescription());
        holder.statusView.setText(items.get(position).getStatus());
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.profileView.setImageResource(items.get(position).getProfile());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
