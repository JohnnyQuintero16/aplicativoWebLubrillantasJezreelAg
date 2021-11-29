/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Cita;
import DTO.Persona;
import DTO.Rol;
import Negocio.Dia;
import Negocio.Hora;
import Negocio.Jezreel;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class main {
    
    public static void main(String[] args) {
        Jezreel j = new Jezreel();
        
        
        String sem = j.cargarHorarios();
        
        
        
        System.out.println(sem);
        
        
//        Dia sab = new Dia("SÁBADO");
//        System.out.println("dia "+sab.getNombre());
//        ArrayList<Hora> h = sab.getHoras();
//        for (Hora ho : h) {
//            System.out.println("hora "+ho.getHora());
//        }
//       
//       
//        CitaDAO c = new CitaDAO();
//        Jezreel j = new Jezreel();
//        Cita ci = c.readCita(3);
//        Date hora = ci.getHora();
//        Date fecha = ci.getFecha();
//        Calendar calendar = Calendar.getInstance();
//        
//        SimpleDateFormat formateador = new SimpleDateFormat(
//                 "HH:mm", new Locale("es_ES"));//la hora de la cita
//        System.out.println(formateador.format(hora));
//        
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");//el dia de la cita
//
//        //hora en la que entro a buscar cita
//        Date diaHoy = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault())
//                            .toInstant());
//        String diaEntroAReservar = simpleDateFormat.format(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault())
//                            .toInstant())).toUpperCase();
//         if(diaEntroAReservar.equals("DOMINGO")){//O ESTA FUERA DEL HORARIO LABORAL
//         
//             //COMIENZO A BUSCAR A PARTIR DEL LUNES en adlante
//             calendar.add(Calendar.DAY_OF_WEEK, 1); //AQUI OBTENGO EL DIA Y LE SUMO 1
//             System.out.println("el dia de reservas comienza el "+simpleDateFormat.format(calendar.getTime()).toUpperCase());
//             Date fechaManana = calendar.getTime();
//             SimpleDateFormat formateadorp = new SimpleDateFormat(
//                 "yyyy-MM-dd", new Locale("es_ES"));
//             String parteFecha = formateadorp.format(fechaManana);
//             System.out.println(parteFecha);
//             String parteHora = "7:00:00";
//             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//             Date fechaAmandar = new Date();
//            try {
//                
//                fechaAmandar = sdf.parse(parteFecha+" "+parteHora);
//                
//            } catch (ParseException ex) {
//                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//             System.out.println("comienzo a buscar en "+fechaAmandar); //DIA LUNES A LAS 7AM
////             buscarHorasDisponible("lunes", simpleDateFormat.format(fecha).toUpperCase());
//         }
//         else{
//             
//         }
//        
//        System.out.println("DAY "+simpleDateFormat.format(fecha).toUpperCase());
//        
//        calendar.setTime(hora);
//        calendar.add(Calendar.MINUTE, 20);
//        System.out.println(formateador.format(calendar.getTime()));
//        //el dia de hoy
//        System.out.println("hoy es "+simpleDateFormat.format(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault())
//                            .toInstant())).toUpperCase());
//        
//        String fecha2 = j.getDia(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault())
//                            .toInstant()));
//        Date diam = j.validarDiaDeBusqueda(fecha2);
//        ArrayList<Dia> sem = j.getSemana(diam);
//        
//        System.out.println("------------------");
//        
//        System.out.println(j.getHora(c.readCita(2).getHora()));
//        System.out.println(j.getDia(diaHoy));
//        for (Dia d : sem) {
//            System.out.println(d.getNombre());
//        }
//        Date d = c.readCita(3).getFecha();
//        Date t = c.readCita(3).getHora();
//        SimpleDateFormat formateador = new SimpleDateFormat(
//                 "dd '/' MM '/' yyyy", new Locale("es_ES"));
//        SimpleDateFormat formateador2 = new SimpleDateFormat(
//                 "hh:mm", new Locale("es_ES"));
//            String fechad = formateador.format(d);
//            String hora = formateador2.format(t);
//        
//        System.out.println(fechad+" Hora: "+hora);
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
        
//        AtencionServicioDAO a = new AtencionServicioDAO();
//        System.out.println(a.read());







    }

     public static ArrayList<Dia> getSemana(Date diaInicio){
        
        Date dia = diaInicio;
        Calendar calendar = Calendar.getInstance();
        ArrayList<Dia> semana = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            
            Dia d = new Dia(getDia(dia)); //CREO UN DIA
            semana.add(d);
            calendar.setTime(dia); //CONFIGUURO EL DIA
            
            calendar.add(Calendar.DAY_OF_WEEK, 1);//LE SUMO UNO
//            System.out.println(calendar.getTime().toString());
            dia = calendar.getTime();
        }
        return semana;
    }
     
     public static String getDia(Date fecha){
    
        SimpleDateFormat ObtenerDia = new SimpleDateFormat("EEEE");
        String dia = ObtenerDia.format(fecha).toUpperCase();
        return dia;
    }

}
