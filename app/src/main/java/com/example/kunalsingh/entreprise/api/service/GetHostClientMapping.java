package com.example.kunalsingh.entreprise.api.service;

import com.example.kunalsingh.entreprise.models.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kunalsingh on 27/06/17.
 */

public interface GetHostClientMapping {

    @GET("all_mappings_host")
    Observable<Result> getHostClientMapping(@Query("access_token") String accessToken);
}
