package org.changemakers.toa.ui.fragments.prevention;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.changemakers.toa.databinding.FragmentPreventionMaskWhyBinding;

public class PreventionMaskWhyFragment extends Fragment {

    FragmentPreventionMaskWhyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPreventionMaskWhyBinding.inflate(getLayoutInflater());

        return binding.getRoot();

    }
}
