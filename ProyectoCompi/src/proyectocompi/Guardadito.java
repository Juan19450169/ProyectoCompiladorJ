/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocompi;

/**
 *
 * @author aleja
 */
public class Guardadito {
  private  String datolex;
   private int token;

    public Guardadito(String datolex, int token) {
        this.datolex = datolex;
        this.token = token;
    }

    public String getDatolex() {
        return datolex;
    }

    public int getToken() {
        return token;
    }

    public void setDatolex(String datolex) {
        this.datolex = datolex;
    }

    public void setToken(int token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Guardadito{" + "datolex=" + datolex + ", token=" + token + '}';
    }
   
}
