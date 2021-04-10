package swe6733.team2.gbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmailVerification extends AppCompatActivity implements View.OnClickListener {

    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                startActivity(new Intent(this, EmailVerification.class));
                break;
            case R.id.PB_SI_Login:
                startActivity(new Intent(this, LogOut.class));
                break;
        }
    }
}