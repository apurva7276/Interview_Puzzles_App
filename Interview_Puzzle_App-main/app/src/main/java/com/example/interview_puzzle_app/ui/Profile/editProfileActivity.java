package com.example.interview_puzzle_app.ui.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//R factor
import com.example.interview_puzzle_app.HomePage;
import com.example.interview_puzzle_app.R;
import com.example.interview_puzzle_app.db.SqliteDBHelper;
import com.example.interview_puzzle_app.db.UserData;
import com.example.interview_puzzle_app.ui.logIn.SecondActivity;

import java.util.Objects;

public class editProfileActivity extends AppCompatActivity {

    public EditText Name, Age;
    public TextView email;
    public Button Save,Clear;
    public CheckBox CBmicrosoft, CBgoogle, CBmeta, CByahoo, CBnetflix, CBapple;     //CB - Check Box Variable
    public int micro=0,goog=0,met=0,yah=0,netf=0,appl=0;
    SqliteDBHelper DB;
    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Fetching Data from database
        DB = new SqliteDBHelper(getApplicationContext());
        userData = DB.getUserInfo();

        //FindViewByID
        {
            Name = (EditText) findViewById(R.id.EPgetName);
            Age = (EditText) findViewById(R.id.EPgetAge);
            email = (TextView) findViewById(R.id.EPgetEmail);
            Save = (Button) findViewById(R.id.EPBSave);
            Clear = (Button) findViewById(R.id.EPBclear);
            //Company CheckBoxes
            CBmicrosoft = (CheckBox) findViewById(R.id.EPCmicrosoft);
            CBgoogle = (CheckBox) findViewById(R.id.EPCgoogle);
            CBmeta = (CheckBox) findViewById(R.id.EPCmeta);
            CByahoo = (CheckBox) findViewById(R.id.EPCyahoo);
            CBnetflix = (CheckBox) findViewById(R.id.EPCnetflix);
            CBapple = (CheckBox) findViewById(R.id.EPCapple);
        }

        //Loading data in respective fields
        Name.setText(userData.getName());
        email.setText(userData.getEmail());
        String SageS = Integer.toString(userData.getAge());
        Age.setText(SageS);
        micro=userData.getMicrosoft();
        goog=userData.getGoogle();
        met=userData.getMeta();
        yah=userData.getYahoo();
        netf=userData.getNetflix();
        appl=userData.getApple();
        setCompanies();

        CBmicrosoft.setOnClickListener(view -> {
            if (CBmicrosoft.isChecked())
                micro=1;
            else
                micro=0;
        });
        CBgoogle.setOnClickListener(view -> {
            if (CBgoogle.isChecked())
                goog=1;
            else
                goog=0;
        });
        CBmeta.setOnClickListener(view -> {
            if (CBmeta.isChecked())
                met=1;
            else
                met=0;
        });
        CByahoo.setOnClickListener(view -> {
            if (CByahoo.isChecked())
                yah=1;
            else
                yah=0;
        });
        CBnetflix.setOnClickListener(view -> {
            if (CBnetflix.isChecked())
                netf=1;
            else
                netf=0;
        });
        CBapple.setOnClickListener(view -> {
            if (CBapple.isChecked())
                appl=1;
            else
                appl=0;
        });

        //Clear Button
        Clear.setOnClickListener(view -> {
            Name.setText("");
            Age.setText("");

            if(CBmicrosoft.isChecked()){
                CBmicrosoft.toggle();
            }
            if(CBgoogle.isChecked()){
                CBgoogle.toggle();
            }
            if(CBmeta.isChecked()){
                CBmeta.toggle();
            }
            if(CByahoo.isChecked()){
                CByahoo.toggle();
            }
            if(CBnetflix.isChecked()){
                CBnetflix.toggle();
            }
            if(CBapple.isChecked()){
                CBapple.toggle();
            }

        });

        //Save Button
        Save.setOnClickListener(view -> {       //-----------also implement logic for re-login of same user
            try {
                int AgeI=userData.getAge(),
                        avatar=userData.getAvtar(),
                        coins = userData.getCoins();
                String nameS = Name.getText().toString();
                String ageS = Age.getText().toString();

                if(nameS.matches("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Your Name"+ageS, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ageS.matches("")){
                    Toast.makeText(getApplicationContext(), "Please Enter Your Age", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!ageS.equals("")){
                    AgeI = Integer.parseInt(ageS);
                }
                if(!CBmicrosoft.isChecked()){
                    if(!CBmeta.isChecked()){
                        if(!CBgoogle.isChecked()){
                            if(!CByahoo.isChecked()){
                                if(!CBnetflix.isChecked()){
                                    if(!CBapple.isChecked()){
                                        Toast.makeText(getApplicationContext(), "Please select Atleast one company", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }

                //Inserting data to sqlite
                DB.openDataBase();
                Boolean checkInsert = DB.updateUser(userData.getEmail(), nameS, AgeI, micro, goog, yah, met, appl, netf);
                if(checkInsert) {
                    Toast.makeText(getApplicationContext(), "Account updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent i3 = new Intent(editProfileActivity.this, Profile.class);
                    startActivity(i3);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Something went wrong\nAccount is not updated", Toast.LENGTH_SHORT).show();


            } catch (Exception e) {
                Log.d("Result" , "Error in saving data : "+e);
                Toast.makeText(getApplicationContext(),"Error in saving data : "+e,Toast.LENGTH_SHORT).show();
            }


        });


    }

    private void setCompanies() {
        CBmicrosoft.setChecked(false);
        CBgoogle.setChecked(false);
        CBmeta.setChecked(false);
        CByahoo.setChecked(false);
        CBnetflix.setChecked(false);
        CBapple.setChecked(false);
        if(micro == 1){
            CBmicrosoft.setChecked(true);
        }
        if(goog == 1){
            CBgoogle.setChecked(true);
        }
        if(met == 1){
            CBmeta.setChecked(true);
        }
        if(yah == 1){
            CByahoo.setChecked(true);
        }
        if(netf == 1){
            CBnetflix.setChecked(true);
        }
        if(appl == 1){
            CBapple.setChecked(true);
        }
    }

   /* @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.SCmicrosoft:
                if (CBmicrosoft.isChecked())
                    micro=1;
                break;
            case R.id.SCapple:
                if (CBapple.isChecked())
                    appl=1;
                break;
            case R.id.SCgoogle:
                if (CBgoogle.isChecked())
                    goog=1;
                break;
            case R.id.SCmeta:
                if (CBmeta.isChecked())
                    met=1;
                break;
            case R.id.SCnetflix:
                if (CBnetflix.isChecked())
                    netf=1;
                break;
            case R.id.SCyahoo:
                if (CByahoo.isChecked())
                    yah=1;
                break;
        }
    }
*/
}