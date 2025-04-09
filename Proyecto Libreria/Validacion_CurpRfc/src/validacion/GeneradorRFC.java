/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validacion;

/**
 *
 * @author Sarai
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneradorRFC extends GeneradorIdentificacion {

    // Implementación del método abstracto para generar RFC
    @Override
    public String generarRFC(String nombre, String apellidoPaterno, String apellidoMaterno,
                             Date fechaNacimiento) {
        // Validaciones
        nombre = nombre.toUpperCase().trim();
        apellidoPaterno = apellidoPaterno.toUpperCase().trim();
        apellidoMaterno = apellidoMaterno.toUpperCase().trim();

        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || fechaNacimiento == null) {
            throw new IllegalArgumentException("Datos incompletos o inválidos");
        }

        SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
        String fecha = df.format(fechaNacimiento);

        String rfcBase = "" +
                apellidoPaterno.charAt(0) +
                getPrimeraVocalInterna(apellidoPaterno) +
                (apellidoMaterno.length() > 0 ? apellidoMaterno.charAt(0) : 'X') +
                nombre.charAt(0) +
                fecha;

        String homoclave = generarHomoclaveOficial(rfcBase, false);

        return rfcBase + homoclave;
    }

    // Métodos de utilidad
    private char getPrimeraVocalInterna(String cadena) {
        String vocales = "AEIOU";
        cadena = cadena.toUpperCase();
        for (int i = 1; i < cadena.length(); i++) {
            if (vocales.indexOf(cadena.charAt(i)) >= 0) {
                return cadena.charAt(i);
            }
        }
        return 'X';
    }

    private String generarHomoclaveOficial(String cadenaBase, boolean esParaCURP) {
        String TABLA_HOMOCLAVE = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
        
        int suma = 0;
        for (int i = 0; i < cadenaBase.length(); i++) {
            char c = cadenaBase.charAt(i);
            int valor = TABLA_HOMOCLAVE.indexOf(c) + 1;
            suma += valor * (cadenaBase.length() - i);
        }

        int longitud = esParaCURP ? 2 : 3;
        StringBuilder homoclave = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            int residuo = suma % TABLA_HOMOCLAVE.length();
            homoclave.append(TABLA_HOMOCLAVE.charAt(residuo));
            suma /= TABLA_HOMOCLAVE.length();
        }

        return homoclave.toString();
    }

    @Override
    public String generarCURP(String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, String genero, String estado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}