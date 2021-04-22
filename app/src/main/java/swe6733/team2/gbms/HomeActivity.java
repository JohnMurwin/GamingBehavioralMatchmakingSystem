package swe6733.team2.gbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    /* Component Variables */
    TextView testText;

    //Private Variables
    private static final String TAG = "GBMS: MainActivity -";

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
        currentUser = firebaseAuth.getCurrentUser();

        //SCREEN NAVIGATION
        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //The Actual Selection for Screens
                switch (item.getItemId()) {
                    //Home Page Button
                    case R.id.Home_Page:
                        //Intent homeIntent = new Intent(HomeActivity.this, HomeActivity.class);
                        //startActivity(homeIntent);
                        break;

                    //Settings Page Button
                    case R.id.Settings_Page:
                        Intent settingsIntent = new Intent(HomeActivity.this, SettingsActivity.class);
                        startActivity(settingsIntent);
                        break;

                    //Match Making Page Button
                    case R.id.Matchmaking_Page:
                        Intent matchMakingIntent = new Intent(HomeActivity.this, MatchMakingActivity.class);
                        startActivity(matchMakingIntent);
                        break;
                }
                return false;
            }
        });
    }
}