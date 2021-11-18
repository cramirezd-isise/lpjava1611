/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.ITutorDAO;
import com.edu.sise.capas.dao.mysql.MySqlDAOManager;
import com.edu.sise.capas.entity.Tutor;
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
public class TutorLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    ITutorDAO dao = factory.getTutorDAO();
    DefaultTableModel modelo =  null;
    DefaultComboBoxModel modeloCB = null;
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Tutor> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Tutor obj : lista){
            Object data[] = {
              obj.getId_tutor(),
              obj.getDni(),
              obj.getNombre(),
              obj.getPapellido(),
              obj.getSapellido(),
              obj.getFnacimiento(),
              obj.getTelefono(),
              obj.getId_prov(),
              obj.getDes_prov()
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
    
    public void insertar(Tutor o) throws Exception {
        dao.insertar(o);
    }
    
    public void modificar(Tutor o) throws Exception {
        dao.modificar(o);
    }
    
    public void eliminar(Tutor o) throws Exception {
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
        String ruta ="D:\\reportes\\rpt_tutores.jasper";
        reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
        JasperPrint jprint = JasperFillManager.fillReport(reporte, null,
                new JRBeanCollectionDataSource(dao.obtenerTodos())
                );
        JasperViewer jViewer = new JasperViewer(jprint,false);
        jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jViewer.setVisible(true);
    }
    
    public void generarReporte(List<Tutor> lista) throws Exception{
        JasperReport reporte;
        String ruta ="D:\\reportes\\rpt_tutores.jasper";
        reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
        JasperPrint jprint = JasperFillManager.fillReport(reporte, null,
                new JRBeanCollectionDataSource(lista)
                );
        JasperViewer jViewer = new JasperViewer(jprint,false);
        jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jViewer.setVisible(true);
    }
    
    public List<Tutor> obtenerListaBusqueda(String valor) throws Exception{
        return dao.obtenerBusqueda(valor);
    }
    
    public void imprimirCB(JComboBox jComboBox)throws Exception{
        jComboBox.setModel(getModeloCB(dao.obtenerTodos()));
    }
    
    private DefaultComboBoxModel getModeloCB(List<Tutor> lista) {
        modeloCB  = new DefaultComboBoxModel();
        for(Tutor o: lista){
            modeloCB.addElement(o);
        }
        
        return modeloCB;
    }
    
    public void buscarCB(JComboBox jComboBox, int id_tutor){
            DefaultComboBoxModel modeloCarrerasCB = (DefaultComboBoxModel)jComboBox.getModel();
            Tutor obj =  null;
            for(int i=0;i<modeloCarrerasCB.getSize();i++){
                obj = (Tutor)modeloCarrerasCB.getElementAt(i);
                
                if(obj.getId_tutor()==id_tutor){
                    modeloCarrerasCB.setSelectedItem(modeloCarrerasCB.getElementAt(i));
                    break;
                }
            }
    }
}
