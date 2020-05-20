package org.changemakers.toa;

import android.app.Application;

import org.changemakers.toa.utils.FontsOverride;

public class AppSingleton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //override default font (fixed by monospace in v/styles & v21/styles
        FontsOverride.setDefaultFont(this, "DEFAULT", "airbnb_cereal_light.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "airbnb_cereal_light.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "airbnb_cereal_light.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "airbnb_cereal_medium.ttf");
    }
}
