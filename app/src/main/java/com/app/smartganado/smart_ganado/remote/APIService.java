package com.app.smartganado.smart_ganado.remote;

import com.app.smartganado.smart_ganado.model.vo.Breed;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.CattleStoryBook;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.Event;
import com.app.smartganado.smart_ganado.model.vo.Gender;
import com.app.smartganado.smart_ganado.model.vo.Lot;
import com.app.smartganado.smart_ganado.model.vo.Purpose;
import com.app.smartganado.smart_ganado.model.vo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIService {


    //Get entities
    @POST("BBDD/")
    Call<List<Breed>> getBreed(@Header("action") String actionName, @Header("entity") String entityName);//Retorna una lista de estates

    @POST("BBDD/")
    Call<List<Cattle>> getCattle(@Header("action") String actionName, @Header("entity") String entityName, @Header("phone") Integer phoneUser);//Retorna una lista de cattles

    @POST("BBDD/")
    Call<List<CattleStoryBook>> getCattleStoryBook(@Header("action") String actionName, @Header("entity") String entityName, @Header("phone") Integer phoneUser);

    @POST("BBDD/")
    Call<List<Estate>> getEstate(@Header("action") String actionName, @Header("entity") String entityName, @Header("phone") Integer phoneUser);

    @POST("BBDD/")
    Call<List<Event>> getEvent(@Header("action") String actionName, @Header("entity") String entityName, @Header("phone") Integer phoneUser);

    @POST("BBDD/")
    Call<List<Gender>> getGender(@Header("action") String actionName, @Header("entity") String entityName);

    @POST("BBDD/")
    Call<List<Lot>> getLot(@Header("action") String actionName, @Header("entity") String entityName);

    @POST("BBDD/")
    Call<List<Purpose>> getPurpose(@Header("action") String actionName, @Header("entity") String entityNamer);

    @POST("BBDD/")
    Call<List<User>> getUser(@Header("action") String actionName, @Header("entity") String entityName, @Header("phone") Integer phoneUser);


    //Insert entities
    @POST("BBDD/")
    Call<Boolean> insertCattle(@Header("action") String actionName, @Header("entity") String entityName, @Header("phone") Integer phoneUser, @Body Cattle cattle); //Retorna true si se inserto correctamante

    @POST("BBDD/")
    Call<Boolean> insertEstate(@Header("action") String actionName, @Header("entity") String entityName, @Header("phone") Integer phoneUser, @Body Estate estate); //Retorna true si se inserto correctamante


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
