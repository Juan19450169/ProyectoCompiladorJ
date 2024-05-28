/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocompi;

/**
 *
 * @author aleja
 */
public class Lexema {
    private String lexema;
    private int identificador;

    public Lexema(String lexema, int identificador) {
        this.lexema = lexema;
        this.identificador = identificador;
    }

    public String getLexema() {
        return lexema;
    }

    public int getIdentificador() {
        return identificador;
    }
}
