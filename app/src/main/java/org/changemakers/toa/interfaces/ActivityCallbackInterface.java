package org.changemakers.toa.interfaces;


import android.view.View;

public interface ActivityCallbackInterface {

    public void onPreventionOptionSelected(View view, int poisition);

    //We use 3 args here. The depth is placed for hierarchie mangagement
    public void onDiagnosisOptionSelected(View view, int depth, int poisition);
}
