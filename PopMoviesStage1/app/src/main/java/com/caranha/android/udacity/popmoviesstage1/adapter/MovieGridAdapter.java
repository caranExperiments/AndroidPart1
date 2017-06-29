package com.caranha.android.udacity.popmoviesstage1.adapter;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.caranha.android.udacity.popmoviesstage1.R;
import com.caranha.android.udacity.popmoviesstage1.datamodel.MovieDataModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by caranha on 6/22/17.
 */

@SuppressWarnings("DefaultFileTemplate")
public class MovieGridAdapter  extends RecyclerView.Adapter<MovieGridAdapter.MovieGridViewHolder> {

    private List<MovieDataModel> mMovieList;
    private ItemClickListener clickListener;

    /**
     * Default Constructor
     */
    public MovieGridAdapter(List<MovieDataModel> movieDataModels) {
        mMovieList = movieDataModels;
    }

    /**
     * Click listener for individual item in movie grid. MUST be implemented by calling class
     */
    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    /**
     * View holder for recycler view item
     */
    public class MovieGridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mMovieImageView;
        private Context mContext;

        public MovieGridViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            mMovieImageView = (ImageView) view.findViewById(R.id.movie_item_image);
            mContext = view.getContext();
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onClick(view, getAdapterPosition());
            }
        }
    }

    @Override
    public MovieGridViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_grid_itemview;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieGridViewHolder(view);
    }



    @Override
    public void onBindViewHolder(MovieGridViewHolder movieGridViewHolder,
                                 int position) {
        MovieDataModel movie = mMovieList.get(position);

        String moviePosterPath = movie.getPosterPath();

        Picasso.with(movieGridViewHolder.mContext)
                .load(moviePosterPath)
                .into(movieGridViewHolder.mMovieImageView);
    }

    @Override
    public int getItemCount() {
        if (mMovieList != null) {
            return mMovieList.size();
        }
        return 0;
    }

    public MovieDataModel getItem(int position) {
        if (mMovieList != null) {
            return mMovieList.get(position);
        }
        // TODO: change this?
        return null;
    }

    public List<MovieDataModel> getMovieList() {
        return mMovieList;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void setMoviesData(List<MovieDataModel> movieList) {
        if (mMovieList != null) {
            mMovieList.clear();
            mMovieList.addAll(movieList);
        } else {
            mMovieList = movieList;
        }
        notifyDataSetChanged();
    }


    public void clear() {
        if (mMovieList != null) {
            mMovieList.clear();
            notifyDataSetChanged();
        }
    }
}