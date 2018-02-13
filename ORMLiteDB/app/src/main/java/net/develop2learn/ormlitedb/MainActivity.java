package net.develop2learn.ormlitedb;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = OpenHelperManager.getHelper(this, DBHelper.class);

//Crear un objeto tipo Usuario
        Dao dao;
        try {
            dao = getHelper().getUsuarioDao();
            QueryBuilder queryBuilder = dao.queryBuilder();

            queryBuilder.setWhere(queryBuilder.where().eq(Usuario.NOMBRE, "Joseph"));
            List usuarios = dao.query(queryBuilder.prepare());
            if (usuarios.isEmpty()) {
                DisplayInfo("No se encontraron usuarios con nombre = Joseph");
            } else {
                DisplayInfo("Recuperado usuarios con nombre = Joseph " + usuarios);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG, "Error creando usuario");
        }


        //Recuperando objeto
        /*try {
            dao = getHelper().getUsuarioDao();
            int idToSearch = 1;
            Usuario usuario = (Usuario) dao.queryForId(idToSearch);
//Un objeto por ID
            if (usuario == null) {
                DisplayInfo("Ningún usuario con id = " + idToSearch);
            } else {
                DisplayInfo("Recuperado usuario con id = " + idToSearch + ": " + usuario.toString());
            }

            List<Usuario> usuarios = dao.queryForEq(Usuario.NOMBRE, "Joseph"); for(Usuario u : usuarios){
                Log.d(TAG, "Usuario: " + u.toString());
            }

            if (usuarios.isEmpty()) {
                DisplayInfo("No se encontraron usuarios con nombre = Joseph");
            } else {
                DisplayInfo("Recuperado usuarios [" + usuarios.size() + "] con nombre = Joseph \n" + usuarios);

            }
        } catch (SQLException e) {
            Log.e(TAG, "Error creando usuario");
        }*/

            /*try {
            dao = getHelper().getUsuarioDao();
            Usuario usuario = new Usuario();
            usuario.setFechaNacimiento(new Date());
            usuario.setNombre("Joseph");
            long result = dao.create(usuario); //returns 1 if susscceful inserted
        } catch (SQLException e) {
            Log.e(TAG, "Error creando usuario");
        }*/

    }

    private DBHelper mDBHelper;

    private DBHelper getHelper() {
        if (mDBHelper == null) {
            mDBHelper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return mDBHelper;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDBHelper != null) {
            OpenHelperManager.releaseHelper();
            mDBHelper = null;
        }
    }

    public void DisplayInfo(String t){
        Toast.makeText(getBaseContext(), t, Toast.LENGTH_LONG).show();
    }


}

