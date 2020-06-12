package org.changemakers.toa.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.changemakers.toa.MainActivity;
import org.changemakers.toa.R;
import org.changemakers.toa.databinding.ActivityFragmentBinding;
import org.changemakers.toa.interfaces.ActivityCallbackInterface;
import org.changemakers.toa.ui.fragments.DiagnosisFragment;
import org.changemakers.toa.ui.fragments.PreventionFragment;
import org.changemakers.toa.ui.fragments.diagnosis.DiagnosisFirstMalariaFragment;
import org.changemakers.toa.ui.fragments.diagnosis.DiagnosisFirstNoneFragment;
import org.changemakers.toa.ui.fragments.diagnosis.DiagnosisSecondDepthTemplate;
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

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, mFragment)
                            .commit();
                    break;
                case MainActivity.FRAGMENT_INDEX_DIAGNOSIS://DIAGNOSIS
                    mFragment = new DiagnosisFragment();
                    mFragment.setArguments(intent.getExtras());

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, mFragment)
                            .commit();
                    break;
                default:

                    break;
            }

        }

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

    @Override
    public void onDiagnosisOptionSelected(View view, int depth, int position) {
        //TODO handle

        switch (depth) {
            case DiagnosisFragment.DIAGNOSIS_OPTIONS_FIRST_DEPTH:
                switch (position) {

                    //if any symptom, then Malaria is suspected
                    case DiagnosisFragment.DIAGNOSIS_OPTIONS_FIRST_DEPTH_POSITION_SYMPTOMS:

                        DiagnosisFirstMalariaFragment fragmentMalaria = new DiagnosisFirstMalariaFragment();

                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragment_container, fragmentMalaria)
                                .commit();
                        break;

                    //NO SYMPTOMS
                    case DiagnosisFragment.DIAGNOSIS_OPTIONS_FIRST_DEPTH_POSITION_NO_SYMPTOM:

                        DiagnosisFirstNoneFragment fragmentNoSymptoms = new DiagnosisFirstNoneFragment();

                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragment_container, fragmentNoSymptoms)
                                .commit();
                        break;


                }
                break;
            case DiagnosisFragment.DIAGNOSIS_OPTIONS_SECOND_DEPTH:

                switch (position) {

                    //when in MalariaFragment, the next button takes to FirstNoneFragment
                    case DiagnosisFragment.DIAGNOSIS_OPTIONS_SECOND_DEPTH_POSITION_MALARIA_NEXT:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .add(R.id.fragment_container, new DiagnosisFirstNoneFragment())
                                .commit();
                        break;


                    default:

                        //when in MalariaFragment, the next button takes to FirstNoneFragment
                        DiagnosisSecondDepthTemplate secondDepthDisease = new DiagnosisSecondDepthTemplate();
                        Bundle data = new Bundle();
                        data.putInt(DiagnosisFragment.EXTRA_SECOND_DEPTH_POSITION, position);
                        secondDepthDisease.setArguments(data);

                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .add(R.id.fragment_container, secondDepthDisease)
                                .commit();
                        break;
                }
                break;
            case DiagnosisFragment.DIAGNOSIS_OPTIONS_THIRD_DEPTH:

                switch (position) {
                    case DiagnosisFragment.DIAGNOSIS_OPTIONS_SECOND_DEPTH_POSITION_ANEMIA:

                        break;
                }

                break;

            //This special position will be used accros the App to load the prevention Fragment
            case DiagnosisFragment.DIAGNOSIS_OPTIONS_PREVENTION_DEPTH:

                //clear stack , back to depth 0
                onBackPressed();

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new PreventionFragment())
                        .commit();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
