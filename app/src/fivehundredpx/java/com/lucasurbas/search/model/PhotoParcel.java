package com.lucasurbas.search.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lucas on 29/08/15.
 */
public class PhotoParcel implements Parcelable {

    private Photo photo;

    public PhotoParcel(Photo photo){
        this.photo = photo;
    }

    protected PhotoParcel(Parcel in) {
        readFromParcel(in);
    }

    public Photo getPhoto() {
        return photo;
    }

    public static final Creator<PhotoParcel> CREATOR = new Creator<PhotoParcel>() {
        @Override
        public PhotoParcel createFromParcel(Parcel in) {
            return new PhotoParcel(in);
        }

        @Override
        public PhotoParcel[] newArray(int size) {
            return new PhotoParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(photo.getId());
        out.writeString(photo.getDescription());
        out.writeString(photo.getImageUrl());
        out.writeString(photo.getUrl());
    }

    private void readFromParcel(Parcel in){
        this.photo = new Photo();
        this.photo.setId(in.readLong());
        this.photo.setDescription(in.readString());
        this.photo.setImageUrl(in.readString());
        this.photo.setUrl(in.readString());
    }
}
