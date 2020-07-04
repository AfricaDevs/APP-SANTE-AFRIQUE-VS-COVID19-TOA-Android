package com.africadevs.toa.ui.fragments.prevention;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.africadevs.toa.R;
import com.africadevs.toa.databinding.FragmentPreventionFoodAlimentsBinding;
import com.africadevs.toa.databinding.ItemPreventionFoodAlimentBinding;

public class PreventionFoodAlimentsFragment extends Fragment {

    final static int IMAGES_COUNT = 14;
    FragmentPreventionFoodAlimentsBinding binding;
    private String[] mOptionsTitles;
    private int[] mColorsArray;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPreventionFoodAlimentsBinding.inflate(getLayoutInflater());

        mOptionsTitles = getResources().getStringArray(R.array.prevention_food_options_titles);
        mColorsArray = getResources().getIntArray(R.array.material_colors);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            binding.expandableIdShowTextview.setText(Html.fromHtml(getResources().getString(R.string.prevention_food_aliments), Html.FROM_HTML_MODE_LEGACY));
        } else
            binding.expandableIdShowTextview.setText(Html.fromHtml(getResources().getString(R.string.prevention_food_aliments)));

        binding.expandableText.setTextMaxLines(3);

        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
        binding.recyclerView.setAdapter(new MyRecyclerViewAdapter());
        binding.recyclerView.setHasFixedSize(true);

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
            if (viewType == ITEM_TYPE_LEFT) {
                ItemPreventionFoodAlimentBinding itemPreventionFoodBinding = ItemPreventionFoodAlimentBinding.inflate(getLayoutInflater(), parent, false);
                return new PreventionFoodViewHolder(itemPreventionFoodBinding);
            } else {
                ItemPreventionFoodAlimentBinding itemPreventionFoodBinding = ItemPreventionFoodAlimentBinding.inflate(getLayoutInflater(), parent, false);
                return new PreventionFoodViewHolder(itemPreventionFoodBinding);
            }
        }


        @Override
        public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.PreventionFoodViewHolder holder, int position) {

            //holder.itemviewBinding.optionTitle.setText(mOptionsTitles[position]);

            Drawable image;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                image = getResources().getDrawable(getResources().getIdentifier("food_" + position, "drawable", getActivity().getPackageName()), getActivity().getTheme());
            } else {
                image = ContextCompat.getDrawable(getActivity(), getResources().getIdentifier("food_" + position, "drawable", getActivity().getPackageName()));
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
        public int getItemViewType(int position) {

            return position % 2 != 0 ? ITEM_TYPE_LEFT : ITEM_TYPE_RIGHT;
        }

        @Override
        public int getItemCount() {
            return IMAGES_COUNT;
        }

        class PreventionFoodViewHolder extends RecyclerView.ViewHolder {

            ItemPreventionFoodAlimentBinding itemviewBinding;

            public PreventionFoodViewHolder(ItemPreventionFoodAlimentBinding binding) {
                super(binding.getRoot());
                this.itemviewBinding = binding;
            }
        }

    }
}
