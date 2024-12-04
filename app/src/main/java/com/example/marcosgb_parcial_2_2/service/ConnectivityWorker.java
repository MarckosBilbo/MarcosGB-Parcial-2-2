package com.example.marcosgb_parcial_2_2.service;

import android.app.Application;
import android.content.Context;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.example.marcosgb_parcial_2_2.modelo.RepositorioEvento;

public class ConnectivityWorker extends Worker {
    public ConnectivityWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        // Crear instancia del repositorio y sincronizar eventos desde Firebase
        RepositorioEvento repositorio = new RepositorioEvento((Application) getApplicationContext());
        repositorio.fetchEventos(); // metodo que se encuentra en RepositorioEvento.java
        return Result.success();
    }
}
