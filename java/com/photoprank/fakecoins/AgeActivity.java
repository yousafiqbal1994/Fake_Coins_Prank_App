package com.photoprank.fakecoins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by YouCaf Iqbal on 11/14/2016.
 */

public class AgeActivity extends AppCompatActivity{
    Button buttonYes,buttonNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.age_activity);
        buttonYes = (Button) findViewById(R.id.yes);
        buttonNo = (Button) findViewById(R.id.no);
        buttonYes.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AgeActivity.this,HackActivity.class);
                startActivity(intent);
            }
        });
        buttonNo.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AgeActivity.this,HackActivity.class);
                startActivity(intent);
            }
        });

    }
}

