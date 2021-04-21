package swe6733.team2.gbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
    Button login;
    EditText username;
    EditText password;
    TextView signup;
    TextView forgotPassword;

    //Sign Up
    EditText su_emailInput;
    EditText su_firstNameInput;
    EditText su_lastNameInput;
    EditText su_dobInput;
    EditText su_usernameInput;
    EditText su_passwordInput;
    Button su_createNewAccountPB;

    //Reset Password
    TextView rp_instructionText;
    EditText rp_resetEmail;
    Button rp_resetPB;

    LinearLayout signInLayout;
    LinearLayout signUpLayout;
    LinearLayout forgotPasswordLayout;
    ScrollView accountSetupScrollView;  //this is acting as a parent layout

    //Private Variables
    private static final String TAG = "GBMS: MainActivity -";

    private int loginMode;  //0 = Sign In, 1 = Sign Up, 3 = Recovery
    private int resetMode; //0 = Reset, 1 = Return

    //Firebase Variables
    private FirebaseAuth firebaseAuth;  //Instance to the FirebaseAuth System
    private FirebaseUser currentUser;

    private FirebaseDatabase database;  //Instance to Firebase Realtime Database System
    private DatabaseReference dbRef;    //Instace to the Particular Database We Want


    //OnCreate Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        //Sign In Component Linking
        username = (EditText) findViewById(R.id.ET_SI_Username);
        password = (EditText) findViewById(R.id.ET_SI_Password);
        login = (Button) findViewById(R.id.PB_SI_Login);
        signup = (TextView) findViewById(R.id.PT_SI_CreateNewAccount);
        forgotPassword = (TextView) findViewById(R.id.PT_SI_ForgotPassword);

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

        //Layout Linking
        signInLayout = (LinearLayout) findViewById(R.id.LL_SignIn);
        signUpLayout = (LinearLayout) findViewById(R.id.LL_SignUp);
        forgotPasswordLayout = (LinearLayout) findViewById(R.id.LL_ForgotPassword);
        accountSetupScrollView = (ScrollView) findViewById(R.id.SV_AccountSetup);


        //Firebase Auth Instancing
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        //Firebase Realtime Database Instancing
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();

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


    //Sign In Click
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

    //Sign Up Click
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

    //Forgot Password Click
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

    //Continue Account Creation
    public void ContinueAccountCreationClick (View view) //This swaps the ViewPort from the SignUp Intro Screen, to the Account Setup Screen
    {
        //Ensure Everything Else is Invisible
        signInLayout.setVisibility(View.INVISIBLE);
        signUpLayout.setVisibility(View.INVISIBLE);
        forgotPasswordLayout.setVisibility(View.INVISIBLE);
        //accountSetupScrollView.setVisibility(View.INVISIBLE);

        //Set SignIn Items to Visible
        accountSetupScrollView.setVisibility(View.VISIBLE);
    }

    //Login Initiate
    public void Login(View view)   //This actually does the Login process through Firebase Auth (depending on Login Type will auto-complete proper sign in process)
    {
        //Login Setup
        final Intent intent = new Intent (this, HomeActivity.class);

        String email = username.getText().toString().toLowerCase();
        String pass = password.getText().toString().toLowerCase();

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
        //Signup Setup
        final Intent homeActivityIntent = new Intent(getApplicationContext(), HomeActivity.class);

        String email = su_emailInput.getText().toString().toLowerCase();
        String pass = su_passwordInput.getText().toString().toLowerCase();
        String username = su_usernameInput.getText().toString().toLowerCase();

        String first = su_firstNameInput.getText().toString().toLowerCase();
        String last = su_lastNameInput.getText().toString().toLowerCase();

        String ageValue = su_dobInput.getText().toString().toLowerCase();
        int age = Integer.parseInt(ageValue);


        //First check if we are in signUp Mode
        if (loginMode == 1)
        {
            //NORMAL ACCOUNT CREATION WITH EMAIL AND PASSWORD

            //First Check if Email Address or Password are Empty
            if (Strings.isEmptyOrWhitespace(email)) {
                Toast.makeText(getApplicationContext(), "Email Cannot Be Empty", Toast.LENGTH_LONG).show();
            }
            else if (Strings.isEmptyOrWhitespace(pass)) {
                Toast.makeText(getApplicationContext(), "Password Cannot Be Empty", Toast.LENGTH_LONG).show();
            }
            //If User did put in acceptable Username / Password
            else {
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
                                try {//Get Users DB Reference

                                    DB_UserProfile userProfile = new DB_UserProfile(first, last, username, age);
                                    dbRef.child("users").child(currentUser.getUid()).setValue(userProfile);   //Store User Profile Data with AuthUID as KeyValue
                                }
                                catch (Exception ex) { //Catch data storage exception
                                    Toast.makeText(getApplicationContext(), "Data Storage Creation failed. " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                //Then We want to Transfer to the UI for Account Setup
                            }
                            else {
                                //TODO: Possibly catch Email Exists Already exception here?
                                Toast.makeText(getApplicationContext(), "User Account Failed to Create", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                catch (Exception ex) { //Catch Account Creation Failed Exception
                    Toast.makeText(getApplicationContext(), "User Account Creation failed. " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
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
                Toast.makeText(getApplicationContext(), "Sending Reset Email failed. " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
}