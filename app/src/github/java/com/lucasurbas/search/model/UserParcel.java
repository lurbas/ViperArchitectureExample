package com.lucasurbas.search.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lucas on 30/08/15.
 */
public class UserParcel implements Parcelable {

    private User user;

    public UserParcel(User user){
        this.user = user;
    }

    protected UserParcel(Parcel in) {
        readFromParcel(in);
    }

    public User getUser() {
        return user;
    }

    public static final Creator<UserParcel> CREATOR = new Creator<UserParcel>() {
        @Override
        public UserParcel createFromParcel(Parcel in) {
            return new UserParcel(in);
        }

        @Override
        public UserParcel[] newArray(int size) {
            return new UserParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(user.getId());
        out.writeString(user.getLogin());
        out.writeString(user.getAvatarUrl());
        out.writeString(user.getUrl());
    }

    private void readFromParcel(Parcel in){
        this.user = new User();
        this.user.setId(in.readLong());
        this.user.setLogin(in.readString());
        this.user.setAvatarUrl(in.readString());
        this.user.setUrl(in.readString());
    }
}
