package org.changemakers.toa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import org.changemakers.toa.databinding.ActivitySplashScreenBinding;

/**
 * An full-screen activity that hides the system UI (i.e.
 * status bar and navigation/system bar)
 *
 * Good UX approach to avoid brutal App landing
 */
public class SplashScreenActivity extends AppCompatActivity {

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 3000;
    private static final int UI_ANIMATION_DURATION = 2600;
    private final Handler mLoadingHandler = new Handler();
    private ActivitySplashScreenBinding binding;

    private final Runnable mLoadingRunnable = new Runnable() {

        @Override
        public void run() {


            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            Pair<View, String> pair1 = Pair.create((View) binding.logo, "shared_logo");

            try {
                ActivityOptionsCompat optionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(SplashScreenActivity.this, pair1);

                ActivityCompat.startActivity(SplashScreenActivity.this, intent, optionsCompat.toBundle());
                //overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in);
            } catch (Exception ex) {
                ex.printStackTrace();

                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable transitions for the logo, from API LEVEL 21
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }

        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //annimate logo size
        ViewCompat.animate(binding.logo).scaleX(1f).setDuration(1000).start();
        ViewCompat.animate(binding.logo).scaleY(1f).setDuration(1000).start();

    }

    @SuppressLint("InlinedApi")
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);



        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined [@SuppressLint("InlinedApi")]
        // at compile-time and do nothing on earlier devices.
        binding.getRoot().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        loadMain(UI_ANIMATION_DELAY);

        TransitionDrawable transition = (TransitionDrawable) binding.getRoot().getBackground();
        transition.startTransition(UI_ANIMATION_DELAY);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    /**
     * Schedules a call to load the main Activity, canceling any
     * previously scheduled calls.
     */
    private void loadMain(int delayMillis) {
        mLoadingHandler.removeCallbacks(mLoadingRunnable);
        mLoadingHandler.postDelayed(mLoadingRunnable, delayMillis);
    }
}
