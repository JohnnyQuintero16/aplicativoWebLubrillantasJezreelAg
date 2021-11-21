/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Calificacion;
import DTO.CalificacionPK;
import Persistencia.CalificacionJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class CalificacionDAO {
     CalificacionJpaController califi;

    public CalificacionDAO() {
        Conexion con = Conexion.getConexion();
        califi = new CalificacionJpaController(con.getBd());
    }
    
    public void create(Calificacion cali){
        try {
            califi.create(cali);
        } catch (Exception ex) {
            Logger.getLogger(CalificacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Calificacion> read(){  //devuelve todos los califimnos
        return califi.findCalificacionEntities();
    }
    
    public Calificacion readCalificacion(CalificacionPK p){
        return califi.findCalificacion(p);
    }
    
    public void update(Calificacion d){
        try {
            califi.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(CalificacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(CalificacionPK p) throws IllegalOrphanException, NonexistentEntityException{
        
        try {
            califi.destroy(p);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CalificacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
