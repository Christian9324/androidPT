package com.example.acorutas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acorutas.Data.models.RegistroUser;
import com.example.acorutas.Data.models.estacionInformacion;
import com.example.acorutas.Data.remote.ApiUtils;
import com.example.acorutas.Data.remote.DjangoRestApi;
import com.example.acorutas.ui.Mapa.RCAdapter;
import com.example.acorutas.ui.formLoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.acorutas.Data.databases.estaciones.estacionesMetro;
import static com.example.acorutas.Data.databases.estaciones.imgEstaciones;

public class MainActivity extends AppCompatActivity{

    public int opcion=0;

    public SharedPreferences prefs;

    private Button btn_acceso, btn_registro;
    private EditText et_usuario;
    private EditText et_password;

    private RecyclerView recyclerView;

    private DjangoRestApi djangoRestApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        opcion = prefs.getInt("opcion", 0);

        // quitar esta opcion
        //opcion = 0;

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

            recyclerView = (RecyclerView) findViewById(R.id.RV_estaciones);

            recyclerView.setHasFixedSize(true);

            ArrayList<estacionInformacion> info = new ArrayList<>();

            for(int i = 0;i<195;i++){
                estacionInformacion infoAUX = new estacionInformacion(i+1,
                        estacionesMetro[i][2], "Estacion: \n" + estacionesMetro[i][2], imgEstaciones[i]);
                info.add(infoAUX);
            }

            recyclerView.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false));

            RCAdapter adapter = new RCAdapter(info);

            recyclerView.setAdapter(adapter);

        }
        else {
            setContentView(R.layout.login);
            btn_acceso = (Button) findViewById(R.id.btn_entrar);
            btn_registro = (Button) findViewById(R.id.btn_registro);
            et_usuario = (EditText) findViewById(R.id.ET_usuario);
            et_password = (EditText) findViewById(R.id.ET_password);

            djangoRestApi = ApiUtils.getAPIService();

            btn_acceso.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    entrar();

                }
            });

            btn_registro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent ven = new Intent( getApplicationContext(), formLoginActivity.class);
                    startActivity(ven);
                    /* codigo para enlazar un fragment
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.login, new FormLogin_Fragment() ).commit();*/

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

            logUser(user, pass);

            prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombre", user);
            editor.commit();

            opcion = prefs.getInt("opcion", 0);

            //Toast.makeText(this, "" + opcion , Toast.LENGTH_LONG).show();

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



        }else {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show();
        }

    }

    private void logUser(String user, String pass){

        RegistroUser infoUser = new RegistroUser(user, pass);

        Call<RegistroUser> call = djangoRestApi.verificarUsuario(infoUser);



        call.enqueue(new Callback<RegistroUser>() {

            @Override
            public void onResponse(Call<RegistroUser> call, Response<RegistroUser> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                RegistroUser usuarioV = response.body();

                if (usuarioV.getNickname().equals("ok") && usuarioV.getPassword().equals("ok")){

                    prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("opcion", 1);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Acceso concedido", Toast.LENGTH_LONG).show();

                }else {

                    prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("opcion", 0);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Usuario o Contraseña incorrecto", Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onFailure(Call<RegistroUser> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
/*

            //Anterior estado de solo inicio con chris
            if(user.equals("Chris") && pass.equals("1234")){

                prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("opcion", 1);
                editor.commit();

                editor.putString("nombre", user);
                editor.commit();

                Toast.makeText(this, "Acceso concedido", Toast.LENGTH_SHORT).show();

                finishAffinity();
                startActivity(new Intent(this, MainActivity.class));



            }else {
                Toast.makeText(this, "Contraseña o Usuario Invalidos", Toast.LENGTH_SHORT).show();
            }

             */