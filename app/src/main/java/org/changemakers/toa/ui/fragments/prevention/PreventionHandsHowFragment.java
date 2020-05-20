package org.changemakers.toa.ui.fragments.prevention;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.changemakers.toa.databinding.FragmentPreventionHandsHowBinding;

public class PreventionHandsHowFragment extends Fragment {

    FragmentPreventionHandsHowBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPreventionHandsHowBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }
}
