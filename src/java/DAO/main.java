/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Persona;
import DTO.Rol;
import Negocio.Jezreel;
import Notificacion.GmailNotificacion;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.mail.MessagingException;

/**
 *
 * @author Cristian
 */
public class main {
    
    public static void main(String[] args) throws IOException, GeneralSecurityException, MessagingException {
//       
        CitaDAO c = new CitaDAO();
        Date d = c.readCita(3).getFecha();
        Date t = c.readCita(3).getHora();
        SimpleDateFormat formateador = new SimpleDateFormat(
                 "dd '/' MM '/' yyyy", new Locale("es_ES"));
        SimpleDateFormat formateador2 = new SimpleDateFormat(
                 "hh:mm", new Locale("es_ES"));
            String fechad = formateador.format(d);
            String hora = formateador2.format(t);
        
        System.out.println(fechad+" Hora: "+hora);
//        c.actualizarCita(4, "si");
                
//        GmailNotificacion n = new GmailNotificacion();
//            
//        n.enviarCorreo("mcris1493@gmail.com","TU SERVICIO ESTA EN PROCESO","📣 Hola desde lubrillantas Jezreel! 😁 \nHola cris queremos notificarte que tu servicio esta en proceso! pronto recibiras una notificacion cuando tu auto es listo.🔩 🔧🚗\n");
            
//Jezreel j = new Jezreel();
//CitaDAO c = new CitaDAO();
//AtencionServicioDAO a = new AtencionServicioDAO();
//        
//        System.out.println(a.read());
        
//        PersonaDAO p = new PersonaDAO();
//        RolDAO r = new RolDAO();
//        System.out.println("hola");
//        //String nombre, String apellido, String contrasenia, String cedula, String correo, String telef, String direccion, Rol rol
//        p.crearPersona("diana","medina","jgjgju67","1090425510","dianapp@gmail.com","21043024","calle 1",r.readRol((short)2));
//        
////        Persona pe = new Persona("1090425512","diana","medina","diana@gmail.com","2104302472","calle 1");
////        pe.setContraseña("4rertert");
////        pe.setIdRol(r.readRol((short)2));
////        p.create(pe);
//       
        
//        RolDAO ro = new RolDAO();
//        r = new Rol((short)5, "yo tambien", "mnmnmm");
//        ro.create(r);
//        System.out.println("hola");
        
        
//        p.create(new Persona("f423423","Johnny", "Quintero", "johnnyaquintero@gmail.com","3105639373", "av4 N 1-1"));
        
        AtencionServicioDAO a = new AtencionServicioDAO();
        System.out.println(a.read());







    }
}
