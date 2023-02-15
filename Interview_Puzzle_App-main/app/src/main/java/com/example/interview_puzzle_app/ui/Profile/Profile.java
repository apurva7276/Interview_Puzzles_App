package com.example.interview_puzzle_app.ui.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
//R factor
import com.example.interview_puzzle_app.HomePage;
import com.example.interview_puzzle_app.R;
import com.example.interview_puzzle_app.db.SqliteDBHelper;
import com.example.interview_puzzle_app.db.UserData;
import com.example.interview_puzzle_app.ui.logIn.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;


public class Profile extends AppCompatActivity {

    //Creating Animation Objects
    Animation rotateOpen;
    Animation rotateClose;
    Animation fromBottom;
    Animation toBottom;

    //Floating Button Objects
    FloatingActionButton moreOption;
    FloatingActionButton editProfile;
    FloatingActionButton changeAvtar;

    public Boolean clicked = false;

    //activity_profile
    TextView name, age, email, companies;
    TextView editProfileDialog, changeAvtarDialog;
    ImageView profielCurrentAvatar;

    //Fetching Data from Database
    SqliteDBHelper DB;
    UserData userData;

    //Avatar
    int[] Avatar ={R.drawable.avtar0,R.drawable.avtar1,R.drawable.avtar2,
            R.drawable.avtar3,R.drawable.avtar4,R.drawable.avtar5,R.drawable.avtar6};

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d("14.04","Inside Profile Activity");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //findViewById
        moreOption = findViewById(R.id.PfloatBMoreOption);
        editProfile = findViewById(R.id.PfloatBedit);
        changeAvtar = findViewById(R.id.PfloatBavtar);

        editProfileDialog = findViewById(R.id.PTeditProfile);
        changeAvtarDialog = findViewById(R.id.PTchangeAvtar);

        rotateOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_open_anim);
        rotateClose =AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_close_anim);
        fromBottom =AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_bottom_anim);
        toBottom =AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_bottom_anim);

        //profile_activity
        name = (TextView) findViewById(R.id.PTgetName);
        age = (TextView) findViewById(R.id.PTgetAge);
        email = (TextView) findViewById(R.id.PTgetEmail);
        companies = (TextView) findViewById(R.id.PTgetCompanies);
        profielCurrentAvatar = (ImageView) findViewById(R.id.currentavtar);

        //Fetching Data
        DB = new SqliteDBHelper(getApplicationContext());
        DB.openDataBase();
        userData = DB.getUserInfo();
        name.setText(userData.getName());
        String ageInString = Integer.toString(userData.getAge());
        age.setText(ageInString);
        email.setText(userData.getEmail());
        companies.setText(fetchCompanies());
        profielCurrentAvatar.setImageResource(Avatar[userData.getAvtar()]);

        //Action Listener
        moreOption.setOnClickListener(view -> {
            onMoreOptionButtonClicked();
        });
        editProfile.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), editProfileActivity.class));
            finish();
        });
        changeAvtar.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), changeAvtarActivity.class));
            finish();
        });

    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }

    private void onMoreOptionButtonClicked() {
        setClickable(clicked);
        setVisibility(clicked);
        setAnimation(clicked);
        clicked = !clicked;
    }

    private void setVisibility(Boolean clicked) {
        if(!clicked){
            editProfile.setVisibility(View.VISIBLE);
            changeAvtar.setVisibility(View.VISIBLE);
            editProfileDialog.setVisibility(View.VISIBLE);
            changeAvtarDialog.setVisibility(View.VISIBLE);
        }
        else {
            editProfile.setVisibility(View.INVISIBLE);
            changeAvtar.setVisibility(View.INVISIBLE);
            editProfileDialog.setVisibility(View.INVISIBLE);
            changeAvtarDialog.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimation(Boolean clicked) {
        if(!clicked){
            editProfile.startAnimation(fromBottom);
            changeAvtar.startAnimation(fromBottom);
            editProfileDialog.startAnimation(fromBottom);
            changeAvtarDialog.startAnimation(fromBottom);
            moreOption.startAnimation(rotateOpen);
        }
        else {
            editProfile.startAnimation(toBottom);
            changeAvtar.startAnimation(toBottom);
            editProfileDialog.startAnimation(toBottom);
            changeAvtarDialog.startAnimation(toBottom);
            moreOption.startAnimation(rotateClose);
        }
    }

    private void setClickable(Boolean clicked) {
        if(!clicked){
            editProfile.setClickable(true);
            changeAvtar.setClickable(true);
        }
        else {
            editProfile.setClickable(false);
            changeAvtar.setClickable(false);
        }
    }

    private String fetchCompanies() {
        String companies="";
        if(userData.getMicrosoft() == 1)
            companies += "Microsoft\n";
        if(userData.getGoogle() == 1)
            companies += "Google\n";
        if(userData.getYahoo() == 1)
            companies += "Yahoo\n";
        if(userData.getMeta() == 1)
            companies += "Meta\n";
        if(userData.getApple() == 1)
            companies += "Apple\n";
        if(userData.getNetflix() == 1)
            companies += "Netflix\n";
        return companies;
    }
}