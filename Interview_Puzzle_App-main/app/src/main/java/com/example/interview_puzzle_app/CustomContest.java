package com.example.interview_puzzle_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CustomContest extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    String[] companyNames = {"Microsoft","Google","Apple","Meta","Netflix","Yahoo"};
    String[] contestLevels = {"Easy","Medium","Hard"};
    Spinner spin1,spin2;
    public Button startBtn;
    public String selectedCompanyName,selectedLevelOfContest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_contest);
        getSupportActionBar().setTitle("Custom Contest");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spin1 = (Spinner)findViewById(R.id.spinnerCompany);
        spin1.setOnItemSelectedListener(this);

        spin2 = (Spinner)findViewById(R.id.spinnerLevel);
        spin2.setOnItemSelectedListener(this);

        ArrayAdapter a1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,companyNames);
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(a1);

        ArrayAdapter a2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,contestLevels);
        a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(a2);

        startBtn = (Button) findViewById(R.id.btnStart);
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int i1,i2;
        i1 = spin1.getSelectedItemPosition();
        i2 = spin2.getSelectedItemPosition();
        selectedCompanyName =companyNames[i1];
        selectedLevelOfContest = contestLevels[i2];
        Toast.makeText(getApplicationContext(),"Company: "+companyNames[i1]+" Level: "+contestLevels[i2],Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        Intent i8 =new Intent(CustomContest.this,StartContest.class);
        i8.putExtra("CompanyName",selectedCompanyName);
        i8.putExtra("LevelOfContest",selectedLevelOfContest);
        startActivity(i8);
    }
}