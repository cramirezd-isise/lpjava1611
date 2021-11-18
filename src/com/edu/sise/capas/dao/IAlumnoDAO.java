/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;

import com.edu.sise.capas.entity.Alumno;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface IAlumnoDAO extends GenericDAO<Alumno, Integer>{
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Alumno> obtenerBusqueda(String valor) throws DAOException;
}
