package com.ferin.loginandmenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
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


        // When this button is clicked, the data from editText is shown as a toast
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

                    // If it is in checked state, proceeds to checks if any field is empty or not
                    if (TextUtils.isEmpty(name)||TextUtils.isEmpty(mobile)||TextUtils.isEmpty(location)||TextUtils.isEmpty(email))
                    {
                        // If any one field is empty, this toast message is shown
                        Toast.makeText(MainActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        // If all field has data, these steps gets executed and toast is shown
                    data = "Name: "+name+" \nMobile: "+mobile+" \nLocation: "+location+" \nEmail: "+email;
                    Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    // If checkbox is not checked, this toast is shown
                    Toast.makeText(MainActivity.this, "Please Agree and Retry", Toast.LENGTH_SHORT).show();
                }

            }
        });


        // When this button is clicked, the data from editText is shown in a AlertDialog Box
        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameEdit.getText().toString();
                mobile = mobileEdit.getText().toString();
                location = locationEdit.getText().toString();
                email = emailEdit.getText().toString();
                if(agreeCheck.isChecked()) {

                    // If it is in checked state, proceeds to checks if any field is empty or not
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(location) || TextUtils.isEmpty(email))
                    {
                        // If any one field is empty, this toast message is shown
                        Toast.makeText(MainActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        // Create a Object for "AlertDialog.Builder" class #First Step for creating AlertDialog
                        AlertDialog.Builder data = new AlertDialog.Builder(MainActivity.this); // Context means currentclass.this
                        // set the title which has to be displayed in alert dialog box
                        data.setTitle("Title Here");  // Not mandatory
                        // set the message which has to be displayed
                        data.setMessage("Message is shown here:\nName: "+name+" \nMobile: "+mobile+" \nLocation: "+location+" \nEmail: "+email);
                        // Set the icon which has to be shown next to Title
                        data.setIcon(android.R.drawable.star_big_on);  // By default icon is set next to Title
                        data.setPositiveButton("Positive",null); // set text to right side of dialog box
                        data.setNegativeButton("Negative",null); // set text to center of dialog box
                        data.setNeutralButton("Neutral",null); // set text to left side of dialog box
                        // This triggers the alertDialog to be displayed, very important
                        data.show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Agree and Retry", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // When this button is clicked, it shows a AlertDialog and upon clicking "Yes" it proceeds to next activity
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
                    // If it is in checked state, proceeds to checks if any field is empty or not
                    if(TextUtils.isEmpty(name)||TextUtils.isEmpty(mobile)||TextUtils.isEmpty(location)||TextUtils.isEmpty(email))
                    {
                        Toast.makeText(MainActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        // Creates an AlertDialog
                        AlertDialog.Builder goToDialog = new AlertDialog.Builder(MainActivity.this);
                        goToDialog.setTitle("Next Activity");
                        goToDialog.setMessage("Do you want to proceed to next activity");
                        goToDialog.setPositiveButton("No",null);
                        // When Yes button is clicked, it proceeds to next activity using Intent
                        goToDialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                              // creates an intent between this MainActivity and MainActivity2
                                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                                intent.putExtra("Name",name);
                                intent.putExtra("Mobile",mobile);
                                intent.putExtra("Location",location);
                                intent.putExtra("Email",email);
                                startActivity(intent);
                            }
                        }).show();
                    }
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

    // To create a customDialog
    public void customDialog(View view) {
        Dialog dia = new Dialog(MainActivity.this); // Dialog is the predefined classname for customDialog
        dia.setContentView(R.layout.activity_thank_you); // This displays activity_thank_you.xml in the customDialogBox
        dia.show();
    }
}