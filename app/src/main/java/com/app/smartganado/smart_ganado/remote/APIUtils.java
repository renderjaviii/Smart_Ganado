package com.app.smartganado.smart_ganado.remote;

public class APIUtils {

    private APIUtils() {
    }

    private static final String BASE_URL = "http://ec2-184-73-37-201.compute-1.amazonaws.com:8080/AppGanadoServer/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}

