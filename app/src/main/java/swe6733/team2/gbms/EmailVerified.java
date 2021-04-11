package swe6733.team2.gbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmailVerified extends AppCompatActivity  implements View.OnClickListener {
    Button letGo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verified);
        letGo = (Button) findViewById(R.id.letGo);
        letGo.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.letGo:
                startActivity(new Intent(this, SetUp.class));
                break;
        }
    }
}