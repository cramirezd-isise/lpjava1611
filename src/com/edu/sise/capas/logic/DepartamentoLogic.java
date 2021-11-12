/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.IDepartamentoDAO;
import com.edu.sise.capas.dao.mysql.MySqlDAOManager;
import com.edu.sise.capas.entity.Departamento;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class DepartamentoLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    IDepartamentoDAO dao = factory.getDepartamentoDAO();
    DefaultTableModel modelo =  null;
    DefaultComboBoxModel modeloCB =  null;
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Departamento> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Departamento obj : lista){
            Object data[] = {
              obj.getId_depa(),
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
    
    public void insertar(Departamento o) throws Exception {
        dao.insertar(o);
    }
    
    public void modificar(Departamento o) throws Exception {
        dao.modificar(o);
    }
    
    public void eliminar(Departamento o) throws Exception {
        dao.eliminar(o);
    }
    
    public DefaultTableModel obtenerBusqueda(String valor) throws Exception{
        return getModelo(modelo, dao.obtenerBusqueda(valor));
    }
    
    public void imprimirTB(JTable jtable, DefaultTableModel modelo) throws Exception{
        jtable.setModel(modelo);
    }
    
    public void imprimirCB(JComboBox jComboBox)throws Exception{
        jComboBox.setModel(getModeloCB(dao.obtenerTodos()));
    }
    
    private DefaultComboBoxModel getModeloCB(List<Departamento> lista) {
        modeloCB  = new DefaultComboBoxModel();
        for(Departamento o: lista){
            modeloCB.addElement(o);
        }
        
        return modeloCB;
    }
    
}
