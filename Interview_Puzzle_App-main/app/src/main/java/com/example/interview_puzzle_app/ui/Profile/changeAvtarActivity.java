package com.example.interview_puzzle_app.ui.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
//R factor
import com.example.interview_puzzle_app.R;
import com.example.interview_puzzle_app.db.SqliteDBHelper;
import com.example.interview_puzzle_app.db.UserData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class changeAvtarActivity extends AppCompatActivity {

    int[] Avatar ={R.drawable.avtar0,R.drawable.avtar1,R.drawable.avtar2,
            R.drawable.avtar3,R.drawable.avtar4,R.drawable.avtar5,R.drawable.avtar6};

    //Floating Button Objects
    FloatingActionButton[] AFBoption = new FloatingActionButton[6]; // AFB - avatar floating button
    FloatingActionButton saveAvatar;

    //Avatar Option Images
    ImageView[] CAavatarOption = new ImageView[7];
//    ImageView profileAvtar;

    //User Data
    SqliteDBHelper DB;
    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avtar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Change Avatar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //FindViewByID
        AFBoption[0] = findViewById(R.id.avtarButtonOption1);
        AFBoption[1] = findViewById(R.id.avtarButtonOption2);
        AFBoption[2] = findViewById(R.id.avtarButtonOption3);
        AFBoption[3] = findViewById(R.id.avtarButtonOption4);
        AFBoption[4] = findViewById(R.id.avtarButtonOption5);
        AFBoption[5] = findViewById(R.id.avtarButtonOption6);
        saveAvatar = findViewById(R.id.CAsaveAvatar);

//        profileAvtar = (ImageView) findViewById(R.id.currentavtar);

        CAavatarOption[0] = (ImageView) findViewById(R.id.CAcurrentavtar);
        CAavatarOption[1] = (ImageView) findViewById(R.id.avtarOption1);
        CAavatarOption[2] = (ImageView) findViewById(R.id.avtarOption2);
        CAavatarOption[3] = (ImageView) findViewById(R.id.avtarOption3);
        CAavatarOption[4] = (ImageView) findViewById(R.id.avtarOption4);
        CAavatarOption[5] = (ImageView) findViewById(R.id.avtarOption5);
        CAavatarOption[6] = (ImageView) findViewById(R.id.avtarOption6);

        //fetching userdata
        DB = new SqliteDBHelper(getApplicationContext());
        DB.openDataBase();
        userData = DB.getUserInfo();

        //SettingUpAvatar
        setUpAvatar();

        //btnListener
        saveAvatar.setOnClickListener(View -> {
            finish();
            Intent i = new Intent(getApplicationContext(), Profile.class);
            startActivityForResult(i, 1);
        });
        AFBoption[0].setOnClickListener(view -> {
            int current = 1;
            Boolean setAvatar = DB.updatedata(userData.getId(), current);
            if(setAvatar){
                Toast.makeText(getApplicationContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                userData = DB.getUserInfo();
                setUpAvatar();
            }
            else {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        AFBoption[1].setOnClickListener(view -> {
            int current = 2;
            Boolean setAvatar = DB.updatedata(userData.getId(), current);
            if(setAvatar){
                Toast.makeText(getApplicationContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                userData = DB.getUserInfo();
                setUpAvatar();
            }
            else {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        AFBoption[2].setOnClickListener(view -> {
            int current = 3;
            Boolean setAvatar = DB.updatedata(userData.getId(), current);
            if(setAvatar){
                Toast.makeText(getApplicationContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                userData = DB.getUserInfo();
                setUpAvatar();
            }
            else {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        AFBoption[4].setOnClickListener(view -> {
            int current = 5;
            Boolean setAvatar = DB.updatedata(userData.getId(), current);
            if(setAvatar){
                Toast.makeText(getApplicationContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                userData = DB.getUserInfo();
                setUpAvatar();
            }
            else {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        AFBoption[5].setOnClickListener(view -> {
            int current = 6;
            Boolean setAvatar = DB.updatedata(userData.getId(), current);
            if(setAvatar){
                Toast.makeText(getApplicationContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                userData = DB.getUserInfo();
                setUpAvatar();
            }
            else {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        AFBoption[3].setOnClickListener(view -> {
            int current = 4;
            Boolean setAvatar = DB.updatedata(userData.getId(), current);
            if(setAvatar){
                Toast.makeText(getApplicationContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                setUpAvatar();
            }
            else {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setUpAvatar() {
        DB.openDataBase();
        userData = DB.getUserInfo();
        Log.d("16.04","current avatar : "+userData.getAvtar());
        setCurrentAvatar();
        setOptionAvatar();
    }

    private void setOptionAvatar() {
        int current = userData.getAvtar();
        for(int i=1;i<7;i++){
            if(i != current){
                CAavatarOption[i].setImageResource(Avatar[i]);
            }
            else {
                CAavatarOption[i].setImageResource(Avatar[0]);
            }
        }
    }

    private void setCurrentAvatar() {
//        profileAvtar.setImageResource(Avatar[userData.getAvtar()]);
        CAavatarOption[0].setImageResource(Avatar[userData.getAvtar()]);
    }
}
