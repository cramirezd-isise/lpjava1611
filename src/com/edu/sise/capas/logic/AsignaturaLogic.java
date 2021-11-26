/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.IAsignaturaDAO;
import com.edu.sise.capas.dao.mysql.MySqlDAOManager;
import com.edu.sise.capas.entity.Asignatura;
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
public class AsignaturaLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    IAsignaturaDAO dao = factory.getAsignaturaDAO();
    DefaultTableModel modelo =  null;
    DefaultComboBoxModel modeloCB = null;
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Asignatura> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Asignatura obj : lista){
            Object data[] = {
              obj.getId_asig(),
              obj.getNombre(),
              obj.getObligatoriedad(),
              obj.getNum_creditos(),
              obj.getId_profe(),
              obj.getDes_profe()

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
    
    public void insertar(Asignatura o) throws Exception {
        dao.insertar(o);
    }
    
    public void modificar(Asignatura o) throws Exception {
        dao.modificar(o);
    }
    
    public void eliminar(Asignatura o) throws Exception {
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
        String ruta ="D:\\reportes\\rpt_asignaturas.jasper";
        reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
        JasperPrint jprint = JasperFillManager.fillReport(reporte, null,
                new JRBeanCollectionDataSource(dao.obtenerTodos())
                );
        JasperViewer jViewer = new JasperViewer(jprint,false);
        jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jViewer.setVisible(true);
    }
    
    public void generarReporte(List<Asignatura> lista) throws Exception{
        JasperReport reporte;
        String ruta ="D:\\reportes\\rpt_asignaturas.jasper";
        reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
        JasperPrint jprint = JasperFillManager.fillReport(reporte, null,
                new JRBeanCollectionDataSource(lista)
                );
        JasperViewer jViewer = new JasperViewer(jprint,false);
        jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jViewer.setVisible(true);
    }
    
    public List<Asignatura> obtenerListaBusqueda(String valor) throws Exception{
        return dao.obtenerBusqueda(valor);
    }
    
    public void imprimirCB(JComboBox jComboBox)throws Exception{
        jComboBox.setModel(getModeloCB(dao.obtenerTodos()));
    }
    
    private DefaultComboBoxModel getModeloCB(List<Asignatura> lista) {
        modeloCB  = new DefaultComboBoxModel();
        for(Asignatura o: lista){
            modeloCB.addElement(o);
        }
        
        return modeloCB;
    }
    
    public void buscarCB(JComboBox jComboBox, int id_asig){
            DefaultComboBoxModel modeloCarrerasCB = (DefaultComboBoxModel)jComboBox.getModel();
            Asignatura obj =  null;
            for(int i=0;i<modeloCarrerasCB.getSize();i++){
                obj = (Asignatura)modeloCarrerasCB.getElementAt(i);
                
                if(obj.getId_asig()==id_asig){
                    modeloCarrerasCB.setSelectedItem(modeloCarrerasCB.getElementAt(i));
                    break;
                }
            }
    }
    
    public Asignatura obtenerxID(Integer id) throws Exception{
        return dao.obtenerxID(id);
    }
}
