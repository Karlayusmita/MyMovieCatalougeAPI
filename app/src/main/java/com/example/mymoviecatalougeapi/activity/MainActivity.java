package com.example.mymoviecatalougeapi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.mymoviecatalougeapi.R;
import com.example.mymoviecatalougeapi.fragment.MovieFragment;
import com.example.mymoviecatalougeapi.fragment.TvShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Fragment fragment;
    private final String TAG_FRAGMENT = "fragment";

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationView
            = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.navigation_movie:
                fragment = new MovieFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_tv_show:
                fragment = new TvShowFragment();
                loadFragment(fragment);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView1 = findViewById(R.id.bottom_navigation);
        bottomNavigationView1.setOnNavigationItemSelectedListener(bottomNavigationView);
        if (savedInstanceState == null) {
            bottomNavigationView1.setSelectedItemId(R.id.navigation_movie);
            fragment = new MovieFragment();
        } else {
            fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
            loadFragment(fragment);
        }
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_layout, fragment, TAG_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.localization_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_change_language){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
