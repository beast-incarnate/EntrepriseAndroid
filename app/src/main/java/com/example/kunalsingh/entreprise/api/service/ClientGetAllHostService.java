package com.example.kunalsingh.entreprise.api.service;

import com.example.kunalsingh.entreprise.models.Result;

import io.reactivex.Observable;
import retrofit2.http.Query;
import retrofit2.http.GET;
/**
 * Created by kunalsingh on 24/06/17.
 */

public interface ClientGetAllHostService {
    @GET("get_all_host")
    Observable<Result> getAllHosts(@Query("access_token") String access_token);
}
