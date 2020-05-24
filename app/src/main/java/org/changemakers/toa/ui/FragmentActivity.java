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
import org.changemakers.toa.ui.fragments.prevention.PreventionFoodFragment;
import org.changemakers.toa.ui.fragments.prevention.PreventionFuneralFragment;
import org.changemakers.toa.ui.fragments.prevention.PreventionGarbageFragment;
import org.changemakers.toa.ui.fragments.prevention.PreventionHandsFragment;
import org.changemakers.toa.ui.fragments.prevention.PreventionMaskFragment;
import org.changemakers.toa.ui.fragments.prevention.PreventionMovementFragment;
import org.changemakers.toa.ui.fragments.prevention.PreventionWaterFragment;

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

            getWindow().setNavigationBarColor(getResources().getColor(R.color.materialGrey2));
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
    public void onPreventionOptionSelected(View view, int position) {

        switch (position) {
            case 0:
                PreventionHandsFragment fragmentPreventionHands = new PreventionHandsFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, fragmentPreventionHands)
                        .commit();

                break;
            case 1:
                PreventionMaskFragment fragmentPreventionMask = new PreventionMaskFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, fragmentPreventionMask)
                        .commit();

                break;
            case 2:
                PreventionMovementFragment fragmentPreventionMovement = new PreventionMovementFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, fragmentPreventionMovement)
                        .commit();
                break;
            case 3:
                PreventionFoodFragment fragmentPreventionFood = new PreventionFoodFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, fragmentPreventionFood)
                        .commit();
                break;
            case 4:
                PreventionWaterFragment fragmentPreventionWater = new PreventionWaterFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, fragmentPreventionWater)
                        .commit();
                break;
            case 5:
                PreventionGarbageFragment fragmentPreventionGarbage = new PreventionGarbageFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, fragmentPreventionGarbage)
                        .commit();
                break;
            case 6:
                PreventionFuneralFragment fragmentPreventionFuneral = new PreventionFuneralFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, fragmentPreventionFuneral)
                        .commit();
                break;
            default:

                break;
        }
    }
}
