package com.photoprank.fakecoins;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.jar.Attributes;

/**
 * Created by YouCaf Iqbal on 11/14/2016.
 */

public class NameActivity extends AppCompatActivity {
    Button buttonNext;
    EditText name;
    public static String  Name;
    private InterstitialAd interstitial;
    public ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.name_activity);
        buttonNext = (Button) findViewById(R.id.next);
        name = (EditText) findViewById(R.id.name);

        ////////////////////////////////////////// Showing Add
        pDialog = new ProgressDialog(NameActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial = new InterstitialAd(NameActivity.this);
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
        interstitial.loadAd(adRequest);
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                super.onAdLoaded();
                pDialog.dismiss();
                displayInterstitial();
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
                pDialog.dismiss();
                super.onAdFailedToLoad(errorCode);

            }
        });
        //////////////////////////////////////////
        buttonNext.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                String nameLength = name.getText().toString();
                if (nameLength.length()<1) {
                    Toast.makeText(getApplicationContext(),"Enter Name",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(NameActivity.this,PhoneActivity.class);
                    Name = name.getText().toString();
                    startActivity(intent);
                }
            }
        });


    }

    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
