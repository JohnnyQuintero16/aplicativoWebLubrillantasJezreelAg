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
import java.text.NumberFormat;
//import java.sql.Date;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class main {
    
    public static void main(String[] args) {
        
//        double money = 5668768;
//        NumberFormat formatter = NumberFormat.getCurrencyInstance();
//        String moneyString = formatter.format(money);
        double amount = 323424;
        System.out.println(String.format("%,.2f", amount));
//        Jezreel j = new Jezreel();
//        CitaDAO c = new CitaDAO();
//        System.out.println(j.getMesesEst());
//        System.out.println(j.cargarHorarios());
//        Date d = new Date(2021-1900, 12-1, 03, 11, 0, 0);
//        Date horac = new Date(2021-1900, 12-1, 03, 11, 0, 0);
//        PersonaDAO p = new PersonaDAO();
//        Cita ci  = new Cita(0,d,horac, "ir por masjfj massssss cauchos","NO ATENDIDO");
//        ci.setIdPersona(p.readPersona("1090493768"));
//        c.create(ci);
        
//        PersonaDAO p  = new PersonaDAO();
//        String fecha[] = "2021-11-30".split("-");
//        System.out.println(Integer.parseInt(fecha[0])+" "+Integer.parseInt(fecha[1])+" "+Integer.parseInt(fecha[2]));
        
        
//Cita cit = c.readCita(32);
        
//        Date fechaCita = cit.getFecha();   //comparo usandoo hora
//        fechaCita.setHours(cit.getHora().getHours()); //le pongo la hora a la fecha
//        
//        
//        Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
//        
//        System.out.println("fecha cita "+fechaCita);
//        System.out.println("fecha hoy "+fechaActual);
//        
//        if (fechaCita.compareTo(fechaActual) > 0) {
//            System.out.println("fecha cita no ha llegado");
//        } else if(fechaCita.compareTo(fechaActual) < 0) {
//            System.out.println("la cita ya paso");
//        }
        
//        String hora = "MIÉRCOLES , 9:00 p.m";
//        String horaDia[] = hora.split(",");   //VIERNES , 9:00 a.m
//        System.out.println("hora"+horaDia[1]);
//        String horaFormat[] = horaDia[1].split(" "); //9:00,p.m
//        System.out.println("hora numero "+horaFormat[1].split(":")[0]);
//        
//        int num = Integer.parseInt(horaFormat[1].split(":")[0]);   //9
//        
//        String ampm = horaFormat[2];//.split(".")[0]; // a,m
//        System.out.println("amppm "+ampm);
////        String ap[] = ampm.split(".");
//        System.out.println("len "+ampm.charAt(0));
////        for (int i = 0; i < ap.length; i++) {
////            System.out.println(ap[i]);
////        }
////        
//        int horaMil = ampm.charAt(0)=='p'?num+12:num;
//        System.out.println(horaMil);
//        String sem = j.cargarHorarios();
//
//
//        
//        System.out.println(sem);
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



//
//
//
//
//    }
//    public static String cargarHorarios(){
//        
//        String rta="";
//        ArrayList<Dia> semana = cargarHorario();
//        
//        for(Dia d : semana){
//            
//            rta+=d.getNombre();
//            ArrayList<Hora> horas = d.getHoras();
//            
//            for (Hora h : horas) {
//               rta+=","+h.getHora();
//            } 
//            rta+=";";
//        }
//        return rta;
//    }
//
//    public static ArrayList<Dia> cargarHorario(){
//        //OBTENGO CITAS NO ATENDIDAS
//        Jezreel j = new Jezreel();
//        List<Cita> citas = j.getCitasNoAtendidas();
//        //OBTENGO EL DIA DE HOY
//        String diaEntroAReservar = getDia(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault())
//                            .toInstant())); //ejm lunes, martes...
//        Date fechaRealDeInicio = j.validarDiaDeBusqueda(diaEntroAReservar);
//        ArrayList<Dia> semana = getSemana(fechaRealDeInicio); //OBTENGO LA SEMANA A PARTIR DEL DIA QUE ME PARE
//        for (Dia d: semana) {
//            System.out.println(d.getNombre()+ ",");
//        }
//        //ENTRO A REVISAR A PARTIR DE LA FECHA
//        for (Cita ci: citas) {
//            
//            //desde las 8 a las 16
//            String diaCita = getDia(ci.getFecha());
//            String horaCita = j.getHora(ci.getHora());
//            System.out.println("dia de la cita "+diaCita);
//            System.out.println("hora de la cita "+horaCita);
//            
//            //CUANDO OBTENGA EL DIA DE LA LISTA AUMENTO EL CUPO Y SI SE LLENA ELIMINO LA HORA DE LA SEMANA
//            
//            Dia diaSemana = getDiaSemana(semana,diaCita); //dia de la semana de esa cita
//            ArrayList<Hora> h = diaSemana.getHoras(); //horas de ese dia
//            Hora horaDia = j.getHoraDia(h,horaCita);   //obtengo la hora de la cita dentro de las horas del dia
//           
//            horaDia.aumentarCupo();
//            System.out.println(diaSemana.getNombre());
//            System.out.println(horaDia.getHora());
//            System.out.println("EL cupo para el "+diaSemana.getNombre()+" es "+horaDia.getCupo());
//            if(horaDia.getCupo()==4){
//               diaSemana.getHoras().remove(horaDia);
//            }
//        }
//        return semana;
//    }
//    
//     public static ArrayList<Dia> getSemana(Date diaInicio){
//        
//        Date dia = diaInicio;
//        Calendar calendar = Calendar.getInstance();
//        ArrayList<Dia> semana = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            
//            Dia d = new Dia(getDia(dia)); //CREO UN DIA
//            semana.add(d);
//            calendar.setTime(dia); //CONFIGUURO EL DIA
//            
//            calendar.add(Calendar.DAY_OF_WEEK, 1);//LE SUMO UNO
////            System.out.println(calendar.getTime().toString());
//            dia = calendar.getTime();
//        }
//        return semana;
//    }
//     
//      public static Dia getDiaSemana(ArrayList<Dia> sem, String dia){
//        
//        for (Dia d : sem) {
//            if(d.getNombre().equals(dia)){
//                return d;
//            }
//        }
//        System.err.println("No se encontro el dia");
//        return null;
//    }
     
//     public static ArrayList<Dia> cargarHorario(){
//        //OBTENGO CITAS NO ATENDIDAS
//        List<Cita> citas = j.getCitasNoAtendidas();
//        //OBTENGO EL DIA DE HOY
//        String diaEntroAReservar = getDia(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault())
//                            .toInstant())); //ejm lunes, martes...
//        Date fechaRealDeInicio = validarDiaDeBusqueda(diaEntroAReservar);
//        ArrayList<Dia> semana = getSemana(fechaRealDeInicio); //OBTENGO LA SEMANA A PARTIR DEL DIA QUE ME PARE
//        //ENTRO A REVISAR A PARTIR DE LA FECHA
//        for (Cita ci: citas) {
//            
//            //desde las 8 a las 16
//            String diaCita = getDia(ci.getFecha());
//            String horaCita = getHora(ci.getHora());
//            
//            //CUANDO OBTENGA EL DIA DE LA LISTA AUMENTO EL CUPO Y SI SE LLENA ELIMINO LA HORA DE LA SEMANA
//            Dia diaSemana = getDiaSemana(semana,diaCita); //dia de la semana de esa cita
//            ArrayList<Hora> h = diaSemana.getHoras(); //horas de ese dia
//            Hora horaDia = getHoraDia(h,horaCita);   //obtengo la hora de la cita dentro de las horas del dia
// 
//            horaDia.aumentarCupo();
//            
//            if(horaDia.getCupo()==4){
//               diaSemana.getHoras().remove(horaDia);
//            }
//        }
//        return semana;
//    }
     
//     public static String getDia(Date fecha){
//    
//        SimpleDateFormat ObtenerDia = new SimpleDateFormat("EEEE");
//        String dia = ObtenerDia.format(fecha).toUpperCase();
//        return dia;
    }

} 
