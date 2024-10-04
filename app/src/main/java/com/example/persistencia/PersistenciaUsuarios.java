package com.example.persistencia;

import android.content.Context;
import java.io.*;
import java.util.ArrayList;

public class PersistenciaUsuarios {

    private static final String NOMBRE_ARCHIVO = "usuarios.txt";


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


    public static ArrayList<Usuario> leerUsuarios(Context context) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(NOMBRE_ARCHIVO);
            ObjectInputStream ois = new ObjectInputStream(fis);
            listaUsuarios = (ArrayList<Usuario>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            listaUsuarios = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }
}

