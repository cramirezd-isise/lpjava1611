/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.utils;

import java.util.ResourceBundle;

/**
 *
 * @author Carlos
 */
public class Utils {
    static ResourceBundle rb = ResourceBundle.getBundle("com.edu.sise.capas.utils.mensajes_utils");
    static public int NO_VACIO = 1;
    static public int NO_LETRAS = 2;
    
    static public String getMensaje(String nombreCampo, int tipo){
        
        String mensaje = "";
        switch(tipo){
            case 1: mensaje = rb.getString("no_vacio"); break;
            case 2: mensaje = rb.getString("no_letras"); break;
        }
        
        return nombreCampo + " " + mensaje;
    }
    static public String getMensajeErrorMySql(int codeError){
        String mensajeError = "";
        switch(codeError){
            case 1062: mensajeError = "Ya se encuentra registrado!!!";
        }
       return mensajeError;
    }
}
