/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Persona;
import DTO.Rol;

/**
 *
 * @author Cristian
 */
public class main {
    
    public static void main(String[] args) {
        AtencionServicioDAO a = new AtencionServicioDAO();
        System.out.println(a.read());
    }
}
