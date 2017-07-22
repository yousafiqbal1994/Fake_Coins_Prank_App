package com.photoprank.fakecoins;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by YouCaf Iqbal on 11/14/2016.
 */

public class HackActivity extends AppCompatActivity {
    Button buttonNormal,buttonAdvance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.hack_activity);
        buttonNormal = (Button) findViewById(R.id.normal);
        buttonAdvance = (Button) findViewById(R.id.advance);


        buttonNormal.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HackActivity.this,NameActivity.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"Normal",Toast.LENGTH_SHORT).show();
            }
        });
        buttonAdvance.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Advance",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HackActivity.this,NameActivity.class);
                startActivity(intent);
            }
        });

    }

}
