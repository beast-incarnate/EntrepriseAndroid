package com.example.kunalsingh.entreprise.api.service;

import com.example.kunalsingh.entreprise.models.Result;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by kunalsingh on 12/06/17.
 */

public interface HostUpdateItemService {

    @POST("host_update_item")
    @FormUrlEncoded
    Observable<Result> updateItemHost(@Field("access_token") String accessToken ,
                                      @Field("id") int id ,
                                      @Field("name") String name ,
                                      @Field("price") int price ,
                                      @Field("amount") int amount);

}
