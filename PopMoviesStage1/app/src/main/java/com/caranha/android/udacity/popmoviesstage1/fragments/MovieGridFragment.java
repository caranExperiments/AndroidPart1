package com.caranha.android.udacity.popmoviesstage1.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.Log;

import com.caranha.android.udacity.popmoviesstage1.MovieDetailActivity;
import com.caranha.android.udacity.popmoviesstage1.R;
import com.caranha.android.udacity.popmoviesstage1.adapter.MovieGridAdapter;
import com.caranha.android.udacity.popmoviesstage1.datamodel.MovieDataModel;
import com.caranha.android.udacity.popmoviesstage1.datamodel.MovieResponseDataModel;
import com.caranha.android.udacity.popmoviesstage1.network.Client;
import com.caranha.android.udacity.popmoviesstage1.network.ClientInterface;
import com.caranha.android.udacity.popmoviesstage1.network.NetworkConnectivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by caranha on 6/22/17.
 */

@SuppressWarnings("DefaultFileTemplate")
public class MovieGridFragment extends Fragment  implements MovieGridAdapter.ItemClickListener{
    protected static final String PREF_MOVIE = "CARANHA.UDACITY.MOVIE";
    private static final String PREF_SORT = "CARANHA.UDACITY.SORT";
    private static final String TAG = "MovieGridFragment";
    private static final String FETCH_POPULAR_MOVIE = "POPULAR";
    private static final String FETCH_TOPRATED_MOVIE = "TOPRATED";
    private TextView mErrorTextView;
    private RecyclerView mMovieListRecyclerView;
    private ProgressBar mProgressBarIndicator;
    private MovieGridAdapter mMovieGridAdapter;
    private String mSortType;
    private List<MovieDataModel> mMovieList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_grid_fragment, container, false);
        mMovieListRecyclerView = (RecyclerView) view.findViewById(R.id.movieList_recyclerview);
        mErrorTextView = (TextView) view.findViewById(R.id.movieList_errorTextView);
        mProgressBarIndicator = (ProgressBar) view.findViewById(R.id.movieList_progressBar);
        if (mSortType == null) {
            mSortType = FETCH_POPULAR_MOVIE;
        }

        if (savedInstanceState != null) {
            mSortType = savedInstanceState.getString(PREF_SORT);
            mMovieList = savedInstanceState.getParcelableArrayList(PREF_MOVIE);
            initView();
        } else{
            if (NetworkConnectivity.isNetworkAvailable(getActivity())) {
                FetchMovies(mSortType);
            } else {
                renderScreen();
            }
        }
        return view;

    }

    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        mMovieGridAdapter = new MovieGridAdapter(mMovieList);
        mMovieGridAdapter.setClickListener(this);
        mMovieListRecyclerView.setAdapter(mMovieGridAdapter);
        mMovieListRecyclerView.setLayoutManager(layoutManager);
        mMovieListRecyclerView.setHasFixedSize(true);
        renderScreen();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PREF_SORT, mSortType);
        ArrayList<MovieDataModel> movieList = new ArrayList<>(mMovieGridAdapter.getMovieList());

        if (movieList != null && mMovieGridAdapter.getItemCount() > 0) {
            outState.putParcelableArrayList(PREF_MOVIE, movieList);
        }
    }

    /* Menu related behaviour */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movie_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.menu_item_popular).setChecked(true);
        if(mSortType != null) {
            if (mSortType.equals(FETCH_TOPRATED_MOVIE)){
                menu.findItem(R.id.menu_item_top_rated).setChecked(true);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_popular:
                mSortType = FETCH_POPULAR_MOVIE;
                FetchMovies(mSortType);
                item.setChecked(true);
                return true;
            case R.id.menu_item_top_rated:
                mSortType = FETCH_TOPRATED_MOVIE;
                FetchMovies(mSortType);
                item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* Displaying the movie grid and handling errors*/

    private void displayMovieGrid(){
        mMovieListRecyclerView.setVisibility(View.VISIBLE);
        mErrorTextView.setVisibility(View.INVISIBLE);
    }

    private void displayMovieGridError(){
        mMovieListRecyclerView.setVisibility(View.INVISIBLE);
        String errorMessage = getActivity().getString(R.string.movieGrid_no_movies_found);
        if(!NetworkConnectivity.isNetworkAvailable(getActivity())){
                errorMessage = getActivity().getString(R.string.no_connection);
            }
        mErrorTextView.setText(errorMessage);
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void renderScreen(){
        if(mMovieGridAdapter != null){
            if (mMovieGridAdapter.getItemCount() != 0){
                displayMovieGrid();
            }else{
                displayMovieGridError();
            }
        }else{
            displayMovieGridError();
        }
    }

    /*
    * Actual data fetch for the movie grid based on the choices sent over
    * */
    private void FetchMovies(String fetchType) {
        if (Client.API_KEY.isEmpty()) {
            // show Error text view
            return;
        }
        mProgressBarIndicator.setVisibility(View.VISIBLE);
        ClientInterface apiService = Client.getClient().create(ClientInterface.class);
        Call<MovieResponseDataModel> call;
        if (fetchType.equals(FETCH_POPULAR_MOVIE)) {
            call = apiService.getPopularMovies(Client.API_KEY);
        } else {
            call = apiService.getTopRatedMovies(Client.API_KEY);
        }
        call.enqueue(new Callback<MovieResponseDataModel>() {
            @Override
            public void onResponse(Call<MovieResponseDataModel>call, Response<MovieResponseDataModel> response) {
                mMovieList = response.body().getResults();
                initView();
                mProgressBarIndicator.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<MovieResponseDataModel>call, Throwable t) {
                Log.e(TAG, t.toString());
                mProgressBarIndicator.setVisibility(View.INVISIBLE);
            }
        });
    }
    /* Click Handling on each movie item in the grid*/
    @Override
    public void onClick(View view, int position) {
        MovieDataModel movie = mMovieGridAdapter.getItem(position);
        Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
        intent.putExtra(PREF_MOVIE, movie);
        startActivity(intent);
    }
}
