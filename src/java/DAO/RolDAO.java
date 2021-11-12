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
 * @author Cristian
 */
public class RolDAO {
    
    RolJpaController dep;

    public RolDAO() {
        Conexion con = Conexion.getConexion();
        dep = new RolJpaController(con.getBd());
    }
    
    public void create(Rol rol){
        try {
            dep.create(rol);
        } catch (Exception ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Rol> read(){  //devuelve todos los depmnos
        return dep.findRolEntities();
    }
    
    public Rol readRol(short id){
        return dep.findRol(id);
    }
    
    public void update(Rol d){
        try {
            dep.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(short id) throws IllegalOrphanException{
        
        try {
            dep.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
