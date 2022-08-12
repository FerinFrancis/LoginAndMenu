package com.ferin.loginandmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThankYouActivity extends AppCompatActivity {

    Button tyExitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        tyExitButton = findViewById(R.id.thanksActExitButton);
        tyExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThankYouActivity.this.finishAffinity();
            }
        });
    }
}