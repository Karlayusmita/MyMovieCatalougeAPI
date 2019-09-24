package com.example.mymoviecatalougeapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class TvShow implements Parcelable {
    private String poster;
    private String name;
    private String original_language;
    private String first_air_date;
    private String overview;
    private int vote_count;

    public TvShow(JSONObject object) {
        try{
            poster = object.getString("poster_path");
            name = object.getString("name");
            original_language = object.getString("original_language");
            first_air_date = object.getString("first_air_date");
            overview = object.getString("overview");
            vote_count = object.getInt("vote_count");

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private TvShow(Parcel in) {
        poster = in.readString();
        name = in.readString();
        original_language = in.readString();
        first_air_date = in.readString();
        overview = in.readString();
        vote_count = in.readInt();
    }

    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster);
        dest.writeString(name);
        dest.writeString(original_language);
        dest.writeString(first_air_date);
        dest.writeString(overview);
        dest.writeInt(vote_count);
    }
}
