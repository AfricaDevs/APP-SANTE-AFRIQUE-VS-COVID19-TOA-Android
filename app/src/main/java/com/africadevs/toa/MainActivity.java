package com.africadevs.toa;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.appbar.AppBarLayout;

import com.africadevs.toa.R;
import com.africadevs.toa.databinding.ActivityMainBinding;
import com.africadevs.toa.interfaces.MainActivityCallbackInterface;
import com.africadevs.toa.ui.FragmentActivity;
import com.africadevs.toa.ui.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements MainActivityCallbackInterface {

    public static final int FRAGMENT_INDEX_PREVENTION = 1;
    public static final int FRAGMENT_INDEX_PREVENTIO_FIRSTN = 11;
    public static final int FRAGMENT_INDEX_PREVENTIO_SECOND = 12;
    public static final int FRAGMENT_INDEX_PREVENTIO_THIRD = 13;
    public static final int FRAGMENT_INDEX_DIAGNOSIS = 2;
    public static final String EXTRA_FRAGMENT_INDEX = "org.africadevs.toa.frag.index";
    private static final int MAIN_ACTIVITY_REQUEST_CODE = 10;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
                getWindow().setAllowEnterTransitionOverlap(true);


            } catch (Exception ex) {
            }
        }


        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        // Handle toolbar actions
        handleToolbar();

        // Handle drawer actions
        handleDrawer();

        goToFragment(null, false);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        binding.appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {

                    ViewCompat.animate(binding.toaLogo).setDuration(400).alpha(1f).start();
                    //EXPANDED;

                    binding.collapsingToolabar.setTitleEnabled(false);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {

                    ViewCompat.animate(binding.toaLogo).setDuration(200).alpha(0f).start();
                    //COLLAPSED;

                    binding.collapsingToolabar.setTitleEnabled(false);
                } else {
                    //binding.appBar.collapsingToolabar.setTitleEnabled(false);

                    //IDDLE
                }
            }
        });
    }

    private void handleToolbar() {
        setSupportActionBar(binding.toolbar);
    }

    private void handleDrawer() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawer, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawer.addDrawerListener(toggle);
        toggle.syncState();

    }


    private void goToFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        if (fragment != null)
            transaction.replace(R.id.container, fragment).commit();
        else
            transaction.replace(R.id.container, new MainFragment()).commit();
    }

    @Override
    public void cardSelected(int poisition, final View sharedView) {

        if (poisition == FRAGMENT_INDEX_PREVENTION) {
            YoYo.with(Techniques.Bounce).onEnd(new YoYo.AnimatorCallback() {
                @Override
                public void call(Animator animator) {

                    Intent intent = new Intent(MainActivity.this, FragmentActivity.class);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptionsCompat optionsCompat =
                                ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, sharedView, ViewCompat.getTransitionName(sharedView));

                        ActivityCompat.startActivityForResult(MainActivity.this, intent, MAIN_ACTIVITY_REQUEST_CODE, optionsCompat.toBundle());
                    } else {
                        startActivityForResult(intent, MAIN_ACTIVITY_REQUEST_CODE);
                    }
                }
            }).playOn(sharedView.getRootView().findViewById(R.id.prevention_container));

        } else { //Diagnosis
            YoYo.with(Techniques.Bounce).onEnd(new YoYo.AnimatorCallback() {
                @Override
                public void call(Animator animator) {

                    Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                    intent.putExtra(EXTRA_FRAGMENT_INDEX, FRAGMENT_INDEX_DIAGNOSIS);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptionsCompat optionsCompat =
                                ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, sharedView, ViewCompat.getTransitionName(sharedView));

                        ActivityCompat.startActivityForResult(MainActivity.this, intent, MAIN_ACTIVITY_REQUEST_CODE, optionsCompat.toBundle());
                    } else {
                        startActivityForResult(intent, MAIN_ACTIVITY_REQUEST_CODE);
                    }
                }
            }).playOn(sharedView.getRootView().findViewById(R.id.diagnosis_container));
        }

    }

}
