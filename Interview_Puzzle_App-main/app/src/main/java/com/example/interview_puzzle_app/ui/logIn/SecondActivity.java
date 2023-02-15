package com.example.interview_puzzle_app.ui.logIn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interview_puzzle_app.HomePage;
import com.example.interview_puzzle_app.R;
import com.example.interview_puzzle_app.db.SqliteDBHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    public EditText Name, Age;
    public TextView email;
    public Button signOut,Save,Clear;
    public CheckBox CBmicrosoft, CBgoogle, CBmeta, CByahoo, CBnetflix, CBapple;     //CB - Check Box Variable
    public int micro=0,goog=0,met=0,yah=0,netf=0,appl=0;
    SqliteDBHelper DB;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // fetching the data from previous activity to get name and email id of user
        Intent ito2=getIntent();
        String userName=ito2.getStringExtra("name");
        String userEmail=ito2.getStringExtra("emailID");

        //FindViewByID and Checkbox
        {
            Name = (EditText) findViewById(R.id.getName);
            Name.setText(userName);
            Age = (EditText) findViewById(R.id.getAge);
            email = (TextView) findViewById(R.id.getEmail);
            email.setText(userEmail);
            signOut = (Button) findViewById(R.id.btnSignOut);
            Save = (Button) findViewById(R.id.SBSave);
            Clear = (Button) findViewById(R.id.SBclear);
            //Company CheckBoxes
            CBmicrosoft = (CheckBox) findViewById(R.id.SCmicrosoft);
            CBmicrosoft.setOnClickListener(this);

            CBgoogle = (CheckBox) findViewById(R.id.SCgoogle);
            CBgoogle.setOnClickListener(this);

            CBmeta = (CheckBox) findViewById(R.id.SCmeta);
            CBmeta.setOnClickListener(this);

            CByahoo = (CheckBox) findViewById(R.id.SCyahoo);
            CByahoo.setOnClickListener(this);

            CBnetflix = (CheckBox) findViewById(R.id.SCnetflix);
            CBnetflix.setOnClickListener(this);

            CBapple = (CheckBox) findViewById(R.id.SCapple);
            CBapple.setOnClickListener(this);
        }

        //Creating object for DataBase
        DB = new SqliteDBHelper(this);

        //SignIn related
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        DB.openDataBase();
        //Checking if user already exist
        if(DB.isAlreadyRegister(userEmail)){
            Toast.makeText(getApplicationContext(), "Email already register", Toast.LENGTH_SHORT).show();
            DB.logingIn(userEmail);
            Intent alreadyRegister = new Intent(this, HomePage.class);
            startActivity(alreadyRegister);
            finish();
        }

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
                DB.openDataBase();
                //Checking if user already exist
                if(DB.isAlreadyRegister(userEmail)){
                    Toast.makeText(getApplicationContext(), "Email already register", Toast.LENGTH_SHORT).show();
                    Intent alreadyRegister = new Intent(this,HomePage.class);
                    startActivity(alreadyRegister);
                    finish();
                    return;
                }

                int AgeI=0;
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
                Boolean checkInsert = DB.RegisterUser(userEmail, 0, nameS, AgeI, 100, micro, goog, yah, met, appl, netf);
                if(checkInsert) {
                    Toast.makeText(getApplicationContext(), "Account created Successfully", Toast.LENGTH_SHORT).show();
                    DB.logingIn(userEmail);
                    Intent i3 = new Intent(SecondActivity.this, HomePage.class);    //@@@@@---------don't send data to HomePage fetch from DB
                    startActivity(i3);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Something went wrong\nAccount is not created", Toast.LENGTH_SHORT).show();


            } catch (Exception e) {
                Log.d("Result" , "Error in saving data : "+e);
                Toast.makeText(getApplicationContext(),"Error in saving data : "+e,Toast.LENGTH_SHORT).show();
            }


        });

        //SignOut Button
        signOut.setOnClickListener(view -> signOutUser());

    }

    void signOutUser()
    {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
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
}

