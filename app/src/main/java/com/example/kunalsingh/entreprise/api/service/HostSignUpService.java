package com.example.kunalsingh.entreprise.api.service;

import com.example.kunalsingh.entreprise.models.Result;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kunalsingh on 11/06/17.
 */

public interface HostSignUpService {


    @POST("host_sign_up")
    @FormUrlEncoded
    Observable<Result> signUpHost(@Field("name") String name ,
                                    @Field("email") String email ,
                                    @Field("address") String address ,
                                    @Field("contact") String contact ,
                                    @Field("password") String password);

}
