package com.example.lunaplanner.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lunaplanner.R;

public class ViewHolder_Nota_Importante extends RecyclerView.ViewHolder {
    View mView;
    private ViewHolder_Nota_Importante.ClickListener mClickListener;

    public interface  ClickListener{
        void onItemClick(View view, int position); /*Se ejecuta al precionar en el item*/
        void onItemLongClick(View view, int position); /*Se ejecuta al mantener presionado en el item*/
    }

    public void  setOnClickListener(ViewHolder_Nota_Importante.ClickListener clickListener){
        mClickListener = clickListener;
    }
    public ViewHolder_Nota_Importante(@NonNull View itemView) {
        super(itemView);
        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getBindingAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getBindingAdapterPosition());
                return false;
            }
        });
    }
    public void SetearDatos(Context context, String id_nota, String uid_usuario, String correo_usuario,
                            String fecha_hora_registro, String titulo, String descripcion, String fecha_nota,
                            String estado){

        //Declarar Variables
        TextView Id_nota_Item, Uid_usuario_Item, Correo_usuario_Item, Fecha_hora_registro_Item, Titulo_Item,
                Descripcion_Item, Fecha_Item, Estado_Item;

        ImageView Tarea_Finalizada_Item, Tarea_No_Finalizada_Item;

        //Establecer la conexion con el item
        Id_nota_Item = mView.findViewById(R.id.Id_nota_Item_I);
        Uid_usuario_Item = mView.findViewById(R.id.Uid_Usuario_Item_I);
        Correo_usuario_Item = mView.findViewById(R.id.Correo_usuario_Item_I);
        Fecha_hora_registro_Item = mView.findViewById(R.id.Fecha_hora_registro_Item_I);
        Titulo_Item = mView.findViewById(R.id.Titulo_Item_I);
        Descripcion_Item = mView.findViewById(R.id.Descripcion_Item_I);
        Fecha_Item = mView.findViewById(R.id.Fecha_Item_I);
        Estado_Item = mView.findViewById(R.id.Estado_Item_I);

        Tarea_Finalizada_Item = mView.findViewById(R.id.Tarea_Finalizada_Item_I);
        Tarea_No_Finalizada_Item = mView.findViewById(R.id.Tarea_No_Finalizada_Item_I);

        //Setear la informacion dentro del item
        Id_nota_Item.setText(id_nota);
        Uid_usuario_Item.setText(uid_usuario);
        Correo_usuario_Item.setText(correo_usuario);
        Fecha_hora_registro_Item.setText(fecha_hora_registro);
        Titulo_Item.setText(titulo);
        Descripcion_Item.setText(descripcion);
        Fecha_Item.setText(fecha_nota);
        Estado_Item.setText(estado);

        //Gestionarmos el color del estado
        if (estado.equals("Finalizado")){
            Tarea_Finalizada_Item.setVisibility(View.VISIBLE);
        }else {
            Tarea_No_Finalizada_Item.setVisibility(View.VISIBLE);
        }

    }
}
