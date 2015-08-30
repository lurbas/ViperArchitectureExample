package com.lucasurbas.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 30/08/15.
 */
public class Response implements SearchItemsProvider {

    @Expose
    @SerializedName("items")
    List<User> userList;

    @Override
    public List<SearchItem> getSearchItems() {
        ArrayList<SearchItem> list = new ArrayList<>();
        SearchItem searchItem;
        for (User u : userList) {
            searchItem = new SearchItem();
            searchItem.setId(u.getId());
            searchItem.setTitle(u.getLogin());
            searchItem.setImageUrl(u.getAvatarUrl());
            searchItem.setWebUrl(u.getUrl());
            list.add(searchItem);
        }
        return list;
    }
}
