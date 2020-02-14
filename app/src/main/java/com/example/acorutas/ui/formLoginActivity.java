package com.example.acorutas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acorutas.Data.models.RegistroUser;
import com.example.acorutas.Data.remote.ApiUtils;
import com.example.acorutas.Data.remote.DjangoRestApi;
import com.example.acorutas.MainActivity;
import com.example.acorutas.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class formLoginActivity extends AppCompatActivity {

    int opcion = 0;

    Button btn_Registro;
    EditText et_nickame, et_correo, et_pass1, et_pass2;

    private DjangoRestApi djangoRestApi;

    public SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_login);

        et_nickame = (EditText) findViewById(R.id.ET_nombreUsuario);
        et_correo = (EditText) findViewById(R.id.ET_correo);
        et_pass1 = (EditText) findViewById(R.id.ET_contraseña1);
        et_pass2 = (EditText) findViewById(R.id.ET_contraseña2);
        btn_Registro = (Button) findViewById(R.id.BTN_Registrar);

        djangoRestApi = ApiUtils.getAPIService();

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

                createUser(nickname, pass1, correo);

                prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nombre", nickname);
                editor.commit();

                new CountDownTimer(1000, 1000) {
                    public void onFinish() {

                        opcion = prefs.getInt("opcion", 0);
                        if(opcion == 1){

                            finishAffinity();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        }

                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();

                //Toast.makeText(getApplicationContext(), "Datos Correctos", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getApplicationContext(), "Las contraseñas no son iguales. Intenta de nuevo", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    private void createUser(String user, String pass, String correo){

        RegistroUser infoUser = new RegistroUser(user, pass, correo);

        Call<RegistroUser> call = djangoRestApi.crearUsuario(infoUser);


        call.enqueue(new Callback<RegistroUser>() {

            @Override
            public void onResponse(Call<RegistroUser> call, Response<RegistroUser> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                RegistroUser usuarioV = response.body();

                if (!usuarioV.getNickname().equals("error") && usuarioV.getPassword().equals("ok") && usuarioV.getCorreo().equals("ok")){

                    prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("opcion", 1);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Datos  Correctos\nAcceso Concedido", Toast.LENGTH_LONG).show();

                }else if(usuarioV.getNickname().equals("error") && usuarioV.getPassword().equals("ok") && usuarioV.getCorreo().equals("ok") ) {

                    Toast.makeText(getApplicationContext(), "Usuario ya esta en uso", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getApplicationContext(), "Usuario o Correo ya esta en uso ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<RegistroUser> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
