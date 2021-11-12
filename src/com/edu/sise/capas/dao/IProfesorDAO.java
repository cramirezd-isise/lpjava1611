/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;

import com.edu.sise.capas.entity.Profesor;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface IProfesorDAO extends GenericDAO<Profesor, Integer>{
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Profesor> obtenerBusqueda(String valor) throws DAOException;
}
