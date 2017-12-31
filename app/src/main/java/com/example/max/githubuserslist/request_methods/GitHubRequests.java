package com.example.max.githubuserslist.request_methods;

import com.example.max.githubuserslist.rest.ApiMethods;
import com.example.max.githubuserslist.rest.GitHubApi;
import com.example.max.githubuserslist.utils.Messages;
import com.example.max.githubuserslist.utils.Settings;

import retrofit2.Call;

/**
 * Created by Max on 31.12.2017.
 */

public class GitHubRequests {

    public static void signIn(int page){
        GitHubApi service =  ApiMethods.createGitHubApi();
        Call call = service.getGitHubUsers(page, Settings.perPage);
        ApiMethods.makeRequest(call, Messages.RESPONSE_GITHUB_USERS);
    }
}
