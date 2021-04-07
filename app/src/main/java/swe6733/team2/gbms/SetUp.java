package swe6733.team2.gbms;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class SetUp extends AppCompatActivity implements View.OnClickListener {


    TextInputLayout textInput;
    AutoCompleteTextView autoCompleteTextView;
    Button next;

    ArrayList<String> LanguageList;
    ArrayAdapter<String> LanguageAdapter;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);


        textInput = (TextInputLayout) findViewById(R.id.textInput);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);

        LanguageList = new ArrayList<>();
        LanguageList.add("English");
        LanguageList.add("Spanish");
        LanguageList.add("French");

        LanguageAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, LanguageList);
        autoCompleteTextView.setAdapter(LanguageAdapter);

        autoCompleteTextView.setThreshold(1);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                startActivity(new Intent(this, GenreSetup.class));
                break;

        }
    }
}