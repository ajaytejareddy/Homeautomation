package com.example.homeautomation;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.Animation;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);


        ImageView iv = findViewById(R.id.img);
        TextView tv = findViewById(R.id.text);

        Animation anim_fin = AnimationUtils.loadAnimation(this,R.anim.fadein);
        Animation anim_fin1 = AnimationUtils.loadAnimation(this,R.anim.fadein1);


        iv.startAnimation(anim_fin);

        tv.startAnimation(anim_fin1);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference board= database.getReference("Boards").child("Zx-zsdgs=asg");


        int TIMER = 4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeintent = new Intent(Splash.this , MainActivity.class);
                startActivity(homeintent);
                finish();
            }
        }, TIMER);
    }
}
