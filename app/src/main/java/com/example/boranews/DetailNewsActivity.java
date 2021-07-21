package com.example.boranews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailNewsActivity extends AppCompatActivity {

    String judul, tanggal, author, sumber, foto, deskripsi, url;
    String formatedURL = "sumber: ";
    TextView d_judul;
    TextView d_tanggal;
    TextView d_author;
    TextView d_sumber;
    TextView d_deskripsi;
    TextView d_url;
    ImageView d_foto;
    Button webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_detail_berita);

        d_judul = findViewById(R.id.detail_judul);
        d_tanggal = findViewById(R.id.detail_tanggal);
        d_author = findViewById(R.id.detail_author);
        d_sumber = findViewById(R.id.detail_sumber);
        d_deskripsi = findViewById(R.id.detail_isi);
        d_url = findViewById(R.id.detail_url);
        d_foto = findViewById(R.id.detail_foto);
        webView = findViewById(R.id.detail_button);


        getData();
        setData();


        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailNewsActivity.this, WebViewNewsActivity.class).putExtra("url", url));
            }
        });
    }

    public void getData(){

        Bundle bundle = getIntent().getExtras();
        if(getIntent().hasExtra("judul")
                && getIntent().hasExtra("tanggal")
                && getIntent().hasExtra("foto")
                && getIntent().hasExtra("sumber")
                && getIntent().hasExtra("author")
                && getIntent().hasExtra("deskripsi")
                && getIntent().hasExtra("url"))
        {
            judul = bundle.getString("judul");
            tanggal = bundle.getString("tanggal");
            foto = bundle.getString("foto");
            sumber = bundle.getString("sumber");
            author = bundle.getString("author");
            deskripsi = bundle.getString("deskripsi");
            url = bundle.getString("url");

        }
    }

    public void setData(){
        d_judul.setText(judul);
        d_tanggal.setText(tanggal);
        d_author.setText(author);
        d_sumber.setText(sumber);
        d_deskripsi.setText(deskripsi);
        d_url.setText(formatedURL+url);
        Picasso.get().load(foto).fit().centerCrop().into(d_foto);
    }
}
