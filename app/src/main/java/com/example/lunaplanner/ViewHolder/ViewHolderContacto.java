package com.example.lunaplanner.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lunaplanner.R;

public class ViewHolderContacto extends RecyclerView.ViewHolder {
    View mView;
    private ViewHolderContacto.ClickListener mClickListener;

    public interface  ClickListener{
        void onItemClick(View view, int position); /*Se ejecuta al precionar en el item*/
        void onItemLongClick(View view, int position); /*Se ejecuta al mantener presionado en el item*/
    }

    public void  setOnClickListener(ViewHolderContacto.ClickListener clickListener){
        mClickListener = clickListener;
    }
    public ViewHolderContacto(@NonNull View itemView) {
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
    public void SetearDatosContacto(Context context,
                                    String id_contacto,
                                    String uid_contacto,
                                    String nombres,
                                    String apellidos,
                                    String correo,
                                    String telefono,
                                    String edad,
                                    String direccion,
                                    String imagen){
        ImageView Imagen_c_Item;
        TextView Id_c_Item, Uid_c_Item, nombres_c_Item, apellidos_c_Item, correo_c_Item, telefono_c_Item, edad_c_Item, direccion_c_Item;

        Imagen_c_Item = mView.findViewById(R.id.Imagen_c_Item);
        Id_c_Item = mView.findViewById(R.id.Id_c_Item);
        Uid_c_Item = mView.findViewById(R.id.Uid_c_Item);
        nombres_c_Item = mView.findViewById(R.id.nombres_c_Item);
        apellidos_c_Item = mView.findViewById(R.id.apellidos_c_Item);
        correo_c_Item = mView.findViewById(R.id.correo_c_Item);
        telefono_c_Item = mView.findViewById(R.id.telefono_c_Item);
        edad_c_Item = mView.findViewById(R.id.edad_c_Item);
        direccion_c_Item = mView.findViewById(R.id.direccion_c_Item);


    }
}
