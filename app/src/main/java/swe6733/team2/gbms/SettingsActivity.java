package swe6733.team2.gbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;


import com.google.android.gms.common.util.Strings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    //Component Variables
    //Main Setting
    Button updateMyInfo;
    Button updateGamingStyles;
    Switch turnOffOn;
    Button logOut;
    Button finish;


    //Update My Info
    EditText mi_changeUsername;
    EditText mi_changeEmail;
    EditText mi_changePassword;
    Button mi_savesChanges;

    LinearLayout mainSettingsLayout;
    LinearLayout updateMyInfoLayout;
    LinearLayout updateGamingStylesLayout;

    //Private Variables


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Main Setting Component Linking
        updateMyInfo=(Button)findViewById(R.id.updateMyInfo);
        updateGamingStyles=(Button)findViewById(R.id.updateGameStyle);
        turnOffOn=(Switch)findViewById(R.id.Switch);

        //Update My Info Component Linking
        mi_changeUsername=(EditText)findViewById(R.id.changeUsername);
        mi_changeEmail=(EditText)findViewById(R.id.changeEmail);
        mi_changePassword=(EditText)findViewById(R.id.changePassword);
        mi_savesChanges=(Button) findViewById(R.id.savesChanges);
        logOut=(Button)findViewById(R.id.log_out);
        finish=(Button)findViewById(R.id.finish);


        //Layout Linking
        mainSettingsLayout=(LinearLayout)findViewById(R.id.LL_MainSettings);
        updateMyInfoLayout=(LinearLayout)findViewById(R.id.LL_UpdateMyInfo);
        updateGamingStylesLayout=(LinearLayout)findViewById(R.id.LL_updateGamingStyles);


        //SCREEN NAVIGATION
        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //The Actual Selection for Screens
                switch (item.getItemId()) {
                    //Home Page Button
                    case R.id.Home_Page:
                        Intent homeIntent = new Intent(SettingsActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                        break;

                    //Settings Page Button
                    case R.id.Settings_Page:
                        //Intent settingsIntent = new Intent(SettingsActivity.this, SettingsActivity.class);
                        //startActivity(settingsIntent);
                        break;

                    //Match Making Page Button
                    case R.id.Matchmaking_Page:
                        Intent matchMakingIntent = new Intent(SettingsActivity.this, MatchMakingActivity.class);
                        startActivity(matchMakingIntent);
                        break;
                    //Sign in Page Button
                    case R.id.log_out:
                        Intent SigninIntent = new Intent(SettingsActivity.this, StartupActivity.class);
                        startActivity(SigninIntent);
                        break;

                }
                return false;
            }
        });
    }

    public void updateMyInfoClick(View view) //This swaps the ViewPort from anything, to the Update My Info Screen
    {
        //Ensure Everything Else is Invisible
        mainSettingsLayout.setVisibility(View.INVISIBLE);
        updateGamingStylesLayout.setVisibility(View.INVISIBLE);

        //Set SignUp Items to Visible
        updateMyInfoLayout.setVisibility(View.VISIBLE);


    }

    public void updateGamingStylesClick(View view) //This swaps the ViewPort from anything, to the Update Gaming Styles Screen
    {
        //Ensure Everything Else is Invisible
        mainSettingsLayout.setVisibility(View.INVISIBLE);
        updateMyInfoLayout.setVisibility(View.INVISIBLE);

        //Set SignUp Items to Visible
        updateGamingStylesLayout.setVisibility(View.VISIBLE);

    }

    public void mainSettingsClick(View view) //This swaps the ViewPort from anything, to the Main Setting Screen
    {
        //Ensure Everything Else is Invisible
        updateMyInfoLayout.setVisibility(View.INVISIBLE);
        updateGamingStylesLayout.setVisibility(View.INVISIBLE);

        //Set SignUp Items to Visible
        mainSettingsLayout.setVisibility(View.VISIBLE);

    }

}