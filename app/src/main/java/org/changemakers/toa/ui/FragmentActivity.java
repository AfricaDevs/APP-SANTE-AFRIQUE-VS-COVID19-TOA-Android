package org.changemakers.toa.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.changemakers.toa.MainActivity;
import org.changemakers.toa.R;
import org.changemakers.toa.databinding.ActivityFragmentBinding;
import org.changemakers.toa.interfaces.ActivityCallbackInterface;
import org.changemakers.toa.ui.fragments.PreventionFragment;
import org.changemakers.toa.ui.fragments.prevention.PreventionHandsFragment;

public class FragmentActivity extends AppCompatActivity implements ActivityCallbackInterface {

    ActivityFragmentBinding binding;
    Fragment mFragment;

    //init to PREVENTION
    private int poisition = MainActivity.FRAGMENT_INDEX_PREVENTION;

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

        if (Build.VERSION.SDK_INT > 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        binding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (savedInstanceState == null && intent != null) {

            poisition = intent.getIntExtra(MainActivity.EXTRA_FRAGMENT_INDEX, MainActivity.FRAGMENT_INDEX_PREVENTION);
            switch (poisition) {
                case MainActivity.FRAGMENT_INDEX_PREVENTION:
                    mFragment = new PreventionFragment();
                    mFragment.setArguments(intent.getExtras());

                    /*
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP  ) {
                        mFragment.setSharedElementEnterTransition(TransitionInflater.from(
                                this).inflateTransition(R.transition.change_image_fragment_transition));
                        mFragment.setEnterTransition(TransitionInflater.from(
                                this).inflateTransition(android.R.transition.fade));
                    }
                     */

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, mFragment)
                            .commit();
                    break;
                default: //PREVENTION
                    mFragment = new PreventionFragment();
                    break;
            }

        }

    }

    @Override
    public void cardSelected(int poisition, View sharedView) {
        //IDDLE
    }

    @Override
    public void onPreventionOptionSelected(View view, int poisition) {

        PreventionHandsFragment fragmentPreventionHands = new PreventionHandsFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("main")
                .replace(R.id.fragment_container, fragmentPreventionHands)
                .commit();
    }
}
