package com.example.aplikasikuisterbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Pilgan extends AppCompatActivity {

    TextView kuis, nilai;
    RadioGroup rg;
    RadioButton PilihanA, PilihanB, PilihanC, PilihanD;
    int nomor = 0;
    public static int hasil, benar, salah;
    private String title = "Pilihan Ganda";


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

    //pilihan jawaban a, b, c, d
    String[] pilihan_jawaban = new String[]{
            "Ir. Soekarno","Joko Widodo","Susilo Bambang Yudhoyono","Gusdur",
            "UUD 1945","Pancasila","Bhinneka Tunggal Ika","Piagam Jakarta",
            "Berbeda-beda tetapi tetap satu","Bersama selamanya","Bersatu teguh bercerai runtuh","Setia dan teguh hati",
            "Semarang","Surabaya","Jakarta","Bandung",
            "Jepang","Belanda","Malaysia","Afrika",
            "Bambu runcing","Ketapel","Shotgun","AK-47",
            "Tugu Muda","Patung Pancoran","Monas","Menara Pisa",
            "Proklamasi","Pancasila","Pembukaan UUD 1945","Janji pandu HW",
            "Jawa","Sumatera","Kalimantan","Bali",
            "Semarang","Bandung","Ambon","Kalimantan"
    };

    //jawaban benar
    String[] jawaban_benar = new String[]{
            "Ir. Soekarno",
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilgan);

        setActionBarTitle(title);



        kuis = (TextView) findViewById(R.id.kuis);
        nilai = (TextView) findViewById(R.id.skor);
        rg = (RadioGroup) findViewById(R.id.pilihan);
        PilihanA = (RadioButton) findViewById(R.id.pilihanA);
        PilihanB = (RadioButton) findViewById(R.id.pilihanB);
        PilihanC = (RadioButton) findViewById(R.id.pilihanC);
        PilihanD = (RadioButton) findViewById(R.id.pilihanD);

        kuis.setText(pertanyaan_kuis[nomor]);
        PilihanA.setText(pilihan_jawaban[0]);
        PilihanB.setText(pilihan_jawaban[1]);
        PilihanC.setText(pilihan_jawaban[2]);
        PilihanD.setText(pilihan_jawaban[3]);

        rg.check(0);
        benar = 0;
        salah = 0;

    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    public void next(View view) {

        //        animasi ketika tombol di tekan
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_menghilang);
        view.startAnimation(anim);

        if (PilihanA.isChecked() || PilihanB.isChecked() || PilihanC.isChecked() || PilihanD.isChecked()) {

            RadioButton jawaban_user = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
            String ambil_jawaban_user = jawaban_user.getText().toString();
            rg.check(0);
            if (ambil_jawaban_user.equalsIgnoreCase(jawaban_benar[nomor])) {
                benar++;
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show();

            }else {
                salah++;
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
            }
            nomor++;
            if (nomor < pertanyaan_kuis.length) {
                kuis.setText(pertanyaan_kuis[nomor]);
                PilihanA.setText(pilihan_jawaban[(nomor * 4) + 0]);
                PilihanB.setText(pilihan_jawaban[(nomor * 4) + 1]);
                PilihanC.setText(pilihan_jawaban[(nomor * 4) + 2]);
                PilihanD.setText(pilihan_jawaban[(nomor * 4) + 3]);
                hasil = benar*10;
                nilai.setText(""+hasil);


            } else {
                hasil = benar * 10;
                nilai.setText(""+hasil);
                Intent selesai = new Intent(getApplicationContext(), HasilKuis.class);
                selesai.putExtra("activity", "Pilgan");
                selesai.putExtra("skorAkhir", hasil);
                startActivity(selesai);

            }
        }
        else {
            Toast.makeText(this,"Jawaban Tidak Boleh Kosong.!",Toast.LENGTH_LONG).show();
        }
    }

    public void onBackPressed() {
        Toast.makeText(this, "Selesaikan kuis terlebih dahulu.!", Toast.LENGTH_SHORT).show();
    }
}