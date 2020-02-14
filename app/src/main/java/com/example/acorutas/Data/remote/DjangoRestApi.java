package com.example.acorutas.Data.remote;

import com.example.acorutas.Data.models.PuntoMapa;
import com.example.acorutas.Data.models.RegistroUser;
import com.example.acorutas.Data.models.Ruta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface DjangoRestApi {

    @GET("/aco/info")
    Call<List<Ruta>> getRutas();

    @POST("/aco/create/")
    Call<Ruta> crearRuta(@Body Ruta rutaPost);

    @POST("/post/create/")
    Call<PuntoMapa> crearMarcador(@Body PuntoMapa puntoMapa);

    @POST("/aco/user_l/")
    Call<RegistroUser> verificarUsuario(@Body RegistroUser loginU);

    @POST("/aco/user_c/")
    Call<RegistroUser> crearUsuario(@Body RegistroUser registroU);

}