/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.IProvinciaDAO;
import com.edu.sise.capas.dao.mysql.MySqlDAOManager;
import com.edu.sise.capas.entity.Provincia;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class ProvinciaLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    IProvinciaDAO dao = factory.getProvinciaDAO();
    DefaultTableModel modelo =  null;
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Provincia> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Provincia obj : lista){
            Object data[] = {
              obj.getId_prov(),
              obj.getNombre()
            };
            modelo.addRow(data);
        }
        return modelo;
    }
    
    private DefaultTableModel obtenerTodos() throws Exception{
        return getModelo(modelo, dao.obtenerTodos());
    }
    
    public void imprimirTB(JTable jtable) throws Exception{
        jtable.setModel(obtenerTodos());
    }
}
