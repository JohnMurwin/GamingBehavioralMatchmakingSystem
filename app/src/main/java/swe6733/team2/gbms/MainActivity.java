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

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    //Private Variables
    private FirebaseAuth firebaseAuth;  //Instance to the FirebaseAuth System

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FirebaseAuth Configuration
        firebaseAuth = FirebaseAuth.getInstance();

        //if (firebaseAuth.getAccessToken(true))
        Log.e("FirebaseAuth Token Status:", String.valueOf(firebaseAuth.getAccessToken(true)));

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

        //Firestore DB Configuration
        //FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Quick Printout of Firebase Instance ID
        //Log.e("FirebaseInstanceID:", db.toString());
    }
}