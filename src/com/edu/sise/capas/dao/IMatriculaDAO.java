/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;

import com.edu.sise.capas.entity.Matricula;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface IMatriculaDAO extends GenericDAO<Matricula, Integer>{
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Matricula> obtenerBusqueda(String valor) throws DAOException;
    List<Matricula> obtenerAlumnos(Integer id_asig) throws DAOException;
    void ingresarNotaFinal(Matricula o) throws DAOException;
}
