package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

import com.example.loginscreen.network.SessionManager;

public class GeneralTextActivity extends AppCompatActivity {

    AppCompatButton continueBtn;
    Button backBtn,vol,logout;
    AppCompatButton e1,e2,w1,w2,t1,t2;

    public static String text1;
    public static int flag1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_text);
        backBtn = findViewById(R.id.back);
        continueBtn = findViewById(R.id.continue_btn);
        e1=findViewById(R.id.ex1);
        e2=findViewById(R.id.ex2);
        w1=findViewById(R.id.wr1);
        w2=findViewById(R.id.wr2);
        t1=findViewById(R.id.tr1);
        t2=findViewById(R.id.tr2);
        vol=findViewById(R.id.button2);
        logout = findViewById(R.id.logout);
        backBtn.setOnClickListener((v-> exitOut()));
        continueBtn.setOnClickListener((v -> toMain()));

        logout.setOnClickListener((v -> {
            SessionManager.INSTANCE.clearData(this);
            SessionManager.INSTANCE.clearData2(this);

            Intent i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(i);
            overridePendingTransition(0,0);
            finish();
        }));


        e1.setOnClickListener(v -> {
            flag1=1;
            text1=e1.getText().toString();
            Intent intent= new Intent(GeneralTextActivity.this,OpenaiActivity.class);
            GeneralTextActivity.this.startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        e2.setOnClickListener(v -> {
            flag1=1;
            text1=e2.getText().toString();
            Intent intent= new Intent(GeneralTextActivity.this,OpenaiActivity.class);
            GeneralTextActivity.this.startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        t1.setOnClickListener(v -> {
            flag1=1;
            text1=t1.getText().toString();
            Intent intent= new Intent(GeneralTextActivity.this,OpenaiActivity.class);
            GeneralTextActivity.this.startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        t2.setOnClickListener(v -> {
            flag1=1;
            text1=t2.getText().toString();
            Intent intent= new Intent(GeneralTextActivity.this,OpenaiActivity.class);
            GeneralTextActivity.this.startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        w1.setOnClickListener(v -> {
            flag1=1;
            text1=w1.getText().toString();
            Intent intent= new Intent(GeneralTextActivity.this,OpenaiActivity.class);
            GeneralTextActivity.this.startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        w2.setOnClickListener(v -> {
            flag1=1;
            text1=w2.getText().toString();
            Intent intent= new Intent(GeneralTextActivity.this,OpenaiActivity.class);
            GeneralTextActivity.this.startActivity(intent);
            overridePendingTransition(0,0);
            finish();
        });

        vol.setOnClickListener(v -> {
            int nightModeFlags =  v.getContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

            switch (nightModeFlags) {
                case Configuration.UI_MODE_NIGHT_YES:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;

                case Configuration.UI_MODE_NIGHT_NO:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;

                case Configuration.UI_MODE_NIGHT_UNDEFINED:
                    //   doStuff();
                    break;
            }
        });
    }
    private void exitOut() {
        startActivity(new Intent(getApplicationContext(), OptionsActivity.class));
        overridePendingTransition(0,0);
    }

    private void toMain() {
        startActivity(new Intent(getApplicationContext(),OpenaiActivity.class));
        overridePendingTransition(0,0);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), OptionsActivity.class));
        overridePendingTransition(0,0);
    }
}