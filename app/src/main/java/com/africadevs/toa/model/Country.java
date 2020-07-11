package com.africadevs.toa.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Country implements Parcelable {
    public String name;
    public String iso2;
    public String iso3;

    public Country() {

    }
    public Country(String name, String iso2, String iso3) {
        this.name = name;
        this.iso2 = iso2;
        this.iso3 = iso3;
    }

    protected Country(Parcel in) {
        name = in.readString();
        iso2 = in.readString();
        iso3 = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(iso2);
        dest.writeString(iso3);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }


}
