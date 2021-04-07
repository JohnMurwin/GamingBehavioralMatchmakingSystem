/*
    SWE6733 - Sprint 21
    Team 2
    Sprint 2021
    Semester Project - Gaming Behavioral Matchmaking System
    *Any use of the following code is forbidden without prior consent.
*/

package swe6733.team2.gbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Private Variables
    private FirebaseAuth firebaseAuth;  //Instance to the FirebaseAuth System
    Button login;
    EditText username;
    EditText password;
    TextView new_account;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        new_account = (TextView) findViewById(R.id.new_account);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        login.setOnClickListener(this);
        new_account.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);

        //FirebaseAuth Configuration
        firebaseAuth = FirebaseAuth.getInstance();

        //if (firebaseAuth.getAccessToken(true))
        Log.e("FirebaseAuth Token Status:", String.valueOf(firebaseAuth.getAccessToken(true)));
    }
        //Test
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

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.login:
                startActivity(new Intent(this,LogOut.class));
                break;
            case R.id.new_account:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }

        //Firestore DB Configuration
        //FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Quick Printout of Firebase Instance ID
        //Log.e("FirebaseInstanceID:", db.toString());
    }
}
