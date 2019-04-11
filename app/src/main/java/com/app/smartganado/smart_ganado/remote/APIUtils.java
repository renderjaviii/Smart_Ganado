package com.app.smartganado.smart_ganado.remote;

public class APIUtils {

    private APIUtils() {
    }

    private static final String BASE_URL = "http://192.168.1.12:8080/AppGanadoServer/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}

