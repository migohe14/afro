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

        String email = getIntent().getStringExtra("EMAIL_EXTRA");
        String password = getIntent().getStringExtra("PASSWORD_EXTRA");

        if (email!=null){emailInput.setText(email);}
        if (password!=null){passwordInput.setText(password);}
    }

    @Override
    public void onClick(View v) {
        if (v == title){
            goToAuth();
        }
        else if (v == enterButton){
            checkFields();
        }
    }

    private void goToAuth() {
        Intent intent =new Intent(SignUpActivity.this, AuthActivity.class);
        if (emailInput.getText() != null){
            intent.putExtra("EMAIL_EXTRA", emailInput.getText().toString());
        }
        if (passwordInput.getText() != null){
            intent.putExtra("PASSWORD_EXTRA", passwordInput.getText().toString());
        }
        startActivity(intent);
    }

    private void checkFields() {
        if(emailInput.getText().toString().length()>0
                && passwordInput.getText().toString().length()>0
                && verifyPasswordInput.getText().toString().length()>0
                && userInput.getText().toString().length()>0){
                    if(verifyPasswordInput.getText().toString().equals(passwordInput.getText().toString())){
                        verifyPasswordInput.setTextColor(getResources().getColor(R.color.black));
                        createNewAccount();
                    }
                    else {
                        passwordNotEqual();
                    }
        }else {
            alertFieldsNull();
        }
    }

    private void passwordNotEqual() {
        Toast.makeText(SignUpActivity.this, "The passwords does not match",Toast.LENGTH_SHORT).show();
        verifyPasswordInput.setTextColor(getResources().getColor(R.color.alert));
    }

    private void alertFieldsNull() {
        Toast.makeText(SignUpActivity.this, "Some fields are empty",Toast.LENGTH_SHORT).show();

        if (emailInput.getText().toString().length()==0 ){
            emailInput.setHintTextColor(getResources().getColor(R.color.alert));
        }
        if (passwordInput.getText().toString().length()==0){
            passwordInput.setHintTextColor(getResources().getColor(R.color.alert));
        }
        if (verifyPasswordInput.getText().toString().length()==0){
            verifyPasswordInput.setHintTextColor(getResources().getColor(R.color.alert));
        }
        if (userInput.getText().toString().length()==0){
            userInput.setHintTextColor(getResources().getColor(R.color.alert));
        }
    }

    private void createNewAccount(){
        mAuth.createUserWithEmailAndPassword(emailInput.getText().toString(), passwordInput.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUpActivity.this, "Authentication success.",
                            Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, task.getException().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


}
