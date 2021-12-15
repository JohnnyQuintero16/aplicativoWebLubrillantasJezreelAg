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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
            cit.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarCita(String anio, String mes, String dia, int hora, String cedula, String descripcion) {

        PersonaDAO p = new PersonaDAO();
        Date fecha = new Date(Integer.parseInt(anio) - 1900, Integer.parseInt(mes) - 1, Integer.parseInt(dia), hora, 0, 0);
        Cita ci = new Cita(0, fecha, fecha, descripcion, "NO ATENDIDO");
        ci.setIdPersona(p.readPersona(cedula));
        create(ci);

    }

    public void delete(int id) throws IllegalOrphanException, NonexistentEntityException {

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
        c.setEstado("ATENDIDO");
        this.update(c);
    }
    
    public List<Cita> getCitasAtendidasAnioActual(){
    
        List<Cita> citas = this.read();
        List<Cita> citasAtendidas = new ArrayList<Cita>();
        Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()); //hora actual
        int anioActual = getAnio(fechaActual);
        
        for (Cita ci : citas) {
            
            if (ci.getEstado().equals("ATENDIDO") && (this.getAnio(ci.getFecha())==anioActual)) {
                citasAtendidas.add(ci);
            }
        }
        return citasAtendidas;
    }
    
    private int getAnio(Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return Integer.parseInt(sdf.format(fecha));
    }

    public List<Cita> citasUsuario(String cedula) {

        Cita ci = new Cita();
        List<Cita> citas = read();
        List<Cita> activas = new ArrayList<Cita>();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC-5"));
        Date fechaActual = calendar.getTime();
        for (Cita c : citas) {

            if (c.getIdPersona().getCedula().equals(cedula) && c.parseLongFecha(fechaActual, fechaActual) <= c.parseLongFecha(c.getFecha(), c.getHora()) && !c.getEstado().equals("ATENDIDO") && !c.getEstado().equals("CANCELADA")) {

                activas.add(c);
            }

        }

        Collections.sort(activas);

        return activas;
    }

    public List<Cita> citasCanceladas() {

        List<Cita> citas = read();
        List<Cita> canceladas = new ArrayList<Cita>();

        for (Cita c : citas) {

            if (c.getEstado().equals("CANCELADA")) {

                canceladas.add(c);

            }
        }
        return canceladas;
    }

    public int cantidadCitas() {
        int cant = 0;
        List<Cita> citas = read();
        if (citas != null) {
            cant = citas.size();
        }

        return cant;
    }

    public int cantidadCitasCanceladas() {
        int cant = 0;
        List<Cita> citas = citasCanceladas();
        if (citas != null) {
            cant = citas.size();
        }

        return cant;
    }
}
