/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.logic;

import com.edu.sise.capas.dao.IMatriculaDAO;
import com.edu.sise.capas.dao.mysql.MySqlDAOManager;
import com.edu.sise.capas.entity.Asignatura;
import com.edu.sise.capas.entity.Matricula;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
public class MatriculaLogic {
    MySqlDAOManager factory = MySqlDAOManager.getInstancia(); //Singleton
    IMatriculaDAO dao = factory.getMatriculaDAO();
    DefaultTableModel modelo =  null;
    DefaultComboBoxModel modeloCB = null;
    
    private DefaultTableModel getModelo(DefaultTableModel modelo, List<Matricula> lista)throws Exception{
        modelo = new DefaultTableModel();
        List<String> listaNomColumn = dao.obtenerNombresColumnas();
        
        for(String columna : listaNomColumn){
            modelo.addColumn(columna.toUpperCase().replace('_', ' '));
        }
        //llenar el modelo con la lista
        for(Matricula obj : lista){
            Object data[] = {
              obj.getId_alum(),
              obj.getId_asig(),
              obj.getFecha_matricula(),
              obj.getNota_final(),
              obj.getDes_alum(),
              obj.getDes_asig()
            };
            modelo.addRow(data);
        }
        return modelo;
    }
    
    private DefaultTableModel getModeloNotasFinales(DefaultTableModel modelo, List<Matricula> lista)throws Exception{
        modelo = new DefaultTableModel();
        modelo.addColumn("ID_ALUM");
        modelo.addColumn("NOMBRE COMPLETO");
        modelo.addColumn("NOTA FINAL");
        //llenar el modelo con la lista
        for(Matricula obj : lista){
            Object data[] = {
              obj.getId_alum(),
              obj.getDes_alum(),
              obj.getNota_final()
            };
            modelo.addRow(data);
        }
        return modelo;
    }
    
    private DefaultTableModel obtenerTodos() throws Exception{
        return getModelo(modelo, dao.obtenerTodos());
    }
    
    public DefaultTableModel obtenerNotasFinales(int id_asig) throws Exception{
        return getModeloNotasFinales(modelo, dao.obtenerAlumnos(id_asig));
    }
    
    public void imprimirTB(JTable jtable) throws Exception{
        jtable.setModel(obtenerTodos());
    }
    
    public void insertar(Matricula o) throws Exception {
        dao.insertar(o);
    }
    
    public void modificar(Matricula o) throws Exception {
        dao.modificar(o);
    }
    
    public void eliminar(Matricula o) throws Exception {
        dao.eliminar(o);
    }
    
    public void ingresarNotaFinal(Matricula o) throws Exception {
        dao.ingresarNotaFinal(o);
    }
    
    public DefaultTableModel obtenerBusqueda(String valor) throws Exception{
        return getModelo(modelo, dao.obtenerBusqueda(valor));
    }
    
    public void imprimirTB(JTable jtable, DefaultTableModel modelo) throws Exception{
        jtable.setModel(modelo);
    }
    
    public void generarReporte() throws Exception{
        JasperReport reporte;
        String ruta ="D:\\reportes\\rpt_matricula.jasper";
        reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
        JasperPrint jprint = JasperFillManager.fillReport(reporte, null,
                new JRBeanCollectionDataSource(dao.obtenerTodos())
                );
        JasperViewer jViewer = new JasperViewer(jprint,false);
        jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jViewer.setVisible(true);
    }
    
    public void generarReporteNotasFinales(Asignatura o) throws Exception{
        JasperReport reporte;
        String ruta ="D:\\reportes\\rpt_notas_finales_v5.jasper";
        reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
        Map<String,Object> parametros = new HashMap<String, Object>();
        parametros.put("id_asig", o.getId_asig());
        parametros.put("nom_asig", o.getNombre());
        parametros.put("CollectionBeanNF", 
                new JRBeanCollectionDataSource(dao.obtenerAlumnos(o.getId_asig())));
        JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros,
                new JRBeanCollectionDataSource(dao.obtenerAlumnos(o.getId_asig()))
                );
        JasperViewer jViewer = new JasperViewer(jprint,false);
        jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jViewer.setVisible(true);
    }
    
    public void generarReporte(List<Matricula> lista) throws Exception{
        JasperReport reporte;
        String ruta ="D:\\reportes\\rpt_matricula.jasper";
        reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
        JasperPrint jprint = JasperFillManager.fillReport(reporte, null,
                new JRBeanCollectionDataSource(lista)
                );
        JasperViewer jViewer = new JasperViewer(jprint,false);
        jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jViewer.setVisible(true);
    }
    
    public List<Matricula> obtenerListaBusqueda(String valor) throws Exception{
        return dao.obtenerBusqueda(valor);
    }
    
    public void imprimirCB(JComboBox jComboBox)throws Exception{
        jComboBox.setModel(getModeloCB(dao.obtenerTodos()));
    }
    
    private DefaultComboBoxModel getModeloCB(List<Matricula> lista) {
        modeloCB  = new DefaultComboBoxModel();
        for(Matricula o: lista){
            modeloCB.addElement(o);
        }
        
        return modeloCB;
    }
    
    public void buscarCB(JComboBox jComboBox, int id_alum, int id_asig){
            DefaultComboBoxModel modeloCarrerasCB = (DefaultComboBoxModel)jComboBox.getModel();
            Matricula obj =  null;
            for(int i=0;i<modeloCarrerasCB.getSize();i++){
                obj = (Matricula)modeloCarrerasCB.getElementAt(i);
                
                if(obj.getId_alum()==id_alum && obj.getId_asig()==id_asig){
                    modeloCarrerasCB.setSelectedItem(modeloCarrerasCB.getElementAt(i));
                    break;
                }
            }
    }
    
    public void llenarResumen(JLabel jCantTotal, JLabel jCantAprob, 
            JLabel jCantDesa, JLabel jPorAprob, JLabel jPorDesa, DefaultTableModel modelo){
        
        int cant_total = 0, cant_aprob = 0, cant_desa = 0;
        double por_aprob = 0, por_desa = 0, nota_final = 0;
        
        cant_total = modelo.getRowCount();
        
        //recorrer el modelo
        for(int i = 0; i< modelo.getRowCount();i++){
            nota_final = Double.parseDouble(modelo.getValueAt(i, 2)+"");
            if(nota_final>=10.5) cant_aprob++;
            else cant_desa ++;
        }
        
        por_aprob = (cant_aprob / (double)cant_total) * 100d;
        por_desa = (cant_desa / (double)cant_total) * 100d;
        
        //impresion den los JLabel
        jCantTotal.setText(cant_total +"");
        jCantAprob.setText(cant_aprob +"");
        jCantDesa.setText(cant_desa +"");
        jPorAprob.setText(por_aprob +"");
        jPorDesa.setText(por_desa +"");
    }
}
