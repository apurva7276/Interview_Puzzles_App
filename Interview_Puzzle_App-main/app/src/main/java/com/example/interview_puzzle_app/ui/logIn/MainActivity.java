package com.example.interview_puzzle_app.ui.logIn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.interview_puzzle_app.HomePage;
import com.example.interview_puzzle_app.R;
import com.example.interview_puzzle_app.db.SqliteDBHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN=100;
    SqliteDBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sqlite object creator
        DB = new SqliteDBHelper(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null)
        {
            String personName = account.getDisplayName();
            String personEmail = account.getEmail();
            Log.d("auth", account.getId());
            finish();
            //Checking if user already exist
            DB.openDataBase();
            if(DB.isAlreadyRegister(personEmail)){
                Toast.makeText(getApplicationContext(), "Logged in with "+personEmail, Toast.LENGTH_SHORT).show();
                DB.logingIn(personEmail);
                Intent alreadyRegister = new Intent(this, HomePage.class);
                startActivity(alreadyRegister);
                finish();
            }
        }
        else {
            SignInButton signInButton = findViewById(R.id.sign_in_button);
            signInButton.setSize(SignInButton.SIZE_STANDARD);
            signInButton.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        signIn();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        String personName="";
        String personEmail="";
        try {
            completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                personName = acct.getDisplayName();
                personEmail = acct.getEmail();
                Toast.makeText(MainActivity.this,"User Email:"+personEmail,Toast.LENGTH_LONG).show();
            }
            finish();
            //Checking if user already exist, if yes then redirecting to homepage
            DB.openDataBase();
            if(DB.isAlreadyRegister(personEmail)){
                DB.logingIn(personEmail);
                Intent alreadyRegister = new Intent(this,HomePage.class);
                startActivity(alreadyRegister);
                finish();
            }
            else {
                //If user login for first time
                Intent ito2 = new Intent(this, SecondActivity.class);
                ito2.putExtra("name", personName);
                ito2.putExtra("emailID", personEmail);
                startActivity(ito2);
                finish();
            }

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("Exception",e.toString());
        }
    }

}