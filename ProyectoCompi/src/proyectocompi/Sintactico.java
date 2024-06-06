/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocompi;
import java.util.List;

/**
 *
 * @author aleja
 */
public class Sintactico {
private List<Guardadito> guardadossintacticos;
    private int indice = 0;

    public Sintactico(List<Guardadito> guardadossintacticos) {
        this.guardadossintacticos = guardadossintacticos;
    }

    private int gettoken() {
        Guardadito g = guardadossintacticos.get(indice++);
        return g.getToken();
    }

    private int token;

    public void Programa() {
        token = gettoken();
        if (token != 101) {
            agregarError("Se esperaba 'start'.");
            return;
        }
        token = gettoken();
        A();
        sentencia();
        if (token != 103) {
            agregarError("Se esperaba 'finish'.");
            return;
        } else {
            System.out.println("El código es correcto.");
        }
    }

    public void A() {
        if (token == 102 || token == 1 || token == 106 || token == 107 || token == 108 || token == 111 || token == 114) {
            return;
        }
        Tipo();
        List_id();
        if (token != 401) {
            agregarError("Se esperaba ';' después de la lista de identificadores.");
        }
        token = gettoken();
        A();
    }

    private void Condicion() {
        Expresion();
        nueva1();
    }

    public void nueva1() {
        switch (token) {
            case 301:
            case 302:
            case 303:
            case 304:
            case 305:
            case 306:
                token = gettoken();
                Expresion();
                break;
            default:
                agregarError("Token inesperado en la condición.");
        }
    }

    public void Tipo() {
        if (token == 104 || token == 105) {
            token = gettoken();
        } else {
            agregarError("Se esperaba un tipo de dato.");
        }
    }

    public void Termino() {
        Factor();
        nueva3();
    }

    public void nueva3() {
        if (token == 201 || token == 202 || token == 113 || token == 301 || token == 302 || token == 303 || token == 304 || token == 305 || token == 306 || token == 501 || token == 403 || token == 103 || token == 112 || token == 110 || token == 401 || token == 109) {
            return;
        }
        E();
        Termino();
    }

    public void E() {
        if (token == 203 || token == 204) {
            token = gettoken();
        } else {
            agregarError("Token inesperado en la expresión.");
        }
    }

    public void Expresion() {
        Termino();
        nueva2();
    }

    public void nueva2() {
        if (token == 113 || token == 301 || token == 302 || token == 303 || token == 304 || token == 305 || token == 306 || token == 501 || token == 403 || token == 103 || token == 112 || token == 110 || token == 109) {
            return;
        }
        D();
        Expresion();
    }

    public void D() {
        if (token == 201 || token == 202) {
            token = gettoken();
        } else {
            agregarError("Token inesperado en la expresión.");
        }
    }

    public void List_id() {
        if (token != 1) {
            agregarError("Se esperaba un identificador.");
        }
        token = gettoken();
        nueva4();
    }

    public void nueva4() {
        if (token == 401 || token == 403) {
            return;
        }
        if (token != 501) {
            agregarError("Se esperaba ',' después del identificador.");
        }
        List_id();
    }

    public void List_expre() {
        Expresion();
        nueva5();
    }

    public void nueva5() {
        if (token != 403) {
            return;
        }
        if (token != 501) {
            agregarError("Se esperaba ',' después de la expresión.");
        }
        List_expre();
    }

    public void Factor() {
        switch (token) {
            case 2:
                token = gettoken();
                break;
            case 402:
                token = gettoken();
                Expresion();
                if (token != 403) {
                    agregarError("Se esperaba el paréntesis que cierra.");
                }
                token = gettoken();
                break;
            case 1:
                token = gettoken();
                break;
            default:
                agregarError("Token inesperado en el Factor.");
        }
    }

    public void B() {
        sentencia();
        nueva6();
    }

    public void nueva6() {
        if (token != 112) {
            return;
        }
        if (token != 401) {
            agregarError("Se esperaba ';' después de la sentencia.");
        }
        B();
    }

    public void C() {
        if (token == 103 || token == 401 || token == 112 || token == 110) {
            return;
        }
        if (token != 110) {
            agregarError("Se esperaba 'else' después de la sentencia.");
        }
        sentencia();
    }

    public void sentencia() {
        switch (token) {
            case 1:
                if (guardadossintacticos.get(indice).getToken() == 205) {
                    indice++;
                    Expresion();
                } else {
                    agregarError("Se esperaba \":=\" después del identificador.");
                }
                break;
            case 106:
                token = gettoken();
                if (token == 402) {
                    token = gettoken();
                    List_id();
                    if (token == 403) {
                        token = gettoken();
                    } else {
                        agregarError("Se esperaba \")\" después de la lista de identificadores.");
                    }
                } else {
                    agregarError("Se esperaba \"(\" después de \"input\".");
                }
                break;
            case 107:
                token = gettoken();
                if (token == 402) {
                    token = gettoken();
                    List_expre();
                    if (token == 403) {
                        token = gettoken();
                    } else {
                        agregarError("Se esperaba \")\" después de la lista de expresiones.");
                    }
                } else {
                    agregarError("Se esperaba \"(\" después de \"output\".");
                }
                break;
            case 108:
                Condicion();
                if (token == 109) {
                    token = gettoken();
                    sentencia();
                    C();
                } else {
                    agregarError("Se esperaba \"then\" después de la condición.");
                }
                break;
            case 111:
                Condicion();
                if (token == 113) {
                    token = gettoken();
                    sentencia();
                } else {
                    agregarError("Se esperaba \"do\" después de la condición.");
                }
                break;
            case 114:
                token = gettoken();
                Expresion();
                if (token == 113) {
                    token = gettoken();
                    sentencia();
                } else {
                    agregarError("Se esperaba \"do\" después de la expresión.");
                }
                break;
            default:
                agregarError("Token inesperado en la sentencia.");
        }
    }

    private void agregarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }

    String getErrors() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
