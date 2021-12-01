/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DAO.AtencionServicioDAO;
import DAO.CitaDAO;
import DAO.CalificacionDAO;
import DAO.DetallesProductoDAO;
import DAO.DetallesServicioDAO;
import DAO.FichaTecnicaDAO;
import DAO.ProductoDAO;
import DAO.ServicioDAO;
import DAO.TipoDAO;
import DAO.VehiculoDAO;
import DTO.AtencionServicio;
import DTO.DetallesProducto;
import DTO.DetallesServicio;
import DTO.Factura;
import DTO.FichaTecnica;
import DAO.MarcaDAO;
import DAO.PersonaDAO;
import DAO.ProductoDAO;
import DAO.ServicioDAO;
import DAO.TipoDAO;
import DAO.main;
import DTO.Cita;
import DTO.Marca;
import DTO.Persona;
import DTO.Producto;
import DTO.Servicio;
import DTO.Tipo;
import DTO.Vehiculo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class Jezreel {

    public String mostrarServicios() {

        ServicioDAO da = new ServicioDAO();
        List<Servicio> servicios = da.read();

        String cardServicios = " ";
        for (Servicio ser : servicios) {
            if (ser.getEstado().equals("ACTIVO")) {

                cardServicios += "			<div class=\"card\">\n"
                        + "	\n"
                        + "				<div class=\"row g-0 \">\n"
                        + "					<div class=\"col-md-5\">\n"
                        + "					  <img src=" + '"' + ser.getImgUrl() + '"' + " class=\"img-fluid rounded-start\" alt=\"...\">\n"
                        + "					</div>\n"
                        + "					<div class=\"col-md-7 mt-4\">\n"
                        + "						<h2 class=\"card-title\">" + ser.getNombre() + "</h2>\n"
                        + "						<p class=\"card-text\">" + ser.getDescripcion() + "</p>\n"
                        + "					</div>\n"
                        + "				  </div>\n"
                        + "		\n"
                        + "	\n"
                        + "			</div> \n";
            }
        }

        return cardServicios;

    }

    public String[] mostrarProductos() {

        String[] tipo = {"ACEITES", "FILROS", "VALVULINAS", "ADICTIVOS", "OTROS"};
        ProductoDAO da = new ProductoDAO();
        String[] rta = new String[tipo.length];
        for (int i = 0; i < tipo.length; i++) {

            List<Producto> pt = da.findProductoTipo(tipo[i]);

            if (!pt.isEmpty()) {
                rta[i] = "";
                for (Producto pro : pt) {
                    if (pro.getEstado().equals("ACTIVO")) {
                        rta[i] += "					<div class=\"card\">\n"
                                + "						<img src=" + '"' + pro.getImgUrl() + '"' + " alt=\"\">\n"
                                + "						<h4 class=\"titulo-card\">" + pro.getNombre() + " </h4>\n"
                                + "						<p  id=\"desc\">" + pro.getDescripcion() + "</p>\n"
                                + "						<p><strong id=\"ref-prec\">Referencia:</strong>" + pro.getReferencia() + "</p>				\n"
                                + "						<p><strong id=\"ref-prec\">Precio: $ </strong>" + pro.getPrecioVenta() + "</p>\n"
                                + "\n"
                                + "						\n"
                                + "					</div> \n";
                    }
                }
            } else {

                rta[i] = "<h4> No se econtraron resultados</h4>";
            }

        }
        return rta;

    }

    public String misServiciosUsu(String cedula) {

        String rta;
        VehiculoDAO vda = new VehiculoDAO();
        AtencionServicioDAO atendao = new AtencionServicioDAO();
        List<AtencionServicio> servi = new ArrayList<AtencionServicio>();
        List<Vehiculo> vehiculos = vda.findVehiculosUser(cedula);

        if (!vehiculos.isEmpty()) {
            for (Vehiculo ve : vehiculos) {

                FichaTecnicaDAO fida = new FichaTecnicaDAO();
                FichaTecnica ficha = fida.findFichaVehiculo(ve.getPlaca());
                atendao.findServiciosFicha(ficha.getId(), servi);

            }
            Collections.sort(servi);
          
            rta = vistaMisServicios(servi);

        } else {
            rta = "<h3>Aun no tienes servicios con nostros :( </h3>";
        }
        return rta;
    }

    public String misServiciosUsuFitroVehiculo(String placa) {
        String rta = "";
        AtencionServicioDAO atendao = new AtencionServicioDAO();
        List<AtencionServicio> servi = new ArrayList<AtencionServicio>();
        FichaTecnicaDAO fida = new FichaTecnicaDAO();
        FichaTecnica ficha = fida.findFichaVehiculo(placa);
        atendao.findServiciosFicha(ficha.getId(), servi);
        Collections.sort(servi);
    
        rta = vistaMisServicios(servi);
        return rta;
    }

    public String vistaMisServicios(List<AtencionServicio> servi) {

        String rta = "";
        DetallesServicioDAO sdao = new DetallesServicioDAO();
        DetallesProductoDAO pdao = new DetallesProductoDAO();
        List<Double> costo = new ArrayList<Double>();
        costo.add(0.0);
        costo.add(0.0);
        int i = 0;

        for (AtencionServicio a : servi) {

            List<DetallesServicio> dser = sdao.findDetalleServicioAtencion(a.getId());
           
            List<DetallesProducto> dpro = pdao.findDetalleProductoAtencion(a.getId());
            Persona mecanico = a.getIdPersona();
            Factura factura = a.getIdFactura();
            try {
                rta += "<div class=\"card\">\n"
                        + "          <div class=\"card-header row \" id=\"cardt\">\n"
                        + "            <div class=\"col-4  verticalLine centrado\">\n"
                        + "              <h4> Vehiculo : " + a.getIdFichaTecnica().getIdVehiculo().getPlaca() + "</h4>\n"
                        + "            </div>\n"
                        + "            <div class=\"col-4  verticalLine centrado\">\n"
                        + "              <h4> Fecha : " + a.formatoFecha(a.getFecha()) + "</h4>\n"
                        + "            </div>\n"
                        + "            <div class=\"col-4\">\n"
                        + "              <h4>Total $  " + a.getIdFactura().getTotal() + "</h4>\n"
                        + "            </div>\n"
                        + "          </div>\n"
                        + "          <div class=\"card-body row\">\n"
                        + "            <!-- img servicio -->\n"
                        + "            <div class=\"col-12 col-sm-6 col-md-4 col-lg-4\">\n"
                        + "             <img src=" + '"' + dser.get(0).getIdServicio().getImgUrl() + '"' + " id=\"imgServicio\">\n"
                        + "            </div>\n"
                        + "\n"
                        + "            <!-- texto del servicio -->\n"
                        + "            <div class=\"col-12 col-sm-6 col-md-8 col-lg-8\">\n"
                        + "              <p class=\"card-text\">" + a.getDescripcion() + "</p>\n"
                        + "\n"
                        + "              <!-- boton del servicio -->\n"
                        + "              <a href=\"#\" class=\"btn\" id=\"boton\" type=\"button\"  data-bs-toggle=\"modal\" data-bs-target=" + '"' + "#modal0" + i + '"' + ">\n"
                        + "                Ver Servicio\n"
                        + "              </a>\n"
                        + "\n"
                        + "              <!-- ventana modal -->\n"
                        + "              <div class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" id=" + '"' + "modal0" + i + '"' + " aria-labelledby=\"modal1\" aria-hidden=\"true\">\n"
                        + "                <div class=\"modal-dialog modal-lg\" role=\"document\">\n"
                        + "                  <div class=\"modal-content\">\n"
                        + "                    <div class=\"modal-header\">\n"
                        + "                      <h2 class=\"modal-title\">Detalles del Servicio</h2>\n"
                        + "                      <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\">\n"
                        + "                      </button>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"modal-body\">\n"
                        + "                    <table class=\"table table-bordered table-responsive table-striped\">\n"
                        + "                          <thead style=\"background-color: #616060a1;\">\n"
                        + "                            <tr>\n"
                        + "                              <th scope=\"col\">Servicio</th>\n"
                        + "                              <th scope=\"col\">Descripcion</th>\n"
                        + "                            </tr>\n"
                        + "                          </thead>\n"
                        + "                          <tbody>\n"
                        + "                            " + tablaListaServiciosUsu(dser)
                        + "                          </tbody>\n"
                        + "                        </table>\n"
                        + "                        <table class=\"table table-bordered table-responsive table-striped\">\n"
                        + "                          <thead style=\"background-color: #616060a1;\">\n"
                        + "                            <tr>\n"
                        + "                              <th scope=\"col\">Producto</th>\n"
                        + "                              <th scope=\"col\">Und</th>\n"
                        + "                              <th scope=\"col\">Precio</th>\n"
                        + "                              <th scope=\"col\">IVA</th>\n"
                        + "                              <th scope=\"col\">Total</th>\n"
                        + "                            </tr>\n"
                        + "                          </thead>\n"
                        + "                          <tbody>\n"
                        + "                            " + tablaListaProductosUsu(dpro, costo)
                        + "                          </tbody>\n"
                        + "                        </table>\n";

                rta += costosAtencion(costo, a.getIdFactura(), a);
            } catch (Exception e) {

            }

            i++;
            costo.set(0, 0.0);
            costo.set(1, 0.0);
        }

        return rta;
    }

    public String tablaListaServiciosUsu(List<DetallesServicio> detalles) {
        String rta = "";
        for (DetallesServicio d : detalles) {

            rta += "<tr>\n"
                    + "                              <td>" + d.getIdServicio().getNombre() + "</td>\n"
                    + "                              <td>" + d.getIdServicio().getDescripcion() + "</td>\n"
                    + "                            </tr>\n";
        }

        return rta;
    }

    public String tablaListaProductosUsu(List<DetallesProducto> detalles, List<Double> costo) {
        String rta = "";
        for (DetallesProducto d : detalles) {

            rta += "<tr>\n"
                    + "                              <td>" + d.getIdProducto().getNombre() + "</td>\n"
                    + "                              <td>" + d.getCantidad() + "</td>\n"
                    + "                              <td> $ " + d.getIdProducto().getPrecioVenta() + "</td>\n"
                    + "                              <td> $ " + d.getIdProducto().getPrecioVenta() * 0.19 + "</td>\n"
                    + "                              <td>$" + (d.getIdProducto().getPrecioVenta() + d.getIdProducto().getPrecioVenta() * 0.19) + "</td>\n"
                    + "                            </tr>\n";

            costo.set(0, costo.get(0) + d.getIdProducto().getPrecioVenta());
            costo.set(1, costo.get(1) + d.getIdProducto().getPrecioVenta() * 0.19);
        }

        return rta;
    }

    public String costosAtencion(List<Double> costo, Factura f, AtencionServicio a) {

        CalificacionDAO cadao = new CalificacionDAO();
        return "  <br>\n"
                + "                        <h6>SUBTOTAL: $" + costo.get(0) + "</h6>\n"
                + "                        <hr width=\"30%\">\n"
                + "                        <h6>IVA: $ " + costo.get(1) + "</h6>\n"
                + "                        <hr width=\"30%\">\n"
                + "                         <h6>DESCUENTO: $ " + (Math.round(((costo.get(0) + costo.get(1)) * (f.getDescuento() / 100.0)) * 100.0) / 100.0) + "</h6>\n"
                + "                        <hr width=\"30%\">\n"
                + "                        <h6>TOTAL : $ " + ((costo.get(0) + costo.get(1)) - (costo.get(0) + costo.get(1)) * (f.getDescuento() / 100.0)) + "</h6>\n"
                + "                        <hr width=\"30%\">\n"
                + "                    </div>\n"
                + "                    <div class=\"modal-footer\" id=\"foterM\">\n"
                + "                    <input style=\"display:none\" value=" + a.getId() + ">\n"
                + "                    "+((cadao.calificado(a)==false)?"<button  class=\"btn\" id=\"boton\" data-bs-toggle=\"modal\" data-bs-target=\"#modal2\" type=\"button\">Calificar servicio</button>\n":"<button style=\"background-color: #119200\" class=\"btn\" id=\"boton\" type=\"button\">Calificado</button>\n")
                + "                    </div>\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "          <br>\n"
                + "        </div>\n"
                + "        <br>";
    }

    public String mostrarVehiculosUser(String cedula) {

        PersonaDAO p = new PersonaDAO();
        Persona per = p.readPersona(cedula);
        List<Vehiculo> ve = per.getVehiculoList();
        String rta = "";

        if (ve.isEmpty()) {

            rta = "<table class=\"tabla-vehiculo\">\n"
                    + "                <tr>\n"
                    + "                  <th>No tienes ningun vehiculo registrado :(</th>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                  <td><img src=\"./img/vehicle.png\" style=\"width: 312px; height: 300px; /></td>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "              </table> <br><br>";

        } else {

            for (Vehiculo vehiculo : ve) {
                rta += "<form action=\"MisServiciosUsu.do\"><table class=\"tabla-vehiculo\">\n"
                        + "                <tr>\n"
                        + "                  <th>Placa</th>\n"
                        + "                  <th>Marca</th>\n"
                        + "                  <th>Kilometraje</th>\n"
                        + "                </tr>\n"
                        + "                <tr>\n"
                        + "                  <input name=\"placa\" hidden value=\"" + vehiculo.getPlaca() + "\" ><td>" + vehiculo.getPlaca() + "</td>\n"
                        + "                  <td>" + vehiculo.getIdMarca().getNombre() + "</td>\n"
                        + "                  <td>" + vehiculo.getKilometraje() + "</td>\n"
                        + "                </tr>\n"
                        + "               \n"
                        + "                <tr>\n"
                        + "                    <th>Modelo</th>\n"
                        + "                    <th>Cilindraje</th>\n"
                        + "                    <th>Opciones</th>\n"
                        + "                  </tr>\n"
                        + "\n"
                        + "                  <tr>\n"
                        + "                    <td>" + vehiculo.getModelo() + "</td>\n"
                        + "                    <td>" + vehiculo.getCilindraje() + "</td>\n"
                        + "                    <td><button class=\"btn btn-primary\" type=\"submit\">Ver historial</button></td>\n"
                        + "                  </tr>\n"
                        + "                  \n"
                        + "              </table></form> <br><br>";
            }

        }
        return rta;
    }

    public String getCitas(){
    
        CitaDAO c = new CitaDAO();
        String rta ="";
        
        List<Cita> citas = c.read();
            for (Cita ci : citas) {
                Persona p = ci.getIdPersona();
                
                rta+="<tr class=\""+ci.getId()+"\">\n" +
"                            <th class=\"enc\" scope=\"row\">"+ci.getId()+"</th>\n" +
"                            <td>"+p.getCedula()+"</td>\n" +
"                            <td>"+p.getNombres()+"</td>                    \n" +
"                            <td>"+p.getCelular()+"</td>\n" +
"                            <td>"+p.getEmail()+"</td>\n" +
"                            <td>"+this.getFecha(ci.getFecha(),ci.getHora())+"</td>\n" +
"                            <td><input   id=\"estado\" hidden value=\""+ci.getEstado()+"\"/>\n" +
                                  "\n<img data-bs-toggle=\"modal\" id=\""+ci.getId()+"\" class=\"mod\" data-bs-target=\"#modal1\"  src=\"img/lupa.png\" style=\"display: block; width: 30px; height: 30px; margin:auto;\"/>\n" +
"\n" +                       "</td>\n" +
"                            <td>\n" +
"                               <div class=\"icons-acciones\">"+ci.getEstado()+"";
                                    if(ci.getEstado().equals("NO ATENDIDO")){
                                        rta+="<div class=\"editt\" id=\""+ci.getId()+"\" data-bs-toggle=\"modal\" data-bs-target=\"#modal2\"><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-pencil-square\" viewBox=\"0 0 16 16\">\n" +
"                                               <path d=\"M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z\"/>\n" +
"                                                <path fill-rule=\"evenodd\" d=\"M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z\"/>\n" +
"                                                   </svg>"
                                           +"</div>";
                                        }
                                    rta+=
"                                </div>\n" +
"                            </td>\n";
                                    if(ci.getEstado().equals("ATENDIDO")){
                
                                        rta+="<td class=\"text-center\"> \n"
                                +            "<form action=\"MostrarFichaTecnica.do\">"//AQUI PONER LA PAGINA DE FICHA TECNICA
                                            +"<input name=\"idCita\" hidden value=\""+ci.getId()+"\"/>"
                                    +        "<button class=\"btn btn-primary\" type=\"submit\" > Ver</button>"
                                +           "</form>\n" +
                                            "</td>\n";
                                    }
                                    if(ci.getEstado().equals("CANCELADA")){
                                        rta+="<td class=\"text-center\">CITA CANCELADA\n"
                                +            
                                            "</td>\n";
                                    }
                                    if(ci.getEstado().equals("NO ATENDIDO") || ci.getEstado().equals("EN PROCESO")){
                                        rta+="<td> \n"+
                                              "<form id=\"confirma"+ci.getId()+"\" action=\"ConfirmaServicioAdmin.do\">"
                                            +"<input name=\"idCitaComfirm\" hidden value=\""+ci.getId()+"\"/>"+
                     "                           <img onclick=\"javascript:enviarMail('"+ci.getIdPersona().getEmail()+","+ci.getId()+"');\" src=\"img/confirmarServ.png\" style=\"display: block; width: 30px; height: 30px; margin:auto;\"/>\n" +
                     "                           \n" +
                                             "</form>\n" +
                 "                           </td>\n";
                                    }
                
                              rta+="</tr>";
            }    
        return rta;
    }
    
    
    
    public String getFecha(Date fecha, Date hora){
            SimpleDateFormat formateador = new SimpleDateFormat(
                 "dd '/' MM '/' yyyy", new Locale("es_ES"));
            SimpleDateFormat formateador2 = new SimpleDateFormat(
                 "hh:mm", new Locale("es_ES"));
            String fechad = formateador.format(fecha);
            String horas = formateador2.format(hora);
        return "Dia: "+fechad.replace(" ", "")+"\nHora: "+horas;
    }
    
    public ArrayList<Dia> cargarHorario(){
        //OBTENGO CITAS NO ATENDIDAS
        List<Cita> citas = this.getCitasNoAtendidas();
        //OBTENGO EL DIA DE HOY
        String diaEntroAReservar = getDia(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault())
                            .toInstant())); //ejm lunes, martes...
        Date fechaRealDeInicio = validarDiaDeBusqueda(diaEntroAReservar);
        ArrayList<Dia> semana = getSemana(fechaRealDeInicio); //OBTENGO LA SEMANA A PARTIR DEL DIA QUE ME PARE
        //ENTRO A REVISAR A PARTIR DE LA FECHA
        for (Cita ci: citas) {
            
            //desde las 8 a las 16
            String diaCita = getDia(ci.getFecha());
            String horaCita = getHora(ci.getHora());
            
            //CUANDO OBTENGA EL DIA DE LA LISTA AUMENTO EL CUPO Y SI SE LLENA ELIMINO LA HORA DE LA SEMANA
            Dia diaSemana = getDiaSemana(semana,diaCita); //dia de la semana de esa cita
            ArrayList<Hora> h = diaSemana.getHoras(); //horas de ese dia
            Hora horaDia = getHoraDia(h,horaCita);   //obtengo la hora de la cita dentro de las horas del dia
            horaDia.aumentarCupo();
            
            if(horaDia.getCupo()==4){
               diaSemana.getHoras().remove(horaDia);
            }
        }
        return semana;
    }
    //PARSEO LA SEMANA CON LAS HORAS A STRING PARA MANIPULARLO EN EL JS
    public String cargarHorarios(){
        
        String rta="";
        ArrayList<Dia> semana = this.cargarHorario();
        
        for(Dia d : semana){
            
            rta+=d.getNombre();
            ArrayList<Hora> horas = d.getHoras();
            
            for (Hora h : horas) {
               rta+=","+h.getHora();
            } 
            rta+=";";
        }
        return rta;
    }
    
    public Hora getHoraDia(ArrayList<Hora> h, String horaCita){
    
        for (Hora ho : h) {
            if(ho.getHora().equals(horaCita)){
                return ho;
            }
        }
        return null;
    }
    
    public String getHora(Date hora){
    
        SimpleDateFormat formateador2 = new SimpleDateFormat(
                 "HH", new Locale("es_ES"));
            String horas = formateador2.format(hora);
            return horas;
    }
    
    public Dia getDiaSemana(ArrayList<Dia> sem, String dia){
        
        for (Dia d : sem) {
            if(d.getNombre().equals(dia)){
                return d;
            }
        }
        System.err.println("No se encontro el dia");
        return null;
    }
    
    public ArrayList<Dia> getSemana(Date diaInicio){
        
        Date dia = diaInicio;
        Calendar calendar = Calendar.getInstance();
        ArrayList<Dia> semana = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
           
            Dia d = new Dia(getDia(dia)); //CREO UN DIA
            semana.add(d); //lo agrego a la semana
            calendar.setTime(dia); //CONFIGUURO EL DIA
            
            calendar.add(Calendar.DAY_OF_WEEK, 1);//LE SUMO UNO
            dia = calendar.getTime();
        }
        return semana;
    }
    
    public Date validarDiaDeBusqueda(String dia){
        
        Date fechaAmandar = new Date();// LA FECHA QUE VOY A USAR
        Calendar calendar = Calendar.getInstance();
        //MODELO DE FECHA QUE QUIERO
        SimpleDateFormat formatearFecha = new SimpleDateFormat("yyyy-MM-dd", new Locale("es_ES"));
        if(dia.equals("DOMINGO")){//O ESTA FUERA DEL HORARIO LABORAL
             //COMIENZO A BUSCAR A PARTIR DEL LUNES en adelante
             calendar.add(Calendar.DAY_OF_WEEK, 1); //AQUI OBTENGO EL DIA(domingo) Y LE SUMO 1
             Date fechaManana = calendar.getTime();
             //LA PARTE STRING DE LA FECHA
             String parteFecha = formatearFecha.format(fechaManana);
             //LA PARTE DE HORAS DE LA FECHA(INICIO HORARIO LABORAL)
             String parteHora = "7:30:00";
             //MODELO DE FORMATO DE FECHA Y HORA A LA QUE VOY A CONVERTIR PARA HACER RESTAS
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            try {
                fechaAmandar = sdf.parse(parteFecha+" "+parteHora);
            } catch (ParseException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
         }else{
            
             Date fechaManana = calendar.getTime();
             //LA PARTE STRING DE LA FECHA
             String parteFecha = formatearFecha.format(fechaManana);
             //LA PARTE DE HORAS DE LA FECHA(INICIO HORARIO LABORAL)
             String parteHora = "7:30:00";
             //MODELO DE FORMATO DE FECHA Y HORA A LA QUE VOY A CONVERTIR PARA COMPARAR MAS ADELANTE
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             try {
                fechaAmandar = sdf.parse(parteFecha+" "+parteHora);
            } catch (ParseException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fechaAmandar;
    }
    
    
    
    public String getDia(Date fecha){
    
        SimpleDateFormat ObtenerDia = new SimpleDateFormat("EEEE");
        String dia = ObtenerDia.format(fecha).toUpperCase();
        return dia;
    }
    
    private List<Cita> getCitasNoAtendidas(){
        CitaDAO c = new CitaDAO();
        List<Cita> citas = c.read();
        List<Cita> citasNoAtendidas = new ArrayList<Cita>();
        for (Cita ci : citas) {
            if(ci.getEstado().equals("NO ATENDIDO")){
                citasNoAtendidas.add(ci);
            }
        }
        return citasNoAtendidas;
    }

    public String mostrarServiciosIndex() {

        ServicioDAO da = new ServicioDAO();
        List<Servicio> servicios = da.read();

        int cantidad = 4;
        if (servicios.size() < 4) {
            cantidad = servicios.size();
        }
        String cardServicios = " ";
        for (int j = 0; j < cantidad; j++) {

            cardServicios += "<div class=\"col-md-6  mb-5 colum\">\n"
                    + "\n"
                    + "                    <a href=" + '"' + servicios.get(j).getImgUrl() + '"' + " data-lightbox=\"galeriaS\" data-title=\"Nombre servicio\"> <img src=" + '"' + servicios.get(j).getImgUrl() + '"' + " alt=\"\"></a>\n"
                    + "                    <div class=\"titulo\">" + servicios.get(j).getNombre() + " </div>\n"
                    + "\n"
                    + "                </div>";

        }

        return cardServicios;
    }

    public String tablaDatosClienteFicha(String cedula) {

        PersonaDAO perdao = new PersonaDAO();
        Persona per = perdao.readPersona(cedula);

        String tabla = "<h4>Datos del Cliente </h4> <br>\n"
                + "                <table  class=\"table table-bordered table-striped table-hover\" >\n"
                + "                    <thead class=\"table-secondary\">\n"
                + "                        <tr>\n"
                + "                            <th class=\"enc\" scope=\"col\">Cédula</th>\n"
                + "                            <th class=\"enc\" scope=\"col\">Nombre</th>\n"
                + "                            <th class=\"enc\" scope=\"col\">Celular</th>\n"
                + "                            <th class=\"enc\" scope=\"col\">Correo Electrónico</th>\n"
                + "\n"
                + "                        </tr>\n"
                + "                    </thead>\n"
                + "                    <tbody style=\"text-align: center\">\n"
                + "                        <tr>\n"
                + "                            <td>" + per.getCedula() + "</td>\n"
                + "                            <td>" + per.getNombres() + " " + per.getApellidos() + "</td>\n"
                + "                            <td>" + per.getCelular() + "</td>\n"
                + "                            <td>" + per.getEmail() + "</td>\n"
                + "                        </tr>\n"
                + "                    </tbody>\n"
                + "                </table>";

        return tabla;
    }

    public String tableDatosVehiculosFicha(String cedula) {

        VehiculoDAO vedao = new VehiculoDAO();
        String tdTable = "";
        List<Vehiculo> vehiculos = vedao.findVehiculosUser(cedula);
        if (!vehiculos.isEmpty()) {
            for (Vehiculo v : vehiculos) {

                tdTable += "             <tr>\n"
                        + "                            <td>" + v.getPlaca() + "</td>\n"
                        + "                            <td>" + v.getIdMarca().getNombre() + "</td>\n"
                        + "                            <td>" + v.getIdTipo().getNombre() + "</td>\n"
                        + "                            <td>" + v.getColor() + "</td>\n"
                        + "                            <td>" + v.getModelo() + "</td>\n"
                        + "                            <td>" + v.getCarroceria() + "</td>\n"
                        + "                            <td>" + v.getCilindraje() + "</td>\n"
                        + "                            <td>" + v.getKilometraje() + "</td>\n"
                        + "                            <td>\n"
                        + "                                <div class=\"icons-acciones\">\n"
                        + "                                    <div>\n"
                        + "                                        <i class=\"fas fa-edit\" data-bs-toggle=\"modal\" data-bs-target=\"#modal2\"></i>Editar\n"
                        + "                                    </div>\n"
                        + "                                </div>\n"
                        + "                            </td>\n"
                        + "                        </tr>";
            }
        } else {
            tdTable = "<td colspan=\"9\">No tiene vehiculos asociados</td>\n";
        }

        return tdTable;
    }

    public String atencionServiciosFicha(String cedula) {

        VehiculoDAO vedao = new VehiculoDAO();
        List<Vehiculo> vehiculos = vedao.findVehiculosUser(cedula);
        AtencionServicioDAO andao = new AtencionServicioDAO();
        List<AtencionServicio> aServicios = new ArrayList<AtencionServicio>();
        String rta = "";

        if (!vehiculos.isEmpty()) {
            for (Vehiculo ve : vehiculos) {

                FichaTecnicaDAO fida = new FichaTecnicaDAO();
                FichaTecnica ficha = fida.findFichaVehiculo(ve.getPlaca());
                andao.findServiciosFicha(ficha.getId(), aServicios);

            }
            Collections.sort(aServicios);
            //System.out.println("SERVICIOS " + servi.toString());
            rta = tableServiciosFicha(aServicios);

        }
        return rta;
    }

    public String tableServiciosFicha(List<AtencionServicio> servi) {
        String tbody = "";
        int i = 1;
        for (AtencionServicio s : servi) {
            tbody += "<tr>\n"
                    + "                            <th class=\"enc\" scope=\"row\">" + i + "</th>\n"
                    + "                            <td>" + stringServicios(s) + "</td>\n"
                    + "                            <td>" + stringProductos(s) + "</td>\n"
                    + "                            <td>" + s.getDescripcion() + "</td>\n"
                    + "                            <td>" + s.formatoFecha(s.getFecha()) + "</td>\n"
                    + "                            <td>" + s.getIdPersona().getNombres() + " " + s.getIdPersona().getApellidos() + "</td>\n"
                    + "                            <td>" + s.getIdFactura().getTotal() + "</td>\n"
                    + "\n"
                    + "                        </tr>";
            i++;
        }
        return tbody;
    }

    public String stringServicios(AtencionServicio a) {
        int i = 1;
        String rta = "";
        DetallesServicioDAO dserdao = new DetallesServicioDAO();
        List<DetallesServicio> detalles = dserdao.findDetalleServicioAtencion(a.getId());
        for (DetallesServicio d : detalles) {
            rta += d.getIdServicio().getNombre() + (detalles.size() > i ? ", " : ".");
            i++;
        }

        return rta;

    }

    public String stringProductos(AtencionServicio a) {
        String rta = "";
        int i = 1;
        DetallesProductoDAO dprodao = new DetallesProductoDAO();
        List<DetallesProducto> detalles = dprodao.findDetalleProductoAtencion(a.getId());
        for (DetallesProducto d : detalles) {
            rta += d.getIdProducto().getNombre() + (detalles.size() > i ? ", " : ".");
            i++;
        }

        return rta;

    }

    public String selectMarca() {

        String rta = "";
        MarcaDAO mdao = new MarcaDAO();
        List<Marca> marca = mdao.read();

        for (Marca m : marca) {

            rta += "       <option value=" + '"' + m.getId() + '"' + ">" + m.getNombre() + "</option>\n";
        }

        return rta;
    }

    public String selectTipo() {

        String rta = "";
        TipoDAO tdao = new TipoDAO();
        List<Tipo> tipo = tdao.read();

        for (Tipo t : tipo) {

            rta += "       <option value=" + '"' + t.getId() + '"' + ">" + t.getNombre() + "</option>\n";
        }

        return rta;
    }
    
    public String getVehiculoClienteAtencion(String cedula){
        String rta = "";
        VehiculoDAO vehiculo = new VehiculoDAO();
        List<Vehiculo> vehiculos = vehiculo.findVehiculosUser(cedula);
        for(Vehiculo ve: vehiculos){
            rta += "       <option value=" + '"' + ve.getPlaca() + '"' + ">" + ve.getPlaca() + " - " + ve.getIdMarca().getNombre() + "</option>\n";
        }
        return rta;
    }
    public String getMecanico(){
        String rta = "";
        PersonaDAO per = new PersonaDAO();
        List<Persona> personas = per.read();
        rta = " <option value=\"0\" selected>" + "Seleccione un Mecánico" + "</option>\n";
        for(Persona p:personas ){
            if(p.getIdRol().getId() == 3){
                String nombre = p.getNombres().split(" ")[0] + " " + p.getApellidos().split(" ")[0];
                rta += " <option value=" + '"' + p.getCedula() + '"' + ">" +nombre + "</option>\n";
                
            }
                
        }
        return rta;
    }
}

