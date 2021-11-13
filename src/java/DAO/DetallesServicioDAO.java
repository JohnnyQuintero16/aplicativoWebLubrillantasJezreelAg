/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DetallesServicio;
import Persistencia.DetallesServicioJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class DetallesServicioDAO {
     DetallesServicioJpaController deSer;
     

    public DetallesServicioDAO() {
        Conexion con = Conexion.getConexion();
        deSer = new DetallesServicioJpaController(con.getBd());
    }
    
    public void create(DetallesServicio dser){
        try {
            deSer.create(dser);
        } catch (Exception ex) {
            Logger.getLogger(DetallesServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<DetallesServicio> read(){  //devuelve todos los deSermnos
        return deSer.findDetallesServicioEntities();
    }
    
    public DetallesServicio readDetallesServicio(int id){
        return deSer.findDetallesServicio(id);
    }
    
    public void update(DetallesServicio d){
        try {
            deSer.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(DetallesServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id) throws IllegalOrphanException, NonexistentEntityException{
        
        try {
            deSer.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DetallesServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
