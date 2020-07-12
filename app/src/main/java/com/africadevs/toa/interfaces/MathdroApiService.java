package com.africadevs.toa.interfaces;

import com.africadevs.toa.model.CountryCases;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface MathdroApiService {

    @Headers("Cache-Control: max-age=640000")
    @GET("countries/{countryName}")
    Call<CountryCases> getCountryCases(@Path("countryName") String countryName);

}
