/*
    SWE6733- Spring '21
    Team 2
    Sprint 2021
    Semester Project - Gaming Behavioral Matchmaking System
    *Any use of the following code is forbidden without prior consent.
*/

package swe6733.team2.gbms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity{

    //Private Variables
    private int loginMode;  //0 = Sign In, 1 = Sign Up, 3 = Recovery
    private FirebaseAuth firebaseAuth;  //Instance to the FirebaseAuth System


    Button login;
    EditText username;
    EditText password;
    TextView new_account;
    TextView forgotPassword;

    LinearLayout signInLayout;
    LinearLayout signUpLayout;
    LinearLayout forgotPasswordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.ET_SI_Username);
        password = (EditText) findViewById(R.id.ET_SI_Password);
        login = (Button) findViewById(R.id.PB_SI_Login);
        new_account = (TextView) findViewById(R.id.PT_SI_CreateNewAccount);
        forgotPassword = (TextView) findViewById(R.id.PT_SI_ForgotPassword);

        //Layouts
        signInLayout = (LinearLayout) findViewById(R.id.LL_SignIn);
        signUpLayout = (LinearLayout) findViewById(R.id.LL_SignUp);
        forgotPasswordLayout = (LinearLayout) findViewById(R.id.LL_ForgotPassword);

        //login.setOnClickListener(this);
        //new_account.setOnClickListener(this);
        //forgotPassword.setOnClickListener(this);

        //FirebaseAuth Configuration
        firebaseAuth = FirebaseAuth.getInstance();

        //if (firebaseAuth.getAccessToken(true))
        Log.e("FirebaseAuth Token Status:", String.valueOf(firebaseAuth.getAccessToken(true)));


        //Firebase Test - DEPRECIATED FOR NOW
        /*
        firebaseAuth.createUserWithEmailAndPassword("test@test.com", "123456")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("CreateAuthSuccess", "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("CreateAuthFailure", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
         */

        //Firestore DB Configuration
        //FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Quick Printout of Firebase Instance ID
        //Log.e("FirebaseInstanceID:", db.toString());
    }


    //Sign In Click
    public void SignInClick (View vIew) //This swaps the ViewPort from anything, to the Sign In Screen
    {
        //Ensure Everything Else is Invisible
        signUpLayout.setVisibility(View.INVISIBLE);
        forgotPasswordLayout.setVisibility(View.INVISIBLE);

        //Set SignIn Items to Visible
        signInLayout.setVisibility(View.VISIBLE);

        //Set loginMode
        loginMode = 0;
    }

    //Sign Up Click
    public void SignUpClick (View vIew) //This swaps the ViewPort from anything, to the Sign Up Screen
    {
        //Ensure Everything Else is Invisible
        signInLayout.setVisibility(View.INVISIBLE);
        forgotPasswordLayout.setVisibility(View.INVISIBLE);

        //Set SignUp Items to Visible
        signUpLayout.setVisibility(View.VISIBLE);

        //Set loginMode
        loginMode = 1;
    }

    //Forgot Password Click
    public void ForgotPasswordClick (View vIew) //This swaps the ViewPort from anything, to the Forgot Password Screen
    {
        //Ensure Everything Else is Invisible
        signInLayout.setVisibility(View.INVISIBLE);
        signUpLayout.setVisibility(View.INVISIBLE);

        //Set ForgotPassword Items to Visible
        forgotPasswordLayout.setVisibility(View.VISIBLE);

        //Set loginMode
        loginMode = 2;
    }


}
