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
import com.mamunsproject.awesome_free_pubg_uc.Fragment.Winner_Fragment;
import com.mamunsproject.awesome_free_pubg_uc.Model.winnerModel;
import com.mamunsproject.awesome_free_pubg_uc.R;

import java.util.List;


public class winnerAdapter extends RecyclerView.Adapter<winnerAdapter.winnerHolder> {



    private Context context;
    private List<winnerModel> list;

    public winnerAdapter(Context context, List<winnerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public winnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viw = LayoutInflater.from(context).inflate(R.layout.winner_sample_layout,parent,false);


        return new winnerHolder(viw);
    }

    @Override
    public void onBindViewHolder(@NonNull winnerHolder holder, int position) {

        holder.rank.setText(String.format("#%d",position+1));
        holder.name.setText(list.get(position).getCategoryName());
        Glide.with(context)
                .load(list.get(position).getCategoryImage())
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class winnerHolder extends RecyclerView.ViewHolder {

        private TextView rank,name;
        private ImageView imageView;


        public winnerHolder(@NonNull View itemView) {
            super(itemView);

            rank=itemView.findViewById(R.id.rankss);
            name=itemView.findViewById(R.id.namesample);
            imageView=itemView.findViewById(R.id.imagessample);
        }
    }
}
