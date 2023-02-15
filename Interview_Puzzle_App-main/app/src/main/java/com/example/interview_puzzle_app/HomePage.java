package com.example.interview_puzzle_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interview_puzzle_app.databinding.ActivityHomePageBinding;
import com.example.interview_puzzle_app.db.SqliteDBHelper;
import com.example.interview_puzzle_app.db.UserData;
import com.example.interview_puzzle_app.ui.logIn.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity/* implements FragmentManager.OnBackStackChangedListener*/{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomePageBinding binding;
    View v1;
    TextView username,emailid1,Coins, welcome;
    Button profile;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    DrawerLayout drawer;
    //ActionBarDrawerToggle mDrawerToggle;
    // private boolean mToolBarNavigationListenerIsRegistered = false;

    UserData userData;
    SqliteDBHelper DB;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("14.04","Inside HomePage");
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarHomePage.toolbar);

         drawer = binding.drawerLayout;
         NavigationView navigationView = binding.navView;

        v1 = navigationView.getHeaderView(0);

        //Fetching Current loged in user data from Data Base
        DB = new SqliteDBHelper(getApplicationContext());
        Log.d("14.04","Opening database in homepage");
        try {
            DB.openDataBase();
        }catch (Exception e){
            Log.d("14.04","Error to open DB : "+e);
        }
        Log.d("14.04","Trying to get userinfo");
        try {
            userData = DB.getUserInfo();
        }catch (Exception e){
            Log.d("14.04","Error to fetching data from DB : "+e);
        }
        Log.d("14.04","User Data Fetched");

        //adding the initials of the name to button
        StringBuilder initials =  new StringBuilder();
        for(String s : userData.getName().split(" "))
        {
            initials.append(s.charAt(0));
        }
        profile=(Button)v1.findViewById(R.id.btnProfileOval);
        profile.setText(initials.toString());

        username =  (TextView)v1.findViewById(R.id.tvUname);
        username.setText(userData.getName());

        emailid1 =(TextView)v1.findViewById(R.id.tvEmail);
        emailid1.setText(userData.getEmail());

        Coins = (TextView) findViewById(R.id.tvCoin);
        Coins.setText(Integer.toString(userData.coins));

        welcome = (TextView) findViewById(R.id.userWelcome);
        welcome.setText("Welcome\n"+userData.getName());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_practiceMode, R.id.nav_slideshow,R.id.nav_history,
                R.id.nav_help,R.id.nav_Profile)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_signout:
                gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
                gsc = GoogleSignIn.getClient(this, gso);
                signOutUser();
                Toast.makeText(this,"Sign out the user",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void signOutUser()
    {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(HomePage.this, MainActivity.class));
            }
        });
    }

}