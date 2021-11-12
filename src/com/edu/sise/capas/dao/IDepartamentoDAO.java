/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;

import com.edu.sise.capas.entity.Departamento;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface IDepartamentoDAO extends GenericDAO<Departamento, Integer>{
    List<String> obtenerNombresColumnas() throws DAOException;
    List<Departamento> obtenerBusqueda(String valor) throws DAOException;
}
