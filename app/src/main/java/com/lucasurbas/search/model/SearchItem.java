package com.lucasurbas.search.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Lucas on 30/08/15.
 */
@DatabaseTable
public class SearchItem implements Parcelable, IdProvider {

    @DatabaseField(id = true)
    private long id;

    @DatabaseField()
    private String title;

    @DatabaseField()
    private String imageUrl;

    @DatabaseField()
    private String webUrl;

    @DatabaseField()
    private boolean isFavourite;

    @DatabaseField()
    private boolean isVisited;

    public SearchItem() {
        // for ormlite
    }

    protected SearchItem(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<SearchItem> CREATOR = new Creator<SearchItem>() {
        @Override
        public SearchItem createFromParcel(Parcel in) {
            return new SearchItem(in);
        }

        @Override
        public SearchItem[] newArray(int size) {
            return new SearchItem[size];
        }
    };

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeLong(id);
        out.writeString(title);
        out.writeString(imageUrl);
        out.writeString(webUrl);
        out.writeByte((byte) (isFavourite ? 1 : 0));
        out.writeByte((byte) (isVisited ? 1 : 0));
    }

    private void readFromParcel(Parcel in){
        id = in.readLong();
        title = in.readString();
        imageUrl = in.readString();
        webUrl = in.readString();
        isFavourite = in.readByte() != 0;
        isVisited = in.readByte() != 0;
    }
}
