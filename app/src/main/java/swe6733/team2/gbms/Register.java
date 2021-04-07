package swe6733.team2.gbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button createNewAccount;
    EditText firstname, lastname, birthdate, email, username, password, passwordconfirmation;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstname=(EditText) findViewById(R.id.firstname);
        lastname=(EditText) findViewById(R.id.lastname);
        birthdate=(EditText) findViewById(R.id.birthdate);
        email=(EditText) findViewById(R.id.email);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        passwordconfirmation=(EditText) findViewById(R.id.passwordconfirmation);

        login=(TextView) findViewById(R.id.login);
        login.setOnClickListener(this);
        createNewAccount= (Button) findViewById(R.id.createNewAccount);
        createNewAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createNewAccount:
                startActivity(new Intent(this,EmailVerification.class));
                break;
            case R.id.login:
                startActivity(new Intent(this,LogOut.class));
                break;
        }
    }
}