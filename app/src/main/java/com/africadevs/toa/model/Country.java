package com.africadevs.toa.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Country  {
    public int id;
    public String name;
    public String iso2;
    public String iso3;

    public Country() {

    }

    public Country(int id, String name, String iso2, String iso3) {
        this.id = id;
        this.name = name;
        this.iso2 = iso2;
        this.iso3 = iso3;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
