/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Rol;
import Persistencia.RolJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *@author Jefersonrr@ufps.edu.co
 */
public class RolDAO {
    
    RolJpaController rol;

    public RolDAO() {
        Conexion con = Conexion.getConexion();
        rol = new RolJpaController(con.getBd());
        System.out.println("Entra");
    }
    
    public void create(Rol r){
        try {
            rol.create(r);
        } catch (Exception ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Rol> read(){  //devuelve todos los rolmnos
        return rol.findRolEntities();
    }
    
    public Rol readRol(short id){
        return rol.findRol(id);
    }
    
    public void update(Rol d){
        try {
            rol.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(short id) throws IllegalOrphanException{
        
        try {
            rol.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
