package swe6733.team2.gbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MatchMakingActivity extends AppCompatActivity {

    Button recommendations;
    Button friendlist;
    LinearLayout Friendlist;
    LinearLayout Recommendations;
    //Private Variables
    private int loginMode;  //0 = Friend List, 1 = Recommendation,
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_making);

        //Layout Linking
        recommendations=findViewById(R.id.R_recommendations);
        friendlist=findViewById(R.id.FL_friends);

        Friendlist = (LinearLayout) findViewById(R.id.LL_Friendlist);
        Recommendations = (LinearLayout) findViewById(R.id.LL_Recommendations);


    }
    //Friend List Click
    public void FriendListClick(View vIew) //This swaps the ViewPort from anything, to the Sign In Screen
    {
        //Ensure Everything Else is Invisible
        Recommendations.setVisibility(View.INVISIBLE);


        //Set SignIn Items to Visible
        Friendlist.setVisibility(View.VISIBLE);

        //Set loginMode
        loginMode = 0;
    }

    //Recommendations Click
    public void Recommendations(View vIew) //This swaps the ViewPort from anything, to the Sign Up Screen
    {
        //Ensure Everything Else is Invisible
        Friendlist.setVisibility(View.INVISIBLE);


        //Set SignUp Items to Visible
        Recommendations.setVisibility(View.VISIBLE);

        //Set loginMode
        loginMode = 1;
    }

}