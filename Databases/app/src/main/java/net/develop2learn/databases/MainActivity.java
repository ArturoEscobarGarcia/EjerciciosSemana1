package net.develop2learn.databases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBAdapter db = new DBAdapter(this);
        try {
            String destPath = "/data/data/" + getPackageName() + "/databases";
            File f = new File(destPath);
            if (!f.exists()) {
                f.mkdirs();
                f.createNewFile();
// Copy the db from assets folder(source code) into the databases folder (phone)
                CopyDB(getResources().getAssets().open("MyDB"),
                        new FileOutputStream(destPath + "/MyDB"));
            }
        } catch (FileNotFoundException fnfE) {
            fnfE.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//--Add contacts--
       /* db.open();
        db.insertContact("J. Rodiz", "rodiz.cj@gmail.com");
        db.insertContact("E. López", "elopez@lania.mx");
        db.close();*/

        //--Get all contacts --
        db.open();
        Cursor c = db.getAllContacts();
        if (c.moveToFirst()) {
            do {
                DisplayContact(c);
            } while (c.moveToNext());
        }
        db.close();
        //--Get a contact --
        /*db.open();
        Cursor c = db.getContact(2);
        if (c.moveToFirst()) DisplayContact(c);
        else
            Toast.makeText(getBaseContext(), "Contact not found :S", Toast.LENGTH_LONG).show();
        db.close();*/
        //--Update a contact --
        /*db.open();
        if(db.updateContact(1, "Joseph Rodiz", "rodiz.cj@me.com")) Toast.makeText(getBaseContext(), "Update successful :D", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getBaseContext(), "Update failed :S", Toast.LENGTH_LONG).show();
        db.close();*/
        //--Delete a contact--
        /*db.open();
        if(db.deleteContact(2))
        Toast.makeText(getBaseContext(), "Delete successful :D", Toast.LENGTH_LONG).show();
        else
        Toast.makeText(getBaseContext(), "Delete failed :S", Toast.LENGTH_LONG).show();
        db.close();*/
    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
//copy max 1KB at a time
        byte buffer[] = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }


    public void DisplayContact(Cursor c) {
        Toast.makeText(getBaseContext(),
            "ID: " + c.getString(0) + "\n"
                    + "Name: " + c.getString(1) + "\n"
                    + "Email: " + c.getString(2) + "\n",
                Toast.LENGTH_LONG).show();
    }


}
