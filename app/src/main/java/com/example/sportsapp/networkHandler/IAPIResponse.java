package com.example.sportsapp.networkHandler;

import com.example.sportsapp.utils.ApiErrorModel;

public interface IAPIResponse {
    void onSuccess(Object object);
    void onError(ApiErrorModel errorModel);
    void onNoInternet();
}
