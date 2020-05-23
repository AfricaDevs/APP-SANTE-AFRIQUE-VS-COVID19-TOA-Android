package org.changemakers.toa.ui.fragments.prevention;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentPreventionWaterUsedBinding;

public class PreventionWaterUsedFragment extends Fragment {

    FragmentPreventionWaterUsedBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPreventionWaterUsedBinding.inflate(getLayoutInflater());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.textView.setText(Html.fromHtml(getResources().getString(R.string.prevention_water_used), Html.FROM_HTML_MODE_LEGACY));

        } else
            binding.textView.setText(Html.fromHtml(getResources().getString(R.string.prevention_water_used)));

        return binding.getRoot();

    }
}
