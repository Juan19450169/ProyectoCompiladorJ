/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocompi;

/**
 *
 * @author aleja
 */

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

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

public class Compilador extends JFrame implements ActionListener {
    private JTextArea textArea, resultArea;
    private JScrollPane scrollPane, resultScrollPane;
    private List<Lexema> lexemas;
    private List<Guardadito> guardados;

    public Compilador() {
        this.guardados = new ArrayList<Guardadito>();
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
        lexemas.add(new Lexema("=", 206));  // Agregado "=" como lexema individual
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
        }
    }

    private String processText(String text) {
        StringBuilder resultBuilder = new StringBuilder();
        // Expresión regular para identificar palabras, números y caracteres especiales
        Pattern pattern = Pattern.compile("([a-zA-Z]\\w*)|([1-9]\\d*|[0])|:=|==|<>|>=|<=|\\+|\\-|\\*|\\/|=|_|;|,|\\(|\\)|>|<|(\\.)");
        Matcher matcher = pattern.matcher(text);

        resultBuilder.append("Lexema\tIdentificador\n"); // Encabezado de columnas

        while (matcher.find()) {
            String group = matcher.group();
            boolean found = false;
            for (Lexema lexema : lexemas) {
                if (lexema.getLexema().equals(group)) {
                    guardados.add(new Guardadito(lexema.getLexema(), lexema.getIdentificador()));
                    resultBuilder.append(lexema.getLexema()).append("\t").append(lexema.getIdentificador()).append("\n");
                    found = true;
                    break;
                }
            }
            if (!found) {
                if (group.matches("\\d+")) {
                    resultBuilder.append(group).append("\t2\n");
                    guardados.add(new Guardadito(group,2));
                } else {
                    resultBuilder.append(group).append("\t1\n");
                    guardados.add(new Guardadito(group, 1));
                }
            }
        }

        // Realizar el análisis sintáctico
        Sintactico sintactico = new Sintactico(guardados);
        sintactico.Programa();

        // Comprobar si hay errores sintácticos
        String errors = sintactico.getErrors();
        if (!errors.isEmpty()) {
            resultBuilder.append("\nErrores sintácticos:\n").append(errors);
        } else {
            resultBuilder.append("\nEl código es sintácticamente correcto.");
        }

        return resultBuilder.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Compilador gui = new Compilador();
            gui.setVisible(true);
        });
    }
}


//Prueba



