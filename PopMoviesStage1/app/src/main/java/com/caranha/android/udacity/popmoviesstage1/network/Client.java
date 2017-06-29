package com.caranha.android.udacity.popmoviesstage1.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by caranha on 6/22/17.
 *
 * To send network requests to an API, we need to use the Retrofit Builder class and specify the base URL for the service.
    Here BASE_URL – it is basic URL of our API: http://api.themoviedb.org/3/
 */

public class Client {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String API_KEY = "DUMMY_KEY";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
