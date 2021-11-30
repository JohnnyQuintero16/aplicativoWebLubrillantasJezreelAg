/*
 * To change this license header, choose License Headers in Project Procitties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Cita;
//import Notificacion.GmailNotificacion;
import Persistencia.CitaJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jefersonrr@ufps.edu.co
 */
public class CitaDAO {
     CitaJpaController cit;

    public CitaDAO() {
        Conexion con = Conexion.getConexion();
        cit = new CitaJpaController(con.getBd());
    }
    
    public void create(Cita cita){
        try {
            cit.create(cita);
        } catch (Exception ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Cita> read(){  //devuelve todas las Citas
        return cit.findCitaEntities();
    }
    
    public Cita readCita(int id){
        return cit.findCita(id);
    }
    
    public void update(Cita c){
        try {
            cit.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarCita(String anio, String mes, String dia, int hora,String cedula, String descripcion){
        
        PersonaDAO p = new PersonaDAO();
        Date fecha = new Date(Integer.parseInt(anio)-1900, Integer.parseInt(mes)-1, Integer.parseInt(dia), hora, 0, 0);
        Cita ci  = new Cita(0,fecha,fecha, descripcion,"NO ATENDIDO");
        ci.setIdPersona(p.readPersona(cedula));
        create(ci);
        
    }
    
    public void delete(int id) throws IllegalOrphanException, NonexistentEntityException{
        
        try {
            cit.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void CitaAProceso(int id, String estado){
    
        Cita c = this.readCita(id);
        if(estado.equals("no")){
            c.setEstado("CANCELADA");
        }else{
            c.setEstado("EN PROCESO");
        }
        this.update(c);
    }
    public void actualizarCita(int id){
        
        Cita c = this.readCita(id);

        //AQUI FALTA RECIBIR UNA ATENCION
        //c.setEstado("ATENDIDA");
//            try{
//            GmailNotificacion n = new GmailNotificacion();
//            
//            n.enviarCorreo(c.getIdPersona().getEmail(),"TU SERVICIO ESTA EN PROCESO","📣 Hola desde lubrillantas Jezreel! 😁 \n"
//                +
//            "Hola "+c.getIdPersona().getNombres()+" queremos notificarte que tu servicio esta en proceso! pronto recibiras una notificacion cuando tu auto es listo.🔩 🔧🚗\n");
//            }catch(Exception e){
//                System.out.println("no se pudo notificar");
//            }
    }
}
