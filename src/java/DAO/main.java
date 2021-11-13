/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Rol;

/**
 *
 * @author Cristian
 */
public class main {
    
    public static void main(String[] args) {
        
        
        RolDAO ro = new RolDAO();
        Rol r = new Rol((short)3, "mecanico", "compra productos en la tienda");
        ro.create(r);
        System.out.println("hola");
        
        
//        p.create(new Persona("f423423","Johnny", "Quintero", "johnnyaquintero@gmail.com","3105639373", "av4 N 1-1"));
        
        







    }
}
