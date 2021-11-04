/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author Carlos
 */
public class Conexion {
    //BD: url conexión, usuario y clave?
    //Driver de conexión?
    
    ResourceBundle rb = ResourceBundle.getBundle("com.edu.sise.capas.dao.cnx");
    
    private static String url="jdbc:mysql://lpjava.cepy5qnydcf5.us-east-1.rds.amazonaws.com:3306/lpjava1611";
    private static String urlSQL="jdbc:sqlserver://localhost:1433;databaseName=demo;user=sa;password=Sise2021$;";
    private static String usuario="";
    private static String clave="";
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String driverSQL="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    Connection conn = null;
    
    static{
        try {
            Class.forName(driver);
            //Class.forName(driverSQL);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error clase Driver: " +  ex);
        }
    }
    
    public Connection getConn(){
        
        try {
            
            usuario = rb.getString("usuario");
            clave =  rb.getString("password");
            
            conn= DriverManager.getConnection(url, usuario, clave);
            //conn= DriverManager.getConnection(urlSQL);
        } catch (SQLException ex) {
            System.out.println("Error de conexión: " + ex);
        }
        return conn;
    }
    
}
