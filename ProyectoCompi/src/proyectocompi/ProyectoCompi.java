/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectocompi;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Clase para representar un lexema junto con su identificador

public class ProyectoCompi extends JFrame implements ActionListener{

     private JTextArea textArea, resultArea;
    private JScrollPane scrollPane, resultScrollPane;
    private List<Lexema> lexemas;
    private TextProcessor textProcessor;

    public ProyectoCompi() {
        setTitle("Text Processor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar lexemas y el procesador de texto
        initializeLexemas();
        textProcessor = new TextProcessor(lexemas);

        // Configuración de la interfaz gráfica
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        textArea = new JTextArea();
        textArea.setLineWrap(true); // Ajuste de línea
        textArea.setWrapStyleWord(true); // Ajuste de palabra
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true); // Ajuste de línea
        resultArea.setWrapStyleWord(true); // Ajuste de palabra

        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        resultScrollPane = new JScrollPane(resultArea);
        resultScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        resultScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, resultScrollPane);
        splitPane.setResizeWeight(0.5); // Ajusta la proporción inicial entre los paneles

        add(splitPane, BorderLayout.CENTER);
    }

    private void initializeLexemas() {
        lexemas = new ArrayList<>();
        lexemas.add(new Lexema("start", 101));
        lexemas.add(new Lexema("begin", 102));
        lexemas.add(new Lexema("finish", 103));
        lexemas.add(new Lexema("int", 104));
        lexemas.add(new Lexema("str", 105));
        lexemas.add(new Lexema("input", 106));
        lexemas.add(new Lexema("output", 107));
        lexemas.add(new Lexema("if", 108));
        lexemas.add(new Lexema("then", 109));
        lexemas.add(new Lexema("else", 110));
        lexemas.add(new Lexema("loop", 111));
        lexemas.add(new Lexema("end", 112));
        lexemas.add(new Lexema("do", 113));
        lexemas.add(new Lexema("repeat", 114));
        lexemas.add(new Lexema("+", 201));
        lexemas.add(new Lexema("-", 202));
        lexemas.add(new Lexema("*", 203));
        lexemas.add(new Lexema("/", 204));
        lexemas.add(new Lexema(":=", 205));
       // lexemas.add(new Lexema("=", 206)); // Agregado "=" como lexema individual
        lexemas.add(new Lexema("==", 301));
        lexemas.add(new Lexema("<>", 302));
        lexemas.add(new Lexema(">", 303));
        lexemas.add(new Lexema(">=", 304));
        lexemas.add(new Lexema("<", 305));
        lexemas.add(new Lexema("<=", 306));
        lexemas.add(new Lexema(";", 401));
        lexemas.add(new Lexema("(", 402));
        lexemas.add(new Lexema(")", 403));
        lexemas.add(new Lexema(",", 501));
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem openMenuItem = new JMenuItem("Abrir");
        openMenuItem.addActionListener(this);
        fileMenu.add(openMenuItem);
        JMenuItem exitMenuItem = new JMenuItem("Salir");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu processMenu = new JMenu("Procesar");
        JMenuItem processMenuItem = new JMenuItem("Procesar Texto");
        processMenuItem.addActionListener(this);
        processMenu.add(processMenuItem);
        menuBar.add(processMenu);

        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Abrir")) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    textArea.setText(stringBuilder.toString());
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("Procesar Texto")) {
            String text = textArea.getText();
            String processedText = textProcessor.processText(text);
            resultArea.setText(processedText);
        }
    }
}

/*
//El dia de hoy domingo a las 11:57 horas terminamos de subir el codigo con los reuqerimientos que nos solicito en la actividad Actividad 22 . att: Alejandro*/




/*Esto es una prueba con el Ing Esaul siendo las 08:42 horas del dia lunes 11 de marzo del 2024 
en el aula de clase en el instituto tecnologico de zacatecas*/
    
/*Se corrige el error que habia dado el dia 09/04/2024 eb el cual ya se puede correr*/

/* 09 abril 2024 se suben los cambios del programa */
/* Comit del 10 de abril del 2024*/

//Ultima modificacion el dia 02 de mayo en donde se agrega la funcionalidad de los lexemas al codigo
//Ya podemos leer los lexemas con su propio identificador
