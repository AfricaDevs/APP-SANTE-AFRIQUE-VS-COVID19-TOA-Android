package org.changemakers.toa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.PointerIcon;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

import org.changemakers.toa.databinding.ActivityMainBinding;
import org.changemakers.toa.databinding.ToaViewHeaderBinding;
import org.changemakers.toa.ui.MainFragment;

import java.util.ArrayList;
import java.util.Arrays;

import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {

    ActivityMainBinding binding;
    ToaViewHeaderBinding bindingHeaderView;

    private MenuAdapter mMenuAdapter;

    private ArrayList<String> mTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.menuOptions)));

        // Handle toolbar actions
        handleToolbar();

        // Handle menu actions
        handleMenu();

        // Handle drawer actions
        handleDrawer();

        mMenuAdapter.setViewSelected(0, true);
        setTitle(mTitles.get(0));
        goToFragment(null, false);

        //underline header view clickable TextViews
        TextView privacyPoliciesView  = binding.menuView.getHeaderView().findViewById(R.id.privacy_policies);
        TextView termsOfUseView  = binding.menuView.getHeaderView().findViewById(R.id.terms_of_use);

        privacyPoliciesView.setPaintFlags(privacyPoliciesView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        termsOfUseView.setPaintFlags(termsOfUseView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        binding.appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {

                    ViewCompat.animate(binding.toaLogo).setDuration(400).alpha(1f).start();
                    //ViewCompat.animate(binding.toaLogo).setDuration(500).scaleX(1f).start();
                    //ViewCompat.animate(binding.toaLogo).setDuration(500).scaleY(1f).start();
                    //EXPANDED;
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {

                    ViewCompat.animate(binding.toaLogo).setDuration(200).alpha(0f).start();
                    //ViewCompat.animate(binding.toaLogo).setDuration(200).scaleX(0f).start();
                    //ViewCompat.animate(binding.toaLogo).setDuration(200).scaleY(0f).start();
                    //COLLAPSED;
                } else {


                    //IDDLE
                }
            }
        });
    }

    private void handleToolbar() {
        setSupportActionBar(binding.toolbar);
    }

    private void handleDrawer() {
        DuoDrawerToggle duoDrawerToggle = new DuoDrawerToggle(this,
                binding.drawer,
                binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);


        binding.drawer.setDrawerListener(duoDrawerToggle);
        duoDrawerToggle.syncState();

    }

    private void handleMenu() {
        mMenuAdapter = new MenuAdapter(mTitles);

        binding.menuView.setOnMenuClickListener(this);
    }

    @Override
    public void onFooterClicked() {

    }

    @Override
    public void onHeaderClicked() {

    }

    @Override
    public void onOptionClicked(int position, Object objectClicked) {
        // Set the toolbar title
        setTitle(mTitles.get(position));

        // Set the right options selected
        mMenuAdapter.setViewSelected(position, true);

        // Navigate to the right fragment
        switch (position) {
            case 0:
                goToFragment(new MainFragment(), false);
                break;
            default:
                goToFragment(new MainFragment(), false);
                break;
        }

        // Close the drawer
        binding.drawer.closeDrawer();
    }


    private void goToFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        if(fragment!=null)
        transaction.add(R.id.container, fragment).commit();
        else
        transaction.add(R.id.container, new MainFragment()).commit();
    }
}
