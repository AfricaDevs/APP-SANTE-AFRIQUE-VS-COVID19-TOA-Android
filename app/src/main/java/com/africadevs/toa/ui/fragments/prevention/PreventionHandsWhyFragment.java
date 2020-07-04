package com.africadevs.toa.ui.fragments.prevention;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.africadevs.toa.R;
import com.africadevs.toa.databinding.FragmentPreventionHandsWhyBinding;

public class PreventionHandsWhyFragment extends Fragment {

    FragmentPreventionHandsWhyBinding binding;
    private int[] mColorsArray;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPreventionHandsWhyBinding.inflate(getLayoutInflater());


        mColorsArray = getResources().getIntArray(R.array.material_colors);

        //apply colors to the left and right background vectors
        binding.card1LeftImageview.setColorFilter(mColorsArray[12]);
        binding.card1RightImageview.setColorFilter(mColorsArray[12]);
        binding.card2LeftImageview.setColorFilter(mColorsArray[1]);
        binding.card2RightImageview.setColorFilter(mColorsArray[1]);
        binding.card3LeftImageview.setColorFilter(mColorsArray[4]);
        binding.card3RightImageview.setColorFilter(mColorsArray[4]);

        return binding.getRoot();

    }
}
