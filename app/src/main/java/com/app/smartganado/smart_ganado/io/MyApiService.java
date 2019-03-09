package com.app.smartganado.io;

import com.app.smartganado.model.Estate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApiService {

    @GET("estates")
    Call<String> getEstates();



    /** @FormUrlEncoded
     @POST("upload/photo") Call<SimpleResponse> postPhoto(
     @Field("image") String base64,
     @Field("extension") String extension,
     @Field("user_id") String user_id
     );

     @GET("login") Call<LoginResponse> getLogin(
     @Query("username") String username,
     @Query("password") String password
     );

     @FormUrlEncoded
     @POST("product") Call<SimpleResponse> postNewProduct(
     @Field("code") String code,
     @Field("name") String name,
     @Field("description") String description
     );*/
}
