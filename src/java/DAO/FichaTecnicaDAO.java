/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.FichaTecnica;
import Persistencia.FichaTecnicaJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class FichaTecnicaDAO {
       FichaTecnicaJpaController ficha;
     

    public FichaTecnicaDAO() {
        Conexion con = Conexion.getConexion();
        ficha = new FichaTecnicaJpaController(con.getBd());
    }
    
    public void create(FichaTecnica fi){
        try {
            ficha.create(fi);
        } catch (Exception ex) {
            Logger.getLogger(FichaTecnicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<FichaTecnica> read(){  //devuelve todos los fichamnos
        return ficha.findFichaTecnicaEntities();
    }
    
    public FichaTecnica readFichaTecnica(int id){
        return ficha.findFichaTecnica(id);
    }
    
    public void update(FichaTecnica d){
        try {
            ficha.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(FichaTecnicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id) throws IllegalOrphanException, NonexistentEntityException{
        
        try {
            ficha.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FichaTecnicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
