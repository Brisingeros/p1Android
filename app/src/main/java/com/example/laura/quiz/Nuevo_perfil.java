package com.example.laura.quiz;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Nuevo_perfil extends AppCompatActivity implements java.io.Serializable{

    EditText nickName;
    ImageButton camara, homer, marge, lisa, bart, maggie;
    Button salir, guardar;
    TextView err_img, err_user;
    ImageView userImage;
    String imgPath = "";
    ArrayList<String> usuarios = new ArrayList<>();
    UserEntity editado = null;

    public static final int REQUEST_PERM_WRITE_STORAGE = 102;
    public static final int CAPTURE_PHOTO = 104;

    private void setImageFromFile(String path){

        int img = getApplicationContext().getResources().getIdentifier(path, "drawable", getApplicationContext().getPackageName());

        System.out.println("path: " + img);

        if(img == 0){

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            this.userImage.setImageBitmap(BitmapFactory.decodeFile(path, options));

        }else{

            this.userImage.setImageResource(img);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.nuevo_perfil);

        Bundle bundle = getIntent().getExtras();
        this.usuarios = bundle.getStringArrayList("usuarios");
        this.editado = (UserEntity) bundle.getSerializable("usuario_editado");

        this.err_img = (TextView) findViewById(R.id.err_img);
        this.err_user = (TextView) findViewById(R.id.err_user);

        this.userImage = (ImageView) findViewById(R.id.imagenUser);

        this.nickName = (EditText) findViewById(R.id.nickname);
        this.camara = (ImageButton) findViewById(R.id.imageCamara);
        this.homer = (ImageButton) findViewById(R.id.imageHomer);
        this.marge = (ImageButton) findViewById(R.id.imageMarge);
        this.maggie = (ImageButton) findViewById(R.id.imageMaggi);
        this.bart = (ImageButton) findViewById(R.id.imageBart);
        this.lisa = (ImageButton) findViewById(R.id.imageLisa);

        this.salir = (Button) findViewById(R.id.salir_nuevo_perfil);
        this.guardar = (Button) findViewById(R.id.guardar_perfil);

        if(editado != null){

            this.nickName.setText(editado.getNombre());
            this.nickName.setEnabled(false);
            this.imgPath = editado.getPath_foto();

            this.setImageFromFile(this.imgPath);

        }

        this.camara.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

                        ActivityCompat.requestPermissions(Nuevo_perfil.this,new String[] {Manifest.permission.CAMERA},1);

                    }
                }

                takePhoto();

            }
        });

        this.guardar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                System.out.println("user: " + nickName.getText());
                err_user.setText("* Campo obligatorio");

                if(editado != null){

                    AsyncTask.execute(new Runnable() {

                        @Override
                        public void run() {

                            DataBase.getDataBase(getApplicationContext()).UserDao().updateUser(imgPath, editado.getId());

                        }

                    });

                    startActivity(new Intent(getApplicationContext(), Users_list.class));
                    finish();

                }else if(!imgPath.equals("") && (!nickName.getText().toString().equals("") && comprobarNombres(nickName.getText().toString()))){

                    AsyncTask.execute(new Runnable() {

                        @Override
                        public void run() {
                            DataBase.getDataBase(getApplicationContext()).UserDao().insert(new UserEntity(nickName.getText().toString(), 0, 0, "", imgPath));

                        }
                    });

                    startActivity(new Intent(getApplicationContext(), Users_list.class));
                    finish();

                }else{

                    System.out.println("Path: " + imgPath);
                    if (imgPath.equals("")) {

                        err_img.setVisibility(View.VISIBLE);

                    } else {
                        err_img.setVisibility(View.INVISIBLE);
                    }

                    if (nickName.getText().toString().equals("")) {

                        err_user.setVisibility(View.VISIBLE);

                    } else {

                        if (!comprobarNombres(nickName.getText().toString())) {

                            err_user.setText("* Este usuario ya existe");
                            err_user.setVisibility(View.VISIBLE);

                        } else {

                            err_user.setVisibility(View.INVISIBLE);

                        }

                    }
                }

            }
        });

        this.salir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Users_list.class)); //iniciamos una nueva partida
                finish();

            }
        });

        this.homer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                imgPath = "homer";
                setBackgroundColor(homer);
                setImageFromFile(imgPath);;
                resetBackground(imgPath);
            }
        });

        this.marge.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                imgPath = "marge";
                setImageFromFile(imgPath);
                setBackgroundColor(marge);
                resetBackground(imgPath);}
        });

        this.bart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                imgPath = "bart";
                setImageFromFile(imgPath);
                setBackgroundColor(bart);
                resetBackground(imgPath);}
        });

        this.lisa.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                imgPath = "lisa";
                setImageFromFile(imgPath);
                setBackgroundColor(lisa);
                resetBackground(imgPath);
            }
        });

        this.maggie.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                imgPath = "maggie";
                setImageFromFile(imgPath);
                //userImage.setImageResource(R.drawable.maggie);
                setBackgroundColor(maggie);
                resetBackground(imgPath);
            }
        });

        requestPermissions();

    }

    private void resetBackground(String name){

        if(!name.equals("homer")){

            homer.setBackgroundColor(0x00000000);

        }

        if(!name.equals("marge")){

            marge.setBackgroundColor(0x00000000);

        }

        if(!name.equals("lisa")){

            lisa.setBackgroundColor(0x00000000);

        }

        if(!name.equals("bart")){

            bart.setBackgroundColor(0x00000000);

        }

        if(!name.equals("maggie")){

            maggie.setBackgroundColor(0x00000000);

        }


    }
    private void setBackgroundColor(ImageButton b){

        b.setBackgroundColor(getColor(R.color.focus));

    }
    private void requestPermissions(){

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(Nuevo_perfil.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERM_WRITE_STORAGE);
        }

    }
    private void takePhoto(){

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAPTURE_PHOTO);

    }
    private boolean comprobarNombres(String name){
        boolean valido = true;
        int iterator = 0;

        if(!this.usuarios.isEmpty()){

            while(iterator < this.usuarios.size() && !this.usuarios.get(iterator).equals(name)){
                iterator++;
            }

            valido = iterator >= this.usuarios.size()?true:false;

        }


        return valido;

    }

    private void saveImage(Bitmap finalBitmap){

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/images");
        myDir.mkdir();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());
        String imageFileName = "PHOTO_" + timeStamp + ".jpg";

        File file = new File(myDir, imageFileName);

        if(file.exists())
            file.delete();

        try{

            FileOutputStream out = new FileOutputStream(file);
            //Bitmap resizedImage = Bitmap.createScaledBitmap(finalBitmap, (finalBitmap.getWidth()/2), (finalBitmap.getHeight()/2), false);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            imgPath = file.getAbsolutePath();

            out.flush();
            out.close();

            this.userImage.setImageBitmap(finalBitmap);

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == CAPTURE_PHOTO && resultCode == RESULT_OK) {

            System.out.println("Llega");
            Bitmap capturedPhoto = (Bitmap) data.getExtras().get("data");

            saveImage(capturedPhoto);


        }

    }


}
