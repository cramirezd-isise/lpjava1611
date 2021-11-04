/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;

import com.edu.sise.capas.entity.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class ClienteDao {
    
    Connection cn = null;
    
    public Cliente obtenerClientexID(int id){
        cn = new Conexion().getConn();
        String sql = "select * from clientes where id_cliente ="+id;
        Cliente objCliente = null;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                objCliente = new Cliente();
                objCliente.setId_cliente(rs.getInt("id_cliente"));
                objCliente.setNombre(rs.getString("nombre"));
                objCliente.setClave(rs.getString("clave"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en Statement: " + ex);
        }finally{
            if(cn!=null){
                try {
                    cn.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar conexión: " + ex);
                }
            }
        }
        
        return objCliente;
    }
    
    public Cliente validarAcceso(Cliente objCliente){
        cn = new Conexion().getConn();
        String sql = "select * from clientes "
                + " where nombre='"+objCliente.getNombre() 
                +"' and clave='"+ objCliente.getClave()+"'";
        
        Cliente rptaCliente = null;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                rptaCliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("clave")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error en Statement: " + ex);
        }finally{
            if(cn!=null){
                try {
                    cn.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar conexión: " + ex);
                }
            }
        }
        return rptaCliente;
    }
    
    public List obtenerTodos(){
        cn = new Conexion().getConn();
        String sql = "select * from clientes";
        
        List listaClientes = new ArrayList<Cliente>();
        Cliente objCliente = null;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                objCliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("clave")
                );
                listaClientes.add(objCliente);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en Statement: " + ex);
        }finally{
            if(cn!=null){
                try {
                    cn.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar conexión: " + ex);
                }
            }
        }
        
        return listaClientes;
    }
}
