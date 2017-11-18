package org.firstinspires.ftc.robotcontroller.internal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.qualcomm.ftcrobotcontroller.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Autonomous Options - Related to additonal functionality added to RC - Distance, red or blue alliance
 */

public class Autonomous_Options extends Activity implements AdapterView.OnItemSelectedListener  {

    SharedPreferences internalPreferences;
    EditText distance;
    TextView distance_text;

    Spinner alliance_colour_selector;
    ArrayAdapter<CharSequence> alliance_colour_adapter;

    EditText knockball_edit;

    EditText alliance_colour_edit;

    String knockball_str;


    String alliance_colour;

    Button btn_save;

    double distance_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autonomous_menu);
        internalPreferences = this.getSharedPreferences("org.firstinspires.ftc.robotcontroller", Context.MODE_PRIVATE);
        //open_settings();

        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.startup_windows);
        mp.start();


        setupAllianceSelector();
        setupDistance();
        setupKnockball();
        btn_save = (Button) findViewById(R.id.save_btn);

    }

    public void saveAndExit(View v)
    {
        //commit changes
        alliance_colour = alliance_colour_edit.getText().toString();
        knockball_str = knockball_edit.getText().toString();
        internalPreferences.edit().putFloat("Distance Travel", ((float) distance_val)).commit();
        internalPreferences.edit().putString("Alliance Colour", alliance_colour).commit();
        internalPreferences.edit().putString("Knock Ball", knockball_str).commit();
        save_settings();
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.startup_windows);
        mp.start();
        finish();
    }

    public void setupAllianceSelector()
    {
        alliance_colour_edit = (EditText) findViewById(R.id.alliance_edit);
        alliance_colour_edit.setText(internalPreferences.getString("Alliance Colour",""));
        /*
        alliance_colour_selector = (Spinner) findViewById(R.id.alliance_selector);

        alliance_colour_adapter = ArrayAdapter.createFromResource(this, R.array.alliance_array, android.R.layout.simple_spinner_item);
mai
        alliance_colour_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        alliance_colour_selector.setAdapter(alliance_colour_adapter);

        alliance_colour_selector.setOnItemSelectedListener(this);

        alliance_colour_selector.setSelection(alliance_colour_adapter.getPosition(internalPreferences.getString("Alliance Colour","")));
    */
    }

    public void setupDistance()
    {
        distance = (EditText) findViewById(R.id.dist_value);
        distance_text = (TextView) findViewById(R.id.dist_name);

    }

    public void setupKnockball()
    {
        knockball_edit = (EditText) findViewById(R.id.knock_edit);
        knockball_edit.setText(internalPreferences.getString("Knock Ball",""));


    }

    @Deprecated
    public void updateSettingsFromPrefs()
    {

        Log.i("CREATION","I am in the updatePrefs");

       internalPreferences.getString("Alliance Colour", alliance_colour);
        if (alliance_colour != null) {
            if (alliance_colour.equals("Red")) {
                alliance_colour_selector.setSelection(0);
            } else if (alliance_colour.equals("Blue")) {
                alliance_colour_selector.setSelection(1);
            }
        }
        internalPreferences.getFloat("Distance Travel",(float) distance_val);
        Log.i("CREATION","Distance Val"+ distance_val);
        if (distance_val !=0)
        {
            Log.i("CREATION","I am in the updatePrefs-Distance");
            distance.setText(Double.toString(distance_val));
        }

    }

    public void save_settings() {

        //Merge Changes
        SharedPreferences globalPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = globalPrefs.edit();
        editor.putFloat("Distance Travel", ((float) distance_val));
        editor.putString("Alliance Colour", internalPreferences.getString("Alliance Colour",""));
        editor.putString("Knock Ball", internalPreferences.getString("Knock Ball",""));
        editor.apply();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

/*
        if (parent.getId() == R.id.alliance_selector)
        {
            alliance_colour = parent.getItemAtPosition(position).toString();
        }

        if (parent.getId() == R.id.knockball_selector)
        {
            knockball_str = parent.getItemAtPosition(position).toString();
        }
        */
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}