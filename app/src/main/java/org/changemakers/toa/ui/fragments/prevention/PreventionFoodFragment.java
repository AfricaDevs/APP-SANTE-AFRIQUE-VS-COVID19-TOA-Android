package org.changemakers.toa.ui.fragments.prevention;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentPreventionFoodBinding;

import java.util.ArrayList;

public class PreventionFoodFragment extends BottomSheetDialogFragment implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {


    private final static int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;
    private final static int INDEX_FIRST_OPTION = 0;
    PagerAdapter mViewPagerAdapter;
    private FragmentPreventionFoodBinding binding;
    private String[] mTitles;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPreventionFoodBinding.inflate(getLayoutInflater());

        mTitles = getResources().getStringArray(R.array.prevention_food_options_titles);

        binding.backArrow.setOnClickListener(this);

        mViewPagerAdapter = new PagerAdapter(getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mViewPagerAdapter.addFragment(new PreventionFoodAlimentsFragment());
        mViewPagerAdapter.addFragment(new PreventionFoodAlimentsFragment());

        binding.viewPager.setAdapter(mViewPagerAdapter);
        binding.bottomNav.setOnNavigationItemSelectedListener(this);

        // bind the option title to the custom Toolbar alike TextView
        binding.preventionOptionTittle.setText(mTitles[INDEX_FIRST_OPTION]);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {

                    case 0:
                        binding.preventionOptionTittle.setText(mTitles[position]);
                        binding.backArrow.setImageResource(R.drawable.ic_arrow_back_green_24dp);

                        binding.bottomNav.setSelectedItemId(R.id.item_food_aliments);
                        binding.bottomNav.getMenu().findItem(R.id.item_food_aliments).setChecked(true);
                        break;
                    case 1:
                        binding.preventionOptionTittle.setText(mTitles[position]);
                        binding.backArrow.setImageResource(R.drawable.ic_arrow_back_blue_24dp);

                        binding.bottomNav.setSelectedItemId(R.id.item_food_drink);
                        binding.bottomNav.getMenu().findItem(R.id.item_food_drink).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.backArrow:
                try {
                    getActivity().onBackPressed();
                } catch (Exception ex) {
                }
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_food_aliments:
                binding.viewPager.setCurrentItem(0);
                break;
            case R.id.item_food_drink:
                binding.viewPager.setCurrentItem(1);
                break;
        }

        return false;
    }


    public class PagerAdapter extends FragmentPagerAdapter {

        public ArrayList<Fragment> fragments = new ArrayList<>();

        public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);

        }

        public void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }

        public void addFragment(int index, Fragment fragment) {
            fragments.add(index, fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
