/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.ICarreraDAO;
import com.edu.sise.capas.dao.mysql.MySqlDAOManager;
import com.edu.sise.capas.entity.Carrera;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class CarreraLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    ICarreraDAO dao = factory.getCarreraDAO();
    DefaultTableModel modelo =  null;
    DefaultComboBoxModel modeloCB = null;
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Carrera> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Carrera obj : lista){
            Object data[] = {
              obj.getId_carrera(),
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
    
    public void insertar(Carrera o) throws Exception {
        dao.insertar(o);
    }
    
    public void modificar(Carrera o) throws Exception {
        dao.modificar(o);
    }
    
    public void eliminar(Carrera o) throws Exception {
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
    
    private DefaultComboBoxModel getModeloCB(List<Carrera> lista) {
        modeloCB  = new DefaultComboBoxModel();
        for(Carrera o: lista){
            modeloCB.addElement(o);
        }
        
        return modeloCB;
    }
    
    public void buscarCB(JComboBox jComboBox, int id_carrera){
            DefaultComboBoxModel modeloCarrerasCB = (DefaultComboBoxModel)jComboBox.getModel();
            Carrera objCarrera =  null;
            for(int i=0;i<modeloCarrerasCB.getSize();i++){
                objCarrera = (Carrera)modeloCarrerasCB.getElementAt(i);
                
                if(objCarrera.getId_carrera()==id_carrera){
                    modeloCarrerasCB.setSelectedItem(modeloCarrerasCB.getElementAt(i));
                    break;
                }
            }
    }
}
