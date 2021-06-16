package com.example.aplikasikuisterbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class HasilKuis extends AppCompatActivity {

    TextView hsl, nilai, krsi;
    private String title = "Hasil Akhir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_kuis);

        setActionBarTitle(title);

        //        animasi ketika tombol di tekan
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_menghilang);


        hsl = (TextView)findViewById(R.id.hasil);
        krsi = (TextView)findViewById(R.id.koreksi);
        Button kmbli = (Button)findViewById(R.id.kembali);
        nilai = (TextView)findViewById(R.id.nilai);

        setSkor();

//        hasil.setText("Jawaban Benar :"+Pilgan.benar+"\nJawaban Salah :"+Pilgan.salah);
//        nilai.setText(""+Pilgan.hasil);

        kmbli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(anim);
                Intent k = new Intent(HasilKuis.this, MainActivity.class);
                startActivity(k);
            }
        });


    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void setSkor() {
        String activity = getIntent().getStringExtra("activity");
        String essay = getIntent().getStringExtra("skorAkhir2");

        if (activity.equals("Pilgan")) {
            hsl.setText("Jawaban Benar :"+Pilgan.benar+"\nJawaban Salah :"+Pilgan.salah);
            nilai.setText(""+Pilgan.hasil);

            if (Pilgan.hasil <= 50 ){
                krsi.setText("Nilai kamu masih kurang, Silahkan bisa mencoba lagi");
            }else if (Pilgan.hasil <= 80 ){
                krsi.setText("HORE, Kamu mendapat nilai cukup bagus");
            }else{
                krsi.setText("HEBAT, Kamu mendapat nilai sempurna");
            }
        }else{
            hsl.setText("Jawaban Benar :"+Essay.benar+"\nJawaban Salah :"+Essay.salah);
            nilai.setText(""+essay);

            if (Essay.hasil <= 50 ){
                krsi.setText("Nilai kamu masih kurang, Silahkan bisa mencoba lagi");
            }else if (Essay.hasil <= 80 ){
                krsi.setText("HORE, Kamu mendapat nilai cukup bagus");
            }else{
                krsi.setText("HEBAT, Kamu mendapat nilai sempurna");
            }
        }
    }
}