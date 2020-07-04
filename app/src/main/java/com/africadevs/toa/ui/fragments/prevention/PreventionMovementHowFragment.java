package com.africadevs.toa.ui.fragments.prevention;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.africadevs.toa.databinding.FragmentPreventionMovementHowBinding;

public class PreventionMovementHowFragment extends Fragment {

    FragmentPreventionMovementHowBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPreventionMovementHowBinding.inflate(getLayoutInflater());

        return binding.getRoot();

    }
}
