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
import java.util.ArrayList; 
import java.util.List;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
// Clase para representar un lexema junto con su identificador
class Lexema {
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
public class ProyectoCompi extends JFrame implements ActionListener{

    private JButton lexemaButton, processButton;
    private JTextArea textArea, resultArea;
    private JScrollPane scrollPane, resultScrollPane;
    private List<Lexema> lexemas;

    public ProyectoCompi() {
        setTitle("Text Processor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Lista de lexemas con sus identificadores
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

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem openMenuItem = new JMenuItem("Abrir");
        openMenuItem.addActionListener(this);
        fileMenu.add(openMenuItem);
        JMenuItem exitMenuItem = new JMenuItem("Salir");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu processMenu = new JMenu("Procesar");
        JMenuItem processMenuItem = new JMenuItem("Procesar Texto");
        processMenuItem.addActionListener(this);
        processMenu.add(processMenuItem);
        menuBar.add(processMenu);

        setJMenuBar(menuBar);

        lexemaButton = new JButton("Lexema");
        lexemaButton.addActionListener(this);

        processButton = new JButton("Procesar");
        processButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(lexemaButton);
        buttonPanel.add(processButton);

        textArea = new JTextArea();
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        scrollPane = new JScrollPane(textArea);
        resultScrollPane = new JScrollPane(resultArea);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(resultScrollPane, BorderLayout.SOUTH);
    }

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
            String processedText = processText(text);
            resultArea.setText(processedText);
        } else if (e.getActionCommand().equals("Lexema")) {
            String text = textArea.getText();
            String processedText = processLexema(text);
            resultArea.setText(processedText);
        }
    }

    private String processText(String text) {
        StringBuilder resultBuilder = new StringBuilder();
        // Expresión regular para identificar palabras, números y caracteres especiales
        Pattern pattern = Pattern.compile("[a-zA-Z]\\w*|\\d+|[^a-zA-Z1-9\\s]");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String group = matcher.group();
            if (group.matches("\\d+") && group.length() > 1 && group.startsWith("0")) {
                // Si es un número que comienza con 0, separar el 0 y los otros dígitos
                resultBuilder.append("0").append("\n");
                group = group.substring(1);
            }
            // Si hay un guión seguido de un número, separarlos
            if (group.matches("-\\d+")) {
                resultBuilder.append("-").append("\n");
                group = group.substring(1);
            }
            // Si hay un guión bajo seguido de un número, separarlos
            if (group.matches("_\\d+")) {
                resultBuilder.append("_").append("\n");
                group = group.substring(1);
            }
            resultBuilder.append(group).append("\n");
        }

        // Reemplazar "< >" con "<>"
        String processedText = resultBuilder.toString().replaceAll("<\n>\n", "<>\n");

        return processedText;
    }

    private String processLexema(String text) {
        StringBuilder resultBuilder = new StringBuilder();
        // Expresión regular para identificar palabras, números y caracteres especiales
        Pattern pattern = Pattern.compile("[a-zA-Z]\\w*|\\d+|[^a-zA-Z1-9\\s]");
        Matcher matcher = pattern.matcher(text);

        resultBuilder.append("Lexema\tIdentificador\n"); // Encabezado de columnas

        while (matcher.find()) {
            String group = matcher.group();
            for (Lexema lexema : lexemas) {
                if (lexema.getLexema().equals(group)) {
                    resultBuilder.append(lexema.getLexema()).append("\t").append(lexema.getIdentificador()).append("\n");
                    break;
                }
            }
        }

        return resultBuilder.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProyectoCompi gui = new ProyectoCompi();
            gui.setVisible(true);
        });
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
