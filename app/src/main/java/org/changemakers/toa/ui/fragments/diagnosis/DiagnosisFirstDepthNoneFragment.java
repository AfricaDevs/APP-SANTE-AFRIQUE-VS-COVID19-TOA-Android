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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentDiagnosisFirstNoneBinding;
import org.changemakers.toa.databinding.ItemDiagnosisNoSymptomsFragmentBinding;
import org.changemakers.toa.interfaces.ActivityCallbackInterface;
import org.changemakers.toa.ui.fragments.DiagnosisFragment;

public class DiagnosisFirstDepthNoneFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private static String[] sDiagnosisOptions;
    ActivityCallbackInterface mCallback;
    GridLayoutManager mLayoutManager;
    DiagnosisRecyclerViewAdapter mAdapter;

    private FragmentDiagnosisFirstNoneBinding binding;

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

        binding = FragmentDiagnosisFirstNoneBinding.inflate(getLayoutInflater());
        sDiagnosisOptions = getResources().getStringArray(R.array.diagnosis_second_options);

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

        mAdapter = new DiagnosisRecyclerViewAdapter();
        mLayoutManager = new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false);


        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setHasFixedSize(true);

        binding.btnNoDisease.setOnClickListener(this);

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
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_no_disease:

                if (mCallback != null) {
                    YoYo.with(Techniques.BounceInUp).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH, 0);
                        }
                    }).playOn(v);

                }

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

    private class DiagnosisRecyclerViewAdapter extends RecyclerView.Adapter<DiagnosisRecyclerViewAdapter.DiagnosisViewHolder> {

        public DiagnosisRecyclerViewAdapter() {

        }

        @NonNull
        @Override
        public DiagnosisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            ItemDiagnosisNoSymptomsFragmentBinding itemDiagnosisNoSymptomsBinding = ItemDiagnosisNoSymptomsFragmentBinding.inflate(getLayoutInflater(), parent, false);
            return new DiagnosisViewHolder(itemDiagnosisNoSymptomsBinding);

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
            return sDiagnosisOptions.length;
        }

        class DiagnosisViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            ItemDiagnosisNoSymptomsFragmentBinding itemviewBinding;
            boolean isChecked;

            public DiagnosisViewHolder(ItemDiagnosisNoSymptomsFragmentBinding binding) {
                super(binding.getRoot());
                this.itemviewBinding = binding;
                itemviewBinding.getRoot().setOnClickListener(this);
            }


            @Override
            public void onClick(final View v) {
                if (mCallback != null) {

                    YoYo.with(Techniques.BounceIn).onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            mCallback.onDiagnosisOptionSelected(v, DiagnosisFragment.DIAGNOSIS_OPTIONS_SECOND_DEPTH, getAdapterPosition());
                        }
                    }).playOn(v);

                }
            }

        }

    }
}
