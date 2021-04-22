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
import android.widget.ScrollView;
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
import com.google.common.io.LineReader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    //Firebase Variables
    private FirebaseAuth firebaseAuth;  //Instance to the FirebaseAuth System
    private FirebaseUser currentUser;

    private FirebaseDatabase database;  //Instance to Firebase Realtime Database System
    private DatabaseReference dbRef;    //Instance to the Particular Database We Want

    /* Component Variables */
    //Root Layouts
    LinearLayout mainSettingsLayout;
    LinearLayout updateAccountLayout;
    ScrollView accountPreferencesScrollView;

    LinearLayout testLayout;

    //Main Settings
    Button ms_updateAccountInfoPB;
    Button ms_updateGamePreferencesPB;
    Switch ms_matchingOnOffSwitch;
    Button ms_logOutPB;
    Button ms_deleteAccountPB;

    //Update Account
    EditText ua_changeUsernameInput;
    EditText ua_changeEmailInput;
    EditText ua_changePasswordOldInput;
    EditText ua_changePasswordNewInput;
    Button ua_saveChangesPB;
    Button ua_backPB;

    //Account Preferences


    //Private Variables


    //OnCreate Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Layout Linking
        mainSettingsLayout = (LinearLayout) findViewById(R.id.LL_MainSettings);
        updateAccountLayout = (LinearLayout) findViewById(R.id.LL_UpdateAccount);
        accountPreferencesScrollView = (ScrollView) findViewById(R.id.SV_AccountPreferences);
        testLayout = (LinearLayout) findViewById(R.id.LL_TestLayout);

        //Main Component Linking
        ua_changeUsernameInput = (EditText) findViewById(R.id.ET_UA_ChangeUsername);
        ua_changeEmailInput = (EditText) findViewById(R.id.ET_UA_ChangeEmail);
        ua_changePasswordOldInput = (EditText) findViewById(R.id.ET_UA_PasswordOld);
        ua_changePasswordNewInput = (EditText) findViewById(R.id.ET_UA_PasswordNew);
        ua_saveChangesPB = (Button) findViewById(R.id.PB_UA_SaveChanges);
        ua_backPB = (Button) findViewById(R.id.PB_UA_Back);


        //Firebase Auth Instancing
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        //Firebase Realtime Database Instancing
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();

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
                }
                return false;
            }
        });
    }

    //Update Account Button Click
    public void updateAccountClick(View view) //This swaps the ViewPort from anything, to the Update My Info Screen
    {
        //Toast.makeText(getApplicationContext(), "Account Settings Screen ", Toast.LENGTH_SHORT).show();

        mainSettingsLayout.setVisibility(View.INVISIBLE);
        accountPreferencesScrollView.setVisibility(View.INVISIBLE);
        //updateAccountLayout.setVisibility(View.INVISIBLE);

        updateAccountLayout.setVisibility(View.VISIBLE);

    }

    //Update User Preferences Button Click
    public void updatePreferencesClick(View view) //This swaps the ViewPort from anything, to the Update Gaming Styles Screen
    {
        //Toast.makeText(getApplicationContext(), "Account Preferences Screen ", Toast.LENGTH_SHORT).show();

        mainSettingsLayout.setVisibility(View.INVISIBLE);
        //accountPreferencesScrollView.setVisibility(View.INVISIBLE);
        updateAccountLayout.setVisibility(View.INVISIBLE);

        accountPreferencesScrollView.setVisibility(View.VISIBLE);
    }

    //Main Settings Button Click
    public void mainSettingsClick(View view) //This swaps the ViewPort from anything, to the Main Setting Screen
    {
        //Toast.makeText(getApplicationContext(), "Main Settings Screen ", Toast.LENGTH_SHORT).show();

        //mainSettingsLayout.setVisibility(View.INVISIBLE);
        accountPreferencesScrollView.setVisibility(View.INVISIBLE);
        updateAccountLayout.setVisibility(View.INVISIBLE);

        mainSettingsLayout.setVisibility(View.VISIBLE);

    }

    //Update User Account Information (Basic Auth & Database, NO Preferences)
    public void UpdateAccountInfo (View view)   //Actually updates the users information when they hit a PushButton (OnCall)
    {
        //First grab data from User Input
        String newUsername = ua_changeUsernameInput.getText().toString();
        String newEmail = ua_changeEmailInput.getText().toString().toLowerCase();   //want to normalize emails
        String newPassword = ua_changePasswordOldInput.getText().toString();    //TODO: Implement a Verification System that recognizes passwords and is actually smart

        //See if we Need to Update Username
        if (!Strings.isEmptyOrWhitespace(newUsername)) {
            try { //to update
                //Create Builder with Updated Profile Information
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(newUsername)
                        .build();

                //Actually try to Update Profile
                currentUser.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    //Update Database UserName as Well
                                    dbRef.child("users").child(currentUser.getUid()).child("userName").setValue(newUsername);

                                    //Alert User
                                    Toast.makeText(getApplicationContext(), "Your Username has been Updated! ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch (Exception ex) { //fail to update username
                Toast.makeText(getApplicationContext(), "Update to Username failed. " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        //See if we Need to Update Email
        if (!Strings.isEmptyOrWhitespace(newEmail)) {
            try { //to update
                currentUser.updateEmail(newEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Your Email has been Updated! ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch (Exception ex) { //fail to update email
                Toast.makeText(getApplicationContext(), "Update to Email failed. " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        //See if we Need to Update Password
        if (!Strings.isEmptyOrWhitespace(newPassword)) {
            try { //to update
                currentUser.updatePassword(newPassword)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "Your Password has been Updated! ", Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (Exception ex) { //fail to update password

            }
        }
    }

    //Auth Logout
    public void Logout (View view)   //Logs the User out of the Application using the Auth System
    {
        //TODO: THIS DOESNT ACTUALLY WORK SO RIP

        //Set CurrentUser to Null
        currentUser = null;

        //Then Call Startup Activity
        final Intent startupActivityIntent = new Intent(getApplicationContext(), StartupActivity.class);

        //CHANGE ACTIVITIES HERE
        startActivity(startupActivityIntent);
    }

    //User Delete
    public void DeleteAccount (View view)   //Logs the User out of the Application using the Auth System
    {
        try { //to delete user
            currentUser.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                //Then Delete Database Data
                                //TODO: Figure out why Auth Account doesnt delete, but Database Does
                                //dbRef.child("users").child(currentUser.getUid()).removeValue();

                                //Then Call Startup Activity
                                final Intent startupActivityIntent = new Intent(getApplicationContext(), StartupActivity.class);

                                //CHANGE ACTIVITIES HERE
                                startActivity(startupActivityIntent);

                                //Toast User
                                Toast.makeText(getApplicationContext(), "Your Account Has Been Deleted! ", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        } catch (Exception ex) { //fail to delete user
            //Toast User
            Toast.makeText(getApplicationContext(), "I Dont Know, Something Happened:  " + ex, Toast.LENGTH_SHORT).show();
        }
    }
}