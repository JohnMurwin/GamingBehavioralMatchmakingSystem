package swe6733.team2.gbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    private FirebaseUser currentUser;
    private FirebaseDatabase database;  //Instance to Firebase Realtime Database System
    private DatabaseReference dbRef;    //Instance to the Particular Database We Want

    //onCreate Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Component Linking


        //Firebase Auth Instancing
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        //Firebase Realtime Database Instancing
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference();

        Toast.makeText(getApplicationContext(), "Welcome " + currentUser.getDisplayName(), Toast.LENGTH_SHORT).show();

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

    //onResume Override
    @Override
    protected void onResume() {
        super.onResume();

        //Get New Groups
        GetGroups();

        //Display New Groups
        DisplayGroups();
    }

    //Grabs updated GroupIDs for the current User
    public void GetGroups () {


    }

    //Displays the Group Contents for the current User
    public void DisplayGroups () {


    }

}