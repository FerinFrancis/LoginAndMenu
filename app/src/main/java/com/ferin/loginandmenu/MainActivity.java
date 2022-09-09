package com.ferin.loginandmenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import com.ferin.loginandmenu.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding actBind;

    EditText nameEdit, mobileEdit,emailEdit,locationEdit;
    CheckBox agreeCheck;
    String name, mobile,location,email,data;
    DatePickerDialog dobPicker;
    TimePickerDialog tobPicker;
    TextView dobTextView, tobTextView;
    String dob,tob;
    MediaPlayer mediaPlayer;
    String spinnerData;

    ArrayList spinnerArrayList;
    ArrayAdapter spinnerArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actBind = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        nameEdit = findViewById(R.id.nameEnterEdit);
        mobileEdit = findViewById(R.id.mobileEnterEdit);
        locationEdit = findViewById(R.id.locationEnterEdit);
        emailEdit = findViewById(R.id.emailEnterEdit);
        dobTextView = findViewById(R.id.dobSelectTextView);
        tobTextView = findViewById(R.id.tobSelectTextView);


        agreeCheck = findViewById(R.id.agreeCheckBox);

        Spinner();


        // When this button is clicked, the data from editText is shown as a toast
        actBind.toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When button is clicked, it stores the data from all editTexts to respective variable
                name = nameEdit.getText().toString();
                mobile = mobileEdit.getText().toString();
                location = locationEdit.getText().toString();
                email = emailEdit.getText().toString();
                dob = dobTextView.getText().toString();
                tob = tobTextView.getText().toString();
                spinnerData = actBind.spinner.getSelectedItem().toString();

                // And then it verifies if checkbox is in checked state
                if(agreeCheck.isChecked()){

                    // If it is in checked state, proceeds to checks if any field is empty or not
                    if (TextUtils.isEmpty(name)||TextUtils.isEmpty(mobile)||TextUtils.isEmpty(location)||TextUtils.isEmpty(email)||TextUtils.isEmpty(dob)||TextUtils.isEmpty(tob))
                    {
                        // If any one field is empty, this toast message is shown
                        Toast.makeText(MainActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        // If all field has data, these steps gets executed and toast is shown
//                    data = "Name: "+name+" \nMobile: "+mobile+" \nLocation: "+location+" \nEmail: "+email;
                        data = "Name: "+name+"\nMobile: "+mobile+"\nLocation: "+location+"\nEmail: "+email+"\nQualification: "+spinnerData+"\nD.O.B: "+dob+"\nT.O.B: "+tob;
                    Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    // If checkbox is not checked, this toast is shown
                    Toast.makeText(MainActivity.this, "Please Agree and Retry", Toast.LENGTH_SHORT).show();
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.sorryaudiofile);
                    mediaPlayer.start();
                }
            }
        });


        // When this button is clicked, the data from editText is shown in a AlertDialog Box
        actBind.textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameEdit.getText().toString();
                mobile = mobileEdit.getText().toString();
                location = locationEdit.getText().toString();
                email = emailEdit.getText().toString();
                spinnerData = actBind.spinner.getSelectedItem().toString();


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
//                        data.setMessage("Message is shown here:\nName: "+name+" \nMobile: "+mobile+" \nLocation: "+location+" \nEmail: "+email);
                        data.setMessage("Message is shown here:\nName: "+name+"\nMobile: "+mobile+"\nLocation: "+location+"\nEmail: "+email+"\nQualification: "+spinnerData+"\nD.O.B: "+dob+"\nT.O.B: "+tob);
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
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.sorryaudiofile);
                    mediaPlayer.start();
                }
            }
        });


        // When this button is clicked, it shows a AlertDialog and upon clicking "Yes" it proceeds to next activity
        actBind.goToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // When button is clicked, it stores the data from all editTexts to respective variable
                name = nameEdit.getText().toString();
                mobile = mobileEdit.getText().toString();
                location = locationEdit.getText().toString();
                email = emailEdit.getText().toString();
                dob = dobTextView.getText().toString();
                tob = tobTextView.getText().toString();
                spinnerData = actBind.spinner.getSelectedItem().toString();

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
                                intent.putExtra("DOB",dob);
                                intent.putExtra("TOB",tob);
                                intent.putExtra("Qualif",spinnerData);
                                startActivity(intent);
                            }
                        }).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Agree and Retry", Toast.LENGTH_SHORT).show();
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.sorryaudiofile);
                    mediaPlayer.start();
                }
            }
        });

        actBind.wifiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
                    Intent intent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Wifi enabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        actBind.bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intent);
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


        dia.setContentView(R.layout.video_layout); // This displays xml layout in the customDialogBox
        VideoView video = dia.findViewById(R.id.videoView);
        Uri uri = Uri.parse("https://www.ebookfrenzy.com/android_book/movie.mp4");
  //      Uri uri = Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"); // Not Working
        video.setVideoURI(uri);
        video.start();

//        USE THIS IF dia.setContentView(R.layout.activity_thank_you) is used
//        dia.findViewById(R.id.thanksActExitButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Working", Toast.LENGTH_SHORT).show();
//            }
//        });
                dia.show();
    }

    public void customDialogTwo(View view) {
        Dialog dia2 = new Dialog(MainActivity.this);
        dia2.setContentView(R.layout.video_button_layout);
        VideoView video2 = dia2.findViewById(R.id.videoView2);
        dia2.findViewById(R.id.videoView2Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.ebookfrenzy.com/android_book/movie.mp4");
                video2.setVideoURI(uri);
                video2.start();
            }
        });
        dia2.show();
    }


    public void SelectDOB(View view) {

        // To set the current date to be set in the datePickerDialogBox
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        // This creates a datePickerDialogBox
        dobPicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dobTextView.setText(i2+"/"+(i1+1)+"/"+i);
            }
        }, mYear, mMonth, mDay);
        dobPicker.show();
    }

    public void SelectTOB(View view) {

        // To set the current time to be set in the timePickerDialogBox
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);

        // This creates a timePickerDialogBox
        tobPicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                tobTextView.setText(i+":"+i1);
            }
        }, hour, minute, true);
        tobPicker.show();
    }

    public void CustomGoogleDialog(MenuItem item) {
        Dialog dia = new Dialog(MainActivity.this);
        dia.setContentView(R.layout.custom_dialogbox);
        dia.show();
    }

     void Spinner(){
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Select an Option");
        spinnerArrayList.add("B.E/B.Tech");
        spinnerArrayList.add("B.Sc");
        spinnerArrayList.add("B.Com");
        spinnerArrayList.add("Diploma");

        spinnerArrayAdapter = new ArrayAdapter(this,R.layout.item_list,spinnerArrayList);
        actBind.spinner.setAdapter(spinnerArrayAdapter);
    }

}