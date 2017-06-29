package com.caranha.android.udacity.popmoviesstage1.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by caranha on 6/22/17.
 */

@SuppressWarnings("DefaultFileTemplate")
public class MovieResponseDataModel {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<MovieDataModel> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieDataModel> getResults() {
        return results;
    }

    public void setResults(List<MovieDataModel> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
