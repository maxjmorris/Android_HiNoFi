package com.example.android_hinofi_prototype.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import com.example.android_hinofi_prototype.R;


public class HelpActivity extends AppCompatActivity {

    private EditText textEditRecipient;
    private EditText textEditSubject;
    private EditText textEditBodyMessage;
    private String txtRecipient;
    private String txtSubject;
    private String txtBodyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

       textEditRecipient = findViewById(R.id.editTextRecipient);
       textEditSubject = findViewById(R.id.editTextSubject);
       textEditBodyMessage = findViewById(R.id.editTextBodyMessage);



        //The send button
        Button btnsendEmail = findViewById(R.id.btnSendEmail);
        btnsendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    protected void sendEmail(){

        //Gets the text from the recipient entry
        txtRecipient = textEditRecipient.getText().toString();

        //Gets the text from the subject entry
        txtSubject = textEditSubject.getText().toString();

        //Gets the text from the body message entry
        txtBodyMessage = textEditBodyMessage.getText().toString();


        Log.i("Send email","");
        String[] TO = {
                "p15213515@my365.dmu.ac.uk"
        };

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,txtSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT,txtBodyMessage);
        try{
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
            finish();
            Log.i("Email Sent","");
        }
        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(HelpActivity.this, "No Email client found", Toast.LENGTH_SHORT).show();
        }

    }
}
