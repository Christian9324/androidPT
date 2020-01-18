package com.example.acorutas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private int opcion=0;

    public SharedPreferences prefs;

    private Button btn_acceso;
    private EditText et_usuario;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        opcion = prefs.getInt("opcion", 0);

        // quitar esta opcion
        opcion = 2;

        if(opcion == 1){

            setContentView(R.layout.activity_main);

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);

            }

            BottomNavigationView navigationView = findViewById(R.id.nav_view);
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navegacion_usuario, R.id.navegacion_ruta, R.id.navegacion_mapa,
                    R.id.navegacion_historial).build();

            NavController navController = Navigation.findNavController(this,
                    R.id.contenerdor_fragment);

            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);


        }else if(opcion==2){

            setContentView(R.layout.bottom_sheet_layout);

        }
        else {
            setContentView(R.layout.login);
            btn_acceso = (Button) findViewById(R.id.btn_entrar);
            et_usuario = (EditText) findViewById(R.id.ET_usuario);
            et_password = (EditText) findViewById(R.id.ET_password);

            btn_acceso.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    entrar();

                }
            });


        }



    }

    private void entrar(){

        String user;
        String pass;

        user = et_usuario.getText().toString();
        pass = et_password.getText().toString();

        if(!user.isEmpty() && !pass.isEmpty()){

            if(user.equals("Chris") && pass.equals("1234")){

                prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("opcion", 1);
                editor.commit();

                Toast.makeText(this, "Acceso concedido", Toast.LENGTH_SHORT).show();

                finishAffinity();
                startActivity(new Intent(this, MainActivity.class));



            }else {
                Toast.makeText(this, "Contraseña o Usuario Invalidos", Toast.LENGTH_SHORT).show();
            }


        }else {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show();
        }

    }
}
