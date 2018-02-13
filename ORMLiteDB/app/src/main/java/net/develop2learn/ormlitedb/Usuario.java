package net.develop2learn.ormlitedb;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable
public class Usuario {

    public static final String ID = "_id";
    public static final String NOMBRE = "nombre";
    public static final String FECHA_NACIMIENTO = "fecha_nacimiento";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(columnName = NOMBRE)
    private String nombre;
    @DatabaseField(columnName = FECHA_NACIMIENTO)
    private Date fechaNacimiento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString(){
        return ("Usuario[" + this.getId() + "]: " + this.getNombre() + " Nacimiento: " + this.getFechaNacimiento().toString());

    }

}


