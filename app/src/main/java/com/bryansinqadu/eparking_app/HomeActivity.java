package com.bryansinqadu.eparking_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    CardView mybookings,myaccount,setting,calender;
    private FirebaseAuth firebaseAuth;
    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle nToggle;
    NavigationView navigationView;
    private long backPressedTime;
    private Toast backToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


       firebaseAuth = FirebaseAuth.getInstance();

        navMenu();

        mybookings = (CardView) findViewById(R.id.cardMyBookings);
        myaccount = (CardView) findViewById(R.id.cardMyaccount);
        setting = (CardView) findViewById(R.id.cardSettings);
        calender = (CardView) findViewById(R.id.cardCalender);

        mybookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,MyBookingsActivity.class));
            }
        });
        myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,MyAccountActivity.class));
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,SettingsActivity.class));
            }
        });
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,CalendarActivity.class));
            }
        });

    }
    public void btnNewBooking(View view) {
        startActivity(new Intent(HomeActivity.this,NewBookingActivity.class));
    }
        private void Logout(){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }

       public void navMenu(){
            nDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
            nToggle = new ActionBarDrawerToggle(HomeActivity.this,nDrawerLayout,R.string.open,R.string.close);
            navigationView = findViewById(R.id.navMenu);
            navigationView.setNavigationItemSelectedListener(this);



            nDrawerLayout.addDrawerListener(nToggle);
            nToggle.syncState();

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.welcome_menu, menu);
        return true;
       }

    @Override
    public void onBackPressed() {
        if (nDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            nDrawerLayout.closeDrawer(GravityCompat.START);
        } else if(backPressedTime + 2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
           backToast = Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
           backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }




       @Override
       public boolean onOptionsItemSelected(MenuItem item) {
        if(nToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
                break;
            }

            case R.id.profileMenu:
                startActivity(new Intent(HomeActivity.this, MyProfileActivity.class));
                break;


            case R.id.googleMapMenu:
                startActivity(new Intent(HomeActivity.this, MapsActivity.class));
                break;

            case R.id.Chat:
                startActivity(new Intent(HomeActivity.this, ChatActivity.class));
                break;

            case R.id.navAccount:
                startActivity(new Intent(HomeActivity.this, MyProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
       }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navAccount:
                startActivity(new Intent(HomeActivity.this,MyProfileActivity.class));
                break;
            case R.id.navBooking:
                startActivity(new Intent(HomeActivity.this,MyBookingsActivity.class));
                break;
            case R.id.navMyProfile:
                startActivity(new Intent(HomeActivity.this,UpdateProfileActivity.class));
                break;
            case R.id.navChat:
                startActivity(new Intent(HomeActivity.this,ChatActivity.class));
                break;

            case R.id.navSettings:
                startActivity(new Intent(HomeActivity.this,SettingsActivity.class));
                break;
            case R.id.navLogout:
                Logout();
                break;

            case R.id.navExit:
                finish();
                System.exit(0);
                break;
        }

        nDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


}