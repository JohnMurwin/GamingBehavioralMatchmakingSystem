package swe6733.team2.gbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GenreSetup extends AppCompatActivity implements View.OnClickListener {

    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_setup);

        finish= (Button) findViewById(R.id.finish);
        finish.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }
}