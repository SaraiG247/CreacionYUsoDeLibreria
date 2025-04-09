/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package validacion;

/**
 *
 * @author Sarai
 */


import java.util.Date;

public abstract class GeneradorIdentificacion {

    // Método abstracto para generar CURP
    public abstract String generarCURP(String nombre, String apellidoPaterno, String apellidoMaterno,
                                        Date fechaNacimiento, String genero, String estado);

    // Método abstracto para generar RFC
    public abstract String generarRFC(String nombre, String apellidoPaterno, String apellidoMaterno,
                                       Date fechaNacimiento);

    // Método para validar si un CURP es válido
    public boolean esCURPValido(String curp) {
        return curp != null && curp.matches("^[A-Z]{4}\\d{6}[HM][A-Z]{5}[A-Z0-9]{2}$");
    }

    // Método para validar si un RFC es válido
    public boolean esRFCValido(String rfc) {
        return rfc != null && rfc.matches("^[A-Z]{4}\\d{6}[A-Z0-9]{3}$");
    }
}