/*
 * To change this license header, choose License Headers in Project Proatencionties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.AtencionServicio;
import Persistencia.AtencionServicioJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jefersonrr@ufps.edu.co
 */
public class AtencionServicioDAO {
    AtencionServicioJpaController atencion;

    public AtencionServicioDAO() {
        Conexion con = Conexion.getConexion();
        atencion = new AtencionServicioJpaController(con.getBd());
    }
    
    public void create(AtencionServicio aten){
        try {
            atencion.create(aten);
        } catch (Exception ex) {
            Logger.getLogger(AtencionServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<AtencionServicio> read(){  //devuelve todos los atencionmnos
        return atencion.findAtencionServicioEntities();
    }
    
    public AtencionServicio readAtencionServicio(int id){
        return atencion.findAtencionServicio(id);
    }
    
    public void update(AtencionServicio d){
        try {
            atencion.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(AtencionServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id) throws IllegalOrphanException, NonexistentEntityException{
        
        try {
            atencion.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AtencionServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public AtencionServicio getServicio(int idCita){
    
        List<AtencionServicio> a = this.read();
        for (AtencionServicio at: a) {
            if(at.getIdCita().getId() == idCita){
                return at;
            }
        }
        return null;
    }
    
    public List<AtencionServicio> findServiciosFicha(int id, List<AtencionServicio> rta){
         
        List<AtencionServicio> servicios = atencion.findAtencionServicioEntities();
        for(AtencionServicio s: servicios){
           
           if(s.getIdFichaTecnica().getId().equals(id)){
           rta.add(s);
                
           }
        } 
      return rta;
    }
    
    public List<AtencionServicio> findAtencionFicha(String idFicha){
        List<AtencionServicio> find = new ArrayList<>();
        List<AtencionServicio> lista = this.read();
        for(AtencionServicio servi:lista){
            if(servi.getIdFichaTecnica().getIdVehiculo().getPlaca().equals(idFicha)){
                find.add(servi);
            }
        }
        return (find.size() != 0)?find:null;
    }
   
}
