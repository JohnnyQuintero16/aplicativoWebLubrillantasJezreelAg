/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DetallesProducto;
import Persistencia.DetallesProductoJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class DetallesProductoDAO {
     DetallesProductoJpaController dePro;

    public DetallesProductoDAO() {
        Conexion con = Conexion.getConexion();
        dePro = new DetallesProductoJpaController(con.getBd());
    }
    
    public void create(DetallesProducto aten){
        try {
            dePro.create(aten);
        } catch (Exception ex) {
            Logger.getLogger(DetallesProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<DetallesProducto> read(){  
        return dePro.findDetallesProductoEntities();
    }
    
    public DetallesProducto readDetallesProducto(int id){
        return dePro.findDetallesProducto(id);
    }
    
    public void update(DetallesProducto d){
        try {
            dePro.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(DetallesProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id) throws IllegalOrphanException, NonexistentEntityException{
        
        try {
            dePro.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DetallesProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
