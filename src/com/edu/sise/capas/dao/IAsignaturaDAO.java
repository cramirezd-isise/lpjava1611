/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;

import com.edu.sise.capas.entity.Asignatura;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface IAsignaturaDAO extends GenericDAO<Asignatura, Integer>{
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Asignatura> obtenerBusqueda(String valor) throws DAOException;
}
