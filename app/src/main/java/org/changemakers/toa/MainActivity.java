package org.changemakers.toa;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
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

import com.google.android.material.appbar.AppBarLayout;

import org.changemakers.toa.databinding.ActivityMainBinding;
import org.changemakers.toa.interfaces.MainActivityCallbackInterface;
import org.changemakers.toa.ui.FragmentActivity;
import org.changemakers.toa.ui.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements MainActivityCallbackInterface {

    public static final int FRAGMENT_INDEX_PREVENTION = 1;
    public static final int FRAGMENT_INDEX_PREVENTIO_FIRSTN = 11;
    public static final int FRAGMENT_INDEX_PREVENTIO_SECOND = 12;
    public static final int FRAGMENT_INDEX_PREVENTIO_THIRD = 13;
    public static final int FRAGMENT_INDEX_DIAGNOSIS = 2;

    private static final int MAIN_ACTIVITY_REQUEST_CODE = 10;
    public static final String EXTRA_FRAGMENT_INDEX = "org.africadevs.toa.frag.index";

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP  ) {
            try {
                getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
                getWindow().setAllowEnterTransitionOverlap(true);
            } catch (Exception ex) { }
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP  ) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION| WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setNavigationBarColor(getResources().getColor(R.color.navBarColor));
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
                    //binding.collapsingToolabar.setTitleEnabled(false);

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

        if(fragment!=null)
        transaction.replace(R.id.container, fragment).commit();
        else
        transaction.replace(R.id.container, new MainFragment()).commit();
    }

    @Override
    public void cardSelected(int poisition, View sharedView){


        Intent intent = new Intent(MainActivity.this, FragmentActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            /*
            setSharedElementReturnTransition(TransitionInflater.from(
                    MainActivity.this).inflateTransition(R.transition.change_image_fragment_transition));
            setExitTransition(TransitionInflater.from(
                    MainActivity.this).inflateTransition(android.R.transition.fade));
             */

            ActivityOptionsCompat optionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, sharedView, ViewCompat.getTransitionName(sharedView));

            ActivityCompat.startActivity(MainActivity.this, intent, optionsCompat.toBundle());
        } else {
            startActivity(new Intent(MainActivity.this, FragmentActivity.class));
        }

    }
}
