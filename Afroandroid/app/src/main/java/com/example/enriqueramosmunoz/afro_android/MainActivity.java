package com.example.enriqueramosmunoz.afro_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logOutButton;
    private TextView tittle;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        tittle = findViewById(R.id.textTittle);
        logOutButton = findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if(mAuth.getCurrentUser()!=null){
            String currentUserEmail = mAuth.getCurrentUser().getEmail();
            tittle.setText(currentUserEmail);
        }
    }

    @Override
    public void onClick(View v) {
        if (v==logOutButton){
            logOut();
        }
    }

    private void logOut() {
        mAuth.signOut();
        goToAuth();
    }

    private void goToAuth() {
        Intent intent =new Intent(MainActivity.this, AuthActivity.class);
        startActivity(intent);
    }
}
