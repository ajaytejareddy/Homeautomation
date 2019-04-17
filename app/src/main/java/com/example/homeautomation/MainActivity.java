package com.example.homeautomation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    RadioButton b1,b2,b3,b4;
    ToggleButton li1,li2,fn,all;
    SwitchCompat sw;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference board= database.getReference("Boards").child("Zx-zsdgs=asg");
    DatabaseReference pin1 = board.child("pin1");
    DatabaseReference pin2 = board.child("pin2");
    DatabaseReference pin3 = board.child("pin3");
    DatabaseReference auto = board.child("Auto");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        li1 = findViewById(R.id.l1);
        li2 = findViewById(R.id.l2);
        fn = findViewById(R.id.f1);
        all = findViewById(R.id.allon);
        sw=findViewById(R.id.switch1);


        b1 =  findViewById(R.id.rb1);
        b2 =  findViewById(R.id.rb2);
        b3 = findViewById(R.id.rb3);
        b4 = findViewById(R.id.rb4);


        ReadfBase();

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    auto.setValue(1);

                } else {

                    auto.setValue(0);

                }
            }
        });


        li1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    pin1.setValue(1);
                    b1.setChecked(true);
                } else {
                    pin1.setValue(0);
                    b1.setChecked(false);

                }
            }
        });


        li2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    pin2.setValue(1);
                    b2.setChecked(true);
                }
                else {
                    pin2.setValue(0);
                    b2.setChecked(false);
                }
            }
        });


        fn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    pin3.setValue(1);
                    b3.setChecked(true);
                }
                else {
                    pin3.setValue(0);
                    b3.setChecked(false);
                }
            }
        });
        all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    pin1.setValue(1);
                    pin2.setValue(1);
                    pin3.setValue(1);
                    b1.setChecked(true);
                    b2.setChecked(true);
                    b3.setChecked(true);
                    b4.setChecked(true);

                } else {
                    pin1.setValue(0);
                    pin2.setValue(0);
                    pin3.setValue(0);
                    b1.setChecked(false);
                    b2.setChecked(false);
                    b3.setChecked(false);
                    b4.setChecked(false);
                    li1.setEnabled(true);
                    li2.setEnabled(true);
                    fn.setEnabled(true);

                }
            }
        });

    }

    private void ReadfBase() {

        final Integer[] p1 = new Integer[1];
        final Integer[] p2=new Integer[1];
        final Integer[] p3=new Integer[1];
        pin1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                p1[0] = dataSnapshot.getValue(Integer.class);

                if (p1[0] ==1){
                    b1.setChecked(true);
                    li1.setChecked(true);
                }
                else {
                    b1.setChecked(false);
                    li1.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        pin2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                p2[0] = dataSnapshot.getValue(Integer.class);

                if (p2[0] ==1){
                    b2.setChecked(true);
                    li2.setChecked(true);
                }
                else {
                    b2.setChecked(false);
                    li2.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        pin3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                p3[0] = dataSnapshot.getValue(Integer.class);

                if (p3[0] ==1){
                    b3.setChecked(true);
                    fn.setChecked(true);
                }
                else {
                    b3.setChecked(false);
                    fn.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    if (b1.isChecked()&&b2.isChecked()&&b3.isChecked())
    {
       b4.setChecked(true);
       all.setChecked(true);
    }



    }

}