package com.example.android_hinofi_prototype.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.android_hinofi_prototype.R;
import com.example.android_hinofi_prototype.adapters.LoginDatabaseAdapter;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{
    private EditText editTextUserEmail;
    private EditText editTextUserPassword;
    public String username;
    private String password;
    String storedPassword;
    Context context=this;

    LoginDatabaseAdapter loginDatabaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Creates an instance of the database
        loginDatabaseAdapter = new LoginDatabaseAdapter(getApplicationContext());

        editTextUserEmail = findViewById(R.id.textEditEmail);
        editTextUserPassword = findViewById(R.id.textEditPassword);
    }


    public void  SignIn(View view){

        try {
            loginDatabaseAdapter = loginDatabaseAdapter.open();
            username = editTextUserEmail.getText().toString();
            password = editTextUserPassword.getText().toString();

            if (username.equals("") || password.equals(""))  {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("ALERT!");
                alertDialog.setMessage("Fill All Fields");
                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){}
                });
                alertDialog.show();
            }

            //fetch the password from the database for respective username
            if(!username.equals(""))
            {
                storedPassword = loginDatabaseAdapter.getSingleUser(username);
            }
            if (password.equals(storedPassword)) {
                Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent1);
                //finish
            }
            else
            {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("ALERT!");
                alertDialog.setMessage("Incorrect Login Please Try Again");
                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){}
                });
                alertDialog.show();
            }
        }
        catch (Exception ex)
        {
            Log.e("Error", "error login");
        }
        }

        public void SignUp(View view)
        {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Close the database
        loginDatabaseAdapter.close();
    }
}



