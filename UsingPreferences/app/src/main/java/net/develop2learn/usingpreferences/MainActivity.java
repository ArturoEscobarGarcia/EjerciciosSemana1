package net.develop2learn.usingpreferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.Display;
import android.view.View;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLoad(View view){
        Intent i = new Intent("net.develop2learn.usingpreferences1.AppPreferenceActivity");
        startActivity(i);
    }
    
    public void onClickDisplay(View v){
        /*SharedPreferences appPrefs = getSharedPreferences("net.develop2learn.usingpreferences_preferences",
                MODE_PRIVATE);*/
        SharedPreferences appPrefs = getSharedPreferences("appPreferences",MODE_PRIVATE);
        DisplayText(appPrefs.getString("editTextPref",""));
    }

    public void onClickModify(View v){
        /*SharedPreferences appPrefs = getSharedPreferences("net.develop2learn.usingpreferences_preferences",
                MODE_PRIVATE);*/
        SharedPreferences appPrefs = getSharedPreferences("appPreferences",MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = appPrefs.edit();
        prefsEditor.putString("editTextPref",((EditText) findViewById(R.id.txtString)).getText().toString());
        prefsEditor.commit();
    }

    private void DisplayText(String t){
        Toast.makeText(getBaseContext(),t, Toast.LENGTH_LONG).show();
    }
}
