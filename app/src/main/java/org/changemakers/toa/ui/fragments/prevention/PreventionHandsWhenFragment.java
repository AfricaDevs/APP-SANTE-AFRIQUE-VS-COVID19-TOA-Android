package org.changemakers.toa.ui.fragments.prevention;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentPreventionHandsWhenBinding;
import org.changemakers.toa.databinding.ItemPreventionWhenBinding;
import org.changemakers.toa.ui.fragments.PreventionFragment;

public class PreventionHandsWhenFragment extends Fragment {

    FragmentPreventionHandsWhenBinding binding;
    private String[] mOptionsTitles;
    private int[] mColorsArray;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPreventionHandsWhenBinding.inflate(getLayoutInflater());

        mOptionsTitles = getResources().getStringArray(R.array.prevention_hands_when);
        mColorsArray = getResources().getIntArray(R.array.material_colors);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(new MyRecyclerViewAdapter());
        binding.recyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        binding.recyclerView.addItemDecoration(itemDecoration);


        return binding.getRoot();

    }



    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.PreventionWhenViewHolder> {


        private static final int ITEM_TYPE_LEFT = 1;
        private static final int ITEM_TYPE_RIGHT = 2;

        public MyRecyclerViewAdapter() {

        }

        @NonNull
        @Override
        public PreventionWhenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == ITEM_TYPE_LEFT) {
                ItemPreventionWhenBinding itemPreventionWhenBinding = ItemPreventionWhenBinding.inflate(getLayoutInflater(), parent, false);
                return new  PreventionWhenViewHolder(itemPreventionWhenBinding);
            } else {
                ItemPreventionWhenBinding itemPreventionWhenBinding = ItemPreventionWhenBinding.inflate(getLayoutInflater(), parent, false);
                return new PreventionWhenViewHolder(itemPreventionWhenBinding);
            }
        }


        @Override
        public void onBindViewHolder(@NonNull  MyRecyclerViewAdapter.PreventionWhenViewHolder holder, int position) {

                holder.itemviewBinding.optionTitle.setText(mOptionsTitles[position]);

                if (position < mColorsArray.length) {
                    holder.itemviewBinding.bgImageview.setColorFilter(mColorsArray[position]);
                    holder.itemviewBinding.bgImageview2.setColorFilter(mColorsArray[position]);
                }
        }

        @Override
        public int getItemViewType(int position) {

            return position % 2 != 0 ? ITEM_TYPE_LEFT : ITEM_TYPE_RIGHT;
        }

        @Override
        public int getItemCount() {
            return mOptionsTitles.length;
        }

        class PreventionWhenViewHolder extends RecyclerView.ViewHolder   {

            ItemPreventionWhenBinding itemviewBinding;

            public PreventionWhenViewHolder(ItemPreventionWhenBinding binding) {
                super(binding.getRoot());
                this.itemviewBinding = binding;
            }
        }

    }
}
