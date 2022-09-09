package com.ferin.loginandmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ferin.loginandmenu.databinding.ActivityMain2Binding;

import java.util.ArrayList;

// Doubt: what is request code in SMS
// SMS permission dialog box displays in MainActivity1 and not in MainActivity2
// Use of message/rfc822

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding actbind2;
    String name, mobile, location, email, dob, tob, qualif;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    AlertDialog.Builder alertDialog;
    SmsManager smsManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actbind2 = DataBindingUtil.setContentView(this, R.layout.activity_main2);



        arrayList = new ArrayList();

        name = getIntent().getStringExtra("Name");
        arrayList.add("Name: "+name);

        mobile = getIntent().getStringExtra("Mobile");
        arrayList.add("Mobile: "+mobile);

        dob = getIntent().getStringExtra("DOB");
        arrayList.add("D.O.B: "+dob);

        tob = getIntent().getStringExtra("TOB");
        arrayList.add("T.O.B: "+tob);

        qualif = getIntent().getStringExtra("Qualif");
        arrayList.add("Occupation: "+qualif);

        location = getIntent().getStringExtra("Location");
        arrayList.add("Location: "+location);

        email = getIntent().getStringExtra("Email");
        arrayList.add("Email: "+email);



        arrayAdapter = new ArrayAdapter(this, R.layout.item_list,arrayList);
        actbind2.listView.setAdapter(arrayAdapter);

        actbind2.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    alertDialog = new AlertDialog.Builder(MainActivity2.this);
                    alertDialog.setMessage("Select an Action");
                    alertDialog.setPositiveButton("Exit App", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finishAffinity();
                        }
                    });
                    alertDialog.setNegativeButton("Send SMS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            // Code to check if permission is granted already

                                ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.SEND_SMS}, 123); // This is the only mandatory line

                        }
                    });
                    alertDialog.setNeutralButton("Email", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent emailIntent = new Intent(Intent.ACTION_SEND);

                            emailIntent.putExtra(Intent.EXTRA_EMAIL,"ferinfrancis1997@gmail.com"); // If single To-Address is there
//                            emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"ferinfrancis1997@gmail.com","testmail@gmail.com"}); // If multiple To-Address is there, storing it in String array
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Test Mail");
                            emailIntent.putExtra(Intent.EXTRA_TEXT,"This is a test mail");

                            // Need this line to display all the apps that helps to send mails
                            // If message/rfc822 is not added, no apps will be shown
                            // If only message/rfc is added, still it works but gmail isn't shown in first in suggestions.
                            // If you want gmail to be shown in front, message/rfc822 has to be entered completely
                            emailIntent.setType("message/rfc822");

                            startActivity(Intent.createChooser(emailIntent,"Choose an email client: "));
                        }
                    });
                    alertDialog.show();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(mobile,null,"Saved",null,null);

                NotificationManager notificationManager = (NotificationManager) MainActivity2.this.getSystemService(Context.NOTIFICATION_SERVICE);

                String channelID = "Channel01";

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(channelID,"SmsConfirmaiton",NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity2.this,channelID)
                        .setSmallIcon(android.R.drawable.ic_media_play).setContentTitle("Sms Confirmation").setContentText("Send has been send from this device");

                notificationManager.notify(01,builder.build());

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void Back(MenuItem item) {
        finish();
    }

    public void About(MenuItem item) {
       Intent tyActivityIntent = new Intent(MainActivity2.this,ThankYouActivity.class);
        startActivity(tyActivityIntent);
    }

    public void Exit(MenuItem item) {
        // This closes the app completely, all activities
        finishAffinity();
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

    // This is the method for button in custom_dialogbox.xml
    public void customDialogGoogleButton(View view) {
        Intent intentNew = new Intent(MainActivity2.this,ThankYouActivity.class);
        startActivity(intentNew);
    }

    public void WebViewMethod(MenuItem item) {
        Intent intentWebView = new Intent(MainActivity2.this,WebViewActivity.class);
        startActivity(intentWebView);
    }
}