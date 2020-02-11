package com.example.acorutas.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import com.example.acorutas.R;

public class FormLogin_Fragment extends Fragment {

    Button btn_Registro;
    //Button btn_regresar;
    EditText et_nickame, et_correo, et_pass1, et_pass2;


    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.formulario_login, container, false);

        et_nickame = (EditText) v.findViewById(R.id.ET_nombreUsuario);
        et_correo = (EditText) v.findViewById(R.id.ET_correo);
        et_pass1 = (EditText) v.findViewById(R.id.ET_contraseña1);
        et_pass2 = (EditText) v.findViewById(R.id.ET_contraseña2);
        btn_Registro = (Button) v.findViewById(R.id.BTN_Registrar);
        //btn_regresar = (Button) v.findViewById(R.id.BTN_Regresar);

        btn_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Se presiono el boton", Toast.LENGTH_LONG).show();
            }
        });

        /*btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        return v;

    }

    /* codigo para enlazar un fragment
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.login, new FormLogin_Fragment() ).commit();*/

}
