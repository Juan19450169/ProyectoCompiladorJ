/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocompi;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author aleja
 */
public class TextProcessor {
    private List<Lexema> lexemas;

    public TextProcessor(List<Lexema> lexemas) {
        this.lexemas = lexemas;
    }

    public String processText(String text) {
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
                    resultBuilder.append(lexema.getLexema()).append("\t").append(lexema.getIdentificador()).append("\n");
                    found = true;
                    break;
                }
            }
            if (!found) {
                if (group.matches("\\d+")) {
                    resultBuilder.append(group).append("\t2\n");
                } else {
                    resultBuilder.append(group).append("\t1\n");
                }
            }
        }

        return resultBuilder.toString();
    }
}
