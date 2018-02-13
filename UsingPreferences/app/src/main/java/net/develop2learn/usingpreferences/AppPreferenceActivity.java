package net.develop2learn.usingpreferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class AppPreferenceActivity extends PreferenceActivity  {

@Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

            PreferenceManager prefMgr = getPreferenceManager();
            prefMgr.setSharedPreferencesName("appPreferences");

            addPreferencesFromResource(R.xml.myapppreferences);
        }
}
