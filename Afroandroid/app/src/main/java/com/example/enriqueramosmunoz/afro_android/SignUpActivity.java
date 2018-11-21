package com.example.enriqueramosmunoz.afro_android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    //String email= getIntent().getStringExtra("EMAIL_EXTRA");
    //String password= getIntent().getStringExtra("PASSWORD_EXTRA");

    private FirebaseAuth mAuth;
    private TextView title;
    private EditText emailInput;
    private EditText userInput;
    private EditText passwordInput;
    private EditText verifyPasswordInput;
    private Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        title = findViewById(R.id.signUpText);
        title.setOnClickListener(this);
        emailInput = findViewById(R.id.emailInput);
        userInput = findViewById(R.id.userInput);
        passwordInput = findViewById(R.id.passwordInput);
        verifyPasswordInput = findViewById(R.id.verifyPasswordInput);
        enterButton = findViewById(R.id.signUpButton);
        enterButton.setOnClickListener(this);

        //if (email!=null){emailInput.setText(email);}
        //if (password!=null){passwordInput.setText(password);}
    }

    @Override
    public void onClick(View v) {
        if (v == title){
            Intent intent =new Intent(SignUpActivity.this, AuthActivity.class);
            //if (emailInput.getText() != null){
            //    getIntent().putExtra("EMAIL_EXTRA", emailInput.getText());
            //}
            //if (passwordInput.getText() != null){
            //    getIntent().putExtra("PASSWORD_EXTRA", passwordInput.getText());
            //}
            startActivity(intent);
        }
        else if (v == enterButton){
            Toast.makeText(SignUpActivity.this, "1",
                    Toast.LENGTH_SHORT).show();
            createNewAccout();
        }
    }

    private void createNewAccout(){
        mAuth.createUserWithEmailAndPassword(emailInput.getText().toString(), passwordInput.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUpActivity.this, "Authentication success.",
                            Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    private void updateUI(FirebaseUser user) {
         FirebaseUser fireUser = user;
        title.setText(user.getEmail());
    }


}
