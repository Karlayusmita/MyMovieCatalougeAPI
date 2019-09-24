package com.example.mymoviecatalougeapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymoviecatalougeapi.R;
import com.example.mymoviecatalougeapi.activity.DetailTvShowActivity;
import com.example.mymoviecatalougeapi.model.TvShow;

import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.mymoviecatalougeapi.api.Api.getPoster;

public class ListTvShowAdapter extends RecyclerView.Adapter<ListTvShowAdapter.ViewHolder> {
    private ArrayList<TvShow> tvArrayList;
    private Context context;

    public ListTvShowAdapter(Context context) {
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    private ArrayList<TvShow> getTvArrayList() {
        return tvArrayList;
    }

    public void setTvArrayList(ArrayList<TvShow> items) {
        this.tvArrayList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        TvShow tv = tvArrayList.get(position);

        URL poster = getPoster(tv.getPoster());

        Glide.with(holder.itemView.getContext())
                .load(poster)
                .apply(new RequestOptions().override(96,144))
                .into(holder.poster);
        holder.name.setText(tv.getName());
        holder.first_air_date.setText(tv.getFirst_air_date());
        holder.vote_count.setText(String.valueOf(tv.getVote_count()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DetailTvShowActivity.class);
            intent.putExtra("TV_SHOW_DATA", getTvArrayList().get(position));
            getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tvArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView name;
        TextView first_air_date;
        TextView vote_count;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.item_poster);
            name = itemView.findViewById(R.id.item_title);
            first_air_date = itemView.findViewById(R.id.item_release_date);
            vote_count = itemView.findViewById(R.id.item_vote);
        }
    }
}
