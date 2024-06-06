/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocompi;
import javax.swing.SwingUtilities;
/**
 *
 * @author aleja
 */
public class Main {
      public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Compilador gui = new Compilador();
            gui.setVisible(true);
        });
    }
}
