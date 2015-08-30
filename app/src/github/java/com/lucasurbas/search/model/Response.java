package com.lucasurbas.search.model;

import android.os.Bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lucasurbas.search.constant.Keys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 30/08/15.
 */
public class Response implements BundleProvider {

    @Expose
    @SerializedName("items")
    List<User> userList;

    @Override
    public Bundle getBundle() {
        ArrayList<UserParcel> list = new ArrayList<>();
        UserParcel up;
        for (User u : userList) {
            up = new UserParcel(u);
            list.add(up);
        }
        Bundle b = new Bundle();
        b.putParcelableArrayList(Keys.PARCEL_USER_LIST, list);
        return b;
    }
}
