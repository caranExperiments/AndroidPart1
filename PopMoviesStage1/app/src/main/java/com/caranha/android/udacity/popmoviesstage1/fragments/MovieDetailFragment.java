package com.caranha.android.udacity.popmoviesstage1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;

import com.caranha.android.udacity.popmoviesstage1.R;
import com.caranha.android.udacity.popmoviesstage1.datamodel.MovieDataModel;
import com.caranha.android.udacity.popmoviesstage1.fragments.MovieGridFragment;
import com.caranha.android.udacity.popmoviesstage1.network.Client;
import com.caranha.android.udacity.popmoviesstage1.network.ClientInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by caranha on 6/22/17.
 */


import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.caranha.android.udacity.popmoviesstage1.network.NetworkConnectivity;
import com.squareup.picasso.Picasso;

public class MovieDetailFragment extends Fragment {

    private static final String PREF_MOVIE_ITEM = "CARANHA.UDACITY.MOVIEDETAIL";
    private static final String TAG = "MovieDetailFragment";
    private LinearLayout mMovieDetailsLayout;
    private LinearLayout mProgressBarLayout;
    private LinearLayout mErrorMessageLayout;
    private MovieDataModel mMovieItem;
    private TextView mMovieTitleTextView;
    private ImageView mMovieImageView;
    private TextView mMovieSummaryTitleTextView;
    private TextView mMovieSummaryTextView;
    private TextView mMovieReleaseDateTextView;
    private TextView mAverageVoteTextView;
    private TextView mRuntimeTextView;

    /**
     * Default empty constructor
     */
    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the movie detail view
        View view = inflater.inflate(R.layout.movie_detail_fragment, container, false);

        // Get handles to our layouts
        mProgressBarLayout = (LinearLayout) view.findViewById(R.id.moviedetail_progressBar_layout);
        mMovieDetailsLayout = (LinearLayout) view.findViewById(R.id.moviedetail_layout);
        mErrorMessageLayout = (LinearLayout) view.findViewById(R.id.moviedetail_error_layout);

        Intent intent = getActivity().getIntent();
        mMovieItem = intent.getParcelableExtra(MovieGridFragment.PREF_MOVIE);

        // Make sure we have a movie object before we try to do anything with it
        if (null == mMovieItem){
            showErrorMessage();
        }else {
            // Get handles to our individual data display views
            mMovieTitleTextView = (TextView) view.findViewById(R.id.movieDetail_title_textView);
            mMovieImageView = (ImageView) view.findViewById(R.id.moviedetail_imageView);
            mMovieSummaryTitleTextView = (TextView) view.findViewById(R.id.moviedetail_summary_header_textView);
            mMovieSummaryTextView = (TextView) view.findViewById(R.id.moviedetail_summary_textView);
            mMovieReleaseDateTextView = (TextView) view.findViewById(R.id.moviedetail_release_textView);
            mAverageVoteTextView = (TextView) view.findViewById(R.id.moviedetail_averageVote_textView);
            mRuntimeTextView = (TextView) view.findViewById(R.id.moviedetail_runtime_textView);

            // Load the basic data that came in with the intent into display views
            initView();

            if(savedInstanceState != null){
                mMovieItem = savedInstanceState.getParcelable(PREF_MOVIE_ITEM);
                showMovieDetails();
            } else {
                if (NetworkConnectivity.isNetworkAvailable(getActivity())) {
                    loadMovieDetailData();
                } else {
                    showErrorMessage();
                }
            }
        }
        // Return the view we just built to the fragment host
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(mMovieItem != null){
            outState.putParcelable(PREF_MOVIE_ITEM, mMovieItem);
        }
    }

    /**
     * Render the basic movie data (title, overview, poster, release date, vote avg)
     * into the details layout
     */
    private void initView() {
        mMovieTitleTextView.setText(mMovieItem.getTitle());
        mMovieSummaryTitleTextView.setText(getActivity().getString(R.string.movie_details_summary));
        mMovieSummaryTextView.setText(mMovieItem.getOverview());
        mMovieReleaseDateTextView.setText(mMovieItem.getReleaseDate());
        mRuntimeTextView.setText(mMovieItem.getRuntime().toString() + " " + getActivity().getString(R.string.moviedetail_duration));
        String avgVote = mMovieItem.getVoteAverage() + getActivity().getString(R.string.movie_details_rating);
        mAverageVoteTextView.setText(avgVote);

        Picasso.with(mMovieImageView.getContext())
                .load(mMovieItem.getPosterPath())
                .into(mMovieImageView);
    }

    private void loadMovieDetailData() {
        if (Client.API_KEY.isEmpty()) {
            // show Error text view?
            return;
        }
        mProgressBarLayout.setVisibility(View.VISIBLE);
        ClientInterface apiService =
                Client.getClient().create(ClientInterface.class);

        Call<MovieDataModel> call = apiService.getMovieDetails(mMovieItem.getId(), Client.API_KEY);
        call.enqueue(new Callback<MovieDataModel>() {
            @Override
            public void onResponse(Call<MovieDataModel>call, Response<MovieDataModel> response) {
                mMovieItem = response.body();
                initView();
                showMovieDetails();
            }

            @Override
            public void onFailure(Call<MovieDataModel>call, Throwable t) {
                Log.e(TAG, t.toString());
                mProgressBarLayout.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void showMovieDetails(){
        mMovieDetailsLayout.setVisibility(View.VISIBLE);
        mProgressBarLayout.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        mMovieDetailsLayout.setVisibility(View.INVISIBLE);
        mProgressBarLayout.setVisibility(View.INVISIBLE);
        mErrorMessageLayout.setVisibility(View.VISIBLE);
    }
}