package org.changemakers.toa.ui.fragments;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentDiagnosisBinding;
import org.changemakers.toa.databinding.ItemDiagnosisMainFragmentBinding;
import org.changemakers.toa.interfaces.ActivityCallbackInterface;
import org.changemakers.toa.utils.SmoothCheckBox;

public class DiagnosisFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    static boolean isNooptionChecked = false;
    static int noOptionCount = 0;

    ActivityCallbackInterface mCallback;
    private static String[] sDiagnosisOptions;
    GridLayoutManager mLayoutManager;
    DiagnosisRecyclerViewAdapter mAdapter;

    public static final int DIAGNOSIS_OPTIONS_FIRST_DEPTH = 1;
    public static final int DIAGNOSIS_OPTIONS_FIRST_DEPTH_POSITION_SYMPTOMS = 1;
    public static final int DIAGNOSIS_OPTIONS_FIRST_DEPTH_POSITION_NO_SYMPTOM = 0;
    public static final int DIAGNOSIS_OPTIONS_PREVENTION_DEPTH = 10;
    public static final int DIAGNOSIS_OPTIONS_RESTART = 11;

    public static final int DIAGNOSIS_OPTIONS_SECOND_DEPTH = 2;
    //The following positions follow the options alignment in /res/strings/@id -> diagnosis_second_options
    public static final int DIAGNOSIS_OPTIONS_SECOND_DEPTH_POSITION_MALARIA_NEXT = 10;
    public static final int DIAGNOSIS_OPTIONS_SECOND_DEPTH_POSITION_ANEMIA = 0;
    public static final int DIAGNOSIS_OPTIONS_SECOND_DEPTH_POSITION_HYPERTENSION = 1;
    public static final int DIAGNOSIS_OPTIONS_SECOND_DEPTH_POSITION_RESPIRATORY = 2;
    public static final int DIAGNOSIS_OPTIONS_SECOND_DEPTH_POSITION_THALASEMIA = 3;
    public static final int DIAGNOSIS_OPTIONS_SECOND_DEPTH_POSITION_DIABETES = 4;
    public static final int DIAGNOSIS_OPTIONS_SECOND_DEPTH_POSITION_OBESITY = 5;

    public static final int DIAGNOSIS_OPTIONS_THIRD_DEPTH = 3;
    public static final int DIAGNOSIS_OPTIONS_THIRD_DEPTH_DISEASES_DETAILS_TEMPLATE = 100;
    public static final int DIAGNOSIS_OPTIONS_THIRD_DEPTH_TEST_POSITIVE = 101;
    public static final int DIAGNOSIS_OPTIONS_THIRD_DEPTH_TEST_NAGATIVE = 102;

    public static final int DIAGNOSIS_OPTIONS_FOURTH_DEPTH = 4;

    public static final String EXTRA_SECOND_DEPTH_POSITION = "org.africadevs.toa.seconddepth";

    private FragmentDiagnosisBinding binding;

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

        binding = FragmentDiagnosisBinding.inflate(getLayoutInflater());
        sDiagnosisOptions = getResources().getStringArray(R.array.diagnosis_options);

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

        binding.expandableText.setTextMaxLines(3);
        binding.btnNext.setOnClickListener(this);
        binding.btnNoSyptom.setOnClickListener(this);

        mAdapter = new DiagnosisRecyclerViewAdapter();
        mLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);


        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {

                //use a full swcreen width for the items having long textes to fill the screen
                //based on sDiagnosisOptions length
                switch (position) {

                    case 0:
                        return 2;
                    case 1:
                        return 2;
                    case 6:
                        return 2;
                    default:
                        return 1;
                }
            }
        });

        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setHasFixedSize(true);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        binding.btnNext.setAlpha(0.4f);
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
            case R.id.btn_next:
                if (noOptionCount == 0) {
                    YoYo.with(Techniques.BounceInUp).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            binding.btnNext.setAlpha(0.4f);
                            Snackbar.make(binding.getRoot(), "Veuillez cocher une case pour continuer", BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }).playOn(v);
                } else {
                    YoYo.with(Techniques.BounceInUp).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {

                            //load next
                            if (mCallback != null)
                                mCallback.onDiagnosisOptionSelected(v, DIAGNOSIS_OPTIONS_FIRST_DEPTH, DIAGNOSIS_OPTIONS_FIRST_DEPTH_POSITION_SYMPTOMS);
                        }
                    }).playOn(v);
                }
                break;
            case R.id.btn_no_syptom:

                YoYo.with(Techniques.BounceInDown).onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        if (mCallback != null)
                            mCallback.onDiagnosisOptionSelected(v, DIAGNOSIS_OPTIONS_FIRST_DEPTH, DIAGNOSIS_OPTIONS_FIRST_DEPTH_POSITION_NO_SYMPTOM);
                    }
                }).playOn(v);
                break;
        }
    }


    private class DiagnosisRecyclerViewAdapter extends RecyclerView.Adapter<DiagnosisRecyclerViewAdapter.DiagnosisViewHolder> {

        public DiagnosisRecyclerViewAdapter() {

        }

        @NonNull
        @Override
        public DiagnosisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            ItemDiagnosisMainFragmentBinding itemDiagnosisMainFragmentBinding = ItemDiagnosisMainFragmentBinding.inflate(getLayoutInflater(), parent, false);
            return new DiagnosisViewHolder(itemDiagnosisMainFragmentBinding);

        }

        @Override
        public void onBindViewHolder(@NonNull DiagnosisViewHolder holder, int position) {

            holder.itemviewBinding.optionTitle.setText(sDiagnosisOptions[position]);

            //if not last item and last item is checked, deselect all others
            if (position + 1 == sDiagnosisOptions.length) {

            }

        }

        @Override
        public int getItemCount() {
            //Use items array size
            return sDiagnosisOptions.length;
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
                if (noOptionCount > 0) {
                    binding.btnNext.setAlpha(1f);
                } else {
                    binding.btnNext.setAlpha(0.4f);
                }
            }
        }

    }
}
