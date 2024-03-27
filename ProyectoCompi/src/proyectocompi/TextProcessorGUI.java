/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocompi;

/**
 *
 * @author aleja
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.*;
public class TextProcessorGUI extends JFrame implements ActionListener {
    private JButton openButton, processButton;
    private JTextArea textArea;
    
    public TextProcessorGUI() {
        setTitle("Text Processor");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        openButton = new JButton("Abrir");
        openButton.addActionListener(this);
        
        processButton = new JButton("Procesar");
        processButton.addActionListener(this);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(processButton);
        
        textArea = new JTextArea();
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                        stringBuilder.append("\n");
                    }
                    textArea.setText(stringBuilder.toString());
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == processButton) {
            String text = textArea.getText();
            processText(text);
        }
    }
    
    private void processText(String text) {
        // Expresiones regulares para identificar variables, números y caracteres
        Pattern variablePattern = Pattern.compile("\\b[a-zA-Z]+(?:_[a-zA-Z]+)*\\b");
        Pattern numberPattern = Pattern.compile("\\b\\d+(?:\\.\\d+)?\\b");
        Pattern characterPattern = Pattern.compile("\\b[^\\w\\s]+\\b");
        
        Matcher variableMatcher = variablePattern.matcher(text);
        Matcher numberMatcher = numberPattern.matcher(text);
        Matcher characterMatcher = characterPattern.matcher(text);
        
        TreeMap<String, Integer> variables = new TreeMap<>();
        TreeSet<Double> numbers = new TreeSet<>();
        TreeSet<String> characters = new TreeSet<>();
        
        while (variableMatcher.find()) {
            String variable = variableMatcher.group();
            variables.put(variable, variables.getOrDefault(variable, 0) + 1);
        }
        
        while (numberMatcher.find()) {
            String number = numberMatcher.group();
            numbers.add(Double.parseDouble(number));
        }
        
        while (characterMatcher.find()) {
            String character = characterMatcher.group();
            characters.add(character);
        }
        
        // Mostrar el resultado en la salida
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Variables:\n");
        for (String variable : variables.keySet()) {
            resultBuilder.append(variable).append("\n");
        }
        resultBuilder.append("Números:\n");
        for (Double number : numbers) {
            resultBuilder.append(number).append("\n");
        }
        resultBuilder.append("Caracteres:\n");
        for (String character : characters) {
            resultBuilder.append(character).append("\n");
        }
        
        JOptionPane.showMessageDialog(this, resultBuilder.toString(), "Resultado del Procesamiento", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextProcessorGUI gui = new TextProcessorGUI();
            gui.setVisible(true);
        });
    }
}
