package com.example.max.githubuserslist.rest;

import android.util.Base64;

import com.example.max.githubuserslist.utils.MessageEvent;
import com.example.max.githubuserslist.utils.Messages;
import com.example.max.githubuserslist.utils.Settings;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Max on 31.12.2017.
 */

public class ApiMethods {

    public static GitHubApi createGitHubApi() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Settings.GITHUB_USERS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(GitHubApi.class);
    }


    public static void makeRequest(Call call, final byte message) {
        call.enqueue(new Callback<Object>() {
                         @Override
                         public void onResponse(Call<Object> call, Response<Object> response) {
                             if (response.isSuccessful()) {
                                 Object serverResponse = response.body();
                                 EventBus.getDefault().post(new MessageEvent(message, serverResponse));
                             }
                             else{
                                 EventBus.getDefault().post(new MessageEvent(Messages.RESPONSE_SERVER_ERROR,null));
                             }
                         }

                         @Override
                         public void onFailure(Call<Object> call, Throwable t) {
                             EventBus.getDefault().post(new MessageEvent(Messages.RESPONSE_SERVER_ERROR, null));
                         }
                     }
        );
    }
}
