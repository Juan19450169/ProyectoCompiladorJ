/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package expreghex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author juans
 */
public class ExpRegHex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           String cadena = "-123.var_56_x;28+";

        // Imprimir la cadena completa
        System.out.println("Cadena completa: " + cadena);

        // Encontrar e imprimir los identificadores
        Pattern patron = Pattern.compile("[a-zA-Z]\\w*|[1-9]\\d*");
        Matcher matcher = patron.matcher(cadena);

        System.out.println("ID VALIDOS:");
        while (matcher.find()) {
            String identificador = matcher.group();
            System.out.println(identificador);
        }
    }
}
