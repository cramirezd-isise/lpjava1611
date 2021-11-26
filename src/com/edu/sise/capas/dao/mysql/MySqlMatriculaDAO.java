/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao.mysql;

import com.edu.sise.capas.dao.DAOException;
import com.edu.sise.capas.dao.IMatriculaDAO;
import com.edu.sise.capas.entity.Matricula;
import com.edu.sise.capas.utils.Utils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class MySqlMatriculaDAO implements IMatriculaDAO{
    
    final String GETALL = "{call pa_listar_matriculas()}";
    final String COLUMNAS = "{call pa_obtener_columnas_matricula()}";
    final String INSERT = "{call pa_insertar_matricula(?,?,?,?)}";
    final String UPDATE = "{call pa_modificar_matricula(?,?,?,?)}";
    final String DELETE = "{call pa_eliminar_matricula(?,?)}";
    final String BUSQUEDA = "{call pa_buscar_matricula(?)}";
    final String GETALUMNOS = "{call pa_obtener_alumnos(?)}";
    final String ADDNOTAFINAL = "{call pa_ingresar_nota_final(?,?,?)}";

    private Connection cn;

    public MySqlMatriculaDAO(Connection cn) {
        this.cn = cn;
    }

    @Override
    public void insertar(Matricula o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
          
        try{
            //Vamos a trabajar con transacciones!!!
            cn.setAutoCommit(false);
            
            cs = cn.prepareCall(INSERT);
            int i = 1;
            cs.setInt(i++,o.getId_alum());
            cs.setInt(i++,o.getId_asig());
            cs.setDate(i++,Date.valueOf(o.getFecha_matricula()));
            cs.setDouble(i++, o.getNota_final());
            if(cs.executeUpdate()==0)
                throw new DAOException("No se pudo realizar el registro!!!");
             cn.commit();
        } catch (SQLException ex) {
            try {
                cn.rollback();
            } catch (SQLException ex1) {
               throw new DAOException("No se pudo revertir los cambios!!!");
            }

            throw new DAOException(Utils.getMensajeErrorMySql(ex.getErrorCode()) +
                    "\nError en SQL " +
                    "\nCódigo: " + ex.getErrorCode() +
                    "\nMensaje: "+ ex.getMessage(), ex);
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
    public void modificar(Matricula o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(UPDATE);
            int i = 1;
            cs.setDate(i++,Date.valueOf(o.getFecha_matricula()));
            cs.setDouble(i++, o.getNota_final());
            cs.setInt(i++,o.getId_alum());
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
    public void ingresarNotaFinal(Matricula o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(ADDNOTAFINAL);
            int i = 1;
            cs.setDouble(i++, o.getNota_final());
            cs.setInt(i++,o.getId_alum());
            cs.setInt(i++,o.getId_asig());
            if(cs.executeUpdate()==0)
                throw new DAOException("No se pudo realizar el registro de la Nota Final!!!");
               
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
    public void eliminar(Matricula o) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = cn.prepareCall(DELETE);
            int i = 1;
            cs.setInt(i++,o.getId_alum());
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
    public List<Matricula> obtenerTodos() throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Matricula> lista = new ArrayList<>();
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
    public Matricula obtenerxID(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Matricula getRS(ResultSet rs) throws SQLException{
        return new Matricula(
                rs.getInt("id_alum"),
                rs.getInt("id_asig"),
                rs.getDate("fecha_matricula").toLocalDate(),
                rs.getDouble("nota_final"),
                rs.getString("des_alum"),
                rs.getString("des_asig")
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
    public List<Matricula> obtenerBusqueda(String valor) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Matricula> lista = new ArrayList<>();
        try {
            cs = cn.prepareCall(BUSQUEDA);
            int i=1;
            cs.setInt(i++, Integer.parseInt(valor));
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
    public List<Matricula> obtenerAlumnos(Integer id_asig) throws DAOException {
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Matricula> lista = new ArrayList<>();
        try {
            cs = cn.prepareCall(GETALUMNOS);
            int i=1;
            cs.setInt(i++, id_asig);
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
