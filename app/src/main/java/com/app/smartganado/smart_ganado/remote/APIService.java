package com.app.smartganado.smart_ganado.remote;

import com.app.smartganado.smart_ganado.model.vo.Breed;
import com.app.smartganado.smart_ganado.model.vo.Cattle;
import com.app.smartganado.smart_ganado.model.vo.Estate;
import com.app.smartganado.smart_ganado.model.vo.Lot;
import com.app.smartganado.smart_ganado.model.vo.Purpose;
import com.app.smartganado.smart_ganado.model.vo.Tank;
import com.app.smartganado.smart_ganado.model.vo.UserApp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIService {


    //UserApp
    @POST("login")
    Call<UserApp> getLogin(@Body UserApp user);

    @POST("userApp")
    Call<Boolean> insertUser(@Header("action") String actionName, @Body UserApp user); //action = insert -> Return true if the insertion is successful


    //Estate
    @POST("estate")
    Call<Boolean> insertEstate(@Header("action") String actionName, @Body Estate estate); //action = insert

    @GET("estate")
    Call<List<Estate>> getEstate(@Header("action") String actionName, @Header("phone") Long phone);

    @GET("estate")
    Call<Boolean> deleteEstate(@Header("action") String actionName, @Header("id") String idEstate);//action = delete

    //Cattle
    @POST("cattle")
    Call<Boolean> insertCattle(@Header("action") String actionName, @Body Cattle cattle); //action = insert

    @GET("cattle")
    Call<List<Cattle>> getCattle(@Header("action") String actionName, @Header("phone") Long phone);//action == getAll

    @GET("cattle")
    Call<List<Cattle>> getCattle(@Header("action") String actionName, @Header("id_cattle") int idCattle);

    @GET("cattle")
    Call<Boolean> deleteCattle(@Header("action") String actionName, @Header("id") String idCattle);//action = delete

    //Lot
    @GET("lot")
    Call<List<Lot>> getLot();//Return lot list

    //Breed
    @GET("breed")
    Call<List<Breed>> getBreed();//Return breed list

    //Purpose
    @GET("purpose")
    Call<List<Purpose>> getPurpose();//Return purpose list


    //Tank
    @POST("tank")
    Call<Boolean> insertTank(@Header("action") String actionName, @Body Tank tank); //action = insert

    @GET("tank")
    Call<List<Tank>> getTank(@Header("action") String actionName, @Header("phone") Long phone);//action == getAll

    @GET("cattle")
    Call<Boolean> deleteTank(@Header("action") String actionName, @Header("id") String idTank);//action = delete

}
