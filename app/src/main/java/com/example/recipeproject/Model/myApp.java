package com.example.recipeproject.Model;

import android.app.Application;

public class myApp extends Application {

    public NetworkingService getNetworkingService(){
        return networkingService;
    }

    private NetworkingService networkingService = new NetworkingService();
    private JSONService jsonService = new JSONService();

    public JSONService getJSONService(){
        return jsonService;
    }
}
