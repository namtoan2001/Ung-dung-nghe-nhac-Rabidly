package com.example.ung_dung_nghe_nhac_rabidly.Service_API;

public class APIService {
    private static String base_url = "https://music4b.000webhostapp.com/Server/";
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
