package org.changemakers.toa.ui.fragments;

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

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentDiagnosisBinding;
import org.changemakers.toa.databinding.ItemDiagnosisMainFragmentBinding;
import org.changemakers.toa.interfaces.ActivityCallbackInterface;
import org.changemakers.toa.utils.SmoothCheckBox;

public class DiagnosisFragment extends BottomSheetDialogFragment {

    private static final int ITEM_TYPE_LEFT = 1;
    private static final int ITEM_TYPE_RIGHT = 2;
    static boolean isNooptionChecked = false;

    ActivityCallbackInterface mCallback;
    private static String[] sDiagnosisOptions;
    GridLayoutManager mLayoutManager;
    DiagnosisRecyclerViewAdapter mAdapter;

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

                    //ViewCompat.animate(binding.lottieSelectedOption).setDuration(200).alpha(0f).start();
                    //COLLAPSED;

                    binding.collapsingToolabar.setTitleEnabled(false);
                } else {

                    //IDDLE
                }
            }
        });

        binding.expandableText.setTextMaxLines(3);

        mAdapter = new DiagnosisRecyclerViewAdapter();
        mLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);


        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {

                if (position == (sDiagnosisOptions.length - 1))
                    return 2;
                return 1;
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
        public int getItemViewType(int position) {

            return position % 2 != 0 ? ITEM_TYPE_LEFT : ITEM_TYPE_RIGHT;
        }

        @Override
        public int getItemCount() {
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

                if (getAdapterPosition() + 1 == sDiagnosisOptions.length) {
                    //No option has been selected, clear other oprions
                    isNooptionChecked = isChecked;
                }
            }
        }

    }
}
