/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.IProfesorDAO;
import com.edu.sise.capas.dao.mysql.MySqlDAOManager;
import com.edu.sise.capas.entity.Profesor;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class ProfesorLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    IProfesorDAO dao = factory.getProfesorDAO();
    DefaultTableModel modelo =  null;
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Profesor> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Profesor obj : lista){
            Object data[] = {
              obj.getId_profe(),
              obj.getDni(),
              obj.getNombre(),
              obj.getPapellido(),
              obj.getSapellido(),
              obj.getFnacimiento(),
              obj.getTelefono(),
              obj.getId_prov(),
              obj.getId_carrera()
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
    
    public void insertar(Profesor o) throws Exception {
        dao.insertar(o);
    }
    
    public void modificar(Profesor o) throws Exception {
        dao.modificar(o);
    }
    
    public void eliminar(Profesor o) throws Exception {
        dao.eliminar(o);
    }
    
    public DefaultTableModel obtenerBusqueda(String valor) throws Exception{
        return getModelo(modelo, dao.obtenerBusqueda(valor));
    }
    
    public void imprimirTB(JTable jtable, DefaultTableModel modelo) throws Exception{
        jtable.setModel(modelo);
    }
}
