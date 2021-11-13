/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template marle, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Marca;
import Persistencia.MarcaJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class MarcaDAO {
          MarcaJpaController marca;
     

    public MarcaDAO() {
        Conexion con = Conexion.getConexion();
        marca = new MarcaJpaController(con.getBd());
    }
    
    public void create(Marca mar){
        try {
            marca.create(mar);
        } catch (Exception ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Marca> read(){  //devuelve todos los marcamnos
        return marca.findMarcaEntities();
    }
    
    public Marca readMarca(int id){
        return marca.findMarca(id);
    }
    
    public void update(Marca d){
        try {
            marca.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id) throws IllegalOrphanException, NonexistentEntityException{
        
        try {
            marca.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
