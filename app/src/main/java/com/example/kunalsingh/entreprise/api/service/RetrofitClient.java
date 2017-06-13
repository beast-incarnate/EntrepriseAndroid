package com.example.kunalsingh.entreprise.api.service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kunalsingh on 11/06/17.
 */

public class RetrofitClient {

        private static Retrofit retrofit =  null;

        public static Retrofit getClient(String baseUrl){

                if(retrofit==null){

                        retrofit = new Retrofit.Builder()
                                .baseUrl(baseUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .build();

                }
                return retrofit;


        }


}
