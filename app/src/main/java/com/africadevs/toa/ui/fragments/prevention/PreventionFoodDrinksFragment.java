package com.africadevs.toa.ui.fragments.prevention;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.africadevs.toa.databinding.FragmentPreventionFoodDrinksBinding;
import com.africadevs.toa.databinding.ItemPreventionFoodDrinkBinding;


public class PreventionFoodDrinksFragment extends Fragment {

    FragmentPreventionFoodDrinksBinding binding;
    int mImagesCount = 11; //editing this may result to an OutOfBoundsException

    GridLayoutManager mGridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPreventionFoodDrinksBinding.inflate(getLayoutInflater());

        mGridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(mGridLayoutManager);
        binding.recyclerView.setAdapter(new MyRecyclerViewAdapter());
        binding.recyclerView.setHasFixedSize(true);

        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                switch (position) {
                    case 0:
                        return 2;
                    case 1:
                        return 2;
                    case 4:
                        return 2;
                    case 7:
                        return 2;
                    case 10:
                        return 2;

                    default:
                        return 1;
                }
            }
        });

        return binding.getRoot();

    }


    private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.PreventionFoodViewHolder> {


        private static final int ITEM_TYPE_LEFT = 1;
        private static final int ITEM_TYPE_RIGHT = 2;

        public MyRecyclerViewAdapter() {

        }

        @NonNull
        @Override
        public PreventionFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            ItemPreventionFoodDrinkBinding itemPreventionFoodBinding = ItemPreventionFoodDrinkBinding.inflate(getLayoutInflater(), parent, false);
            return new PreventionFoodViewHolder(itemPreventionFoodBinding);

        }


        @Override
        public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.PreventionFoodViewHolder holder, int position) {

            //holder.itemviewBinding.optionTitle.setText(mOptionsTitles[position]);

            Drawable image;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //the Titles array
                image = getResources().getDrawable(getResources().getIdentifier("drink_" + position, "drawable", getActivity().getPackageName()), getActivity().getTheme());
            } else {
                image = ContextCompat.getDrawable(getActivity(), getResources().getIdentifier("drink_" + position, "drawable", getActivity().getPackageName()));
            }

            holder.itemviewBinding.imageView.setImageDrawable(image);

            Palette palette = Palette.from(((BitmapDrawable) image).getBitmap()).generate();
            Palette.Swatch vibrant = palette.getVibrantSwatch();

            if (vibrant != null) {
                holder.itemviewBinding.bgImageview.setColorFilter(vibrant.getRgb());
                holder.itemviewBinding.bgImageview2.setColorFilter(vibrant.getRgb());
            }

        }


        @Override
        public int getItemCount() {
            return mImagesCount;
        }

        class PreventionFoodViewHolder extends RecyclerView.ViewHolder {

            ItemPreventionFoodDrinkBinding itemviewBinding;

            public PreventionFoodViewHolder(ItemPreventionFoodDrinkBinding binding) {
                super(binding.getRoot());
                this.itemviewBinding = binding;
            }
        }

    }
}
