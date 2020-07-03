package com.africadevs.toa.ui.fragments.prevention;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.africadevs.toa.R;
import com.africadevs.toa.databinding.FragmentPreventionWaterConsumptionBinding;

public class PreventionWaterConsumptionFragment extends Fragment {

    FragmentPreventionWaterConsumptionBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPreventionWaterConsumptionBinding.inflate(getLayoutInflater());

        binding.expandableText.setTextMaxLines(6);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.textview2.setText(Html.fromHtml(getResources().getString(R.string.prevention_water_consumption_second), Html.FROM_HTML_MODE_LEGACY));
        } else
            binding.textview2.setText(Html.fromHtml(getResources().getString(R.string.prevention_water_consumption_second)));

        return binding.getRoot();

    }
}
