/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.dao;

/**
 *
 * @author Carlos
 */
public interface IDAOManager {
    IEmpleadoDAO getEmpleadoDAO();
    IProvinciaDAO getProvinciaDAO();
    ICarreraDAO getCarreraDAO();
}
