package org.changemakers.toa.interfaces;


import android.view.View;

public interface ActivityCallbackInterface {
    public void cardSelected(int poisition, View sharedView);

    public void onPreventionOptionSelected(View view, int poisition);
}
