package org.changemakers.toa.interfaces;


import android.view.View;

public interface ActivityCallbackInterface {
    public void cardSelected(int pisition, View sharedView);

    public void onPreventionOptionSelected(View view, int poisition);

    public void onDiagnosis(View view, int poisition);
}
