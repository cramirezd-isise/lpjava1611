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
public class Provincia {
    private Integer id_prov;
    private String nombre;
    private Integer id_depa;

    public Provincia() {
    }

    public Provincia(Integer id_prov, String nombre, Integer id_depa) {
        this.id_prov = id_prov;
        this.nombre = nombre;
        this.id_depa = id_depa;
    }

    public Integer getId_prov() {
        return id_prov;
    }

    public void setId_prov(Integer id_prov) {
        this.id_prov = id_prov;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_depa() {
        return id_depa;
    }

    public void setId_depa(Integer id_depa) {
        this.id_depa = id_depa;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
