/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelo;

/**
 *
 * @author DanielWilfredo
 */
public class Especies {
    private int codiEspe;
    private String nombEspe;
    private String descEspe;
    private int codiRefeEspe;
    private String nombRefeEspe;


    @Override
    public String toString() {
        return this.nombEspe;
    }

  public Especies(int codiEspe, int codiRefeEspe, String nombRefeEspe, String nombEspe, String descEspe)
  {
      this.codiEspe = codiEspe;
        this.nombEspe = nombEspe;
        this.descEspe = descEspe;
        this.codiRefeEspe = codiRefeEspe;
        this.nombRefeEspe = nombRefeEspe;
  }
  public Especies(){}

    public int getCodiEspe() {
        return codiEspe;
    }

    public void setCodiEspe(int codiEspe) {
        this.codiEspe = codiEspe;
    }

    public String getNombEspe() {
        return nombEspe;
    }

    public void setNombEspe(String nombEspe) {
        this.nombEspe = nombEspe;
    }

    public String getDescEspe() {
        return descEspe;
    }

    public void setDescEspe(String descEspe) {
        this.descEspe = descEspe;
    }

    public int getCodiRefeEspe() {
        return codiRefeEspe;
    }

    public void setCodiRefeEspe(int codiRefeEspe) {
        this.codiRefeEspe = codiRefeEspe;
    }

    public String getNombRefeEspe() {
        return nombRefeEspe;
    }

    public void setNombRefeEspe(String nombRefeEspe) {
        this.nombRefeEspe = nombRefeEspe;
    }
  
    
    
    
    
}
