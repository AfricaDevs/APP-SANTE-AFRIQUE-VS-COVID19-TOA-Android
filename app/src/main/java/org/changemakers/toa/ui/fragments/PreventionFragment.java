package org.changemakers.toa.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentPreventionBinding;
import org.changemakers.toa.databinding.ItemPreventionFragmentBinding;

public class PreventionFragment extends BottomSheetDialogFragment {

    private static String[] sPreventionOptions;

    private FragmentPreventionBinding binding;

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
        binding = FragmentPreventionBinding.inflate(getLayoutInflater());

        try {
            ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        sPreventionOptions = getResources().getStringArray(R.array.prevention_options);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(new PreventionRecyclerViewAdapter());
        binding.recyclerView.setHasFixedSize(true);


        binding.appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {

                    ViewCompat.animate(binding.lottieSelectedOption).setDuration(400).alpha(1f).start();
                    //EXPANDED;

                    binding.collapsingToolabar.setTitleEnabled(false);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {

                    //ViewCompat.animate(binding.lottieSelectedOption).setDuration(200).alpha(0f).start();
                    //COLLAPSED;

                    binding.collapsingToolabar.setTitleEnabled(false);
                } else {
                    if(binding.lottieSelectedOption.getAlpha()==1)
                    ViewCompat.animate(binding.lottieSelectedOption).setDuration(200).alpha(0f).start();

                    //IDDLE
                }
            }
        });

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private class PreventionRecyclerViewAdapter extends RecyclerView.Adapter<PreventionRecyclerViewAdapter.PreventionViewHolder>{

        public PreventionRecyclerViewAdapter(){

        }

        @NonNull
        @Override
        public PreventionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            ItemPreventionFragmentBinding itemPreventionFragmentBinding = ItemPreventionFragmentBinding.inflate(getLayoutInflater(), parent, false);
            return new PreventionViewHolder(itemPreventionFragmentBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull PreventionViewHolder holder, int position) {

            holder.itemviewBinding.optionTitle.setText(sPreventionOptions[position]);
        }

        @Override
        public int getItemCount() {
            return sPreventionOptions.length;
        }

        class PreventionViewHolder extends RecyclerView.ViewHolder{

            ItemPreventionFragmentBinding itemviewBinding;

            public PreventionViewHolder(ItemPreventionFragmentBinding binding) {
                super(binding.getRoot());
                this.itemviewBinding = binding;
            }
        }

    }
}
