package com.example.kunalsingh.entreprise.api.service;

/**
 * Created by kunalsingh on 11/06/17.
 */

public class ApiUtils {


    private ApiUtils(){}

    public static final String BASE_URL = "http://100.72.88.178:3000/api/v1/";

    public static HostSignUpService getHostSignUpService(){

                    return RetrofitClient.getClient(BASE_URL).create(HostSignUpService.class);

    }
    public static HostSignInService getHostSignInService(){

        return RetrofitClient.getClient(BASE_URL).create(HostSignInService.class);
    }

    public static ClientSignUpService getClientSignUpService(){

        return RetrofitClient.getClient(BASE_URL).create(ClientSignUpService.class);
    }

    public static ClientSignInService getClientSignInService(){

        return RetrofitClient.getClient(BASE_URL).create(ClientSignInService.class);

    }

    public static HostAddItemService getHostAddItemService(){

        return RetrofitClient.getClient(BASE_URL).create(HostAddItemService.class);
    }

    public static HostDeleteItemService getHostDeleteItemService(){

        return RetrofitClient.getClient(BASE_URL).create(HostDeleteItemService.class);
    }

    public static HostUpdateItemService getHostUpdateItemService(){

        return RetrofitClient.getClient(BASE_URL).create(HostUpdateItemService.class);
    }

    public static HostGetAllItemsService getHostAllItemsService(){

        return RetrofitClient.getClient(BASE_URL).create(HostGetAllItemsService.class);
    }

    public static ClientGetAllHostService getAllHostService(){
        return RetrofitClient.getClient(BASE_URL).create(ClientGetAllHostService.class);
    }
}
