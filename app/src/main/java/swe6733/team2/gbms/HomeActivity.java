package swe6733.team2.gbms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    //Component Variables

    //Firebase Variables
    private FirebaseAuth firebaseAuth;  //Instance to the FirebaseAuth System
    private FirebaseFirestore db = FirebaseFirestore.getInstance(); //Instance to the Firebase Firestore Cloud
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Component Linking


        //Firebase Auth Instancing
        firebaseAuth = FirebaseAuth.getInstance();
    }

    //OnStart Override
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly
        currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(getApplicationContext(), "Welcome: " + currentUser.getDisplayName(), Toast.LENGTH_LONG).show();

            //Quick Test to Link Authentication User to DB User ID
        }
    }
}