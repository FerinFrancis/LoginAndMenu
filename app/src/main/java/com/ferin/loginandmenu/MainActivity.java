package com.ferin.loginandmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nameEdit, mobileEdit,locationEdit,emailEdit;
    CheckBox agreeCheck;
    Button toastButtonVar,textViewButton, goNextButton;
    TextView displayView;
    String name, mobile,location,email,data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEdit = findViewById(R.id.nameEnterEdit);
        mobileEdit = findViewById(R.id.mobileEnterEdit);
        locationEdit = findViewById(R.id.locationEnterEdit);
        emailEdit = findViewById(R.id.emailEnterEdit);

        agreeCheck = findViewById(R.id.agreeCheckBox);
        toastButtonVar = findViewById(R.id.toastButton);
        textViewButton = findViewById(R.id.textViewButton);
        goNextButton = findViewById(R.id.goToNext);
        displayView = findViewById(R.id.displayTextView);


        toastButtonVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When button is clicked, it stores the data from all editTexts to respective variable
                name = nameEdit.getText().toString();
                mobile = mobileEdit.getText().toString();
                location = locationEdit.getText().toString();
                email = emailEdit.getText().toString();

                // And then it verifies if checkbox is in checked state
                if(agreeCheck.isChecked()){

                    // If it is in checked state, proceeds to checks if name is empty or not
                    if (TextUtils.isEmpty(name))
                    {
                        // If name is empty, this toast message is shown
                        Toast.makeText(MainActivity.this,
                                "Empty field not allowed!",
                                Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        // If name has data, these steps gets executed
                    data = "Name: "+name+" \nMobile: "+mobile+" \nLocation: "+location+" \nEmail: "+email;
                    Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Agree and Retry", Toast.LENGTH_SHORT).show();
                }

            }
        });


        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameEdit.getText().toString();
                mobile = mobileEdit.getText().toString();
                location = locationEdit.getText().toString();
                email = emailEdit.getText().toString();
                if(agreeCheck.isChecked()){
                    data = "Name: "+name+" \nMobile: "+mobile+" \nLocation: "+location+" \nEmail: "+email;
                    displayView.setText(data);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Agree and Retry", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When button is clicked, it stores the data from all editTexts to respective variable
                name = nameEdit.getText().toString();
                mobile = mobileEdit.getText().toString();
                location = locationEdit.getText().toString();
                email = emailEdit.getText().toString();

                // And then it verifies if checkbox is in checked state
                if(agreeCheck.isChecked()){
                    // If it is in checked state, creates an intent between this MainActivity and MainActivity2
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    intent.putExtra("Name",name);
                    intent.putExtra("Mobile",mobile);
                    intent.putExtra("Location",location);
                    intent.putExtra("Email",email);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Agree and Retry", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // This method is used to create Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This code informs the method which menu.xml file has to be used
        getMenuInflater().inflate(R.menu.main_options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void Welcome(MenuItem item) {
        Toast.makeText(this, "Welcome to the App", Toast.LENGTH_SHORT).show();
    }

    public void Settings(MenuItem item) {
        Toast.makeText(this, "Settings will be opened later!", Toast.LENGTH_SHORT).show();
    }
}