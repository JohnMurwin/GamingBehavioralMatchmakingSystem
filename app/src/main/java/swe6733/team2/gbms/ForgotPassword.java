package swe6733.team2.gbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    Button resetPassword;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        resetPassword= (Button) findViewById(R.id.resetPassword);
        resetPassword.setOnClickListener(this);
        back=(TextView)findViewById(R.id.back);
        back.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resetPassword:
                startActivity(new Intent(this, ResetPassword.class));
                break;
            case R.id.back:
                startActivity((new Intent(this, MainActivity.class)));

        }
    }
}