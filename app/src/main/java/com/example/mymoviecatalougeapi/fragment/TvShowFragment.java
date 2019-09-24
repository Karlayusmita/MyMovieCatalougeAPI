package com.example.mymoviecatalougeapi.fragment;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mymoviecatalougeapi.R;
import com.example.mymoviecatalougeapi.adapter.ListTvShowAdapter;
import com.example.mymoviecatalougeapi.api.Api;
import com.example.mymoviecatalougeapi.model.TvShow;
import com.example.mymoviecatalougeapi.network.Network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<TvShow> arrayList = new ArrayList<>();
    private ListTvShowAdapter listTVShowAdapter;


    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_tv_show);
        progressBar = view.findViewById(R.id.progress_bar_tv_show);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showRecyclerList();
        if (savedInstanceState == null) {
            loadData();
        } else {
            arrayList = savedInstanceState.getParcelableArrayList("list");
            if (arrayList!=null){
                listTVShowAdapter.setTvArrayList(arrayList);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list", arrayList);
    }

    private void loadData() {
        URL url = Api.getListTvShow();
        Log.e("url", url.toString());
        new TVShowAsyncTask().execute(url);
    }

    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listTVShowAdapter = new ListTvShowAdapter(getActivity());
        listTVShowAdapter.setTvArrayList(arrayList);
        recyclerView.setAdapter(listTVShowAdapter);
    }

    private class TVShowAsyncTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String result = null;
            try {
                result = Network.getFromNetwork(url);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            Log.e("result", result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i=0; i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    TvShow tvShow = new TvShow(object);
                    arrayList.add(tvShow);
                }
            }
            catch (JSONException e){
                e.printStackTrace();
            }
            listTVShowAdapter.setTvArrayList(arrayList);
        }

    }

}
