package com.bryansinqadu.eparking_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MyAccountActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void GoToBookings(View view) {
        startActivity(new Intent(MyAccountActivity.this,MyBookingsActivity.class));
    }

    public void GoToMyProfile(View view) {
        startActivity(new Intent(MyAccountActivity.this,MyProfileActivity.class));
    }

    public void GoToMessages(View view) {
        startActivity(new Intent(MyAccountActivity.this,MessagesActivity.class));
    }

    public void GoToNotifications(View view) {
        startActivity(new Intent(MyAccountActivity.this,NotificationsActivity.class));
    }

    public void GoToSettings(View view) {
        startActivity(new Intent(MyAccountActivity.this,SettingsActivity.class));
    }

    public void Logout(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MyAccountActivity.this);
        dialog.setTitle("Logout");
        dialog.setMessage("Are you sure you want to logout?");
        dialog

              .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      mAuth.signOut();
                      startActivity(new Intent(MyAccountActivity.this,LoginActivity.class));
                  }
              })
              .setNegativeButton("No", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      dialog.cancel();
                  }
              });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}
