package com.example.mymoviecatalougeapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymoviecatalougeapi.R;
import com.example.mymoviecatalougeapi.model.Movie;

import java.net.URL;

import static com.example.mymoviecatalougeapi.api.Api.getPoster;

public class DetailMovieActivity extends AppCompatActivity {
    ImageView poster;
    TextView title;
    TextView original_language;
    TextView release_date;
    TextView overview;
    TextView vote_average;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        poster = findViewById(R.id.detail_poster);
        title = findViewById(R.id.detail_title);
        original_language = findViewById(R.id.detail_original_language);
        release_date = findViewById(R.id.detail_release_date);
        overview = findViewById(R.id.detail_overview);
        vote_average = findViewById(R.id.detail_vote_average);

        movie = getIntent().getParcelableExtra("MOVIE_DATA");
        URL posterURL = getPoster(movie.getPoster());

        Glide.with(this)
                .load(posterURL)
                .apply(new RequestOptions().override(96,144))
                .into(poster);
        title.setText(movie.getTitle());
        original_language.setText(movie.getOriginal_language());
        release_date.setText(movie.getRelease_date());
        overview.setText(movie.getOverview());
        vote_average.setText(String.valueOf(movie.getVote_average()));
    }
}
