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
public class Asignatura {
    private Integer id_asig;
    private String nombre;
    private int obligatoriedad;
    private int num_creditos;
    private int id_profe;
    private String des_profe;

    public Asignatura() {
    }

    public Asignatura(Integer id_asig, String nombre, int obligatoriedad, int num_creditos, int id_profe, String des_profe) {
        this.id_asig = id_asig;
        this.nombre = nombre;
        this.obligatoriedad = obligatoriedad;
        this.num_creditos = num_creditos;
        this.id_profe = id_profe;
        this.des_profe = des_profe;
    }

    public Asignatura(Integer id_asig, String nombre, int obligatoriedad, int num_creditos, int id_profe) {
        this.id_asig = id_asig;
        this.nombre = nombre;
        this.obligatoriedad = obligatoriedad;
        this.num_creditos = num_creditos;
        this.id_profe = id_profe;
    }

    public Integer getId_asig() {
        return id_asig;
    }

    public void setId_asig(Integer id_asig) {
        this.id_asig = id_asig;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getObligatoriedad() {
        return obligatoriedad;
    }

    public void setObligatoriedad(int obligatoriedad) {
        this.obligatoriedad = obligatoriedad;
    }

    public int getNum_creditos() {
        return num_creditos;
    }

    public void setNum_creditos(int num_creditos) {
        this.num_creditos = num_creditos;
    }

    public int getId_profe() {
        return id_profe;
    }

    public void setId_profe(int id_profe) {
        this.id_profe = id_profe;
    }

    public String getDes_profe() {
        return des_profe;
    }

    public void setDes_profe(String des_profe) {
        this.des_profe = des_profe;
    }

    @Override
    public String toString() {
        return getId_asig() + " - " + getNombre();
    }

   
}
