package com.example.kunalsingh.entreprise.api.service;

import com.example.kunalsingh.entreprise.models.Result;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by kunalsingh on 12/06/17.
 */

public interface ClientSignUpService {

    @POST("client_sign_up")
    @FormUrlEncoded
    Observable<Result> signUpClient(@Field("name") String name,
                                    @Field("email") String email ,
                                    @Field("password") String password ,
                                    @Field("contact") String contact);

}
