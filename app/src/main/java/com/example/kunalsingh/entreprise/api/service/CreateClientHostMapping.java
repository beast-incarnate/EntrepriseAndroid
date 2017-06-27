package com.example.kunalsingh.entreprise.api.service;

import com.example.kunalsingh.entreprise.models.Result;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by kunalsingh on 27/06/17.
 */

public interface CreateClientHostMapping {

    @POST("create_host_client_mapping")
    @FormUrlEncoded
    Observable<Result> createClientHostMapping(@Field("access_token") String access_token ,
                                               @Field("client_id") int clientId,
                                               @Field("item_id") int itemId,
                                               @Field("quantity") int quantity,
                                               @Field("status") int status);
}
