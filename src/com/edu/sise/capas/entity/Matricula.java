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
public class Matricula {
    private Integer id_alum;
    private Integer id_asig;
    private LocalDate fecha_matricula;
    private double nota_final;
    private String des_alum;
    private String des_asig;

    public Matricula() {
    }

    public Matricula(Integer id_alum, Integer id_asig, LocalDate fecha_matricula, double nota_final, String des_alum, String des_asig) {
        this.id_alum = id_alum;
        this.id_asig = id_asig;
        this.fecha_matricula = fecha_matricula;
        this.nota_final = nota_final;
        this.des_alum = des_alum;
        this.des_asig = des_asig;
    }

    public Matricula(Integer id_alum, Integer id_asig, LocalDate fecha_matricula, double nota_final) {
        this.id_alum = id_alum;
        this.id_asig = id_asig;
        this.fecha_matricula = fecha_matricula;
        this.nota_final = nota_final;
    }

    public Integer getId_alum() {
        return id_alum;
    }

    public void setId_alum(Integer id_alum) {
        this.id_alum = id_alum;
    }

    public Integer getId_asig() {
        return id_asig;
    }

    public void setId_asig(Integer id_asig) {
        this.id_asig = id_asig;
    }

    public LocalDate getFecha_matricula() {
        return fecha_matricula;
    }

    public void setFecha_matricula(LocalDate fecha_matricula) {
        this.fecha_matricula = fecha_matricula;
    }

    public double getNota_final() {
        return nota_final;
    }

    public void setNota_final(double nota_final) {
        this.nota_final = nota_final;
    }

    public String getDes_alum() {
        return des_alum;
    }

    public void setDes_alum(String des_alum) {
        this.des_alum = des_alum;
    }

    public String getDes_asig() {
        return des_asig;
    }

    public void setDes_asig(String des_asig) {
        this.des_asig = des_asig;
    }

}
