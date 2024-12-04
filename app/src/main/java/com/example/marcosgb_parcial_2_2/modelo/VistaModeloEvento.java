package com.example.marcosgb_parcial_2_2.modelo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class VistaModeloEvento extends AndroidViewModel {
    private RepositorioEvento repositorio;
    private LiveData<List<Evento>> eventos;

    public VistaModeloEvento(@NonNull Application application) {
        super(application);
        repositorio = new RepositorioEvento(application);
        eventos = repositorio.obtenerEventos();
    }

    public LiveData<List<Evento>> obtenerEventos() {
        return eventos;
    }

    public void agregarEvento(Evento evento) {
        repositorio.agregarEvento(evento);
    }

    public void eliminarEvento(Evento evento) {
        repositorio.eliminarEvento(evento);
    }
}

