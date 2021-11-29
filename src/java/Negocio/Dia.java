/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.util.ArrayList;

/**
 *
 * @author Cristian
 */
public class Dia {
    
    private ArrayList<Hora> horas;
    private String nombre;
    
    public Dia(String nombre) {
       horas = new ArrayList<Hora>();
       
       if(!nombre.equals("DOMINGO")){
            horas.add(new Hora("8",0));
            horas.add(new Hora("9",0));
            horas.add(new Hora("10",0));
            horas.add(new Hora("11",0));
            horas.add(new Hora("12",0));
            horas.add(new Hora("13",0));
            horas.add(new Hora("14",0));
            horas.add(new Hora("15",0));
            horas.add(new Hora("16",0));
            if(!nombre.equals("SABADO")){
                 horas.add(new Hora("17",0));
            }
       }else{
        horas.add(new Hora("0",0));
       }
       this.nombre = nombre;
    }

    public ArrayList<Hora> getHoras() {
        return horas;
    }

    public void setHoras(ArrayList<Hora> horas) {
        this.horas = horas;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
