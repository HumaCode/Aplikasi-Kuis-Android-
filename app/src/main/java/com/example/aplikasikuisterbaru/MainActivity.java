package com.example.aplikasikuisterbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout pilgan, essay;
    private String title = "Menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pilgan = (LinearLayout) findViewById(R.id.pilgan);
        essay = (LinearLayout) findViewById(R.id.essay);

//        title actionBar
        setActionBarTitle(title);

//        animasi ketika tombol di tekan
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_menghilang);


        pilgan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(anim);
                Intent p = new Intent(MainActivity.this, Pilgan.class);
                startActivity(p);
            }
        });

        essay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(anim);
                Intent e = new Intent(MainActivity.this, Essay.class);
                startActivity(e);
            }
        });
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}