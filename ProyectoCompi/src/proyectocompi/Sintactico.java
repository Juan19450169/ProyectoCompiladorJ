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
         private int indice =0;
///////////////////////////////////////////////////////////////////7
    public Sintactico(List<Guardadito> guardadossintacticos) {
        this.guardadossintacticos = guardadossintacticos;
    }
   ///////////////////////////////////////////////////////////////////
    private int gettoken(){
        Guardadito g= guardadossintacticos.get(indice++);
        return g.getToken();
    }

     private int token;

    //<Programa>→start<A><sentencia>finish
    //<Programa>→101<A><sentencia>103
    public void Programa() {
        token = gettoken();
        if (token != 101) {
            System.out.println("Error");
        }
        token = gettoken();
        A();
        sentencia();
        if (token != 103) {
             System.out.println(" se esperaba finish en este caso");
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
            System.out.println("Error tontuelo");
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
                System.out.println("Error tontuelo ningun token coincidio");

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
    public void Tipo(){
        if (token == 104 || token == 105){
           token = gettoken(); 
        }else{
            //mensaje de error
            System.out.println("Error tontuelo");

        }
        
    }
    
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
    
    //<E>→*
    //<E>→/
    //<E>→203
    //<E>→204
    
    public void E(){
        if (token == 203 || token == 204){
           token = gettoken(); 
        }else{
            //mensaje de error 
            System.out.println("Error tontuelo");

        }
        
    }
    //<Expresión>→<Termino><nueva2>
  
    public void Expresion(){
        Termino();
        nueva2();
    }
    
      //<nueva2>→ε
    //<nueva2>→<D><Expresión>
    //Follow(nueva2)={do,==,<>,>,>=,<,<=,,,),finish,end,else,then}
    //Follow(nueva2)={113,301,302,303,304,305,306,501,403,103,112,110,109}

    public void nueva2(){
        if (token == 113 || token == 301 || token == 302 || token == 303 || token == 304 || token == 305 || token == 306 || token== 501 || token == 403 || token == 103 || token == 112 || token == 110 || token == 109){
            return;
        }
        D();
        Expresion();
    }
    
    ////////////////////////////////////
    //<D>→+
    //<D>→-
    //<D>→201
    //<D>→202
    public void D(){
        if (token == 201 || token == 202){
           token = gettoken(); 
        }else{
            System.out.println("Error tontuelo");
        }
    }
    //////////////////////////////////////////////////////
    //<List_id>→identificador<nueva4>
    //<List_id>→1<nueva4>

    //////////////////////////////////////////////////////
    public void List_id(){
         if (token != 1) {
            //mandar mensaje de error
            System.out.println("Error tontuelo");
        }
        token = gettoken();
        nueva4();
    }
    /////////////////////////////////////////////////////////
    //<nueva4>→ε
    //<nueva4>→,<List_id>
    //<nueva4>→501<List_id>

    //Follow(nueva4)={;,)}
     //Follow(nueva4)={401,403}

    public void nueva4(){
        if(token==401 || token == 403){
            return;
        }
        if (token != 501) {
            //mandar mensaje de error
            System.out.println("Error tontuelo");
        }
        List_id();
        
    }
    
    
    //////////////////////////////////////////////////////
   // <List_expre>→<Expresión><nueva5>
    public void List_expre(){
        Expresion();
        nueva5();
    }
    
    ///////////////////////////////////////////////////////
    //<nueva5>→ε
    //<nueva5>→,<List_expre>
    //Follow(nueva5)={)}
    public void nueva5(){
        if (token != 403){
            return;
        }
        if (token != 501) {
            //mandar mensaje de error
            System.out.println("Error tontuelo");
        }
        List_expre();
    }
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    //////////////////////////////////////////////////
    /////////////////////////////////////////////////
    ///////////////////////////////////////////////
    /////////////////////////////////////////////
    ////////////////////////////////////////////
    //
    //<Factor>→numero
    //<Factor>→(<Expresión>)
    //<Factor>→identificador
    public void Factor(){
        switch (token) {
            case 2:
            case 402:
            token = gettoken();
            Expresion();
            if (token != 403){
                System.out.println("Se esperaba el parentesis que cierra");
            }
            case 1:
            
        }
    }
    //<B>→<sentencia><nueva6>

    public void B(){
        sentencia();
        nueva6();
    }
    ///////////////////////////////////////////////////////
   // <nueva6>→ε
    //<nueva6>→;<B>
    //<nueva6>→401<B>
    //Follow(nueva6)={end}
    //Follow(nueva6)={112}
    public void nueva6(){
         if (token != 112){
            return;
        }
         if (token != 401) {
            //mandar mensaje de error
            System.out.println("Error tontuelo");
        }
         B();
    }
    /////////////////////////////////////////////////////
    //<C>→else<sentencia>
     //<C>→110<sentencia>
    //<C>→ε
    //Follow(C)={finish,;,end,else}
    //Follow(C)={103,401,112,110}
    public void C(){
        if (token == 103 || token == 401 || token == 112 || token == 110){
           return; 
        }
        if (token != 110) {
            //mandar mensaje de error
            System.out.println("Error hijo");
        }
        sentencia();
    }
    
    //////////////////////////////////////////////////////////
 //<sentencia>→begin<B>end
//<sentencia>→identificador:=<Expresión>
//<sentencia>→input(<List_id>)
//<sentencia>→output(<List_expre>)
//<sentencia>→if<Condicion>then<sentencia><C>
//<sentencia>→loop<Condicion>do<sentencia>
//<sentencia>→repeat<Expresión>do<sentencia>
    
    
    public void sentencia(){
        
    }
    
    
    /////////////////////////////////////////////////////////7

    
    ////////////////////////////////////////////////////////
    
}