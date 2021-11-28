/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DAO.AtencionServicioDAO;
import DAO.CitaDAO;
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
import DTO.Cita;
import DTO.Marca;
import DTO.Persona;
import DTO.Producto;
import DTO.Servicio;
import DTO.Tipo;
import DTO.Vehiculo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        return cardServicios;

    }

    public String[] mostrarProductos() {

        String[] tipo = {"ACEITES", "FILROS", "VALVULINAS", "ADICTIVOS", "OTROS"};
        ProductoDAO da = new ProductoDAO();
        String[] rta = new String[tipo.length];
        for (int i = 0; i < tipo.length; i++) {

            List<Producto> pt = da.findProductoTipo(tipo[i]);

            if (pt.size() != 0) {
                rta[i] = "";
                for (Producto pro : pt) {

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
            } else {

                rta[i] = "<h4> No se econtraron resultados</h4>";
            }

        }
        return rta;

    }

    public String misServiciosUsu(String cedula) {

        String rta = "";
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
            System.out.println("SERVICIOS " + servi.toString());
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
        System.out.println("SERVICIOS " + servi.toString());
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
            System.out.println("SOY LLOS SER SERVICIOS :" + dser.toString());
            List<DetallesProducto> dpro = pdao.findDetalleProductoAtencion(a.getId());
            Persona mecanico = a.getIdPersona();
            Factura factura = a.getIdFactura();

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
                    + "              <img src=" + '"' + dser.get(0).getIdServicio().getImgUrl() + '"' + " id=\"imgServicio\">\n"
                    + "            </div>\n"
                    + "\n"
                    + "            <!-- texto del servicio -->\n"
                    + "            <div class=\"col-12 col-sm-6 col-md-8 col-lg-8\">\n"
                    + "              <p class=\"card-text\">" + a.getDescripcion() + "</p>\n"
                    + "\n"
                    + "              <!-- boton del servicio -->\n"
                    + "              <a href=\"#\" class=\"btn\" id=\"boton\" type=\"button\"  data-bs-toggle=\"modal\" data-bs-target=" + '"' + "#modal" + i + '"' + ">\n"
                    + "                Ver Servicio\n"
                    + "              </a>\n"
                    + "\n"
                    + "              <!-- ventana modal -->\n"
                    + "              <div class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" id=" + '"' + "modal" + i + '"' + " aria-labelledby=\"modal1\" aria-hidden=\"true\">\n"
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

            rta += costosAtencion(costo, a.getIdFactura());
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

    public String costosAtencion(List<Double> costo, Factura f) {

        return "  <br>\n"
                + "                        <h6>SUBTOTAL: $" + costo.get(0) + "</h6>\n"
                + "                        <hr width=\"30%\">\n"
                + "                        <h6>IVA: $ " + costo.get(1) + "</h6>\n"
                + "                        <hr width=\"30%\">\n"
                + "                         <h6>DESCUENTO: $ " + (Math.round(((costo.get(0) + costo.get(1)) * (f.getDescuento() / 100.0))*100.0)/100.0) + "</h6>\n"
                + "                        <hr width=\"30%\">\n"
                + "                        <h6>TOTAL : $ " + ((costo.get(0) + costo.get(1)) - (costo.get(0) + costo.get(1)) * (f.getDescuento() / 100.0)) + "</h6>\n"
                + "                        <hr width=\"30%\">\n"
                + "                    </div>\n"
                + "                    <div class=\"modal-footer\" id=\"foterM\">\n"
                + "                      <a href=\"#\" class=\"btn\" id=\"boton\" type=\"button\">Calificar servicio</a>\n"
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
                rta += "<form action=\"MisServiciosUsu.do\"><table class=\"tabla-vehiculo\">\n" +
                        "                <tr>\n" +
                        "                  <th>Placa</th>\n" +
                        "                  <th>Marca</th>\n" +
                        "                  <th>Kilometraje</th>\n" +
                        "                </tr>\n" +
                        "                <tr>\n" +
                        "                  <input name=\"placa\" hidden value=\""+vehiculo.getPlaca()+"\" ><td>"+vehiculo.getPlaca()+"</td>\n" +
                        "                  <td>"+vehiculo.getIdMarca().getNombre()+"</td>\n" +
                        "                  <td>"+vehiculo.getKilometraje()+"</td>\n" +
                        "                </tr>\n" +
                        "               \n" +
                        "                <tr>\n" +
                        "                    <th>Modelo</th>\n" +
                        "                    <th>Cilindraje</th>\n" +
                        "                    <th>Opciones</th>\n" +
                        "                  </tr>\n" +
                        "\n" +
                        "                  <tr>\n" +
                        "                    <td>"+vehiculo.getModelo()+"</td>\n" +
                        "                    <td>"+vehiculo.getCilindraje()+"</td>\n" +
                        "                    <td><button class=\"btn btn-primary\" type=\"submit\">Ver historial</button></td>\n" +
                        "                  </tr>\n" +
                        "                  \n" +
                        "              </table></form> <br><br>";
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
                                +            "<form>"//AQUI PONER LA PAGINA DE FICHA TECNICA
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
                                    if(ci.getEstado().equals("NO ATENDIDO")){
                                        rta+="<td> \n"+
                                              "<form name=\"confirma\" action=\"ConfirmaServicioAdmin.do\">"
                                            +"<input name=\"idCitaComfirm\" hidden value=\""+ci.getId()+"\"/>"+
                     "                           <img onclick=\"javascript:enviarMail('"+ci.getIdPersona().getEmail()+"');\" src=\"img/confirmarServ.png\" style=\"display: block; width: 30px; height: 30px; margin:auto;\"/>\n" +
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

    public String mostrarServiciosIndex() {

        ServicioDAO da = new ServicioDAO();
        List<Servicio> servicios = da.read();

        int cantidad = 4;
        if (servicios.size() < 4) {
            cantidad = servicios.size();
        }
        String cardServicios = " ";
        for (int j = 0; j< cantidad ; j++) {

            cardServicios += "<div class=\"col-md-6  mb-5 colum\">\n"
                    + "\n"
                    + "                    <a href=" + '"' + servicios.get(j).getImgUrl() + '"' + " data-lightbox=\"galeriaS\" data-title=\"Nombre servicio\"> <img src=" + '"' + servicios.get(j).getImgUrl() + '"' + " alt=\"\"></a>\n"
                    + "                    <div class=\"titulo\">" + servicios.get(j).getNombre() + " </div>\n"
                    + "\n"
                    + "                </div>";

        }

        return cardServicios;
    }
    
   
   
}
