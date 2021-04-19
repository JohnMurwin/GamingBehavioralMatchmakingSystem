package swe6733.team2.gbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

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


    //Update My Info
    EditText mi_changeUsername;
    EditText mi_changeEmail;
    EditText mi_changePassword;
    Button mi_savesChanges;

    LinearLayout mainSettingsLayout;
    LinearLayout updateMyInfoLayout;

    //Private Variables
    private int settingMode;

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

        //Layout Linking
        mainSettingsLayout=(LinearLayout)findViewById(R.id.LL_MainSettings);
        updateMyInfoLayout=(LinearLayout)findViewById(R.id.LL_UpdateMyInfo);

    }

    //Main Setting Click
    public void SignInClick(View vIew)
    {
        //Ensure Everything Else is Invisible
        updateMyInfoLayout.setVisibility(View.INVISIBLE);

        //Set Main Setting Items to Visible
        mainSettingsLayout.setVisibility(View.VISIBLE);

        //Set Setting Mode
        settingMode = 0;

    }

    //Main Setting Click
    public void SignOutClick(View vIew)
    {
        //Ensure Everything Else is Invisible
        mainSettingsLayout.setVisibility(View.INVISIBLE);

        //Set Main Setting Items to Visible
        updateMyInfoLayout.setVisibility(View.VISIBLE);

        //Set Setting Mode
        settingMode = 1;

    }
}