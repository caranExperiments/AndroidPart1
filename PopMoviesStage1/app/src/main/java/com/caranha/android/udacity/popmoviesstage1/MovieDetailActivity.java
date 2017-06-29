package com.caranha.android.udacity.popmoviesstage1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        setContentView(R.layout.movie_detail_activity);
    }
}