package com.example.aplikasikuisterbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Essay extends AppCompatActivity {
    TextView skr, soal2;
    ImageView gambar;
    EditText jwb;
    Button next;
    int arr;
    int x = 0;
    String jawaban;
    public static int hasil, benar, salah;
    private String title = "Essay";

    //pertanyaan
    String[] pertanyaan_kuis = new String[]{
            "Siapa nama presiden Indonesia yang pertama ?",
            "Ideologi dasar bagi negara Indonesia adalah ...",
            "Bhinneka Tunggal Ika mempunyai arti ...",
            "Ibukota negara Indonesia saat ini adalah ...",
            "Siapa yang menjajah Indonesia selama 350 tahun ?",
            "Saat masa penjajahan, senjata yang biasa digunakan oleh pahlawan Indonesia adalah ...",
            "Monumen untuk mengenang perlawanan dan perjuanagan rakyat Indonesia untuk merebut kemerdekaan dari pemerintahan kolonial Hindia Belanda adalah ...",
            "Teks yang dibacakan Ir. Soekarno yang menyatakan Indonesia merdeka dalah teks ...",
            "Pulau terbesar di Indonesia adalah ...",
            "Ibu Kota Jawa Tengah Adalah.."
    };

    String[] image = new String[] {
            "foto_soekarno",
            "lambang_garuda",
            "lambang_garuda",
            "jakarta",
            "penjajahan",
            "bambu_runcing",
            "monas",
            "teks_proklamasi",
            "pulau_kalimantan",
            "semarang"
    };

    //jawaban benar
    String[] jawaban_benar = new String[]{
            "Ir Soekarno",
            "Pancasila",
            "Berbeda-beda tetapi tetap satu",
            "Jakarta",
            "Belanda",
            "Bambu runcing",
            "Monas",
            "Proklamasi",
            "Kalimantan",
            "Semarang"
    };

    public String getPertanyaan(int x) {
        String soal = pertanyaan_kuis[x];
        return soal;
    }

    public String getStringGambar(int x) {
        String gambar = image[x];
        return gambar;
    }

    public String getJawabanBenar(int x) {
        String jawaban = jawaban_benar[x];
        return jawaban;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay);

        setActionBarTitle(title);

        //        animasi ketika tombol di tekan
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_menghilang);

        skr= (TextView) findViewById(R.id.skor);
        soal2= (TextView) findViewById(R.id.soal2);
        gambar= (ImageView) findViewById(R.id.gambar);
        jwb= (EditText) findViewById(R.id.jawaban);
        next = (Button) findViewById(R.id.next);

        setKontent();
        
        benar = 0;
        salah = 0;
        hasil = 0;

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(anim);
                cekJawaban();
            }
        });
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void cekJawaban() {
        if (!jwb.getText().toString().isEmpty()) {
            if (jwb.getText().toString().equalsIgnoreCase(jawaban)) {
                hasil = hasil+10;
                skr.setText(""+hasil);
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
                setKontent();
                benar++;
            }else {
                skr.setText(""+hasil);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKontent();
                salah++;
            }
        }else{
            Toast.makeText(this, "Isikan Jawaban Anda.!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setKontent() {
        jwb.setText(null);
        arr = pertanyaan_kuis.length;
        if (x >= arr) {
            String jumlahSkor = String.valueOf(hasil);
            Intent i = new Intent(Essay.this, HasilKuis.class);
            i.putExtra("skorAkhir2",jumlahSkor);
            i.putExtra("activity","Essay");
            startActivity(i);
        }else{
            soal2.setText(getPertanyaan(x));
            ubahGambar();
            jawaban = getJawabanBenar(x);
        }x++;
    }

    private void ubahGambar() {
        Resources res = getResources();
        String mPhoto = getStringGambar(x);
        int resID = res.getIdentifier(mPhoto, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID);
        gambar.setImageDrawable(drawable);
    }

    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan kuis terlebih dahulu.!", Toast.LENGTH_SHORT).show();
    }
}