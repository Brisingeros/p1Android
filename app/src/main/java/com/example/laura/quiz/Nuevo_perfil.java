package com.example.laura.quiz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Nuevo_perfil extends AppCompatActivity {

    TextInputEditText nickName;
    ImageButton camara, homer, marge, lisa, bart, maggie;
    Button salir, guardar;
    String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.nuevo_perfil);

        this.nickName = (TextInputEditText) findViewById(R.id.nickname);
        this.camara = (ImageButton) findViewById(R.id.imageCamara);
        this.homer = (ImageButton) findViewById(R.id.imageHomer);
        this.marge = (ImageButton) findViewById(R.id.imageMarge);
        this.maggie = (ImageButton) findViewById(R.id.imageMaggi);
        this.bart = (ImageButton) findViewById(R.id.imageBart);
        this.lisa = (ImageButton) findViewById(R.id.imageLisa);

        this.salir = (Button) findViewById(R.id.salir_nuevo_perfil);
        this.guardar = (Button) findViewById(R.id.guardar_perfil);

        this.camara.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,0);

            }
        });

        this.homer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            imgPath = "homer";

            }
        });

        this.marge.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            imgPath = "marge";

            }
        });

        this.bart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            imgPath = "bart";

            }
        });

        this.lisa.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            imgPath = "lisa";

            }
        });

        this.maggie.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            imgPath = "maggie";

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);
        Bitmap path = (Bitmap) data.getExtras().get("data");

        //GUARDAR IMAGEN EN MEMORIA

    }
}
