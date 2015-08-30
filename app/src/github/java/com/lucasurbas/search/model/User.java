package com.lucasurbas.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Lucas on 30/08/15.
 */
public class User extends RealmObject {

    @Expose
    @SerializedName("id")
    @PrimaryKey
    private long id;

    @Expose
    @SerializedName("login")
    private String login;

    @Expose
    @SerializedName("avatar_url")
    private String avatarUrl;

    @Expose
    @SerializedName("html_url")
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
