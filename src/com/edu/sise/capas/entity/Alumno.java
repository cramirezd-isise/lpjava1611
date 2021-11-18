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
public class Alumno {
    private Integer id_alum;
    private String dni;
    private String nombre;
    private String papellido;
    private String sapellido;
    private LocalDate fnacimiento;
    private String telefono;
    private Integer id_tutor;
    private Integer id_prov;
    private String des_prov;
    private String des_tutor;

    public Alumno() {
    }

    public Alumno(Integer id_alum, String dni, String nombre, String papellido, String sapellido, LocalDate fnacimiento, String telefono, Integer id_tutor, Integer id_prov) {
        this.id_alum = id_alum;
        this.dni = dni;
        this.nombre = nombre;
        this.papellido = papellido;
        this.sapellido = sapellido;
        this.fnacimiento = fnacimiento;
        this.telefono = telefono;
        this.id_tutor = id_tutor;
        this.id_prov = id_prov;
    }
    
    

    public Alumno(Integer id_alum, String dni, String nombre, String papellido, String sapellido, LocalDate fnacimiento, String telefono, Integer id_tutor, Integer id_prov, String des_prov, String des_tutor) {
        this.id_alum = id_alum;
        this.dni = dni;
        this.nombre = nombre;
        this.papellido = papellido;
        this.sapellido = sapellido;
        this.fnacimiento = fnacimiento;
        this.telefono = telefono;
        this.id_tutor = id_tutor;
        this.id_prov = id_prov;
        this.des_prov = des_prov;
        this.des_tutor = des_tutor;
    }

    public Integer getId_alum() {
        return id_alum;
    }

    public void setId_alum(Integer id_alum) {
        this.id_alum = id_alum;
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

    public Integer getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(Integer id_tutor) {
        this.id_tutor = id_tutor;
    }

    public Integer getId_prov() {
        return id_prov;
    }

    public void setId_prov(Integer id_prov) {
        this.id_prov = id_prov;
    }

    public String getDes_prov() {
        return des_prov;
    }

    public void setDes_prov(String des_prov) {
        this.des_prov = des_prov;
    }

    public String getDes_tutor() {
        return des_tutor;
    }

    public void setDes_tutor(String des_tutor) {
        this.des_tutor = des_tutor;
    }

    
    
}
