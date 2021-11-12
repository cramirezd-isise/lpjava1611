/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao.mysql;

import com.edu.sise.capas.dao.DAOException;
import com.edu.sise.capas.dao.IProfesorDAO;
import com.edu.sise.capas.entity.Profesor;
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
public class MySqlProfesorDAO implements IProfesorDAO{
    
    final String GETALL = "{call pa_listar_profesores()}";
    final String COLUMNAS = "SELECT * FROM Profesores LIMIT 0";
    final String INSERT = "{call pa_insertar_profesor(?,?,?,?,?,?,?,?)}";
    final String UPDATE = "{call pa_modificar_profesor(?,?,?,?,?,?,?,?,?)}";
    final String DELETE = "{call pa_eliminar_profesor(?)}";
    final String BUSQUEDA = "{call pa_buscar_profesor(?)}";

    private Connection cn;

    public MySqlProfesorDAO(Connection cn) {
        this.cn = cn;
    }

    @Override
    public void insertar(Profesor o) throws DAOException {
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
            cs.setInt(i++,o.getId_prov());
            cs.setInt(i++,o.getId_carrera());
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
    public void modificar(Profesor o) throws DAOException {
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
            cs.setInt(i++,o.getId_prov());
            cs.setInt(i++,o.getId_carrera());
            cs.setInt(i++,o.getId_profe());
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
    public void eliminar(Profesor o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(DELETE);
            int i = 1;
            cs.setInt(i++,o.getId_profe());
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
    public List<Profesor> obtenerTodos() throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Profesor> lista = new ArrayList<>();
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
    public Profesor obtenerxID(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Profesor getRS(ResultSet rs) throws SQLException{
        return new Profesor(
                rs.getInt("id_profe"),
                rs.getString("dni"),
                rs.getString("nombre"),
                rs.getString("papellido"),
                rs.getString("sapellido"),
                rs.getDate("fnacimiento").toLocalDate(),
                rs.getString("telefono"),
                rs.getInt("id_prov"),
                rs.getInt("id_carrera")
            );
    }

    @Override
    public List<String> obtenerNombresColumnas() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        List<String> listaNomColumn = new ArrayList<>();
        
        try {
            ps = cn.prepareStatement(COLUMNAS);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            for(int i=1; i<=rsmd.getColumnCount();i++)
                listaNomColumn.add(rsmd.getColumnName(i));
            
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally{
            try{
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
            }catch(SQLException ex){
                throw new DAOException("Error en SQL", ex);
            }
        }
        return listaNomColumn;
    }

    @Override
    public List<Profesor> obtenerBusqueda(String valor) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Profesor> lista = new ArrayList<>();
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
