package com.hv_07.bunker.user;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public String ID;

    protected User(Parcel in) {
        ID = in.readString();
    }
    public User(String id){
        this.ID=id;
    }
    public User(){
        this.ID=null;
    }
    public void setID(String id){
        this.ID=id;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
    }
}
