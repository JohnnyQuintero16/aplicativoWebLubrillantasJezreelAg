/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Producto;
import Persistencia.ProductoJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jefersonrr@ufps.edu.co
 */
public class ProductoDAO {
        ProductoJpaController prod;

    public ProductoDAO() {
        Conexion con = Conexion.getConexion();
        prod = new ProductoJpaController(con.getBd());
    }
    
    public void create(Producto producto){
        try {
            prod.create(producto);
        } catch (Exception ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Producto> read(){  //devuelve todos los productos
        return prod.findProductoEntities();
    }
    
    public Producto readProducto(String codigo){
        return prod.findProducto(codigo);
    }
    
    public void update(Producto p){
        try {
           prod.edit(p);
        } catch (Exception ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(String codigo) throws IllegalOrphanException, NonexistentEntityException{
        
        try {
            prod.destroy(codigo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<Producto> findProductoTipo(String tipo){
        return prod.findProductoTipo(tipo);
    }
}