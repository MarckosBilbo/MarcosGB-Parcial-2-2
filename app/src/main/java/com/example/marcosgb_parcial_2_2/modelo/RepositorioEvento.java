package com.example.marcosgb_parcial_2_2.modelo;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEvento {
    private DatabaseReference databaseReference;
    private MutableLiveData<List<Evento>> eventos;
    private String userId;

    public RepositorioEvento(Application application) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://marcosgb-parcial-2-2-default-rtdb.europe-west1.firebasedatabase.app/");
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = database.getReference("usuarios").child(userId).child("eventos");
        eventos = new MutableLiveData<>();
        fetchEventos();
    }

    public void fetchEventos() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Evento> listaEventos = new ArrayList<>();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Evento evento = childSnapshot.getValue(Evento.class);
                    if (evento != null) {
                        evento.setId(childSnapshot.getKey());
                        listaEventos.add(evento);
                    }
                }
                eventos.postValue(listaEventos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("RepositorioEvento", "Error al obtener eventos", error.toException());
            }
        });
    }

    public void agregarEvento(Evento evento) {
        DatabaseReference nuevaReferencia = databaseReference.push();
        evento.setId(nuevaReferencia.getKey());
        nuevaReferencia.setValue(evento);
    }

    public void eliminarEvento(Evento evento) {
        if (evento.getId() != null) {
            databaseReference.child(evento.getId()).removeValue();
        }
    }

    public LiveData<List<Evento>> obtenerEventos() {
        return eventos;
    }
}
