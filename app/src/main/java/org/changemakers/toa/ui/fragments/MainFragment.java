package org.changemakers.toa.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.changemakers.toa.MainActivity;
import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentMainBinding;
import org.changemakers.toa.interfaces.ActivityCallbackInterface;

public class MainFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private FragmentMainBinding binding;

    private ActivityCallbackInterface mCallback;

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

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_prevention:
                mCallback.cardSelected(MainActivity.FRAGMENT_INDEX_PREVENTION, binding.lottieCovidAnimPrevention);
                break;
            case R.id.card_diagnosis:
                mCallback.cardSelected(MainActivity.FRAGMENT_INDEX_PREVENTION, binding.lottieCovidAnimPrevention);
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (mCallback == null)
            mCallback = (ActivityCallbackInterface) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (mCallback != null)
            mCallback = null;

    }
}
