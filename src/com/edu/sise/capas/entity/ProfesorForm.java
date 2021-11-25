/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.entity;

/**
 *
 * @author Carlos
 */
public class ProfesorForm {
    private String id_profe;
    private String dni;
    private String nombre;
    private String papellido;
    private String sapellido;
    private String fnacimiento;
    private String telefono;
    private String id_prov;
    private String id_carrera;

    public ProfesorForm() {
    }

    public ProfesorForm(String id_profe, String dni, String nombre, String papellido, String sapellido, String fnacimiento, String telefono, String id_prov, String id_carrera) {
        this.id_profe = id_profe;
        this.dni = dni;
        this.nombre = nombre;
        this.papellido = papellido;
        this.sapellido = sapellido;
        this.fnacimiento = fnacimiento;
        this.telefono = telefono;
        this.id_prov = id_prov;
        this.id_carrera = id_carrera;
    }

    public String getId_profe() {
        return id_profe;
    }

    public void setId_profe(String id_profe) {
        this.id_profe = id_profe;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPapellido() {
        return papellido;
    }

    public void setPapellido(String papellido) {
        this.papellido = papellido;
    }

    public String getSapellido() {
        return sapellido;
    }

    public void setSapellido(String sapellido) {
        this.sapellido = sapellido;
    }

    public String getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId_prov() {
        return id_prov;
    }

    public void setId_prov(String id_prov) {
        this.id_prov = id_prov;
    }

    public String getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(String id_carrera) {
        this.id_carrera = id_carrera;
    }
    
    
}
