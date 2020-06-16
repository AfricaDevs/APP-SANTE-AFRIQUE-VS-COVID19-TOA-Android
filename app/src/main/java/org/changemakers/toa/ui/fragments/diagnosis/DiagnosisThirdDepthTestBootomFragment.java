package org.changemakers.toa.ui.fragments.diagnosis;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentDiagnosisThirdTestBinding;
import org.changemakers.toa.interfaces.ActivityCallbackInterface;
import org.changemakers.toa.ui.fragments.DiagnosisFragment;

public class DiagnosisThirdDepthTestBootomFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    ActivityCallbackInterface mCallback;
    private FragmentDiagnosisThirdTestBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDiagnosisThirdTestBinding.inflate(getLayoutInflater());

        binding.btnNo.setOnClickListener(this);
        binding.btnYes.setOnClickListener(this);

        return binding.getRoot();
    }


    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_no:


                if (mCallback != null) {
                    YoYo.with(Techniques.BounceIn).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH, DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH_TEST_NAGATIVE);
                            dismiss();
                        }
                    }).playOn(v);

                }

                break;

            case R.id.btn_yes:

                if (mCallback != null) {
                    YoYo.with(Techniques.BounceIn).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH, DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH_TEST_POSITIVE);
                            dismiss();
                        }
                    }).playOn(v);

                }

                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
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
