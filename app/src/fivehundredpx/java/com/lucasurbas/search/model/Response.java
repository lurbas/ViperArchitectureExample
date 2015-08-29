package com.lucasurbas.search.model;

import android.os.Bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lucasurbas.search.constant.Keys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 29/08/15.
 */
public class Response implements BundleProvider {

    @Expose
    @SerializedName("photos")
    List<Photo> photoList;

    @Override
    public Bundle getBundle() {
        ArrayList<PhotoParcel> list = new ArrayList<>();
        PhotoParcel pp;
        for (Photo p : photoList) {
            pp = new PhotoParcel(p);
            list.add(pp);
        }
        Bundle b = new Bundle();
        b.putParcelableArrayList(Keys.PARCEL_PHOTO_LIST, list);
        return b;
    }
}
