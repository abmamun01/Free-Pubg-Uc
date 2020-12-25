package com.mamunsproject.awesome_free_pubg_uc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mamunsproject.awesome_free_pubg_uc.Model.User;
import com.mamunsproject.awesome_free_pubg_uc.R;

import java.util.ArrayList;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.LeaderBoardHolder> {

   private Context context;
   private ArrayList<User> users;

    public LeaderBoardAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public LeaderBoardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.leaderboards_sample,parent,false);
        return new LeaderBoardHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull LeaderBoardHolder holder, int position) {

        User user=users.get(position);

        holder.name.setText(user.getName());
        holder.coins.setText(String.valueOf(user.getCoins()));
        holder.index.setText(String.format("#%d",position+1));


        Glide.with(context).load(user.getProfile()).into(holder.imageView7);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class LeaderBoardHolder extends RecyclerView.ViewHolder {

        TextView index,name,coins;
        ImageView imageView7;
        public LeaderBoardHolder(@NonNull View itemView) {
            super(itemView);

            index=itemView.findViewById(R.id.index);
            name=itemView.findViewById(R.id.name);
            coins=itemView.findViewById(R.id.coins);
            imageView7=itemView.findViewById(R.id.imageView7);
        }
    }
}
