/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Servicio;
import Persistencia.ServicioJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class ServicioDAO {
          ServicioJpaController servicio;
     

    public ServicioDAO() {
        Conexion con = Conexion.getConexion();
        servicio = new ServicioJpaController(con.getBd());
    }
    
    public void create(Servicio ser){
        try {
            servicio.create(ser);
        } catch (Exception ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Servicio> read(){  //devuelve todos los serviciomnos
        return servicio.findServicioEntities();
    }
    
    public Servicio readServicio(int id){
        return servicio.findServicio(id);
    }
    
    public void update(Servicio d){
        try {
            servicio.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id) throws IllegalOrphanException, NonexistentEntityException{
        
        try {
            servicio.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
