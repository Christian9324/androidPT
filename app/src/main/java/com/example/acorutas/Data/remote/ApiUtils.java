package com.example.acorutas.Data.remote;

public class ApiUtils {

    public static final String BASE_URL = "http://192.168.10.46:8080";

    public static DjangoRestApi getAPIService() {

        //llamas a la funcion de retrofitClient
        return RetrofitClient.getClient(BASE_URL).create(DjangoRestApi.class);
    }
}
