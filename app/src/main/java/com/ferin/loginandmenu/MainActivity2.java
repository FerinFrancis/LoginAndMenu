package com.ferin.loginandmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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
        Intent backIntent = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(backIntent);
    }

    public void About(MenuItem item) {
        Intent tyActivityIntent = new Intent(MainActivity2.this,ThankYouActivity.class);
        startActivity(tyActivityIntent);
    }

    public void Exit(MenuItem item) {
        // This closes the app completely, all activities
        MainActivity2.this.finishAffinity();
    }
}