package swe6733.team2.gbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


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