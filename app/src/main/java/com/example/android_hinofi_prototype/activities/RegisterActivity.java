package com.example.android_hinofi_prototype.activities;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;


import com.example.android_hinofi_prototype.R;
import com.example.android_hinofi_prototype.models.User;
import com.example.android_hinofi_prototype.helpers.InputValidation;

import com.example.android_hinofi_prototype.sql.DatabaseHelper;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatTextView lnkLogin;

    private AppCompatButton SignUpButton;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /***
     * Method to initialise the views of the Registering Page
     */
    private void initViews() {
        nestedScrollView = findViewById(R.id.login_form);

        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextUsername = findViewById(R.id.textInputEditUsername);
        textInputEditTextEmail = findViewById(R.id.textInputEditEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditPassword);
        textInputEditTextConfirmPassword = findViewById(R.id.textInputEditConfirmPassword);


        SignUpButton = findViewById(R.id.btnRegister);
        lnkLogin = findViewById(R.id.lnkLogin);

    }
    /***
     * Method to initialise the listeners of the Registering page
     */
    private void initListeners() {
        SignUpButton.setOnClickListener(this);
        lnkLogin.setOnClickListener(this);
    }

    /**
     * method to initialise the objects we are going to use for Registering
     */
    private void initObjects()
    {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnRegister:
                postDataToSQLite();
                break;

            case R.id.lnkLogin:
                finish();
                break;
        }
    }





        public void postDataToSQLite() {
            if (!inputValidation.isInputEditTextFilled(textInputEditTextUsername, textInputLayoutUsername, getString(R.string.error_message_username))) {
                return;
            }

            if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail,textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
            }


            if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
                return;
            }

            if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
                return;
            }

            if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword,textInputEditTextConfirmPassword,
                    textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
                return;
            }

            if(!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
            user.setUsername(textInputEditTextUsername.getText().toString().trim());
            user.setEmailAddress(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextEmail.getText().toString().trim());

            databaseHelper.addUser(user);

            //Snack bar to show success of the message that saved successfully
                Snackbar.make(nestedScrollView, getString(R.string.message_success),
                        Snackbar.LENGTH_LONG).show();
                emptyInputEditText();
            }
            else {
                //Snack bar will show error message if a user isn't saved successfully or already exists
                Snackbar.make(nestedScrollView, getString(R.string.error_user_exists), Snackbar.LENGTH_LONG).show();
            }

        }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextUsername.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);

    }

}



