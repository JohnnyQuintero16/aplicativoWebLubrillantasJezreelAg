/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DAO.RolDAO;
import DTO.Rol;

/**
 *
 * @author johnny
 */
public class Persona {
    public static void main(String[] args) {
        RolDAO rol = new RolDAO();
        rol.create(new Rol((short)1,"ayuda","quiero cancelar"));
    }
}
