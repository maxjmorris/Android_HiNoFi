package com.example.android_hinofi_prototype.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.android_hinofi_prototype.R;
import com.example.android_hinofi_prototype.helpers.InputValidation;
import com.example.android_hinofi_prototype.sql.DatabaseHelper;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton LoginButton;

    private AppCompatButton SignUpButton;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /***
     * Method to initialise the views of the login page
     */
    private void initViews() {
        nestedScrollView = findViewById(R.id.login_form);

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = findViewById(R.id.textInputEditEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditPassword);
        LoginButton = findViewById(R.id.btnLogin);

        SignUpButton = findViewById(R.id.btnRegister);


    }

    /***
     * Method to initialise the listeners of the login page
     */
    private void initListeners() {
        LoginButton.setOnClickListener(this);
        SignUpButton.setOnClickListener(this);
    }

    /**
     * method to initialise the objects we are going to use for this login
     * Which are the database helper to connect to our class user
     * and the input validation so the right text is put in for the entries
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                verifyFromSQLite();
                break;
            case R.id.btnRegister:
                //Navigates to the register page
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString((R.string.error_message_email)))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString((R.string.error_message_email)))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString((R.string.error_message_password)))) {
            return;
        }

        if(!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(),
                textInputEditTextPassword.getText().toString().trim())) {

            Intent accountsIntent = new Intent(activity, MyAccountActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        }
        else {
            Snackbar.make(nestedScrollView, getString(R.string.error_invalid_email), Snackbar.LENGTH_LONG).show();
        }
        }
    /**
     * This method is to empty all input edit text
     */
        private void emptyInputEditText()
        {
            textInputEditTextEmail.setText(null);
            textInputEditTextPassword.setText(null);
        }
    }


