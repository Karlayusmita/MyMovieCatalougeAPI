package com.example.mymoviecatalougeapi.api;

import android.net.Uri;

import com.example.mymoviecatalougeapi.BuildConfig;

import java.net.MalformedURLException;
import java.net.URL;

public class Api {
    private static final String API_KEY = BuildConfig.API_KEY;
    private static final String BASE_URL = BuildConfig.BASE_URL;
    private static final String DISCOVER = "discover";
    private static final String MOVIE = "movie";
    private static final String TV = "tv";
    private static final String API = "api_key";
    private static final String API_VALUE = "cc95b5cc8e36b6e407ca52aebbf908ec";
    private static final String LANGUAGE = "language";
    private static final String LANGUAGE_VALUE = "en-US";

    public static URL getListTvShow(){
        //https://api.themoviedb.org/3/discover/tv?api_key=cc95b5cc8e36b6e407ca52aebbf908ec&language=en-US
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(API_KEY)
                .appendPath(DISCOVER)
                .appendPath(TV)
                .appendQueryParameter(API, API_VALUE)
                .appendQueryParameter(LANGUAGE, LANGUAGE_VALUE)
                .build();

        URL url = null;
        try{
            url = new URL(uri.toString());
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;

    }

    public static URL getListMovie(){
        //https://api.themoviedb.org/3/discover/movie?api_key=cc95b5cc8e36b6e407ca52aebbf908ec&language=en-US
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(API_KEY)
                .appendPath(DISCOVER)
                .appendPath(MOVIE)
                .appendQueryParameter(API, API_VALUE)
                .appendQueryParameter(LANGUAGE, LANGUAGE_VALUE)
                .build();

        URL url = null;
        try{
            url = new URL(uri.toString());
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;

    }

    public static URL getPoster(String poster_path){
        // https://image.tmdb.org/t/p/w185/bI37vIHSH7o4IVkq37P8cfxQGMx.jpg
        poster_path = poster_path.startsWith("/")?poster_path.substring(1) : poster_path;
        Uri uri = Uri.parse("https://image.tmdb.org/t/p/").buildUpon()
                .appendPath("w185")
                .appendPath(poster_path)
                .build();

        URL url = null;
        try{
            url = new URL(uri.toString());
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;

    }
}
