/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Vehiculo;
import Persistencia.VehiculoJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class VehiculoDAO {

    VehiculoJpaController vehiculo;

    public VehiculoDAO() {
        Conexion con = Conexion.getConexion();
        vehiculo = new VehiculoJpaController(con.getBd());
    }

    public void create(Vehiculo ve) {
        try {
            vehiculo.create(ve);
        } catch (Exception ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Vehiculo> read() {  //devuelve todos los vehiculomnos
        return vehiculo.findVehiculoEntities();
    }

    public Vehiculo readVehiculo(String placa) {
        return vehiculo.findVehiculo(placa);
    }

    public void update(Vehiculo d) {
        try {
            vehiculo.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String placa) throws IllegalOrphanException, NonexistentEntityException {

        try {
            vehiculo.destroy(placa);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Vehiculo> findVehiculosUser(String cedula) {

        List<Vehiculo> rta = new ArrayList<Vehiculo>();
        List<Vehiculo> vehiculos = vehiculo.findVehiculoEntities();
        for (Vehiculo v : vehiculos) {
            if (v.getIdPersona().getCedula().equals(cedula)) {
                rta.add(v);
            }
        }
        return rta;
    }
}
