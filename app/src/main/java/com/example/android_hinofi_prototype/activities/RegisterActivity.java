package com.example.android_hinofi_prototype.activities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_hinofi_prototype.R;
import com.example.android_hinofi_prototype.adapters.DatabaseAdapter;

public class RegisterActivity extends AppCompatActivity {

    /**
     * Create objects fot the text entries in the register page
     */
    private EditText textEditUsername;
    private EditText textEditEmail;
    private EditText textEditPassword;
    private EditText textEditDOB;
    private EditText textEditConfirmPassword;

    private String userName;
    private String emailAddress;
    private String password;
    private String dateOfBirth;
    private Button btnSignUpUser;
    private TextView linkLoginPage;
    String receiveOk;
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Sign Up");
        //get instance of the databases adapter
        /**
         * Connects to the databaseAdapter adapter and will open the databaseAdapter in the app
         */
        databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter = databaseAdapter.open();
        /**
         * Initialises the view for the register page
         */
        textEditEmail = findViewById(R.id.textEditEmail);
        textEditPassword = findViewById(R.id.textEditPassword);
        textEditConfirmPassword = findViewById(R.id.textEditConfirmPassword);
        textEditUsername = findViewById(R.id.textEditUsername);
        textEditDOB = findViewById(R.id.textEditDOB);
        btnSignUpUser = findViewById(R.id.btnSignUpUser);
        /**
         * Initiates the insertion of the user signing them by clicking the sign up button
         */
        btnSignUpUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OK(v);
            }
        });

        /***
         * Will send the user back to the login page
         */
        linkLoginPage = findViewById(R.id.lnkLogin);
        linkLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }




    public void OK(View view) {

        userName = textEditUsername.getText().toString();
        emailAddress = textEditEmail.getText().toString();
        password = textEditPassword.getText().toString();
        dateOfBirth = textEditDOB.getText().toString();


        if ((userName.equals("")) || (emailAddress.equals("")) || (password.equals("")) || (dateOfBirth.equals(""))) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("ALERT!");
            alertDialog.setMessage("Fill All Fields");
            alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }


        else {


            if (!textEditPassword.getText().toString().equals(textEditConfirmPassword.getText().toString()))
            {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("ALERT!");
                alertDialog.setMessage("Passwords don't match");
                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
            else
            {
                //Save the data in the databases
                receiveOk= databaseAdapter.insertUser(userName,emailAddress,password, dateOfBirth);

                android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(this).create();
                alertDialog.setTitle("SUCCESS!");
                alertDialog.setMessage("You've signed up");
                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    }
                });
                alertDialog.show();
                finish();
            }

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        databaseAdapter.close();
    }
}



