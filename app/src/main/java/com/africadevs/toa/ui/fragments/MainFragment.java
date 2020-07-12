package com.africadevs.toa.ui.fragments;

import android.content.Context;
import android.graphics.Region;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.africadevs.toa.databinding.FragmentMainBinding;
import com.africadevs.toa.interfaces.MathdroApiService;
import com.africadevs.toa.model.Country;
import com.africadevs.toa.model.CountryCases;
import com.africadevs.toa.utils.CompatUtils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.africadevs.toa.MainActivity;
import com.africadevs.toa.R;
import com.africadevs.toa.interfaces.MainActivityCallbackInterface;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends BottomSheetDialogFragment implements View.OnClickListener, NumberPicker.OnValueChangeListener{

    private FragmentMainBinding binding;
    private MainActivityCallbackInterface mCallback;

    long cacheSize = 5 * 1024 * 1024;
    Cache myCache;
    Country[] countries;

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

        myCache  = new Cache(getActivity().getCacheDir(), cacheSize);
        String JSON_STRING =  CompatUtils.getJsonFromAssets(getActivity(), "countries_list.json");

        Gson gson = new Gson();

        countries = gson.fromJson(JSON_STRING, Country[].class);

        String[] displayedCountries = new String[countries.length];
        for (Country country: countries ) {
            displayedCountries[country.getId()] = country.getName();
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
                .baseUrl("https://covid19.mathdro.id/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MathdroApiService service = retrofit.create(MathdroApiService.class);
        service.getCountryCases(countryName).enqueue(new Callback<CountryCases>() {
            @Override
            public void onResponse(Call<CountryCases> call, Response<CountryCases> response) {
                Log.i("cases", response.body().confirmed.value+"");

                //bind data
                binding.casesConfirmedCount.setText(""+response.body().confirmed.value);
                binding.casesRecoveredCount.setText(""+response.body().recovered.value);
                binding.casesDeathsCount.setText(""+response.body().deaths.value);
                binding.casesActiveCount.setText(""+(response.body().confirmed.value - (response.body().recovered.value + response.body().deaths.value)));
            }

            @Override
            public void onFailure(Call<CountryCases> call, Throwable t) {
                Log.i("cases_error", t.getMessage());
            }

        });

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
        //Use the NumberPicker int index to load a country name from countries Array

        //reinit values
        binding.casesConfirmedCount.setText("0");
        binding.casesRecoveredCount.setText("0");
        binding.casesDeathsCount.setText("0");
        binding.casesActiveCount.setText("0");
        binding.imageCountryFlag.setImageResource(  getResources().getIdentifier(countries[newValCountryIndex].getIso2().toLowerCase(), "drawable", getActivity().getPackageName()) );

        loadCountryCases( countries[newValCountryIndex].getName() );

    }
}
