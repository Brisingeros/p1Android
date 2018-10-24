package com.example.laura.quiz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolderUsers> implements View.OnClickListener {

    ArrayList<UserEntity> usuarios;
    ArrayList<String> nombresUsados = new ArrayList<>();
    Context context;
    private View.OnClickListener listener;

    public UsersAdapter(Context c,ArrayList<UserEntity> usuarios) {

        this.usuarios = usuarios;
        this.context = c;

    }

    @NonNull
    @Override
    public ViewHolderUsers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_1,null,false);
        view.setOnClickListener(this);
        return new ViewHolderUsers(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUsers holder, int position) {

        holder.setUser(this.context,this.usuarios.get(position));
    }

    @Override
    public int getItemCount() {
        return this.usuarios.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {

        if(this.listener != null){
            listener.onClick(v);
        }
    }

    public class ViewHolderUsers extends RecyclerView.ViewHolder {

        TextView name;
        TextView ult_partida;
        TextView num_partidas;
        TextView puntuacion_max;
        ImageView imagen;

        public ViewHolderUsers(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.nombre);
            this.ult_partida = (TextView) itemView.findViewById(R.id.ult_partida);
            this.num_partidas = (TextView) itemView.findViewById(R.id.num_partidas);
            this.puntuacion_max = (TextView) itemView.findViewById(R.id.puntuacion_max);
            this.imagen = (ImageView) itemView.findViewById(R.id.imagen);
        }

        public void setUser(Context c, UserEntity user) {

            this.name.setText(user.getNombre());
            this.ult_partida.setText(this.ult_partida.getText() + user.getUlt_partida());
            this.num_partidas.setText(this.num_partidas.getText() + String.valueOf(user.getNum_partidas()));
            this.puntuacion_max.setText(this.puntuacion_max.getText() + String.valueOf(user.getPunt_max()));

            getImage(c, user.getPath_foto());
        }

        private void getImage(Context c, String path){

            int img = c.getResources().getIdentifier(path, "drawable", c.getPackageName());

            System.out.println("path: " + img);

            if(img == 0){

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                this.imagen.setImageBitmap(BitmapFactory.decodeFile(path, options));

            }else{

                this.imagen.setImageResource(img);
            }

        }
    }
}
