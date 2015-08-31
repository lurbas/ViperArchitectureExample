package com.lucasurbas.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Lucas on 29/08/15.
 */
public class Photo {

    @Expose
    @SerializedName("id")
    private long id;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("image_url")
    private String imageUrl;

    @Expose
    @SerializedName("url")
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
