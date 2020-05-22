package org.changemakers.toa.ui.fragments.prevention;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentPreventionHandsHowBinding;
import org.changemakers.toa.databinding.ItemPreventionHowBinding;

public class PreventionHandsHowFragment extends Fragment {

    FragmentPreventionHandsHowBinding binding;
    private String[] mOptionsTitles;
    private String[] mOptionsIcons;
    private int[] mColorsArray;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPreventionHandsHowBinding.inflate(getLayoutInflater());


        mOptionsTitles = getResources().getStringArray(R.array.prevention_hands_how);
        mOptionsIcons = getResources().getStringArray(R.array.prevention_hands_how_icons);
        mColorsArray = getResources().getIntArray(R.array.material_colors);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(new PreventionHandsHowFragment.MyRecyclerViewAdapter());
        binding.recyclerView.setHasFixedSize(true);


        return binding.getRoot();

    }


    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.PreventionHowViewHolder> {


        public MyRecyclerViewAdapter() {

        }

        @NonNull
        @Override
        public PreventionHowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            ItemPreventionHowBinding itemPreventionWhenBinding = ItemPreventionHowBinding.inflate(getLayoutInflater(), parent, false);
            return new PreventionHowViewHolder(itemPreventionWhenBinding);
        }


        @Override
        public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.PreventionHowViewHolder holder, int position) {

            holder.itemviewBinding.optionTitle.setText(mOptionsTitles[position]);
            holder.itemviewBinding.imageView.setImageResource(getResources().getIdentifier(mOptionsIcons[position], "drawable", getActivity().getPackageName()));

            if (position < mColorsArray.length) {
                holder.itemviewBinding.bgImageview.setColorFilter(mColorsArray[position]);
                holder.itemviewBinding.bgImageview2.setColorFilter(mColorsArray[position]);
            }
        }

        @Override
        public int getItemCount() {
            return mOptionsTitles.length;
        }

        class PreventionHowViewHolder extends RecyclerView.ViewHolder {

            ItemPreventionHowBinding itemviewBinding;

            public PreventionHowViewHolder(ItemPreventionHowBinding binding) {
                super(binding.getRoot());
                this.itemviewBinding = binding;
            }
        }

    }
}
