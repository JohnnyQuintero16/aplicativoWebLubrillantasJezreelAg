/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DetallesProducto;
import DTO.MarcaProducto;
import Persistencia.DetallesProductoJpaController;
import Persistencia.MarcaProductoJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johnny
 */
public class MarcaProductoDAO {
         MarcaProductoJpaController marPro;

    public MarcaProductoDAO() {
        Conexion con = Conexion.getConexion();
        marPro = new MarcaProductoJpaController(con.getBd());
    }
    
    public void create(MarcaProducto marca){
        try {
            marPro.create(marca);
        } catch (Exception ex) {
            Logger.getLogger(DetallesProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<MarcaProducto> read(){  
        return marPro.findMarcaProductoEntities();
    }
    
    public MarcaProducto readMarcaProducto(int id){
        return marPro.findMarcaProducto(id);
    }
    
    public void update(MarcaProducto d){
        try {
            marPro.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(DetallesProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id) throws IllegalOrphanException, NonexistentEntityException{
        
        try {
            marPro.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DetallesProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
