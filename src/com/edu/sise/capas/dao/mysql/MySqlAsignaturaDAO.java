/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao.mysql;

import com.edu.sise.capas.dao.DAOException;
import com.edu.sise.capas.dao.IAsignaturaDAO;
import com.edu.sise.capas.entity.Asignatura;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class MySqlAsignaturaDAO implements IAsignaturaDAO{
    
    final String GETALL = "{call pa_listar_asignaturas()}";
    final String COLUMNAS = "{call pa_obtener_columnas_asignatura()}";
    final String INSERT = "{call pa_insertar_asignatura(?,?,?,?)}";
    final String UPDATE = "{call pa_modificar_asignatura(?,?,?,?,?)}";
    final String DELETE = "{call pa_eliminar_asignatura(?)}";
    final String BUSQUEDA = "{call pa_buscar_asignatura(?)}";
    final String GETBYID = "{call pa_obtener_asignatura_by_id(?)}";

    private Connection cn;

    public MySqlAsignaturaDAO(Connection cn) {
        this.cn = cn;
    }

    @Override
    public void insertar(Asignatura o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(INSERT);
            int i = 1;
            cs.setString(i++,o.getNombre());
            cs.setInt(i++,o.getObligatoriedad());
            cs.setInt(i++,o.getNum_creditos());
            cs.setInt(i++,o.getId_profe());

            if(cs.executeUpdate()==0)
                throw new DAOException("No se pudo realizar el registro!!!");
               
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public void modificar(Asignatura o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(UPDATE);
            int i = 1;
            cs.setString(i++,o.getNombre());
            cs.setInt(i++,o.getObligatoriedad());
            cs.setInt(i++,o.getNum_creditos());
            cs.setInt(i++,o.getId_profe());
            cs.setInt(i++,o.getId_asig());
            if(cs.executeUpdate()==0)
                throw new DAOException("No se pudo realizar la modificación del registro!!!");
               
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public void eliminar(Asignatura o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(DELETE);
            int i = 1;
            cs.setInt(i++,o.getId_asig());
            if(cs.executeUpdate()==0)
                throw new DAOException("No se pudo realizar la eliminación del registro!!!");
               
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
    }

    @Override
    public List<Asignatura> obtenerTodos() throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Asignatura> lista = new ArrayList<>();
        try {
            cs = cn.prepareCall(GETALL);
            rs = cs.executeQuery();
            while(rs.next()){
                lista.add(getRS(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
        return lista;
    }

    @Override
    public Asignatura obtenerxID(Integer id) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        Asignatura obj =  null;
        try {
            cs = cn.prepareCall(GETALL);
            rs = cs.executeQuery();
            while(rs.next()){
                obj =getRS(rs);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
        return obj;
    }
    
    private Asignatura getRS(ResultSet rs) throws SQLException{
        return new Asignatura(
                rs.getInt("id_asig"),
                rs.getString("nombre"),
                rs.getInt("obligatoriedad"),
                rs.getInt("num_creditos"),
                rs.getInt("id_profe"),
                rs.getString("des_profe")
            );
    }

    @Override
    public List<String> obtenerNombresColumnas() throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        List<String> listaNomColumn = new ArrayList<>();
        
        try {
            cs = cn.prepareCall(COLUMNAS);
            rs = cs.executeQuery();
            rsmd = rs.getMetaData();
            for(int i=1; i<=rsmd.getColumnCount();i++)
                listaNomColumn.add(rsmd.getColumnName(i));
            
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
        return listaNomColumn;
    }

    @Override
    public List<Asignatura> obtenerBusqueda(String valor) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Asignatura> lista = new ArrayList<>();
        try {
            cs = cn.prepareCall(BUSQUEDA);
            int i=1;
            cs.setString(i++, valor);
            rs = cs.executeQuery();
            while(rs.next()){
                lista.add(getRS(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(cs!=null) cs.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
        return lista;
    }
    
}
