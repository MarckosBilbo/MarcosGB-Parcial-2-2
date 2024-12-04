package com.example.marcosgb_parcial_2_2.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Evento implements Parcelable {
    private String id;          // Identificador único (Firebase key)
    private String nombre;      // Nombre del evento
    private String descripcion; // Breve descripción del evento
    private String direccion;   // Dirección donde ocurre el evento
    private double precio;      // Precio del evento
    private String fecha;       // Fecha en formato YYYY-MM-DD
    private int aforo;          // Capacidad máxima del evento

    public Evento() {
        // Constructor vacío para Firebase
    }

    public Evento(String nombre, String descripcion, String direccion, double precio, String fecha, int aforo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.precio = precio;
        this.fecha = fecha;
        this.aforo = aforo;
    }

    protected Evento(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        descripcion = in.readString();
        direccion = in.readString();
        precio = in.readDouble();
        fecha = in.readString();
        aforo = in.readInt();
    }

    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }

        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(direccion);
        dest.writeDouble(precio);
        dest.writeString(fecha);
        dest.writeInt(aforo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }
}