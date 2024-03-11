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
           String cadena = "hola+masmundo*adios123-algo_666perro";

        // Imprimir la cadena completa
        System.out.println("Cadena completa: " + cadena);

        // Encontrar e imprimir los identificadores
        Pattern patron = Pattern.compile("\\b[a-zA-Z_][a-zA-Z0-9_]*\\b");
        Matcher matcher = patron.matcher(cadena);

        System.out.println("Identificadores encontrados:");
        while (matcher.find()) {
            String identificador = matcher.group();
            System.out.println(identificador);
        }
    }
}
