package com.africadevs.toa.ui.fragments.diagnosis;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.appbar.AppBarLayout;

import com.africadevs.toa.R;
import com.africadevs.toa.databinding.FragmentDiagnosisFourthDepthTestPositiveBinding;
import com.africadevs.toa.interfaces.ActivityCallbackInterface;
import com.africadevs.toa.ui.fragments.DiagnosisFragment;

public class DiagnosisFourthTestPositiveFragment extends Fragment implements View.OnClickListener {

    ActivityCallbackInterface mCallback;
    private FragmentDiagnosisFourthDepthTestPositiveBinding binding;

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

        binding = FragmentDiagnosisFourthDepthTestPositiveBinding.inflate(getLayoutInflater());


        try {
            ((AppCompatActivity) getActivity()).setSupportActionBar(binding.appBar.toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        binding.appBar.appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    //EXPANDED;

                    binding.appBar.collapsingToolabar.setTitleEnabled(false);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {

                    //COLLAPSED;

                    binding.appBar.collapsingToolabar.setTitleEnabled(false);
                } else {

                    //IDDLE
                }
            }
        });

        binding.btnFourthRestart.setOnClickListener(this);
        binding.btnFourthPrevention.setOnClickListener(this);
        binding.btnFourthPreventionOthers.setOnClickListener(this);

        binding.getRoot().setTranslationY(-getResources().getDisplayMetrics().heightPixels);
        binding.appBar.toolbarTitle.setText(getResources().getString(R.string.diagnisis_result_toolbar_title));

        ViewCompat.animate(binding.getRoot()).translationY(0).setDuration(1000).setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
            }

            @Override
            public void onAnimationEnd(View view) {

            }

            @Override
            public void onAnimationCancel(View view) {

            }
        });


        return binding.getRoot();
    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {


            case R.id.btn_fourth_restart:

                YoYo.with(Techniques.BounceInDown).onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {

                        if (mCallback != null)
                            mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_RESTART, 0);
                    }
                }).playOn(v);
                break;

            case R.id.btn_fourth_prevention:

                YoYo.with(Techniques.BounceInDown).onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {

                        if (mCallback != null)
                            mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_PREVENTION_DEPTH, R.id.btn_fourth_prevention);

                    }
                }).playOn(v);
                break;
            case R.id.btn_fourth_prevention_others:

                YoYo.with(Techniques.BounceInDown).onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {

                        if (mCallback != null)
                            mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_PREVENTION_DEPTH, R.id.btn_fourth_prevention);

                    }
                }).playOn(v);
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
