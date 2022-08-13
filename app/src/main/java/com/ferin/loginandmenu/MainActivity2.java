package com.ferin.loginandmenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView nameText,mobileText,locationText,emailText;
    String name, mobile, location, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameText = findViewById(R.id.nameTextView);
        mobileText = findViewById(R.id.mobileTextView);
        locationText = findViewById(R.id.locationTextView);
        emailText = findViewById(R.id.emailTextView);

        name = getIntent().getStringExtra("Name");
        nameText.setText(name);

        mobile = getIntent().getStringExtra("Mobile");
        mobileText.setText(mobile);

        location = getIntent().getStringExtra("Location");
        locationText.setText(location);

        email = getIntent().getStringExtra("Email");
        emailText.setText(email);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void Back(MenuItem item) {
//        Intent backIntent = new Intent(MainActivity2.this,MainActivity.class);
//        startActivity(backIntent);
        finish();
    }

    public void About(MenuItem item) {
       Intent tyActivityIntent = new Intent(MainActivity2.this,ThankYouActivity.class);
        startActivity(tyActivityIntent);
    }

    public void Exit(MenuItem item) {
        // This closes the app completely, all activities
        MainActivity2.this.finishAffinity();
    }

    public void AlertBox(MenuItem item) {
        AlertDialog.Builder alertBox = new AlertDialog.Builder(MainActivity2.this);
        alertBox.setTitle("AlertBox");
        alertBox.setMessage("Click on the Center Button for Custom Dialog Box");
        alertBox.setPositiveButton("Right",null);
        alertBox.setNegativeButton("CustomDialog", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog googleDialog = new Dialog(MainActivity2.this);
                googleDialog.setContentView(R.layout.custom_dialogbox);
                googleDialog.show();
            }
        });
        alertBox.setIcon(R.drawable.androidicon);
        alertBox.setNeutralButton("Left",null);
        alertBox.show();
    }

    public void displayImage(View view) {
        Intent intentNew = new Intent(MainActivity2.this,ThankYouActivity.class);
        startActivity(intentNew);
    }

    public void WebViewMethod(MenuItem item) {
        Intent intentWebView = new Intent(MainActivity2.this,WebViewActivity.class);
        startActivity(intentWebView);
    }
}