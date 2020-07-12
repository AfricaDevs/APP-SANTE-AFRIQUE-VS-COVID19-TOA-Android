package com.africadevs.toa.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.africadevs.toa.MainActivity;
import com.africadevs.toa.R;
import com.africadevs.toa.databinding.FragmentMainBinding;
import com.africadevs.toa.interfaces.MainActivityCallbackInterface;
import com.africadevs.toa.interfaces.MathdroApiService;
import com.africadevs.toa.model.Country;
import com.africadevs.toa.model.CountryCases;
import com.africadevs.toa.utils.CompatUtils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

;

public class MainFragment extends BottomSheetDialogFragment implements View.OnClickListener, NumberPicker.OnValueChangeListener{

    private FragmentMainBinding binding;
    private MainActivityCallbackInterface mCallback;

    Country[] countries;
    MathdroApiService service;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(getLayoutInflater());

        binding.cardPrevention.setOnClickListener(this);
        binding.cardDiagnosis.setOnClickListener(this);



        String JSON_STRING =  CompatUtils.getJsonFromAssets(getActivity(), "countries_list.json");

        Gson gson = new Gson();

        countries = gson.fromJson(JSON_STRING, Country[].class);

        String[] displayedCountries = new String[countries.length];
        for (Country country: countries ) {
            displayedCountries[country.getId()] = country.getName();

            if(country.getName() == null)
                Log.i("empty_country", country.getId()+"");
        }

        binding.countryNumberPicker.setMinValue(0);
        binding.countryNumberPicker.setMaxValue( countries.length-1 );
        binding.countryNumberPicker.setDisplayedValues( displayedCountries );
        binding.countryNumberPicker.setOnValueChangedListener(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadCountryCases(countries[0].getName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_prevention:
                mCallback.cardSelected(MainActivity.FRAGMENT_INDEX_PREVENTION, binding.lottieCovidAnimPrevention);
                break;
            case R.id.card_diagnosis:
                mCallback.cardSelected(MainActivity.FRAGMENT_INDEX_DIAGNOSIS, binding.lottieCovidAnimPrevention);
                break;
        }
    }

    private void loadCountryCases(String countryName){

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://covid19.mathdro.id/api/")
                .build();

        service = retrofit.create(MathdroApiService.class);
        service.getCountryCases(countryName).enqueue(new Callback<CountryCases>() {
            @Override
            public void onResponse(Call<CountryCases> call, retrofit2.Response<CountryCases> response) {
                Log.i("cases", response.body().confirmed.value+"");

                //bind data
                binding.casesConfirmedCount.setText( formatNumber(response.body().confirmed.value));
                binding.casesRecoveredCount.setText(formatNumber(response.body().recovered.value));
                binding.casesDeathsCount.setText(formatNumber(response.body().deaths.value));
                binding.casesActiveCount.setText(formatNumber( (response.body().confirmed.value - (response.body().recovered.value + response.body().deaths.value))));
            }

            @Override
            public void onFailure(Call<CountryCases> call, Throwable t) {

            }
        });

    }

    private String formatNumber(int number){
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance((Locale.getDefault()));
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        return formatter.format(number).replaceAll(","," ");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (mCallback == null)
            mCallback = (MainActivityCallbackInterface) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (mCallback != null)
            mCallback = null;

    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newValCountryIndex) {

        //reinit values
        binding.casesConfirmedCount.setText("0");
        binding.casesRecoveredCount.setText("0");
        binding.casesDeathsCount.setText("0");
        binding.casesActiveCount.setText("0");

        //Use the NumberPicker int index to load a country name from countries Array
        binding.imageCountryFlag.setImageResource(getResources().getIdentifier(countries[newValCountryIndex].getIso2().toLowerCase(), "drawable", getActivity().getPackageName()));

        //If the user is online, we load online data
        if(CompatUtils.isNetworkAvailable(getActivity())) {

            //We hide the view that says : the user is offline
            binding.onlineStatus.setAlpha(0.0f);
            loadCountryCases(countries[newValCountryIndex].getName());
        } else{
            //We show the view that says : the user is offline
            binding.onlineStatus.setAlpha(1.0f);
            YoYo.with(Techniques.FadeIn).playOn(binding.onlineStatus);
        }
    }

}
