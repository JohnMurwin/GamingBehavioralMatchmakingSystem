package swe6733.team2.gbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogOut extends AppCompatActivity implements View.OnClickListener {

    Button updateMyInfo, updateGameStyle, turnOffMatching, log_out, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

        updateMyInfo=(Button) findViewById(R.id.updateMyInfo);
        updateGameStyle=(Button) findViewById(R.id.updateGameStyle);
        turnOffMatching=(Button) findViewById(R.id.turnOffMatching);
        log_out=(Button) findViewById(R.id.log_out);
        delete=(Button) findViewById(R.id.delete);

        log_out.setOnClickListener(this);
        delete.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.log_out:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}