/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Factura;
import Persistencia.FacturaJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jefersonrr@ufps.edu.co
 */
public class FacturaDAO {
       FacturaJpaController factura;
     

    public FacturaDAO() {
        Conexion con = Conexion.getConexion();
        factura = new FacturaJpaController(con.getBd());
    }
    
    public void create(Factura fac){
        try {
            factura.create(fac);
        } catch (Exception ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Factura> read(){  
        return factura.findFacturaEntities();
    }
    
    public Factura readFactura(int id){
        return factura.findFactura(id);
    }
    
    public void update(Factura d){
        try {
            factura.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id) throws IllegalOrphanException, NonexistentEntityException{
        
        try {
            factura.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
