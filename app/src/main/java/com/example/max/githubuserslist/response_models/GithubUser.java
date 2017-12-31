package com.example.max.githubuserslist.response_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Max on 31.12.2017.
 */

public class GithubUser {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
//    @SerializedName("gravatar_id")
//    @Expose
//    private String gravatarId;
//    @SerializedName("url")
//    @Expose
//    private String url;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
//    @SerializedName("followers_url")
//    @Expose
//    private String followersUrl;
//    @SerializedName("following_url")
//    @Expose
//    private String followingUrl;
//    @SerializedName("gists_url")
//    @Expose
//    private String gistsUrl;
//    @SerializedName("starred_url")
//    @Expose
//    private String starredUrl;
//    @SerializedName("subscriptions_url")
//    @Expose
//    private String subscriptionsUrl;
//    @SerializedName("organizations_url")
//    @Expose
//    private String organizationsUrl;
//    @SerializedName("repos_url")
//    @Expose
//    private String reposUrl;
//    @SerializedName("events_url")
//    @Expose
//    private String eventsUrl;
//    @SerializedName("received_events_url")
//    @Expose
//    private String receivedEventsUrl;
//    @SerializedName("type")
//    @Expose
//    private String type;
//    @SerializedName("site_admin")
//    @Expose
//    private Boolean siteAdmin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }


}
