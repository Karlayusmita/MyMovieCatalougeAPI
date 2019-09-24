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
import com.example.mymoviecatalougeapi.activity.DetailMovieActivity;
import com.example.mymoviecatalougeapi.model.Movie;

import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.mymoviecatalougeapi.api.Api.getPoster;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder> {
    private ArrayList<Movie> movieArrayList;
    private Context context;

    public ListMovieAdapter(Context context) {
        this.context = context;
    }

    private Context getContext() {
        return context;
    }

    private ArrayList<Movie> getMovieArrayList() {
        return movieArrayList;
    }

    public void setMovieArrayList(ArrayList<Movie> items) {
        this.movieArrayList = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        return new ListMovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieAdapter.ViewHolder holder, final int position) {
        Movie movies = movieArrayList.get(position);

        URL poster = getPoster(movies.getPoster());

        Glide.with(holder.itemView.getContext())
                .load(poster)
                .apply(new RequestOptions().override(96,144))
                .into(holder.poster);
        holder.title.setText(movies.getTitle());
        holder.release_date.setText(movies.getRelease_date());
        holder.vote_average.setText(String.valueOf(movies.getVote_average()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DetailMovieActivity.class);
            intent.putExtra("MOVIE_DATA", getMovieArrayList().get(position));
            getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title;
        TextView release_date;
        TextView vote_average;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.item_poster);
            title = itemView.findViewById(R.id.item_title);
            release_date = itemView.findViewById(R.id.item_release_date);
            vote_average= itemView.findViewById(R.id.item_vote);
        }
    }
}

