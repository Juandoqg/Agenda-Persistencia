package com.example.persistencia;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    EditText cajaBuscarModi, cajaNombreModi, cajaApellidoModi, cajaEmailModi, cajaTelefonoModi, cajaDocumentoModi, cajaEdadModi,cajaDireccionModi, cajaNacimientoModi;

    RadioGroup radioGroupGeneroModi,radioGroupEstadoModi;

    RadioButton cajaMasculinoModi, cajaFemeninoModi, cajaSolteroModi, cajaCasadoModi;

    CheckBox checkCineModi, checkMusicaModi, checkDeporteModi, checkViajesModi, checkComidaModi, checkLibrosModi;

    Spinner spinnerModi;

    EditText cajaPeliculaModi, cajaColorModi, cajaComidaModi,cajaLibroModi, cajaCancionModi, cajaDescripcionModi;
    ArrayList<Usuario> listaUsuarios;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        cajaBuscarModi = findViewById(R.id.cajaBuscarModi);
        cajaNombreModi = findViewById(R.id.cajaNombreModi);
        cajaApellidoModi = findViewById(R.id.cajaApellidoModi);
        cajaEmailModi = findViewById(R.id.cajaEmailModi);
        cajaTelefonoModi = findViewById(R.id.cajaTelefonoModi);
        cajaDocumentoModi = findViewById(R.id.cajaDocumentoModi);
        cajaEdadModi = findViewById(R.id.cajaEdadModi);
        cajaDireccionModi = findViewById(R.id.cajaDireccionModi);
        cajaNacimientoModi = findViewById(R.id.cajaNacimientoModi);
        //////
        cajaPeliculaModi= findViewById(R.id.cajaPeliculaModi);
        cajaColorModi = findViewById(R.id.cajaColorModi);
        cajaComidaModi = findViewById(R.id.cajaComidaModi);
        cajaLibroModi = findViewById(R.id.cajaLibroModi);
        cajaCancionModi = findViewById(R.id.cajaCancionModi);
        cajaDescripcionModi = findViewById(R.id.cajaDescripcionModi);
        //////
        radioGroupEstadoModi = findViewById(R.id.radioGroupEstadoModi);
        cajaSolteroModi = findViewById(R.id.cajaSolteroModi);
        cajaCasadoModi = findViewById(R.id.cajaCasadoModi);
        //
        radioGroupGeneroModi = findViewById(R.id.radioGroupGeneroModi);
        cajaMasculinoModi = findViewById(R.id.cajaMasculinoModi);
        cajaFemeninoModi = findViewById(R.id.cajaFemeninoModi);
        //////
        checkCineModi = findViewById(R.id.checkCineModi);
        checkMusicaModi = findViewById(R.id.checkMusicaModi);
        checkDeporteModi = findViewById(R.id.checkDeporteModi);
        checkViajesModi = findViewById(R.id.checkViajesModi);
        checkComidaModi = findViewById(R.id.checkComidaModi);
        checkLibrosModi = findViewById(R.id.checkLibrosModi);
        //////
        spinnerModi = findViewById(R.id.spinnerModi);
        ArrayList<String> items = new ArrayList<>();
        items.add("Barcelona");
        items.add("Real Madrid");
        items.add("Milan");
        items.add("Inter");
        items.add("Bayern");
        items.add("Paris");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerModi.setAdapter(adapter);
        listaUsuarios = (ArrayList<Usuario>) getIntent().getSerializableExtra("listaUsuarios");
    }


    public void BuscarUsuario(View view) {
        String emailBuscado = cajaBuscarModi.getText().toString();
        ArrayList<Usuario> listaUsuarios = PersistenciaUsuarios.leerUsuarios(this);
        Usuario usuarioEncontrado = buscarUsuarioPorEmail(emailBuscado, listaUsuarios);

        if (usuarioEncontrado != null) {
            cajaNombreModi.setText(usuarioEncontrado.getNombre());
            cajaApellidoModi.setText(usuarioEncontrado.getApellido());
            cajaDocumentoModi.setText(usuarioEncontrado.getDocumento());
            cajaEdadModi.setText(usuarioEncontrado.getEdad());
            cajaTelefonoModi.setText(usuarioEncontrado.getTelefono());
            cajaDireccionModi.setText(usuarioEncontrado.getDireccion());
            cajaNacimientoModi.setText(usuarioEncontrado.getNacimiento());
            cajaEmailModi.setText(usuarioEncontrado.getEmail());
            //seteo de radiogrouo
            String genero = usuarioEncontrado.getGenero();
            if (genero.equals("Masculino")) {
                radioGroupGeneroModi.check(R.id.cajaMasculinoModi);
            } else if (genero.equals("Femenino")) {
                radioGroupGeneroModi.check(R.id.cajaFemeninoModi);
            }

            String estado = usuarioEncontrado.getEstado();
            if (estado.equals("Casado")) {
                radioGroupEstadoModi.check(R.id.cajaCasadoModi);
            } else if (estado.equals("Soltero")) {
                radioGroupEstadoModi.check(R.id.cajaSolteroModi);
            }
            //
            checkMusicaModi.setChecked(usuarioEncontrado.isCheckMusica());
            checkDeporteModi.setChecked(usuarioEncontrado.isCheckDeporte());
            checkCineModi.setChecked(usuarioEncontrado.isCheckCine());
            checkViajesModi.setChecked(usuarioEncontrado.isCheckViajes());
            checkComidaModi.setChecked(usuarioEncontrado.isCheckComida());
            checkLibrosModi.setChecked(usuarioEncontrado.isCheckLibros());
            //seteo de spinner
            String equipo = usuarioEncontrado.getEquipo();
            int position = adapter.getPosition(equipo);
            if (position >= 0) {
                spinnerModi.setSelection(position);
            } else {

                Toast.makeText(this,"Equipo no encontrado", Toast.LENGTH_SHORT).show();
            }

            //
            cajaPeliculaModi.setText(usuarioEncontrado.getPelicula());
            cajaColorModi.setText(usuarioEncontrado.getColor());
            cajaComidaModi.setText(usuarioEncontrado.getComida());
            cajaLibroModi.setText(usuarioEncontrado.getLibro());
            cajaCancionModi.setText(usuarioEncontrado.getCancion());
            cajaDescripcionModi.setText(usuarioEncontrado.getDescripcion());

        } else {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    private Usuario buscarUsuarioPorEmail(String email, ArrayList<Usuario> listaUsuarios) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    public void modificarUsuario(View view) {
        String nombre = cajaNombreModi.getText().toString();
        String apellido = cajaApellidoModi.getText().toString();
        String documento = cajaDocumentoModi.getText().toString();
        String edad = cajaEdadModi.getText().toString();
        String telefono = cajaTelefonoModi.getText().toString();
        String direccion = cajaDireccionModi.getText().toString();
        String nacimiento = cajaNacimientoModi.getText().toString();
        String email = cajaEmailModi.getText().toString();
        boolean musica = checkMusicaModi.isChecked();
        boolean cine = checkCineModi.isChecked();
        boolean deporte = checkDeporteModi.isChecked();
        boolean boxcomida = checkComidaModi.isChecked();
        boolean viajes = checkViajesModi.isChecked();
        boolean libros = checkLibrosModi.isChecked();
        String genero = "";
        String estado = "";
        String equipo = spinnerModi.getSelectedItem().toString();
        String pelicula = cajaPeliculaModi.getText().toString();
        String color = cajaColorModi.getText().toString();
        String comida = cajaComidaModi.getText().toString();
        String libro = cajaLibroModi.getText().toString();
        String cancion = cajaCancionModi.getText().toString();
        String descripcion = cajaDescripcionModi.getText().toString();

        int selectedGeneroId = radioGroupGeneroModi.getCheckedRadioButtonId();
        if (selectedGeneroId != -1) {
            RadioButton selectedGenero = findViewById(selectedGeneroId);
            genero = selectedGenero.getText().toString();
        }

        int selectedEstadoId = radioGroupEstadoModi.getCheckedRadioButtonId();
        if (selectedEstadoId != -1) {
            RadioButton selectedEstado = findViewById(selectedEstadoId);
            estado = selectedEstado.getText().toString();
        }

        // Leer los usuarios desde el archivo
        ArrayList<Usuario> listaUsuarios = PersistenciaUsuarios.leerUsuarios(this);

        boolean usuarioModificado = false;
        if (validarCampos()) {
            for (int i = 0; i < listaUsuarios.size(); i++) {
                Usuario usuario = listaUsuarios.get(i);

                if (usuario.getEmail().equals(cajaBuscarModi.getText().toString())) {

                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setDocumento(documento);
                    usuario.setEdad(edad);
                    usuario.setTelefono(telefono);
                    usuario.setDireccion(direccion);
                    usuario.setNacimiento(nacimiento);
                    usuario.setEmail(email);
                    usuario.setGenero(genero);
                    usuario.setEstado(estado);
                    usuario.setCheckCine(cine);
                    usuario.setCheckMusica(musica);
                    usuario.setCheckDeporte(deporte);
                    usuario.setCheckComida(boxcomida);
                    usuario.setCheckViajes(viajes);
                    usuario.setCheckLibros(libros);
                    usuario.setEquipo(equipo);
                    usuario.setPelicula(pelicula);
                    usuario.setColor(color);
                    usuario.setComida(comida);
                    usuario.setLibro(libro);
                    usuario.setCancion(cancion);
                    usuario.setDescripcion(descripcion);

                    listaUsuarios.set(i, usuario);

                    usuarioModificado = true;
                    limpiarCampos();
                    break;
                }
            }
        }

        if (usuarioModificado) {

            PersistenciaUsuarios.guardarUsuarios( listaUsuarios,this);
            Toast.makeText(this, "Usuario modificado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show();
        }
    }


    private void limpiarCampos() {
        cajaNombreModi.setText("");
        cajaApellidoModi.setText("");
        cajaDocumentoModi.setText("");
        cajaEdadModi.setText("");
        cajaTelefonoModi.setText("");
        cajaDireccionModi.setText("");
        cajaNacimientoModi.setText("");
        cajaEmailModi.setText("");
        radioGroupGeneroModi.clearCheck();
        radioGroupEstadoModi.clearCheck();
        spinnerModi.setSelection(0);
        checkCineModi.setChecked(false);
        checkMusicaModi.setChecked(false);
        checkDeporteModi.setChecked(false);
        checkComidaModi.setChecked(false);
        checkLibrosModi.setChecked(false);
        checkViajesModi.setChecked(false);
        cajaPeliculaModi.setText("");
        cajaColorModi.setText("");
        cajaComidaModi.setText("");
        cajaLibroModi.setText("");
        cajaCancionModi.setText("");
        cajaDescripcionModi.setText("");
    }
    private boolean validarCampos() {

        String nacimiento = cajaNacimientoModi.getText().toString().trim();

        if (cajaNombreModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo nombre es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaApellidoModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo apellido es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaDocumentoModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo documento es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (cajaTelefonoModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo teléfono es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaTelefonoModi.getText().length() < 10) {
            Toast.makeText(this, "El teléfono debe tener al menos 10 dígitos", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!nacimiento.matches("\\d{2}\\d{2}\\d{4}")) { // Comprueba si el formato es ddmmyyyy
            Toast.makeText(this, "El formato de nacimiento debe ser ddmmyyyy", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaEdadModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo edad es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaDireccionModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo dirección es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaNacimientoModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo nacimiento es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaEmailModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo email es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(cajaEmailModi.getText().toString()).matches()) {
            Toast.makeText(this, "El email ingresado no es válido", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (cajaPeliculaModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo película es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaColorModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo color es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaComidaModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo comida es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaLibroModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo libro es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaCancionModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo canción es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cajaDescripcionModi.getText().toString().isEmpty()) {
            Toast.makeText(this, "El campo descripción es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void volver(View view){
        Intent intent3 = new Intent();
        intent3.putExtra("listaUsuarios", listaUsuarios);
        setResult(RESULT_OK, intent3);
        finish();

    }
}