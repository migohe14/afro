package com.example.enriqueramosmunoz.afro_android;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {



    private TextView textInfo;
    private EditText emailInput;
    private EditText passwordInput;
    private Button signIn;
    private FirebaseAuth mAuth;
    private TextView signUp;
    private TextView facebookLogin;
    private TextView forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();

        textInfo = findViewById(R.id.textInfo);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(this);
        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(this);
        facebookLogin=findViewById(R.id.loginWithFacebook);
        facebookLogin.setOnClickListener(this);
        forgotPassword=findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);
        String email= getIntent().getStringExtra("EMAIL_EXTRA");
        String password= getIntent().getStringExtra("PASSWORD_EXTRA");
        if (email!=null){emailInput.setText(email);}
        if (password!=null){passwordInput.setText(password);}
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == signIn){
            signIn();
        }
        else if (v == signUp){
            Intent intent =new Intent(AuthActivity.this, SignUpActivity.class);
            if (emailInput.getText().toString().length()>0){
                intent.putExtra("EMAIL_EXTRA", emailInput.getText().toString());
            }
            if (passwordInput.getText().toString().length()>0){
                intent.putExtra("PASSWORD_EXTRA", passwordInput.getText().toString());
            }
            startActivity(intent);
            //finish();
        }
        else if(v==facebookLogin){
            facebookLogin();
        }
        else if(v==forgotPassword){
            forgotPassword();
        }
    }

    private void facebookLogin() {
        Intent intent = new Intent(AuthActivity.this, FacebookLoginActivity.class);
        startActivity(intent);
    }

    private void forgotPassword() {
        Intent intent = new Intent(AuthActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    private void signIn() {
        if (verifyFields()) {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(AuthActivity.this, "Authentication success.",Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    textInfo.setText("Loged as: " + user.getEmail());
                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(AuthActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    textInfo.setText("Sign In failed");
                    }
                }
            });
        }else alertFieldsNull();
    }

    private void alertFieldsNull() {
        Toast.makeText(AuthActivity.this, "Some fields are empty",Toast.LENGTH_SHORT).show();

        if (emailInput.getText().toString().length()==0 ){
            emailInput.setHintTextColor(getResources().getColor(R.color.alert));
        }
        if (passwordInput.getText().toString().length()==0){
            passwordInput.setHintTextColor(getResources().getColor(R.color.alert));
        }
    }

    private boolean verifyFields() {
        if(emailInput.getText().toString().length()>=1 && passwordInput.getText().toString().length()>=1){
            return true;
        }else {
            return false;
        }
    }
    


}
