/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao.mysql;

import com.edu.sise.capas.dao.Conexion;
import com.edu.sise.capas.dao.IAlumnoDAO;
import com.edu.sise.capas.dao.ICarreraDAO;
import com.edu.sise.capas.dao.IDAOManager;
import com.edu.sise.capas.dao.IDepartamentoDAO;
import com.edu.sise.capas.dao.IEmpleadoDAO;
import com.edu.sise.capas.dao.IProfesorDAO;
import com.edu.sise.capas.dao.IProvinciaDAO;
import com.edu.sise.capas.dao.ITutorDAO;
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
    private IDepartamentoDAO departamentoDao = null;
    private IProfesorDAO profesorDao = null;
    private ITutorDAO tutorDao = null;
    private IAlumnoDAO alumnoDao = null;
    
    @Override
    public IEmpleadoDAO getEmpleadoDAO() {
        if(empleadoDao==null){
            empleadoDao = new MySqlEmpleadoDAO(cn);
        }
        return empleadoDao;
    }

    @Override
    public IProvinciaDAO getProvinciaDAO() {
        if(provinciaDao==null)
            provinciaDao = new MySqlProvinciaDAO(cn);
        return provinciaDao;
    }

    @Override
    public ICarreraDAO getCarreraDAO() {
        if(carreraDao==null)
            carreraDao = new MySqlCarreraDAO(cn);
        return carreraDao;
    }

    @Override
    public IDepartamentoDAO getDepartamentoDAO() {
        if(departamentoDao==null)
            departamentoDao = new MySqlDepartamentoDAO(cn);
        return departamentoDao;
    }

    @Override
    public IProfesorDAO getProfesorDAO() {
        if(profesorDao==null)
            profesorDao = new MySqlProfesorDAO(cn);
        return profesorDao;
    }

    @Override
    public ITutorDAO getTutorDAO() {
        if(tutorDao==null)
            tutorDao = new MySqlTutorDAO(cn);
        return tutorDao;
    }

    @Override
    public IAlumnoDAO getAlumnoDAO() {
        if(alumnoDao==null)
            alumnoDao = new MySqlAlumnoDAO(cn);
        return alumnoDao;
    }
    
}
