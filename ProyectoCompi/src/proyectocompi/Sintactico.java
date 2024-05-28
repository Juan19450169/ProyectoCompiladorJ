/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocompi;

/**
 *
 * @author aleja
 */
public class Sintactico {

    private int token;

    //<Programa>→start<A><sentencia>finish
    //<Programa>→101<A><sentencia>103
    public void Programa() {
        token = gettoken();
        if (token != 101) {
            //Lanzar un error
        }
        token = gettoken();
        A();
        sentencia();
        if (token != 103) {
            //Lanzar un error por que se esperaba finish en este caso

        } else {
            System.out.println("Todo estuvo bien gracias a Dios");
        }
    }
    //<A>→<Tipo><List_id>;<A>
    //<A>→<Tipo><List_id>401<A>
    //<A>→ε
    //Follow(A)={begin,identificador,input,output,if,loop,repeat}
    //Follow(A)={102,1,106,107,108,111,114}

    public void A() {
        if (token == 102 || token == 1 || token == 106 || token == 107 || token == 108 || token == 111 || token == 114) {
            return;
        }
        Tipo();
        List_id();
        if (token != 401) {
            //mandar mensaje de error
        }
        token = gettoken();
        A();

    }

    //<Condicion>→<Expresión><nueva1>
    private void Condicion() {
        Expresion();
        nueva1();
    }
//<nueva1>→==<Expresión>
//<nueva1>→<><Expresión>
//<nueva1>→><Expresión>
//<nueva1>→>=<Expresión>
//<nueva1>→<<Expresión>
//<nueva1>→<=<Expresión>

    //Vamos a cambiar por su respectivo token correspondiente
////////////////////////////////////////////////////////////
//<nueva1>→301<Expresión>
//<nueva1>→302<Expresión>
//<nueva1>→303<Expresión>
//<nueva1>→304<Expresión>
//<nueva1>→305<Expresión>
//<nueva1>→306<Expresión>
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
                //mandar un mensaje de error debido a que ningun token coincidio por eso el msj de error
                
        }
    }
    //////////////////////////////////////////////////////////////////////////
    //A qui empiezo yo solo
    ///////////////////////////////////////////////////////////////////////////
    //<Tipo>→int
    //<Tipo>→str
    //<Tipo>→104
    //<Tipo>→105
    
    //////////////////////////////////////////////////////////////////////////
    //<Termino>→<Factor><nueva3>
    public void Termino(){
        Factor();
        nueva3();
    }
    
    //////////////////////////////////////////////////////////////////////////
   // <nueva3>→ε
  //<nueva3>→<E><Termino>
   //Follow(nueva3)={+,-,do,==,<>,>,>=,<,<=,,,),finish,end,else,;,then}
   //Follow(nueva3)={201,202,113,301,302,303,304,305,306,501,403,103,112,110,401,109}
    
    public  void nueva3(){
        if(token == 201 || token == 202 || token == 113 || token == 301 || token == 302 || token == 303 || token == 304 || token == 305 || token == 306 || token== 501 || token == 403 || token == 103 || token == 112 || token == 110 || token == 401 || token == 109 ){
            return;
        }
          E();
          Termino();
    }
  
}
