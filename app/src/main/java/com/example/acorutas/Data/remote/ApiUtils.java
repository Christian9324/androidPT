package com.example.acorutas.Data.remote;

public class ApiUtils {

    //IP de la Vaio
    //public static final String BASE_URL = "http://192.168.10.46:8080";

    //Ip de la raspberry pi 4
    public static final String BASE_URL = "http://192.168.100.11:1337";

    public static DjangoRestApi getAPIService() {

        //llamas a la funcion de retrofitClient
        return RetrofitClient.getClient(BASE_URL).create(DjangoRestApi.class);
    }
}
