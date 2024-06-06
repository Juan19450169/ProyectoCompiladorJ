/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocompi;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author aleja
 */
public class TextProcessor {
    private List<Guardadito> guardados; 
    private List<Lexema> lexemas;

    public TextProcessor(List<Lexema> lexemas) {
        this.lexemas = lexemas;
        this.guardados = new ArrayList<Guardadito>(); 
        
        
    }

    public String processText(String text) {
        StringBuilder resultBuilder = new StringBuilder();
        System.out.println(guardados);
        // Expresión regular para identificar palabras, números y caracteres especiales
        Pattern pattern = Pattern.compile("([a-zA-Z]\\w*)|([1-9]\\d*|[0])|:=|==|<>|>=|<=|\\+|\\-|\\*|\\/|=|_|;|,|\\(|\\)|>|<|(\\.)");
        Matcher matcher = pattern.matcher(text);

        resultBuilder.append("Lexema\tIdentificador\n"); // Encabezado de columnas

        while (matcher.find()) {
            String group = matcher.group();
            boolean found = false;
           /* for (Lexema lexema : lexemas) {
                if (lexema.getLexema().equals(group)) {
                   guardados.add(new Guardadito(lexema.getLexema(), lexema.getIdentificador()));
                        
                    resultBuilder.append(lexema.getLexema()).append("\t").append(lexema.getIdentificador()).append("\n");
                    found = true;
                    break;
                }
            }*/
            
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
        System.out.println(guardados);
        
        return resultBuilder.toString();
    }
}
