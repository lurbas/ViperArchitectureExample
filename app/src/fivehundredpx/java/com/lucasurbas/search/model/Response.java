package com.lucasurbas.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 29/08/15.
 */
public class Response implements SearchItemsProvider {

    @Expose
    @SerializedName("photos")
    List<Photo> photoList;

    @Override
    public List<SearchItem> getSearchItems() {
        ArrayList<SearchItem> list = new ArrayList<>();
        SearchItem searchItem;
        for (Photo p : photoList) {
            searchItem = new SearchItem();
            searchItem.setId(p.getId());
            searchItem.setTitle(p.getDescription());
            searchItem.setImageUrl(p.getImageUrl());
            searchItem.setWebUrl(String.format("https://500px.com%s", p.getUrl()));
            list.add(searchItem);
        }
        return list;
    }
}
