/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.ClienteDao;
import com.edu.sise.capas.entity.Cliente;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class ClienteLogic {
    
    ClienteDao dao =  null;
    
    public Cliente obtenerClientexID(int id){
        Cliente objCliente = null;
        dao = new ClienteDao();
        if(id>=0){
            objCliente = dao.obtenerClientexID(id);
        }
        return objCliente;
    }
    
    public Cliente validarAcceso(Cliente objCliente){
        dao = new ClienteDao();
        return dao.validarAcceso(objCliente);
    }
    
    public DefaultTableModel obtenerTodos(){
        dao = new ClienteDao();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CLAVE");
        
        List<Cliente> listaCliente = dao.obtenerTodos();
        Cliente objCliente =  null;
        
        for(int i=0; i<listaCliente.size();i++){
            objCliente = listaCliente.get(i);
            
            Object data[] = {
                objCliente.getId_cliente(),
                objCliente.getNombre(),
                objCliente.getClave()
            };
            
            modelo.addRow(data);
        }
        
        return modelo;
    }
    
    public void imprimirTB(JTable jTable){
        jTable.setModel(obtenerTodos());
    }
    
}
