package com.example.acorutas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acorutas.R;

public class formLoginActivity extends AppCompatActivity {

    Button btn_Registro;
    EditText et_nickame, et_correo, et_pass1, et_pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_login);

        et_nickame = (EditText) findViewById(R.id.ET_nombreUsuario);
        et_correo = (EditText) findViewById(R.id.ET_correo);
        et_pass1 = (EditText) findViewById(R.id.ET_contraseña1);
        et_pass2 = (EditText) findViewById(R.id.ET_contraseña2);
        btn_Registro = (Button) findViewById(R.id.BTN_Registrar);

        btn_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Se presiono el boton", Toast.LENGTH_LONG).show();
                validarInfo();
            }
        });

    }

    public void validarInfo(){

        String nickname, correo, pass1, pass2;

        nickname = et_nickame.getText().toString();
        correo = et_correo.getText().toString();
        pass1 = et_pass1.getText().toString();
        pass2 = et_pass2.getText().toString();

        if(!nickname.isEmpty() && !correo.isEmpty() && !pass1.isEmpty() && !pass2.isEmpty()){
            if(pass1.equals(pass2)){
                Toast.makeText(getApplicationContext(), "Datos Correctos", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "Las contraseñas no son iguales. Intenta de nuevo", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}
