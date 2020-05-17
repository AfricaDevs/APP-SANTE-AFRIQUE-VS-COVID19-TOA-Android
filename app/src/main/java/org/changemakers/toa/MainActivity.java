package org.changemakers.toa;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.AppBarLayout;

import org.changemakers.toa.databinding.ActivityMainBinding;
import org.changemakers.toa.ui.MainFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity  {

    ActivityMainBinding binding;

    private ArrayList<String> mTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP  ) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION| WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setNavigationBarColor(getResources().getColor(R.color.shapes_green_color));
        }

        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.menuOptions)));

        // Handle toolbar actions
        handleToolbar();

        // Handle drawer actions
        handleDrawer();

        goToFragment(null, false);

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
}
