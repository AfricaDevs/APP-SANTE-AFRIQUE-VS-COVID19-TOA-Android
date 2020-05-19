package org.changemakers.toa.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.changemakers.toa.MainActivity;
import org.changemakers.toa.R;
import org.changemakers.toa.databinding.ActivityFragmentBinding;
import org.changemakers.toa.ui.fragments.PreventionFragment;

public class FragmentActivity extends AppCompatActivity  {

    ActivityFragmentBinding binding;
    Fragment mFragment;

    //init to PREVENTION
    private int poisition = MainActivity.FRAGMENT_INDEX_PREVENTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor( Color.TRANSPARENT);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP  ) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        binding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        if(savedInstanceState==null && intent!=null){

            poisition = intent.getIntExtra(MainActivity.EXTRA_FRAGMENT_INDEX, MainActivity.FRAGMENT_INDEX_PREVENTION);
            switch (poisition){
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

}
