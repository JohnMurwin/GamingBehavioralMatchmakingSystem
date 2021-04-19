package swe6733.team2.gbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //SCREEN NAVIGATION
        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.MatchMaking_Page:
                        Intent intent = new Intent(Home.this, MatchMaking.class);
                        startActivity(intent);
                        break;

                    case R.id.Home_Page:
                        //Intent intent2 = new Intent(HomeActivity.this, HomeActivity.class);
                        //startActivity(intent2);
                        break;

                    case R.id.Settings_Page:
                        Intent intent3 = new Intent (Home.this, Settings.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
    }

}