package com.micaiah.leaderboard.apis;

import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://docs.google.com/forms/d/e/";
    private static RetrofitClient mInstance;
    private Retrofit mRetrofit;

    private RetrofitClient(){
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient okHttpClient =
//                new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }
    public ApiManager getApi(){
        return mRetrofit.create(ApiManager.class);
    }
}
