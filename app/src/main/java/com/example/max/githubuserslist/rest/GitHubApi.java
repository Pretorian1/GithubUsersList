package com.example.max.githubuserslist.rest;

import com.example.max.githubuserslist.response_models.GithubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Max on 31.12.2017.
 */

public interface GitHubApi {

    String USERS_URI ="users";

    @GET(USERS_URI)
    Call<List<GithubUser>> getGitHubUsers(@Query("since") int since);

}
