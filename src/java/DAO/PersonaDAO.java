/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Persona;
import Persistencia.PersonaJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class PersonaDAO {
    
    PersonaJpaController per;

    public PersonaDAO() {
        Conexion con = Conexion.getConexion();
        per = new PersonaJpaController(con.getBd());
    }
    
    public void create(Persona persona){
        try {
            per.create(persona);
        } catch (Exception ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Persona> read(){  //devuelve todos los permnos
        return per.findPersonaEntities();
    }
    
    public Persona readPersona(String id){
        return per.findPersona(id);
    }
    
    public void update(Persona d){
        try {
            per.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(String id) throws IllegalOrphanException{
        
        try {
            per.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
