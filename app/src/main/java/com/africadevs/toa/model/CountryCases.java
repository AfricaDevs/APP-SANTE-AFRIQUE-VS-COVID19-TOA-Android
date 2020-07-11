package com.africadevs.toa.model;

import android.os.Parcelable;

import java.util.Date;

public class CountryCases   {

    public CountryCasesDetails confirmed;
    public CountryCasesDetails recovered;
    public CountryCasesDetails deaths;
    public String lastUpdate = new Date().toString();

}
