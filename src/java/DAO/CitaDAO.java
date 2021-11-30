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
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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

    public void create(Cita cita) {
        try {
            cit.create(cita);
        } catch (Exception ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Cita> read() {  //devuelve todas las Citas
        return cit.findCitaEntities();
    }

    public Cita readCita(int id) {
        return cit.findCita(id);
    }

    public void update(Cita d) {
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

    public void CitaAProceso(int id, String estado) {

        Cita c = this.readCita(id);
        if (estado.equals("no")) {
            c.setEstado("CANCELADA");
        } else {
            c.setEstado("EN PROCESO");
        }
        this.update(c);
    }

    public void actualizarCita(int id) {

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

    public List<Cita> citasUsuario(String cedula) {

        Cita ci = new Cita();
        List<Cita> citas = read();
        List<Cita> activas = new ArrayList<Cita>();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC-5"));
        Date fechaActual = calendar.getTime();
        for (Cita c : citas) {
           // System.out.println("SOY FECHA ACTUAL : " + ci.parseIntFecha(fechaActual));
            //System.out.println("SOY FECHA ENTRANTE : " + ci.parseIntFecha(c.getFecha()));
            if (c.getIdPersona().getCedula().equals(cedula) && c.parseIntFecha(fechaActual,fechaActual) <= c.parseIntFecha(c.getFecha(),c.getHora())) {
                
                //System.out.println("SOY FECHA ELEGIDA : " + c.parseIntFecha(c.getFecha()));
                activas.add(c);
            }

        }

        Collections.sort(activas);


        return activas;
    }
}
