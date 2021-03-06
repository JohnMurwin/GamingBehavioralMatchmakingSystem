package swe6733.team2.gbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.Strings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartupActivity extends AppCompatActivity {
    /* Component Variables */
    //Sign In
    Button si_loginPB;
    EditText si_usernameInput;
    EditText si_passwordInput;
    TextView si_signupTV;
    TextView si_forgotPasswordTV;

    //Sign Up
    EditText su_emailInput;
    EditText su_firstNameInput;
    EditText su_lastNameInput;
    EditText su_dobInput;
    EditText su_usernameInput;
    EditText su_passwordInput;
    Button su_createNewAccountPB;

    //Account Setup
    SeekBar as_aggressionSeekBar;
    RadioGroup as_languageSelectionGroup;
    RadioGroup as_communicationStyleSelectionGroup;
    CheckBox as_assaultClass;
    CheckBox as_heavyClass;
    CheckBox as_medicClass;
    CheckBox as_reconClass;
    CheckBox as_supportClass;
    CheckBox as_warriorClass;
    CheckBox as_thiefClass;
    CheckBox as_clericClass;
    CheckBox as_rangerClass;
    CheckBox as_wizardClass;
    CheckBox as_horrorStyle;
    CheckBox as_sandboxStyle;
    CheckBox as_zombieStyle;
    CheckBox as_baseball;
    CheckBox as_football;
    CheckBox as_soccer;
    CheckBox as_hockey;
    CheckBox as_basketball;
    CheckBox as_racingGames;
    CheckBox as_buildingSimulation;
    CheckBox as_battleRoyale;
    Button as_finishAccountPB;

    //Reset Password
    TextView rp_instructionText;
    EditText rp_resetEmail;
    Button rp_resetPB;

    LinearLayout signInLayout;
    LinearLayout signUpLayout;
    LinearLayout forgotPasswordLayout;
    ScrollView accountSetupScrollView;  //this is acting as a parent layout

    //Firebase Variables
    private FirebaseAuth firebaseAuth;  //Instance to the FirebaseAuth System
    private FirebaseUser currentUser;

    private FirebaseDatabase database;  //Instance to Firebase Realtime Database System
    private DatabaseReference dbRef;    //Instance to the Particular Database We Want

    /* Private Variables */
    private static final String TAG = "GBMS: MainActivity -";

    private int loginMode;  //0 = Sign In, 1 = Sign Up, 3 = Recovery
    private int resetMode; //0 = Reset, 1 = Return

    private int aggressionSeekBarValue;

    boolean assaultTicked;
    boolean heavyTicked;
    boolean medicTicked;
    boolean reconTicked;
    boolean supportTicked;

    boolean warriorTicked;
    boolean thiefTicked;
    boolean clericTicked;
    boolean rangerTicked;
    boolean wizardTicked;

    boolean horrorTicked;
    boolean sandboxTicked;
    boolean zombieTicked;

    boolean baseballTicked;
    boolean footballTicked;
    boolean soccerTicked;
    boolean hockeyTicked;
    boolean basketballTicked;

    boolean racingTicked;
    boolean buildingTicked;
    boolean battleroyaleTicked;

    String first;
    String last;
    String email;
    String pass;
    String username;
    String ageValue;
    int age;

    String languageSelection;
    String communicationStyle;


    //OnCreate Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        //Sign In Component Linking
        si_usernameInput = (EditText) findViewById(R.id.ET_SI_Username);
        si_passwordInput = (EditText) findViewById(R.id.ET_SI_Password);
        si_loginPB = (Button) findViewById(R.id.PB_SI_Login);
        si_signupTV = (TextView) findViewById(R.id.PT_SI_CreateNewAccount);
        si_forgotPasswordTV = (TextView) findViewById(R.id.PT_SI_ForgotPassword);

        //Sign Up Component Linking
        su_emailInput = (EditText) findViewById(R.id.ET_SU_EmailAddressInput);
        su_firstNameInput = (EditText) findViewById(R.id.ET_SU_FirstNameInput);
        su_lastNameInput = (EditText) findViewById(R.id.ET_SU_LastNameInput);
        su_dobInput = (EditText) findViewById(R.id.ET_SU_AgeInput);
        su_usernameInput = (EditText) findViewById(R.id.ET_SU_UsernameInput);
        su_passwordInput = (EditText) findViewById(R.id.ET_SU_PasswordInput);
        su_createNewAccountPB = (Button) findViewById(R.id.PB_SU_CreateNewAccount);

        //Reset Password Component Linking
        rp_instructionText = (TextView) findViewById(R.id.TV_RP_ResetText);
        rp_resetEmail = (EditText) findViewById(R.id.ET_RP_Username);
        rp_resetPB = (Button) findViewById(R.id.PB_PR_LoginReturn);

        //Account Setup Component Linking
        as_languageSelectionGroup = (RadioGroup) findViewById(R.id.RG_Language);
        as_aggressionSeekBar = (SeekBar) findViewById(R.id.SBD_AggressionStyle);
        as_communicationStyleSelectionGroup = (RadioGroup) findViewById(R.id.RG_Communication);
        as_assaultClass = (CheckBox) findViewById(R.id.CB_SU_Assault);
        as_heavyClass = (CheckBox) findViewById(R.id.CB_SU_Heavy);;
        as_medicClass = (CheckBox) findViewById(R.id.CB_SU_Medic);;
        as_reconClass = (CheckBox) findViewById(R.id.CB_SU_Recon);;
        as_supportClass = (CheckBox) findViewById(R.id.CB_SU_Support);;
        as_warriorClass = (CheckBox) findViewById(R.id.CB_SU_Warrior);;
        as_thiefClass = (CheckBox) findViewById(R.id.CB_SU_Thief);;
        as_clericClass = (CheckBox) findViewById(R.id.CB_SU_Cleric);;
        as_rangerClass = (CheckBox) findViewById(R.id.CB_SU_Ranger);;
        as_wizardClass = (CheckBox) findViewById(R.id.CB_SU_Wizard);;
        as_horrorStyle = (CheckBox) findViewById(R.id.CB_SU_Horror);;
        as_sandboxStyle = (CheckBox) findViewById(R.id.CB_SU_Sandbox);;
        as_zombieStyle = (CheckBox) findViewById(R.id.CB_SU_Zombie);;
        as_baseball = (CheckBox) findViewById(R.id.CB_SU_Baseball);;
        as_football = (CheckBox) findViewById(R.id.CB_SU_Football);;
        as_soccer = (CheckBox) findViewById(R.id.CB_SU_Soccer);;
        as_hockey = (CheckBox) findViewById(R.id.CB_SU_Hockey);;
        as_basketball = (CheckBox) findViewById(R.id.CB_SU_Basketball);;
        as_racingGames = (CheckBox) findViewById(R.id.CB_SU_Racing);;
        as_buildingSimulation = (CheckBox) findViewById(R.id.CB_SU_BuildingSimulation);;
        as_battleRoyale = (CheckBox) findViewById(R.id.CB_SU_BattleRoyale);;
        as_finishAccountPB = (Button) findViewById(R.id.PB_AS_FinishAccount);

        //Layout Linking
        signInLayout = (LinearLayout) findViewById(R.id.LL_SignIn);
        signUpLayout = (LinearLayout) findViewById(R.id.LL_SignUp);
        forgotPasswordLayout = (LinearLayout) findViewById(R.id.LL_ForgotPassword);
        accountSetupScrollView = (ScrollView) findViewById(R.id.SV_AccountPreferences);


        //Firebase Auth Instancing
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        //Firebase Realtime Database Instancing
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();


        /* COMPONENT REQUIREMENT METHODS */
        as_aggressionSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aggressionSeekBarValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    //OnStart Override
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly
        if (currentUser != null) {

            //Login Setup
            //final Intent homeActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);

            //CHANGE ACTIVITIES HERE
            //startActivity(homeActivityIntent);
        }
    }


    //Sign In Button Click
    public void SignInClick(View view) //This swaps the ViewPort from anything, to the Sign In Screen
    {
        //Ensure Everything Else is Invisible
        //signInLayout.setVisibility(View.INVISIBLE);
        signUpLayout.setVisibility(View.INVISIBLE);
        forgotPasswordLayout.setVisibility(View.INVISIBLE);
        accountSetupScrollView.setVisibility(View.INVISIBLE);

        //Set SignIn Items to Visible
        signInLayout.setVisibility(View.VISIBLE);

        //Set loginMode
        loginMode = 0;
    }

    //Sign Up Button Click
    public void SignUpClick(View view) //This swaps the ViewPort from anything, to the Sign Up Screen
    {
        //Ensure Everything Else is Invisible
        signInLayout.setVisibility(View.INVISIBLE);
        //signUpLayout.setVisibility(View.INVISIBLE);
        forgotPasswordLayout.setVisibility(View.INVISIBLE);
        accountSetupScrollView.setVisibility(View.INVISIBLE);


        //Set SignUp Items to Visible
        signUpLayout.setVisibility(View.VISIBLE);

        //Set loginMode
        loginMode = 1;
    }

    //Forgot Password Button Click
    public void ForgotPasswordClick(View view) //This swaps the ViewPort from anything, to the Forgot Password Screen
    {
        //Ensure Everything Else is Invisible
        signInLayout.setVisibility(View.INVISIBLE);
        signUpLayout.setVisibility(View.INVISIBLE);
        //forgotPasswordLayout.setVisibility(View.INVISIBLE);
        accountSetupScrollView.setVisibility(View.INVISIBLE);


        //Set ForgotPassword Items to Visible
        forgotPasswordLayout.setVisibility(View.VISIBLE);

        //Ensure Proper Text is Set
        rp_instructionText.setText("Input your email and press \"Reset\" to send password reset link!");
        rp_resetPB.setText ("Reset");

        //Set loginMode
        loginMode = 2;
    }

    //Continue Account Button Creation Click
    public void ContinueAccountCreationClick (View view) //This swaps the ViewPort from the SignUp Intro Screen, to the Account Setup Screen
    {
        //Ensure Everything Else is Invisible
        signInLayout.setVisibility(View.INVISIBLE);
        //signUpLayout.setVisibility(View.INVISIBLE);   //This is commented out as we dont want to turn this view off if the user hasn't filled in values yet
        forgotPasswordLayout.setVisibility(View.INVISIBLE);
        //accountSetupScrollView.setVisibility(View.INVISIBLE);

        //Check Setup For Proper Information before Going Forward
        String email = su_emailInput.getText().toString().toLowerCase();
        String pass = su_passwordInput.getText().toString().toLowerCase();
        String username = su_usernameInput.getText().toString().toLowerCase();

        String first = su_firstNameInput.getText().toString().toLowerCase();
        String last = su_lastNameInput.getText().toString().toLowerCase();

        String ageValue = su_dobInput.getText().toString().toLowerCase();

        if (Strings.isEmptyOrWhitespace(first)) {
            Toast.makeText(getApplicationContext(), "First Name Cannot Be Empty", Toast.LENGTH_LONG).show();
        }
        else if (Strings.isEmptyOrWhitespace(last)) {
            Toast.makeText(getApplicationContext(), "Last Name Cannot Be Empty", Toast.LENGTH_LONG).show();
        }
        else if (Strings.isEmptyOrWhitespace(email)) {
            Toast.makeText(getApplicationContext(), "Email Cannot Be Empty", Toast.LENGTH_LONG).show();
        }
        else if (Strings.isEmptyOrWhitespace(username)) {
            Toast.makeText(getApplicationContext(), "Username Cannot Be Empty", Toast.LENGTH_LONG).show();
        }
        else if (Strings.isEmptyOrWhitespace(ageValue)) {
            Toast.makeText(getApplicationContext(), "Age Cannot Be Empty", Toast.LENGTH_LONG).show();
        }
        else if (Strings.isEmptyOrWhitespace(pass)) {
            Toast.makeText(getApplicationContext(), "Password Cannot Be Empty", Toast.LENGTH_LONG).show();
        }
        else {
            //Set Account Creation Items to Visible
            signUpLayout.setVisibility(View.INVISIBLE);
            accountSetupScrollView.setVisibility(View.VISIBLE);
        }
    }

    //Login Initiate
    public void Login(View view)   //This actually does the Login process through Firebase Auth (depending on Login Type will auto-complete proper sign in process)
    {
        //Login Setup
        final Intent intent = new Intent (this, HomeActivity.class);

        String email = si_usernameInput.getText().toString().toLowerCase();
        String pass = si_passwordInput.getText().toString().toLowerCase();

        //final String displayName;

        //check if we are in login Mode
        if (loginMode == 0) {
            //NORMAL AUTHENTICATION WITH EMAIL AND PASSWORD

            //First Check if Username or Password is Empty and Alert User
            if (Strings.isEmptyOrWhitespace(email)) {
                Toast.makeText(getApplicationContext(), "Email Cannot Be Empty", Toast.LENGTH_LONG).show();
            } else if (Strings.isEmptyOrWhitespace(pass)) {
                Toast.makeText(getApplicationContext(), "Password Cannot Be Empty", Toast.LENGTH_LONG).show();
            }
            //If User did put in a acceptable Username / Password, try Login
            else {
                try {
                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(StartupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Print Log
                                Log.d(TAG, "signInWithEmail:success");
                                Toast.makeText(getApplicationContext(), "Authentication Success.", Toast.LENGTH_SHORT).show();

                                //Store User
                                currentUser = firebaseAuth.getCurrentUser();

                                //CHANGE ACTIVITIES HERE
                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (Exception ex) { //Catch Authentication Failed Exception

                    Toast.makeText(getApplicationContext(), "Authentication failed. " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //Signup Initiate
    public void Signup (View view)  //This actually does the Sign Up process through Firebase Auth (right now only works with Email and Password)
    {
        //Signup Variables Setup
        final Intent homeActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);

        //Get Input
        GetInputData();

        //Next check if we are in signUp Mode
        if (loginMode == 1)
        {
            //NORMAL ACCOUNT CREATION WITH EMAIL AND PASSWORD

            //User Firebase Auth to Create a New User Login
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Print Log
                            Log.d(TAG, "createAccountWithEmailAddress:success");

                            //Store User
                            currentUser = firebaseAuth.getCurrentUser();

                            //User Firebase Realtime Database to store user Information
                            try {

                                WriteUserPreferenceData();
                                //TODO: Based off Values, Determine Matchmaking Group
                            }
                            catch (Exception ex) { //Catch data storage exception
                                Toast.makeText(getApplicationContext(), "Data Storage Failed to Create " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            //Then Go to HomeActivity
                            startActivity(homeActivityIntent);
                        }
                        else {
                            //TODO: Possibly catch Email Exists Already exception here?
                            Toast.makeText(getApplicationContext(), "User Account Failed to Create", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
            catch (Exception ex) { //Catch Account Creation Failed Exception
                Toast.makeText(getApplicationContext(), "User Account Failed to Create " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Forgot Password Initiate
    public void ForgotPassword (View view)  //This actually sends the Reset Password command
    {
        //Check Reset Mode or Return Mode
        if (resetMode == 0)
        {
            //First set text to feedback:
            rp_instructionText.setText("A Password Reset link has been sent to your email! If you didnt recieve it, wait and then press \"Send Again\"");
            rp_resetPB.setText("Return to Login");

            //Initiate Password Reset
            try {
                firebaseAuth.sendPasswordResetEmail(rp_resetEmail.toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");
                                }
                            }
                        });

            }catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Sending Reset Email failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            resetMode = 1;
        }
        else
        {
            //Call the Sign In Screen
            SignInClick(getCurrentFocus());

            resetMode = 0;
        }
    }

    //Get User Preference Input Data
    private void GetInputData()   //Is a helper function to cleanup the Sign Up Activity Code
    {
        //First we need to grab data from input fields
        first = su_firstNameInput.getText().toString().toLowerCase();
        last = su_lastNameInput.getText().toString().toLowerCase();

        email = su_emailInput.getText().toString().toLowerCase();
        pass = su_passwordInput.getText().toString().toLowerCase();
        username = su_usernameInput.getText().toString().toLowerCase();

        ageValue = su_dobInput.getText().toString().toLowerCase();
        age = Integer.parseInt(ageValue);


        //Language RadioGroup
        if (as_languageSelectionGroup.getCheckedRadioButtonId() == findViewById(R.id.RD_SU_English).getId())
            languageSelection = "English";
        else if (as_languageSelectionGroup.getCheckedRadioButtonId() == findViewById(R.id.RD_SU_Chinese).getId())
            languageSelection = "Chinese";
        else if (as_languageSelectionGroup.getCheckedRadioButtonId() == findViewById(R.id.RD_SU_Spanish).getId())
            languageSelection = "Spanish";
        else if (as_languageSelectionGroup.getCheckedRadioButtonId() == findViewById(R.id.RD_SU_French).getId())
            languageSelection = "French";
        else
            languageSelection = "N/A";

        //Communication RadioGroup
        if (as_communicationStyleSelectionGroup.getCheckedRadioButtonId() == findViewById(R.id.RB_SU_Tactile).getId())
            communicationStyle = "Tactical";
        else if (as_communicationStyleSelectionGroup.getCheckedRadioButtonId() == findViewById(R.id.RB_SU_Aggressive).getId())
            communicationStyle = "Aggressive";
        else if (as_communicationStyleSelectionGroup.getCheckedRadioButtonId() == findViewById(R.id.RB_SU_Calm).getId())
            communicationStyle = "Calm";
        else if (as_communicationStyleSelectionGroup.getCheckedRadioButtonId() == findViewById(R.id.RB_SU_None).getId())
            communicationStyle = "None";
        else
            communicationStyle = "N/A";

        //FPS Class
        if (as_assaultClass.isChecked())
            assaultTicked = true;
        else
            assaultTicked = false;
        if (as_heavyClass.isChecked())
            heavyTicked = true;
        else
            heavyTicked = false;
        if (as_medicClass.isChecked())
            medicTicked = true;
        else
            medicTicked = false;
        if (as_reconClass.isChecked())
            reconTicked = true;
        else
            reconTicked = false;
        if (as_supportClass.isChecked())
            supportTicked = true;
        else
            supportTicked = false;

        //RPG Class
        if (as_warriorClass.isChecked())
            warriorTicked = true;
        else
            warriorTicked = false;
        if (as_thiefClass.isChecked())
            thiefTicked = true;
        else
            thiefTicked = false;
        if (as_clericClass.isChecked())
            clericTicked = true;
        else
            clericTicked = false;
        if (as_rangerClass.isChecked())
            rangerTicked = true;
        else
            rangerTicked = false;
        if (as_wizardClass.isChecked())
            wizardTicked = true;
        else
            wizardTicked = false;

        //Survival Choice
        if (as_horrorStyle.isChecked())
            horrorTicked = true;
        else
            horrorTicked = false;
        if (as_sandboxStyle.isChecked())
            sandboxTicked = true;
        else
            sandboxTicked = false;
        if (as_zombieStyle.isChecked())
            zombieTicked = true;
        else
            zombieTicked = false;

        //Sports Choice
        if (as_baseball.isChecked())
            baseballTicked = true;
        else
            baseballTicked = false;
        if (as_football.isChecked())
            footballTicked = true;
        else
            footballTicked = false;
        if (as_soccer.isChecked())
            soccerTicked = true;
        else
            soccerTicked = false;
        if (as_hockey.isChecked())
            hockeyTicked = true;
        else
            hockeyTicked = false;
        if (as_basketball.isChecked())
            basketballTicked = true;
        else
            basketballTicked = false;

        //Misc Choice
        if (as_racingGames.isChecked())
            racingTicked = true;
        else
            racingTicked = false;
        if (as_buildingSimulation.isChecked())
            buildingTicked = true;
        else
            buildingTicked = false;
        if (as_battleRoyale.isChecked())
            battleroyaleTicked = true;
        else
            battleroyaleTicked = false;
    }

    //Writes User Preference Data to Database
    private void WriteUserPreferenceData() //Is a helper function to cleanup the Sign Up Activity Code
    {
        //Create new UserProfile Object
        DB_UserProfile userProfile = new DB_UserProfile(first, last, username, age);

        //Store User Profile Data with AuthUID as KeyValue
        dbRef.child("users").child(currentUser.getUid()).setValue(userProfile);

        /* Store User Account Preference Data using AuthUID as KeyValue */
        //Matchmaking On/Off
        dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("status").setValue(true);

        //Language
        dbRef.child("users").child(currentUser.getUid()).child("language").setValue(languageSelection);

        //Aggression
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("aggression").setValue(aggressionSeekBarValue);

        //Communication Style
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("communicationStyle").setValue(communicationStyle);

        //FPS Class
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("fpsClass").child("assault").setValue(assaultTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("fpsClass").child("heavy").setValue(heavyTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("fpsClass").child("medic").setValue(medicTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("fpsClass").child("recon").setValue(reconTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("fpsClass").child("support").setValue(supportTicked);

        //RPG Class
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("rpgClass").child("warrior").setValue(warriorTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("rpgClass").child("thief").setValue(thiefTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("rpgClass").child("cleric").setValue(clericTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("rpgClass").child("ranger").setValue(rangerTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("rpgClass").child("wizard").setValue(wizardTicked);

        //Survival Style
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("survivalStyle").child("horror").setValue(horrorTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("survivalStyle").child("sandbox").setValue(sandboxTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("survivalStyle").child("zombie").setValue(zombieTicked);

        //Sports Game
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("sportsChoice").child("baseball").setValue(baseballTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("sportsChoice").child("football").setValue(footballTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("sportsChoice").child("soccer").setValue(soccerTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("sportsChoice").child("hockey").setValue(hockeyTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("sportsChoice").child("basketball").setValue(basketballTicked);

        //Misc Styles
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("miscChoice").child("racing").setValue(racingTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("miscChoice").child("building-simulation").setValue(buildingTicked);
        dbRef.child("users").child(currentUser.getUid()).child("preferences").child("miscChoice").child("battle-royale").setValue(battleroyaleTicked);

        //Once we have written User Data, sort them into Groups
        SortIntoGroup();

    }

    //Uses the Input Data to Put user in UserGroup
    private void SortIntoGroup ()   //Runs a bunch of routines
    {
        //Sort Language
        sortLanguageGroup();

        //Sort Communication
        sortCommunicationGroup();

        //Sort Aggression Group
        sortAggressionGroup();

        //Sort FPS Group
        sortFPSGroup();

        //Sort RPG Group
        sortRPGGroup();

        //Sort Survival Group
        sortSurvivalGroup();

        //Sort Sports Group
        sortSportGroup();

        //Sort Misc Group
        sortMiscGroup();
    }

    //Sort User Based off Selected Language
    private void sortLanguageGroup ()
    {
        //First Sort by Language
        if (languageSelection.equals("English")) {  //English
            dbRef.child("groups").child("region").child("english").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("region").child("english").setValue(true);
        }
        else if (languageSelection.equals("Chinese")) {    //Chinese
            dbRef.child("groups").child("region").child("chinese").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("region").child("chinese").setValue(true);
        }
        else if (languageSelection.equals("Spanish")) {    //Spanish
            dbRef.child("groups").child("region").child("spanish").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("region").child("spanish").setValue(true);
        }
        else if (languageSelection.equals("French")) {
            dbRef.child("groups").child("region").child("french").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("region").child("french").setValue(true);
        }
        else {  //Catch All for No Group
            dbRef.child("groups").child("region").child("none").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("region").child("none").setValue(true);
        }
    }

    //Sort User Based off Selected Communication Style
    private void sortCommunicationGroup ()
    {
        //Then by Communication Style
        if (communicationStyle.equals("Tactical")) {    //Tactical
            dbRef.child("groups").child("commStyle").child("tactical").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("commStyle").child("tactical").setValue(true);
        }
        else if (communicationStyle.equals("Aggressive")) { //Aggressive
            dbRef.child("groups").child("commStyle").child("aggressive").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("commStyle").child("aggressive").setValue(true);
        }
        else if (communicationStyle.equals("Calm")) {   //Calm
            dbRef.child("groups").child("commStyle").child("calm").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("commStyle").child("calm").setValue(true);
        }
        else if (communicationStyle.equals("None")) {   //None
            dbRef.child("groups").child("commStyle").child("none").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("commStyle").child("none").setValue(true);
        }
        else {  //????
            //TODO: Then do nothing? Sort into Catch All Group?
        }

    }

    //Sort User Based off Determined Aggression
    private void sortAggressionGroup ()
    {
        //Then by Aggression
        if (aggressionSeekBarValue < 2) {   //Passive
            dbRef.child("groups").child("aggression").child("passive").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("aggression").child("passive").setValue(true);
        }
        else if (aggressionSeekBarValue >= 3 && aggressionSeekBarValue <= 5){   //Reasonable
            dbRef.child("groups").child("aggression").child("reasonable").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("aggression").child("reasonable").setValue(true);
        }
        else if (aggressionSeekBarValue >= 6 && aggressionSeekBarValue <=8){    //Aggressive
            dbRef.child("groups").child("aggression").child("aggressive").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("aggression").child("aggressive").setValue(true);
        }
        else{   //Excessive
            dbRef.child("groups").child("aggression").child("excessive").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("aggression").child("excessive").setValue(true);
        }
    }

    //Sort User Based off Selected FPS Class Selections
    private void sortFPSGroup ()
    {
        //Then by FPS Group
        if (assaultTicked) {    //The Assault Group for FPS Players
            dbRef.child("groups").child("fps").child("assault").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("fps").child("assault").setValue(true);
        }

        if (heavyTicked) {  //The Heavy Group for FPS Players
            dbRef.child("groups").child("fps").child("heavy").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("fps").child("heavy").setValue(true);
        }

        if (medicTicked) {  //The Medic Group for FPS Players
            dbRef.child("groups").child("fps").child("medic").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("fps").child("medic").setValue(true);
        }

        if (reconTicked) {  //The Recon Group for FPS Players
            dbRef.child("groups").child("fps").child("recon").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("fps").child("recon").setValue(true);
        }

        if (supportTicked) {    //The Support Group for FPS Players
            dbRef.child("groups").child("fps").child("support").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("fps").child("support").setValue(true);
        }

    }

    //Sort User Based off Selected RPG Class Selections
    private void sortRPGGroup ()
    {
        //Then by FPS Group
        if (warriorTicked) {    //The warrior Group for RPG Players
            dbRef.child("groups").child("rpg").child("warrior").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("rpg").child("warrior").setValue(true);
        }

        if (thiefTicked) {  //The thief Group for RPG Players
            dbRef.child("groups").child("rpg").child("thief").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("rpg").child("thief").setValue(true);
        }

        if (clericTicked) {  //The cleric Group for RPG Players
            dbRef.child("groups").child("rpg").child("cleric").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("rpg").child("cleric").setValue(true);
        }

        if (rangerTicked) {  //The ranger Group for RPG Players
            dbRef.child("groups").child("rpg").child("ranger").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("rpg").child("ranger").setValue(true);
        }

        if (wizardTicked) {    //The wizard Group for RPF Players
            dbRef.child("groups").child("rpg").child("wizard").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("rpg").child("wizard").setValue(true);

        }
    }

    //Sort User Based off Selected Survival Preferences
    private void sortSurvivalGroup()
    {

        if (horrorTicked) {    //The horror Group for Survival Players
            dbRef.child("groups").child("survival").child("horror").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("survival").child("horror").setValue(true);
        }

        if (sandboxTicked) {    //The sandbox Group for Survival Players
            dbRef.child("groups").child("survival").child("sandbox").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("survival").child("sandbox").setValue(true);
        }

        if (zombieTicked) {    //The zombie Group for Survival Players
            dbRef.child("groups").child("survival").child("zombie").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("survival").child("zombie").setValue(true);
        }

    }

    //Sort User Based off Selected Sport Preferences
    private void sortSportGroup()
    {

        if (baseballTicked) {    //The baseball Group for sports Players
            dbRef.child("groups").child("sports").child("baseball").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("sports").child("baseball").setValue(true);
        }

        if (soccerTicked) {    //The soccer Group for sports Players
            dbRef.child("groups").child("sports").child("soccer").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("sports").child("soccer").setValue(true);
        }

        if (footballTicked) {    //The football Group for sports Players
            dbRef.child("groups").child("sports").child("football").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("sports").child("football").setValue(true);
        }

        if (hockeyTicked) {    //The hockey Group for sports Players
            dbRef.child("groups").child("sports").child("hockey").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("sports").child("hockey").setValue(true);
        }

        if (basketballTicked) {    //The basketball Group for sports Players
            dbRef.child("groups").child("sports").child("basketball").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("sports").child("basketball").setValue(true);
        }

    }

    //Sort User Based off Selected Misc Preferences
    private void sortMiscGroup()
    {

        if (racingTicked) {    //The racing Group for misc Players
            dbRef.child("groups").child("misc").child("racing").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("misc").child("racing").setValue(true);
        }

        if (buildingTicked) {    //The building Group for misc Players
            dbRef.child("groups").child("misc").child("building").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("misc").child("building").setValue(true);
        }

        if (battleroyaleTicked) {    //The battleroyale Group for misc Players
            dbRef.child("groups").child("misc").child("battleroyale").child("members").child(currentUser.getUid()).setValue(true);
            dbRef.child("users").child(currentUser.getUid()).child("matchMaking").child("groups").child("misc").child("battleroyale").setValue(true);
        }
    }

}