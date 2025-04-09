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

import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneradorCURP extends GeneradorIdentificacion {

    // Implementación del método abstracto para generar CURP
    @Override
    public String generarCURP(String nombre, String apellidoPaterno, String apellidoMaterno,
                               Date fechaNacimiento, String genero, String estado) {
        // Validaciones
        nombre = nombre.toUpperCase().trim();
        apellidoPaterno = apellidoPaterno.toUpperCase().trim();
        apellidoMaterno = apellidoMaterno.toUpperCase().trim();
        genero = normalizarGenero(genero);
        estado = obtenerClaveEstado(estado);

        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || fechaNacimiento == null || genero == null || estado == null) {
            throw new IllegalArgumentException("Datos incompletos o inválidos");
        }

        SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
        String fecha = df.format(fechaNacimiento);

        char l1 = apellidoPaterno.charAt(0);
        char v1 = getPrimeraVocalInterna(apellidoPaterno);
        char l2 = apellidoMaterno.length() > 0 ? apellidoMaterno.charAt(0) : 'X';
        char l3 = nombre.charAt(0);

        String curpBase = "" + l1 + v1 + l2 + l3 + fecha + genero + estado;

        char ci1 = getPrimeraConsonanteInterna(apellidoPaterno);
        char ci2 = getPrimeraConsonanteInterna(apellidoMaterno);
        char ci3 = getPrimeraConsonanteInterna(nombre);

        String homoclave = generarHomoclaveOficial(curpBase + ci1 + ci2 + ci3, true);

        return curpBase + ci1 + ci2 + ci3 + homoclave;
    }

    // Métodos de utilidad
    private String normalizarGenero(String genero) {
        genero = genero.toLowerCase().trim();
        if (genero.equals("f") || genero.contains("mujer") || genero.contains("femenino")) {
            return "M";
        }
        return "H";
    }

    private String obtenerClaveEstado(String estado) {
        estado = estado.toUpperCase().trim();
        String[][] ESTADOS = {
            {"AGUASCALIENTES", "AS"}, {"BAJA CALIFORNIA", "BC"}, {"BAJA CALIFORNIA SUR", "BS"},
            {"CAMPECHE", "CC"}, {"CHIAPAS", "CS"}, {"CHIHUAHUA", "CH"}, {"COAHUILA", "CL"},
            {"COLIMA", "CM"}, {"CIUDAD DE MEXICO", "DF"}, {"DURANGO", "DG"}, {"GUANAJUATO", "GT"},
            {"GUERRERO", "GR"}, {"HIDALGO", "HG"}, {"JALISCO", "JC"}, {"MEXICO", "MC"},
            {"MICHOACAN", "MN"}, {"MORELOS", "MS"}, {"NAYARIT", "NT"}, {"NUEVO LEON", "NL"},
            {"OAXACA", "OC"}, {"PUEBLA", "PL"}, {"QUERETARO", "QT"}, {"QUINTANA ROO", "QR"},
            {"SAN LUIS POTOSI", "SL"}, {"SINALOA", "SI"}, {"SONORA", "SR"}, {"TABASCO", "TB"},
            {"TAMAULIPAS", "TM"}, {"TLAXCALA", "TL"}, {"VERACRUZ", "VZ"}, {"YUCATAN", "YN"},
            {"ZACATECAS", "ZS"}
        };

        for (String[] entrada : ESTADOS) {
            if (entrada[0].equals(estado)) {
                return entrada[1];
            }
        }
        throw new IllegalArgumentException("El estado no existe");
    }

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

    private char getPrimeraConsonanteInterna(String cadena) {
        String consonantes = "BCDFGHJKLMNPQRSTVWXYZ";
        cadena = cadena.toUpperCase();
        for (int i = 1; i < cadena.length(); i++) {
            if (consonantes.indexOf(cadena.charAt(i)) >= 0) {
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
    public String generarRFC(String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}