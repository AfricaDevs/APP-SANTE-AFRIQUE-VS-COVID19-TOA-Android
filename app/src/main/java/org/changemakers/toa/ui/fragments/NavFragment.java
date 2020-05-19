package org.changemakers.toa.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.changemakers.toa.R;
import org.changemakers.toa.databinding.FragmentNavBinding;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     TeamFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class NavFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";


    private FragmentNavBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNavBinding.inflate(getLayoutInflater());


        binding.privacyPolicies.setOnClickListener(this);

        return binding.getRoot();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.privacy_policies:
                Intent ppIntent = new Intent(Intent.ACTION_VIEW);
                ppIntent.setData(Uri.parse(getString(R.string.privacy_policies_link)));
                try{
                    startActivity(ppIntent);

                } catch (Exception ex){
                    Toast.makeText(getActivity(), getString(R.string.alert_cant_handle), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
