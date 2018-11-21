package com.example.enriqueramosmunoz.afro_android;

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

 //   String email= getIntent().getStringExtra("EMAIL_EXTRA");
 //   String password= getIntent().getStringExtra("PASSWORD_EXTRA");

    private TextView textInfo;
    private EditText emailInput;
    private EditText passwordInput;
    private Button signIn;
    private Button signOut;
    private FirebaseAuth mAuth;
    private TextView signUp;


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
        signOut = findViewById(R.id.signOut);
        signOut.setOnClickListener(this);
        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(this);

//        if (email!=null){emailInput.setText(email);}
//        if (password!=null){passwordInput.setText(password);}
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        if (v == signIn){
            signIn();
        }
        else if (v == signOut){
            signOut();
        }
        else if (v == signUp){
            Intent intent =new Intent(AuthActivity.this, SignUpActivity.class);
            //if (emailInput.getText() != null){
            //    getIntent().putExtra("EMAIL_EXTRA", emailInput.getText());
            //}
            //if (passwordInput.getText() != null){
            //    getIntent().putExtra("PASSWORD_EXTRA", passwordInput.getText());
            //}
            startActivity(intent);
        }
    }

    private void signIn() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(AuthActivity.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            textInfo.setText("Loged as: " + user.getEmail());
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            textInfo.setText("Sign In failed");
                        }

                        // ...
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
        textInfo.setText("Sign Out Success");
        Toast.makeText(AuthActivity.this, "Sign Out...",
                Toast.LENGTH_SHORT).show();
    }


}
