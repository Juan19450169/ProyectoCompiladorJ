/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectocompi;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProyectoCompi extends JFrame {

    private JTextArea txtContenido;
    private JTextArea txtMensajes;

    public ProyectoCompi() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Proyecto de Apertura de Archivo");

        txtContenido = new JTextArea();
        JScrollPane scrollContenido = new JScrollPane(txtContenido);

        txtMensajes = new JTextArea();
        JScrollPane scrollMensajes = new JScrollPane(txtMensajes);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuArchivo = new JMenu("Archivo");
        menuBar.add(menuArchivo);

        JMenuItem menuItemAbrir = new JMenuItem("Abrir");
        menuArchivo.add(menuItemAbrir);

        menuItemAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArchivo();
            }
        });

        JMenu menuProcesar = new JMenu("Procesar");
        menuBar.add(menuProcesar);

        JMenuItem menuItemSeparar = new JMenuItem("Separar");
        menuProcesar.add(menuItemSeparar);

        menuItemSeparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                separarRegExp();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollContenido, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(scrollMensajes))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollContenido, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollMensajes, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                // Mostrar contenido en el área de texto principal
                txtContenido.setText(content.toString());

                // Limpiar el área de mensajes al abrir un nuevo archivo
                txtMensajes.setText("");

                reader.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void separarRegExp() {
        String contenido = txtContenido.getText();
        txtMensajes.setText(""); // Limpiar el área de mensajes

        // Expresión regular para identificar identificadores de variables y números
        String regex = "\\b([a-zA-Z_]\\w*|\\d+(\\.\\d+)?)\\b ";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contenido);

        while (matcher.find()) {
            String encontrado = matcher.group();
            txtMensajes.append(encontrado + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProyectoCompi().setVisible(true);
            }
        });
    }
}