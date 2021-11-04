/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao.mysql;

import com.edu.sise.capas.dao.Conexion;
import com.edu.sise.capas.dao.ICarreraDAO;
import com.edu.sise.capas.dao.IDAOManager;
import com.edu.sise.capas.dao.IEmpleadoDAO;
import com.edu.sise.capas.dao.IProvinciaDAO;
import java.sql.Connection;

/**
 *
 * @author Carlos
 */
public class MySqlDAOManager implements IDAOManager{
    //Singleton - parte 1
    private static final MySqlDAOManager instancia = new MySqlDAOManager();
    private Connection cn;
    
    private MySqlDAOManager(){
        cn = new Conexion().getConn();
    }
    
    //Singleton - parte 2
    public static MySqlDAOManager getInstancia(){
        return instancia;
    }
    
    //Factory
    private IEmpleadoDAO empleadoDao = null;
    private IProvinciaDAO provinciaDao = null;
    private ICarreraDAO carreraDao = null;
    
    @Override
    public IEmpleadoDAO getEmpleadoDAO() {
        if(empleadoDao==null){
            empleadoDao = new MySqlEmpleadoDAO(cn);
        }
        return empleadoDao;
    }

    @Override
    public IProvinciaDAO getProvinciaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ICarreraDAO getCarreraDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
