package com.app.smartganado.smart_ganado.remote;

public class APIUtils {

    private APIUtils() {
    }

    public static final String BASE_URL = "http://192.168.137.1:8080/AppServer/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}

