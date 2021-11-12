/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.capas.entity;

import java.time.LocalDate;

/**
 *
 * @author Carlos
 */
public class Profesor {
    private Integer id_profe;
    private String dni;
    private String nombre;
    private String papellido;
    private String sapellido;
    private LocalDate fnacimiento;
    private String telefono;
    private Integer id_prov;
    private Integer id_carrera;

    public Profesor() {
    }

    public Profesor(Integer id_profe, String dni, String nombre, String papellido, String sapellido, LocalDate fnacimiento, String telefono, Integer id_prov, Integer id_carrera) {
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

    public Integer getId_profe() {
        return id_profe;
    }

    public void setId_profe(Integer id_profe) {
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

    public LocalDate getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(LocalDate fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getId_prov() {
        return id_prov;
    }

    public void setId_prov(Integer id_prov) {
        this.id_prov = id_prov;
    }

    public Integer getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(Integer id_carrera) {
        this.id_carrera = id_carrera;
    }
    
    
    
}
