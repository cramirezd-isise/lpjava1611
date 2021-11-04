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
public class Empleado {
    private Integer id_empleado;
    private String nombre;
    private String ape_pat;
    private String ape_mat;
    private LocalDate fnacimiento; 
    private double sueldo;

    public Empleado() {
    }

    public Empleado(Integer id_empleado, String nombre, String ape_pat, String ape_mat, LocalDate fnacimiento, double sueldo) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.ape_pat = ape_pat;
        this.ape_mat = ape_mat;
        this.fnacimiento = fnacimiento;
        this.sueldo = sueldo;
    }

    public Integer getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Integer id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe_pat() {
        return ape_pat;
    }

    public void setApe_pat(String ape_pat) {
        this.ape_pat = ape_pat;
    }

    public String getApe_mat() {
        return ape_mat;
    }

    public void setApe_mat(String ape_mat) {
        this.ape_mat = ape_mat;
    }

    public LocalDate getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(LocalDate fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
          
          

}
