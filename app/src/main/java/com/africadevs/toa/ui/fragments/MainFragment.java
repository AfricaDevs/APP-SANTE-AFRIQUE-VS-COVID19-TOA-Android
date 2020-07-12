package com.africadevs.toa.ui.fragments;

import android.content.Context;
import android.graphics.Region;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.africadevs.toa.databinding.FragmentMainBinding;
import com.africadevs.toa.model.Country;
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

public class MainFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private FragmentMainBinding binding;

    private MainActivityCallbackInterface mCallback;

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


        return binding.getRoot();
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
}
