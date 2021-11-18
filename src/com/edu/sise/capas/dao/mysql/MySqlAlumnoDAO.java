/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao.mysql;

import com.edu.sise.capas.dao.DAOException;
import com.edu.sise.capas.dao.IAlumnoDAO;
import com.edu.sise.capas.entity.Alumno;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class MySqlAlumnoDAO implements IAlumnoDAO{
    
    final String GETALL = "{call pa_listar_alumnos()}";
    final String COLUMNAS = "{call pa_obtener_columnas_alumno()}";
    final String INSERT = "{call pa_insertar_alumno(?,?,?,?,?,?,?,?)}";
    final String UPDATE = "{call pa_modificar_alumno(?,?,?,?,?,?,?,?,?)}";
    final String DELETE = "{call pa_eliminar_alumno(?)}";
    final String BUSQUEDA = "{call pa_buscar_alumno(?)}";

    private Connection cn;

    public MySqlAlumnoDAO(Connection cn) {
        this.cn = cn;
    }

    @Override
    public void insertar(Alumno o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(INSERT);
            int i = 1;
            cs.setString(i++,o.getDni());
            cs.setString(i++,o.getNombre());
            cs.setString(i++,o.getPapellido());
            cs.setString(i++,o.getSapellido());
            cs.setDate(i++, Date.valueOf(o.getFnacimiento()));
            cs.setString(i++,o.getTelefono());
            cs.setInt(i++,o.getId_tutor());
            cs.setInt(i++,o.getId_prov());
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
    public void modificar(Alumno o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(UPDATE);
            int i = 1;
            cs.setString(i++,o.getDni());
            cs.setString(i++,o.getNombre());
            cs.setString(i++,o.getPapellido());
            cs.setString(i++,o.getSapellido());
            cs.setDate(i++, Date.valueOf(o.getFnacimiento()));
            cs.setString(i++,o.getTelefono());
            cs.setInt(i++,o.getId_tutor());
            cs.setInt(i++,o.getId_prov());
            cs.setInt(i++,o.getId_alum());
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
    public void eliminar(Alumno o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(DELETE);
            int i = 1;
            cs.setInt(i++,o.getId_alum());
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
    public List<Alumno> obtenerTodos() throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Alumno> lista = new ArrayList<>();
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
    public Alumno obtenerxID(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Alumno getRS(ResultSet rs) throws SQLException{
        return new Alumno(
                rs.getInt("id_alum"),
                rs.getString("dni"),
                rs.getString("nombre"),
                rs.getString("papellido"),
                rs.getString("sapellido"),
                rs.getDate("fnacimiento").toLocalDate(),
                rs.getString("telefono"),
                rs.getInt("id_tutor"),
                rs.getInt("id_prov"),
                rs.getString("des_prov"),
                rs.getString("des_tutor")
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
    public List<Alumno> obtenerBusqueda(String valor) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Alumno> lista = new ArrayList<>();
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
