package com.example.laura.quiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolderUsers> {

    ArrayList<User> usuarios;
    Context context;

    public UsersAdapter(Context c,ArrayList<User> usuarios) {

        this.usuarios = usuarios;
        this.context = c;

    }

    @NonNull
    @Override
    public ViewHolderUsers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_1,null,false);
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

        public void setUser(Context c, User user) {

            this.name.setText(user.getNombre());
            this.ult_partida.setText(this.ult_partida.getText() + user.getUlt_partida());
            this.num_partidas.setText(this.num_partidas.getText() + user.getNum_partidas());
            this.puntuacion_max.setText(this.puntuacion_max.getText() + user.getPuntosMax());

            int img = c.getResources().getIdentifier(user.getImgPath(), "drawable", c.getPackageName());//NO DEBERIA COGERLAS DE AQUI, TIENE QUE COGERLAS DE LA
            //CARPETA DONDE SE GUARDEN LAS IMAGENES AL SELECCIONARLAS
            this.imagen.setImageResource(img);

        }
    }
}
