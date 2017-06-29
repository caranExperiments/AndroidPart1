package com.caranha.android.udacity.popmoviesstage1.datamodel;

import android.graphics.Movie;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caranha on 6/22/17.
 */

/* The data model is obtained from: http://api.themoviedb.org/3/movie/top_rated?api_key=MY_API_KEY
{
page: 1,
total_results: 6440,
total_pages: 322,
results: [
{
vote_count: 78,
id: 374430,
video: false,
vote_average: 8.8,
title: "Black Mirror: White Christmas",
popularity: 1.628217,
poster_path: "/he609rnU3tiwBjRklKNa4n2jQSd.jpg",
original_language: "en",
original_title: "Black Mirror: White Christmas",
genre_ids: [
18,
27,
9648,
878,
53,
10770
],
backdrop_path: "/rMCew7St2vy9iV3QOPzx15sAkFJ.jpg",
adult: false,
overview: "This feature-length special consists of three interwoven stories. In a mysterious and remote snowy outpost, Matt and Potter share a Christmas meal, swapping creepy tales of their earlier lives in the outside world. Matt is a charismatic American trying to bring the reserved, secretive Potter out of his shell. But are both men who they appear to be? A woman gets thrust into a nightmarish world of 'smart' gadgetry. Plus a look at what would happen if you could 'block' people in real life.",
release_date: "2014-12-16"
}]
}
Translated using jsonViewer
*/


@SuppressWarnings("DefaultFileTemplate")
public class MovieDataModel implements Parcelable{
    private static final String POSTER_PATH = "http://image.tmdb.org/t/p/w185";

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("imdb_id")
    private String imdb_id;
    @SerializedName("revenue")
    private Double revenue;
    @SerializedName("runtime")
    private  Double runtime;
    @SerializedName("status")
    private String status;


    public MovieDataModel(String posterPath, boolean adult, String overview, String releaseDate, List<Integer> genreIds, Integer id,
                 String originalTitle, String originalLanguage, String title, String backdropPath, Double popularity,
                 Integer voteCount, Boolean video, Double voteAverage, String homepage, String imdbId, Double revenue, Double runTime, String status) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
        this.homepage = homepage;
        this.imdb_id = imdbId;
        this.revenue = revenue;
        this.runtime = runTime;
        this.status = status;
    }

    public String getPosterPath() {
        return POSTER_PATH + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        if (this.homepage != null) {
            this.homepage = homepage;
        } else {
            this.homepage = "";
        }
    }

    public String getImdbId() {
        return imdb_id;
    }

    public void setImdbId(String imdb_id) {
        if (this.imdb_id != null) {
            this.imdb_id = imdb_id;
        } else {
            this.imdb_id = "";
        }
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        if (this.revenue != null) {
            this.revenue = revenue;
        } else {
            this.revenue = 0.00;
        }
    }

    public Double getRuntime() { return runtime; }

    public void setRuntime(Double runtime) {
        if (this.runtime != null) {
            this.runtime = runtime;
        } else {
            this.runtime = 0.00;
        }
    }

    public String getMovieStatus() {
        return status;
    }

    public void setMovieStatus(String status) {
        if (this.status != null) {
            this.status = status;
        } else {
            this.status = "";
        }
    }

    public MovieDataModel(MovieDataModel movie){
        this.posterPath = movie.getPosterPath();
        this.adult = movie.isAdult();
        this.overview = movie.getOverview();
        this.releaseDate = movie.getReleaseDate();
        this.genreIds = movie.getGenreIds();
        this.id = movie.getId();
        this.originalTitle = movie.getOriginalTitle();
        this.originalLanguage = movie.getOriginalLanguage();
        this.title = movie.getTitle();
        this.backdropPath = movie.getBackdropPath();
        this.popularity = movie.getPopularity();
        this.voteCount = movie.getVoteCount();
        this.video = movie.getVideo();
        this.voteAverage = movie.getVoteAverage();
        this.homepage = movie.getHomepage();
        this.imdb_id = movie.getImdbId();
        this.revenue = movie.getRevenue();
        this.runtime = movie.getRuntime();
        this.status = movie.getMovieStatus();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeByte((byte) (this.adult ? 1 : 0));
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeList(this.genreIds);
        dest.writeInt(this.id);
        dest.writeString(this.originalTitle);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.title);
        dest.writeString(this.backdropPath);
        dest.writeDouble(this.popularity);
        dest.writeInt(this.voteCount);
        dest.writeByte((byte) (this.video ? 1 : 0));
        dest.writeDouble(this.voteAverage);

        if (this.homepage == null){
            this.homepage = "";
        }
        dest.writeString(this.homepage);

        if (this.imdb_id == null){
            this.imdb_id = "";
        }
        dest.writeString(this.getImdbId());
        if (this.revenue == null){
            this.revenue = 0.00;
        }
        dest.writeDouble(this.getRevenue());

        if (this.runtime == null){
            this.runtime = 0.00;
        }
        dest.writeDouble(this.runtime);

        if (this.status == null){
            this.status = "";
        }
        dest.writeString(this.status);
    }

    protected MovieDataModel(Parcel in) {
        this.posterPath = in.readString();
        this.adult = in.readByte() != 0;
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.genreIds = new ArrayList<Integer>();
        in.readList(this.genreIds, null);
        this.id = in.readInt();
        this.originalTitle = in.readString();
        this.originalLanguage = in.readString();
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.popularity = in.readDouble();
        this.voteCount = in.readInt();
        this.video = in.readByte() != 0;
        this.voteAverage = in.readDouble();
        this.homepage = in.readString();
        this.imdb_id = in.readString();
        this.revenue = in.readDouble();
        this.runtime = in.readDouble();
        this.status =in.readString();
    }

    public static final Creator<MovieDataModel> CREATOR = new Creator<MovieDataModel>() {
        @Override
        public MovieDataModel createFromParcel(Parcel in) {
            return new MovieDataModel(in);
        }

        @Override
        public MovieDataModel[] newArray(int size) {
            return new MovieDataModel[size];
        }
    };

}
