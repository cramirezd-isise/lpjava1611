/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;

import java.util.List;

/**
 *
 * @author Carlos
 */
public interface GenericDAO<T,K> {
    
    void insertar(T o) throws DAOException;
    void modificar(T o) throws DAOException;
    void eliminar(T o) throws DAOException;
    List<T> obtenerTodos() throws DAOException;
    T obtenerxID(K id) throws DAOException;
    
}
