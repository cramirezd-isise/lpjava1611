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
}
