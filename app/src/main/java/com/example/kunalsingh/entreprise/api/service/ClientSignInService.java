package com.example.kunalsingh.entreprise.api.service;

import com.example.kunalsingh.entreprise.models.Result;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kunalsingh on 12/06/17.
 */

public interface ClientSignInService {


    @GET("client_sign_in")
    Observable<Result> signInCLient(@Query("email") String email ,
                                    @Query("password") String password);

}
