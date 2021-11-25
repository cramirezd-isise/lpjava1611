/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.IProfesorDAO;
import com.edu.sise.capas.dao.mysql.MySqlDAOManager;
import com.edu.sise.capas.entity.Profesor;
import com.edu.sise.capas.entity.ProfesorForm;
import com.edu.sise.capas.utils.Utils;
import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Carlos
 */
public class ProfesorLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    IProfesorDAO dao = factory.getProfesorDAO();
    DefaultTableModel modelo =  null;
    DefaultComboBoxModel modeloCB = null;
    
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
              obj.getId_carrera(),
              obj.getDes_prov(),
              obj.getDes_carrera()
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
    
    public void generarReporte() throws Exception{
        JasperReport reporte;
        String ruta ="D:\\reportes\\rpt_profesores.jasper";
        reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
        JasperPrint jprint = JasperFillManager.fillReport(reporte, null,
                new JRBeanCollectionDataSource(dao.obtenerTodos())
                );
        JasperViewer jViewer = new JasperViewer(jprint,false);
        jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jViewer.setVisible(true);
    }
    
    public void generarReporte(List<Profesor> lista) throws Exception{
        JasperReport reporte;
        String ruta ="D:\\reportes\\rpt_profesores.jasper";
        reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
        JasperPrint jprint = JasperFillManager.fillReport(reporte, null,
                new JRBeanCollectionDataSource(lista)
                );
        JasperViewer jViewer = new JasperViewer(jprint,false);
        jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jViewer.setVisible(true);
    }
    
    public List<Profesor> obtenerListaBusqueda(String valor) throws Exception{
        return dao.obtenerBusqueda(valor);
    }
    
    public void hayInputError(ProfesorForm o) throws Exception{
        
        if(o.getId_profe().isEmpty() || o.getId_profe().trim().length()==0) throw new Exception(Utils.getMensaje("ID", Utils.NO_VACIO));
        if(o.getDni().isEmpty() || o.getDni().trim().length()==0) throw new Exception(Utils.getMensaje("DNI", Utils.NO_VACIO));
        if(o.getNombre().isEmpty() || o.getNombre().trim().length()==0) throw new Exception(Utils.getMensaje("NOMBRE", Utils.NO_VACIO));
        if(o.getPapellido().isEmpty() || o.getPapellido().trim().length()==0) throw new Exception(Utils.getMensaje("APELLIDO PATERNO", Utils.NO_VACIO));
        if(o.getSapellido().isEmpty() || o.getSapellido().trim().length()==0) throw new Exception(Utils.getMensaje("APELLIDO MATERNO", Utils.NO_VACIO));

        
    }
    
    public void imprimirCB(JComboBox jComboBox)throws Exception{
        jComboBox.setModel(getModeloCB(dao.obtenerTodos()));
    }
    
    private DefaultComboBoxModel getModeloCB(List<Profesor> lista) {
        modeloCB  = new DefaultComboBoxModel();
        for(Profesor o: lista){
            modeloCB.addElement(o);
        }
        
        return modeloCB;
    }
    
    public void buscarCB(JComboBox jComboBox, int id_profe){
            DefaultComboBoxModel modeloCarrerasCB = (DefaultComboBoxModel)jComboBox.getModel();
            Profesor obj =  null;
            for(int i=0;i<modeloCarrerasCB.getSize();i++){
                obj = (Profesor)modeloCarrerasCB.getElementAt(i);
                
                if(obj.getId_profe()==id_profe){
                    modeloCarrerasCB.setSelectedItem(modeloCarrerasCB.getElementAt(i));
                    break;
                }
            }
    }
}
