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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentDiagnosisFourthTestNegativeBinding;
import org.changemakers.toa.databinding.ItemDiagnosisMainFragmentBinding;
import org.changemakers.toa.interfaces.ActivityCallbackInterface;
import org.changemakers.toa.ui.fragments.DiagnosisFragment;
import org.changemakers.toa.utils.SmoothCheckBox;

public class DiagnosisFourthTestNegativeFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    static int noOptionCount = 0;
    private static int depth_position = 1;
    private static String[] sDiagnosisOptionsDepth2, sDiagnosisOptionsDepth3;
    ActivityCallbackInterface mCallback;
    GridLayoutManager mLayoutManager2, mLayoutManager3;
    DiagnosisRecyclerViewAdapter mAdapter2, mAdapter3;


    private FragmentDiagnosisFourthTestNegativeBinding binding;

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

        binding = FragmentDiagnosisFourthTestNegativeBinding.inflate(getLayoutInflater());
        sDiagnosisOptionsDepth2 = getResources().getStringArray(R.array.diagnosis_options_fourth_depth2);
        sDiagnosisOptionsDepth3 = getResources().getStringArray(R.array.diagnosis_options_fourth_depth3);

        try {
            ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        binding.appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    //EXPANDED;

                    binding.collapsingToolabar.setTitleEnabled(false);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {

                    //COLLAPSED;

                    binding.collapsingToolabar.setTitleEnabled(false);
                } else {

                    //IDDLE
                }
            }
        });

        //First Depth bindings
        binding.btnFirstSymptoms.setOnClickListener(this);
        binding.btnFirstNoSyptom.setOnClickListener(this);


        //Second Depth bindings
        binding.btnSecondNext.setOnClickListener(this);
        binding.btnSecondNoSyptom.setOnClickListener(this);

        mAdapter2 = new DiagnosisRecyclerViewAdapter(sDiagnosisOptionsDepth2, 2);
        mLayoutManager2 = new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false);

        binding.secondRecyclerView.setLayoutManager(mLayoutManager2);
        binding.secondRecyclerView.setAdapter(mAdapter2);
        binding.secondRecyclerView.setHasFixedSize(true);


        //Third Depth bindings
        binding.btnThirdNext.setOnClickListener(this);
        binding.btnThirdNoSyptom.setOnClickListener(this);

        mAdapter3 = new DiagnosisRecyclerViewAdapter(sDiagnosisOptionsDepth3, 3);
        mLayoutManager3 = new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false);

        binding.thirdRecyclerView.setLayoutManager(mLayoutManager3);
        binding.thirdRecyclerView.setAdapter(mAdapter3);
        binding.thirdRecyclerView.setHasFixedSize(true);

        //Third Depth bindings
        binding.btnThirdNext.setOnClickListener(this);
        binding.btnThirdNoSyptom.setOnClickListener(this);

        //Fourth Depth bindings
        binding.btnFourthRestart.setOnClickListener(this);
        binding.btnFourthPrevention.setOnClickListener(this);

        loadDepthScreen(1);
        return binding.getRoot();
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

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_first_symptoms:

                YoYo.with(Techniques.BounceInUp).onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH, DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH_TEST_POSITIVE);
                        getChildFragmentManager().popBackStack();
                        getChildFragmentManager().popBackStack();
                    }
                }).playOn(v);

                break;
            case R.id.btn_first_no_syptom:

                YoYo.with(Techniques.BounceInUp).onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        //load depth2
                        loadDepthScreen(2);
                    }
                }).playOn(v);

                break;

            case R.id.btn_second_next:

                if (noOptionCount == 0) {
                    YoYo.with(Techniques.BounceInUp).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            binding.btnSecondNext.setAlpha(0.4f);
                            Snackbar.make(binding.getRoot(), getString(R.string.snack_check_one_case), BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }).playOn(v);
                } else {
                    YoYo.with(Techniques.BounceInUp).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {

                            //load next
                            if (mCallback != null)
                                mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH, DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH_TEST_POSITIVE);
                        }
                    }).playOn(v);
                }
                break;

            case R.id.btn_second_no_syptom:

                YoYo.with(Techniques.BounceInDown).onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        //load depth3
                        binding.btnThirdNext.setAlpha(0.4f);
                        loadDepthScreen(3);
                    }
                }).playOn(v);
                break;

            case R.id.btn_third_next:

                if (noOptionCount == 0) {
                    YoYo.with(Techniques.BounceInUp).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            binding.btnThirdNext.setAlpha(0.4f);
                            Snackbar.make(binding.getRoot(), getString(R.string.snack_check_one_case), BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }).playOn(v);
                } else {
                    YoYo.with(Techniques.BounceInUp).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {

                            //load next
                            if (mCallback != null)
                                mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH, DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH_TEST_POSITIVE);
                        }
                    }).playOn(v);
                }
                break;


            case R.id.btn_third_no_syptom:

                YoYo.with(Techniques.BounceInDown).onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        loadDepthScreen(4);
                    }
                }).playOn(v);
                break;

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
                            mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_PREVENTION_DEPTH, DiagnosisFragment.DIAGNOSIS_OPTIONS_FOURTH_DEPTH);

                    }
                }).playOn(v);
                break;
        }
    }


    private void loadDepthScreen(int depth) {
        switch (depth) {
            case 1:
                binding.firstScreen.setVisibility(View.VISIBLE);
                binding.secondScreen.setVisibility(View.GONE);
                binding.thirdScreen.setVisibility(View.GONE);

                break;

            case 2:
                binding.secondScreen.setVisibility(View.VISIBLE);
                binding.secondScreen.setAlpha(0f);

                binding.btnSecondNext.setAlpha(0.4f);
                ViewCompat.animate(binding.firstScreen).alpha(0).setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        binding.firstScreen.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(View view) {

                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                });

                ViewCompat.animate(binding.secondScreen).alpha(1);
                break;

            case 3:
                binding.thirdScreen.setVisibility(View.VISIBLE);
                binding.thirdScreen.setAlpha(0f);

                binding.btnThirdNext.setAlpha(0.4f);
                ViewCompat.animate(binding.secondScreen).alpha(0).setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        binding.secondScreen.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(View view) {

                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                });

                ViewCompat.animate(binding.thirdScreen).alpha(1);
                break;
            case 4:
                binding.fourthScreen.setTranslationY(-getResources().getDisplayMetrics().heightPixels);
                binding.fourthScreen.setVisibility(View.VISIBLE);
                binding.toolbarTitle.setText("VOTRE RÉSULTAT");

                ViewCompat.animate(binding.thirdScreen).alpha(0);
                ViewCompat.animate(binding.fourthScreen).translationY(0).setDuration(1000).setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        binding.secondScreen.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(View view) {

                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                });

                break;
        }
    }

    private class DiagnosisRecyclerViewAdapter extends RecyclerView.Adapter<DiagnosisRecyclerViewAdapter.DiagnosisViewHolder> {

        String[] options;
        int depth;

        public DiagnosisRecyclerViewAdapter(String[] options, int depth) {
            this.options = options;
            this.depth = depth;
        }

        @NonNull
        @Override
        public DiagnosisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            ItemDiagnosisMainFragmentBinding itemDiagnosisMainFragmentBinding = ItemDiagnosisMainFragmentBinding.inflate(getLayoutInflater(), parent, false);
            return new DiagnosisViewHolder(itemDiagnosisMainFragmentBinding);

        }

        @Override
        public void onBindViewHolder(@NonNull DiagnosisViewHolder holder, int position) {

            holder.itemviewBinding.optionTitle.setText(options[position]);

        }

        @Override
        public int getItemCount() {
            //Use items array size
            return options.length;
        }

        class DiagnosisViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, SmoothCheckBox.OnCheckedChangeListener {

            ItemDiagnosisMainFragmentBinding itemviewBinding;
            boolean isChecked;

            public DiagnosisViewHolder(ItemDiagnosisMainFragmentBinding binding) {
                super(binding.getRoot());
                this.itemviewBinding = binding;
                itemviewBinding.getRoot().setOnClickListener(this);
                itemviewBinding.checkbox.setOnCheckedChangeListener(this);
            }


            @Override
            public void onClick(View v) {
                itemviewBinding.checkbox.performClick();
            }

            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                this.isChecked = isChecked;

                if (isChecked) {
                    noOptionCount++;
                } else noOptionCount--;

                //Handle button enable/disable appearance

                if (depth == 2) {

                    if (noOptionCount > 0) {
                        binding.btnSecondNext.setAlpha(1f);
                    } else {
                        binding.btnSecondNext.setAlpha(0.4f);
                    }
                } else { //depth=3
                    if (noOptionCount > 0) {
                        binding.btnThirdNext.setAlpha(1f);
                    } else {
                        binding.btnThirdNext.setAlpha(0.4f);
                    }
                }
            }
        }

    }
}
