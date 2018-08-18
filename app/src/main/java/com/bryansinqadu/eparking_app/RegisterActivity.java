package com.bryansinqadu.eparking_app;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;


public class RegisterActivity extends AppCompatActivity {


    private EditText name, phone,gender, email, password;
    String Name, Phone,Gender, Email, Password;
    TextView login;
    FirebaseAuth mAuth;
    DatabaseReference mdatabase;
    ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.etFullName);
        phone = (EditText) findViewById(R.id.etPhone);
        gender = (EditText) findViewById(R.id.etGender);
        email = (EditText) findViewById(R.id.etEmail);
         password = (EditText) findViewById(R.id.etPassword);
        login = (TextView) findViewById(R.id.tvLogin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");

    }

    public void btnRegister(View view) {
        UserRegister();
    }



    private void UserRegister() {
        Name = name.getText().toString().trim();
        Phone = phone.getText().toString().trim();
        Gender = gender.getText().toString().trim();
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();


        if (TextUtils.isEmpty(Name)){
            Toast.makeText(RegisterActivity.this, "Enter FullName..", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Email)){
            Toast.makeText(RegisterActivity.this, "Enter Email..", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Gender)){
            Toast.makeText(RegisterActivity.this, "Enter Gender..", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Password)){
            Toast.makeText(RegisterActivity.this, "Enter Password..", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Phone)){
            Toast.makeText(RegisterActivity.this, "Enter Phone..", Toast.LENGTH_SHORT).show();
            return;
        }else if (Password.length()<6){
            Toast.makeText(RegisterActivity.this,"Password must be greater then 6 digit",Toast.LENGTH_SHORT).show();
            return;
        }
        mDialog.setMessage("Creating User please wait...");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //sendEmailVerification();
                    mDialog.dismiss();
                    OnAuth(task.getResult().getUser());
                    mAuth.signOut();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    Toast.makeText(RegisterActivity.this,"Successful registered..you can log in",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this,"There was an error on creating user",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendEmailVerification() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Check your Email for verification",Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }else{
                        Toast.makeText(RegisterActivity.this,"Email was not sent successfully..",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void OnAuth(FirebaseUser user) {
        createAnewUser(user.getUid());
    }

    private void createAnewUser(String uid) {
        User user = BuildNewUser();
        mdatabase.child(uid).setValue(user);
    }


    private User BuildNewUser(){
        return new User(
                getName(),
                getPhone(),
                getGender(),
                getEmail(),
                getPassword()
        );
    }

    public String getName() {
        return Name;
    }
    public String getPhone(){
        return Phone;
    }
    public String getGender(){
        return Gender;
    }
    public String getEmail() {
        return Email;
    }
    public String getPassword(){
        return Password;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch(item.getItemId()){

            case R.id.settingsMenu:
                startActivity(new Intent(RegisterActivity.this, SettingsActivity.class));
                break;


            case R.id.googleMapMenu:
                startActivity(new Intent(RegisterActivity.this, MapsActivity.class));
                break;
            case R.id.exitMenu:
                finish();
                System.exit(0);
                break;
            }


        return super.onOptionsItemSelected(item);
    }


    public void tvLogin(View view) {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}