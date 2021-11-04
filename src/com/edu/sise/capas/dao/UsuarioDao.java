/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;

import com.edu.sise.capas.entity.Usuario;
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
public class UsuarioDao {
    
    Connection cn = null;
    Statement st = null;
    ResultSet rs =  null;
    
    public Usuario obtenerUsuarioxID(int id){
        cn = new Conexion().getConn();
        String sql = "select * from usuarios where id_usuario ="+id;
        Usuario objUsuario = null;
        try {
             st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next()){
                objUsuario = new Usuario();
                objUsuario.setId_usuario(rs.getInt("id_usuario"));
                objUsuario.setNombre(rs.getString("nombre"));
                objUsuario.setClave(rs.getString("clave"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en Statement: " + ex);
        }finally{
            try {
                if(rs!=null) rs.close();
                if(st!=null) st.close(); 
                if(cn!=null) cn.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex);
            }
        }
        
        return objUsuario;
    }
    
    public Usuario validarAcceso(Usuario objUsuario){
        cn = new Conexion().getConn();
        String sql = "select * from usuarios "
                + " where nombre='"+objUsuario.getNombre() 
                +"' and clave='"+ objUsuario.getClave()+"'";
        
        Usuario rptaUsuario = null;
        
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                rptaUsuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("clave")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error en Statement: " + ex);
        }finally{
            try {
                if(rs!=null) rs.close();
                if(st!=null) st.close(); 
                if(cn!=null) cn.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex);
            }
        }
        return rptaUsuario;
    }
    
    public List obtenerTodos(){
        cn = new Conexion().getConn();
        String sql = "select * from usuarios";
        
        List listaUsuarios = new ArrayList<Usuario>();
        Usuario objUsuario = null;
        
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                objUsuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("clave")
                );
                listaUsuarios.add(objUsuario);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en Statement: " + ex);
        }finally{
            try {
                if(rs!=null) rs.close();
                if(st!=null) st.close(); 
                if(cn!=null) cn.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex);
            }
        }
        
        return listaUsuarios;
    }

    public boolean agregarUsuario(Usuario objUsuario){
        cn = new Conexion().getConn();
        String sql = "insert into usuarios(nombre, clave) "
                + " values ('"+objUsuario.getNombre()+"','"+objUsuario.getClave()+"')";
        int rpta = -1;
        try {
            st = cn.createStatement();
            rpta = st.executeUpdate(sql);  
        } catch (SQLException ex) {
            System.out.println("Error en Statement: " + ex);
        }finally{
            try {
                if(rs!=null) rs.close();
                if(st!=null) st.close(); 
                if(cn!=null) cn.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex);
            }
        }
        return (rpta>0);
    }
    
    public boolean modificarUsuario(Usuario objUsuario){
        cn = new Conexion().getConn();
        String sql ="UPDATE usuarios "
                + " SET nombre = '"+objUsuario.getNombre()+"', clave = '"+objUsuario.getClave()+"' "
                + " WHERE id_usuario = " + objUsuario.getId_usuario();
        System.out.println("SQL: " + sql);
        int rpta = -1;
        try {
            st = cn.createStatement();
            rpta = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error en Statement: " + ex);
        }finally{
            try {
                if(rs!=null) rs.close();
                if(st!=null) st.close(); 
                if(cn!=null) cn.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex);
            }
        }
        
        return (rpta>0);
    }
    
    public boolean eliminarUsuario(int id){
        cn = new Conexion().getConn();
        String sql = "delete from usuarios where id_usuario = " + id;
        int rpta = -1;
        try {
            st = cn.createStatement();
            rpta = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error en Statement: " + ex);
        }finally{
            try {
                if(rs!=null) rs.close();
                if(st!=null) st.close(); 
                if(cn!=null) cn.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex);
            }
        }
        return (rpta>0);
    }
    
    public List buscarxNombre(String nombre){
        cn = new Conexion().getConn();
        String sql = "select * from usuarios where nombre like '"+nombre+"%'";
        
        List listaUsuarios = new ArrayList<Usuario>();
        Usuario objUsuario = null;
        
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()){
                objUsuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("clave")
                );
                listaUsuarios.add(objUsuario);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en Statement: " + ex);
        }finally{
            try {
                if(rs!=null) rs.close();
                if(st!=null) st.close(); 
                if(cn!=null) cn.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex);
            }
        }
        
        return listaUsuarios;
    }
}
