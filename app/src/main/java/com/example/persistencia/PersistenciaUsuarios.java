package com.example.persistencia;

import android.content.Context;
import java.io.*;
import java.util.ArrayList;

public class PersistenciaUsuarios {

    private static final String NOMBRE_ARCHIVO = "usuarios.txt";

    // Método para guardar la lista de usuarios en un archivo
    public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(NOMBRE_ARCHIVO, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaUsuarios);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para leer la lista de usuarios desde un archivo
    public static ArrayList<Usuario> leerUsuarios(Context context) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(NOMBRE_ARCHIVO);
            ObjectInputStream ois = new ObjectInputStream(fis);
            listaUsuarios = (ArrayList<Usuario>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            // Si no existe el archivo, devolver lista vacía
            listaUsuarios = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }
}

