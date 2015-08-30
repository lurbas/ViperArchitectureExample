package com.lucasurbas.search.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lucas on 30/08/15.
 */
public class SearchItemParcel implements Parcelable {

    private SearchItem searchItem;

    public SearchItemParcel(SearchItem searchItem){
        this.searchItem = searchItem;
    }

    protected SearchItemParcel(Parcel in) {
        readFromParcel(in);
    }

    public SearchItem getSearchItem() {
        return searchItem;
    }

    public static final Creator<SearchItemParcel> CREATOR = new Creator<SearchItemParcel>() {
        @Override
        public SearchItemParcel createFromParcel(Parcel in) {
            return new SearchItemParcel(in);
        }

        @Override
        public SearchItemParcel[] newArray(int size) {
            return new SearchItemParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(searchItem.getId());
        out.writeString(searchItem.getTitle());
        out.writeString(searchItem.getImageUrl());
        out.writeString(searchItem.getWebUrl());
        out.writeByte((byte) (searchItem.isFavourite() ? 1 : 0));
        out.writeByte((byte) (searchItem.isVisited() ? 1 : 0));
    }

    private void readFromParcel(Parcel in){
        this.searchItem = new SearchItem();
        this.searchItem.setId(in.readLong());
        this.searchItem.setTitle(in.readString());
        this.searchItem.setImageUrl(in.readString());
        this.searchItem.setWebUrl(in.readString());
        this.searchItem.setIsFavourite(in.readByte() != 0);
        this.searchItem.setIsVisited(in.readByte() != 0);
    }
}
