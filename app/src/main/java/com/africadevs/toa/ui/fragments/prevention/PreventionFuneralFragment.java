package com.africadevs.toa.ui.fragments.prevention;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import com.africadevs.toa.R;
import com.africadevs.toa.databinding.FragmentPreventionFuneralBinding;

import java.util.ArrayList;

public class PreventionFuneralFragment extends BottomSheetDialogFragment implements View.OnClickListener {


    private final static int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;
    private final static int INDEX_FIRST_OPTION = 0;
    PagerAdapter mViewPagerAdapter;
    private FragmentPreventionFuneralBinding binding;
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
        binding = FragmentPreventionFuneralBinding.inflate(getLayoutInflater());

        mTitles = getResources().getStringArray(R.array.prevention_funeral_options_titles);

        binding.backArrow.setOnClickListener(this);

        mViewPagerAdapter = new PagerAdapter(getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mViewPagerAdapter.addFragment(new PreventionFuneralHowFragment());

        binding.viewPager.setAdapter(mViewPagerAdapter);

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
                        binding.backArrow.setImageResource(R.drawable.ic_arrow_back_blue_24dp);

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
