/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author Cristian
 */
public class Hora {
    
    String hora;
    int cupo;

    public Hora(String hora, int cupo) {
        this.hora = hora;
        this.cupo = cupo;
    }
    
    public void aumentarCupo(){
        this.cupo++;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }
    
    
    
}
