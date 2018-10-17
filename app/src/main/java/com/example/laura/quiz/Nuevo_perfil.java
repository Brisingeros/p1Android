package com.example.laura.quiz;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.File;

public class Nuevo_perfil extends AppCompatActivity {

    EditText nickName;
    ImageButton camara, homer, marge, lisa, bart, maggie;
    Button salir, guardar;
    String imgPath;
    Uri foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.nuevo_perfil);

        this.nickName = (EditText) findViewById(R.id.nickname);
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

                requestPermission();
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(i.resolveActivity(getPackageManager()) != null) {
                   /* foto = createFileURI();
                    i.putExtra(MediaStore.EXTRA_OUTPUT, foto);*/
                    startActivityForResult(i, 0);
                }

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

    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(Nuevo_perfil.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Nuevo_perfil.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
        }
    }
    private Uri createFileURI() {

        File file = new File(Environment.getExternalStorageDirectory(), nickName.getText() + ".jpg");
        Uri uri = Uri.fromFile(file);

        return uri;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);
        String path = foto.getPath();
        System.out.println(path);
    }
}
