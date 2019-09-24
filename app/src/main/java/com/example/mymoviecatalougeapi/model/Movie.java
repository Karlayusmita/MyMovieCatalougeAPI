package com.example.mymoviecatalougeapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Parcelable {
    private String poster;
    private String title;
    private String original_language;
    private String release_date;
    private String overview;
    private int vote_average;

    public Movie(JSONObject object){
        try{
            poster = object.getString("poster_path");
            title = object.getString("title");
            original_language = object.getString("original_language");
            release_date = object.getString("release_date");
            overview = object.getString("overview");
            vote_average = object.getInt("vote_average");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    protected Movie(Parcel in) {
        poster = in.readString();
        title = in.readString();
        original_language = in.readString();
        release_date = in.readString();
        overview = in.readString();
        vote_average = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster);
        dest.writeString(title);
        dest.writeString(original_language);
        dest.writeString(release_date);
        dest.writeString(overview);
        dest.writeInt(vote_average);
    }
}
