package com.example.android_hinofi_prototype.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android_hinofi_prototype.R;
public class MyAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        String username = getIntent().getStringExtra("Username");
        String DOB = getIntent().getStringExtra("DateOfBirth");
        String EmailAddress = getIntent().getStringExtra("EmailAddress");

        TextView textViewUsername = findViewById(R.id.txtViewUsername);
        TextView textViewDOB = findViewById(R.id.txtViewDOB);
        TextView textViewEmailAddress = findViewById(R.id.txtViewEmailAddress);

        textViewUsername.setText(username);
        textViewDOB.setText(DOB);
        textViewEmailAddress.setText(EmailAddress);
    }
}
