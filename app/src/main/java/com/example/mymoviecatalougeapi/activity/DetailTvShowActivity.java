package com.example.mymoviecatalougeapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mymoviecatalougeapi.R;
import com.example.mymoviecatalougeapi.model.TvShow;

import java.net.URL;

import static com.example.mymoviecatalougeapi.api.Api.getPoster;

public class DetailTvShowActivity extends AppCompatActivity {
    ImageView poster;
    TextView name;
    TextView original_language;
    TextView first_air_date;
    TextView overview;
    TextView vote_count;
    TvShow tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        poster = findViewById(R.id.detail_poster);
        name = findViewById(R.id.detail_name);
        original_language = findViewById(R.id.detail_original_language);
        first_air_date = findViewById(R.id.detail_first_air_date);
        overview = findViewById(R.id.detail_overview);
        vote_count = findViewById(R.id.detail_vote_count);

        tvShow = getIntent().getParcelableExtra("TV_SHOW_DATA");

        URL posterURL = getPoster(tvShow.getPoster());

        Glide.with(this)
                .load(posterURL)
                .apply(new RequestOptions().override(96,144))
                .into(poster);
        name.setText(tvShow.getName());
        original_language.setText(tvShow.getOriginal_language());
        first_air_date.setText(tvShow.getFirst_air_date());
        overview.setText(tvShow.getOverview());
        vote_count.setText(String.valueOf(tvShow.getVote_count()));
    }
}
