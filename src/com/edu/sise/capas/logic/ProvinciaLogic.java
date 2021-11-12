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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
    DefaultComboBoxModel modeloCB = null;
    
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
              obj.getNombre(),
              obj.getId_depa()
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
    
    public void insertar(Provincia o) throws Exception {
        dao.insertar(o);
    }
    
    public void modificar(Provincia o) throws Exception {
        dao.modificar(o);
    }
    
    public void eliminar(Provincia o) throws Exception {
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
    
    private DefaultComboBoxModel getModeloCB(List<Provincia> lista) {
        modeloCB  = new DefaultComboBoxModel();
        for(Provincia o: lista){
            modeloCB.addElement(o);
        }
        
        return modeloCB;
    }
    
    public void buscarCB(JComboBox jComboBox, int id_prov){
            DefaultComboBoxModel modeloCarrerasCB = (DefaultComboBoxModel)jComboBox.getModel();
            Provincia obj =  null;
            for(int i=0;i<modeloCarrerasCB.getSize();i++){
                obj = (Provincia)modeloCarrerasCB.getElementAt(i);
                
                if(obj.getId_prov()==id_prov){
                    modeloCarrerasCB.setSelectedItem(modeloCarrerasCB.getElementAt(i));
                    break;
                }
            }
    }

}
