package com.bryansinqadu.eparking_app;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class LoginActivity extends AppCompatActivity implements TextWatcher,
        CompoundButton.OnCheckedChangeListener  {

    private EditText etEmail;
    private EditText etPassword;
    private TextView forgotpassword;
    private TextView Info;
    private Button Login, Register;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String password, email;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "email";
    private static final String KEY_PASS = "pass";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        Register = (Button) findViewById(R.id.btnRegister);
        forgotpassword = (TextView) findViewById(R.id.tvForgotPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        rem_userpass = (CheckBox) findViewById(R.id.cbRememberMe);


        sharedPreferences();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Check()) {
                    validate(etEmail.getText().toString(), etPassword.getText().toString());
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    Toast.makeText(LoginActivity.this,"Logged in successfully..",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotpasswordIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                LoginActivity.this.startActivity(forgotpasswordIntent);
            }
        });
    }

    private void validate(String userEmail, String userPassword) {

        progressDialog.setMessage("Please wait, verifying your details!");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                   // checkEmailVerification();

                }
            }
        });

    }

    public Boolean Check() {
        Boolean result = false;


        email = etEmail.getText().toString();
        password = etPassword.getText().toString();


        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password to login", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();



        if (emailflag) {
            finish();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        } else {
            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

    public void sharedPreferences(){
      sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
      editor = sharedPreferences.edit();


      if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
          rem_userpass.setChecked(true);
      else
          rem_userpass.setChecked(false);

      etEmail.setText(sharedPreferences.getString(KEY_USERNAME,""));
      etPassword.setText(sharedPreferences.getString(KEY_PASS,""));

      etEmail.addTextChangedListener(this);
      etPassword.addTextChangedListener(this);
      rem_userpass.setOnCheckedChangeListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        managePrefs();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }

    private void managePrefs(){
        if(rem_userpass.isChecked()){
            editor.putString(KEY_USERNAME, etEmail.getText().toString().trim());
            editor.putString(KEY_PASS, etPassword.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);
            editor.remove(KEY_USERNAME);
            editor.apply();
        }
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