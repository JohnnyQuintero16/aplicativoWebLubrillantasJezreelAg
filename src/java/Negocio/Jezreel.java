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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
                + "                         <h6>DESCUENTO: $ " + (costo.get(0) + costo.get(1)) * (f.getDescuento() / 100.0) + "</h6>\n"
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
    
    public String mostrarVehiculosUser(String cedula){
    
        PersonaDAO p = new PersonaDAO();
        Persona per = p.readPersona(cedula);
        List<Vehiculo> ve = per.getVehiculoList();
        String rta = "";
        
        if(ve.isEmpty()){
        
            rta = "<table class=\"tabla-vehiculo\">\n" +
                        "                <tr>\n" +
                        "                  <th>No tienes ningun vehiculo registrado :(</th>\n" +
                        "                </tr>\n" +
                        "                <tr>\n" +
                        "                  <td><img src=\"./img/vehicle.png\" style=\"width: 312px; height: 300px; /></td>\n" +
                        "                </tr>\n" +
                        "               \n" +
                        "              </table> <br><br>";
        
        }else{
        
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
        String rta ="<section class=\"home-section\">\n" +
"            <div class=\"title\">            \n" +
"                <div class=\"titulo\">\n" +
"                    <h1>Lista de Agendamientos</h1>\n" +
"                </div>\n" +
"\n" +
"\n" +
"            </div>\n" +
"\n" +
"            <div class=\"table-responsive table-style\">\n" +
"                <table id=\"example\" class=\"table table-bordered table-striped table-hover\">\n" +
"                    <thead class=\"table-secondary\">\n" +
"                        <tr>\n" +
"                            <th class=\"enc\" scope=\"col\">No</th>\n" +
"                            <th class=\"enc\" scope=\"col\">Cédula</th>\n" +
"                            <th class=\"enc\" scope=\"col\">Nombre</th>\n" +
"                            <th class=\"enc\" scope=\"col\">Celular</th>\n" +
"                            <th class=\"enc\" scope=\"col\">Correo Electrónico</th>\n" +
"                            <th class=\"enc\" scope=\"col\">Fecha/Hora</th>\n" +
"                            <th class=\"enc\" scope=\"col\">Servicio</th>\n" +
"                            <th class=\"enc\" scope=\"col\">Asistencia</th>\n" +
"                            <th class=\"enc\" scope=\"col\">Confirmación</th>\n" +
"                        </tr>\n" +
"                    </thead>\n" +
"                    <tbody>";
        List<Cita> citas = c.read();
            for (Cita ci : citas) {
                Persona p = ci.getIdPersona();
                rta+="<tr>\n" +
"                            <th class=\"enc\" scope=\"row\">1</th>\n" +
"                            <td>"+p.getCedula()+"</td>\n" +
"                            <td>"+p.getNombres()+"</td>                    \n" +
"                            <td>"+p.getCelular()+"</td>\n" +
"                            <td>"+p.getEmail()+"</td>\n" +
"                            <td>"+ci.getFecha().toString()+"</td>\n" +
"                            <td> \n" +
"\n" +
"                                <img data-bs-toggle=\"modal\" data-bs-target=\"#modal1"+ci.getId()+"\"  src=\"img/lupa.png\" style=\"display: block; width: 30px; height: 30px; margin:auto;\"/>\n" +
"\n" +
"                            </td>\n" +
"                            <td>\n" +
"                                <div class=\"icons-acciones\">             \n" +
"\n" +
"                                    <div>   \n" +
"                                        <img data-bs-toggle=\"modal\" data-bs-target=\"#modal2\" src=\"img/aprobado.png\" style=\"display: block; width: 30px; height: 30px; margin-left:auto; \"/>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                            </td>\n" +
"\n" +
"\n" +
"                            <td> \n" +
"\n" +
"                                <a href=\"<%=basePath%>index.jsp\"> <img src=\"img/confirmarServ.png\" style=\"display: block; width: 30px; height: 30px;            margin:auto;\"/>\n" +
"                                </a>\n" +
"                            </td>\n" +
"\n" +
"\n" +
"                        </tr>";
            }
        rta+="</tbody>\n" +
"\n" +
"                </table>\n" +
"\n" +
"                <!-- <div class=\"boton\">\n" +
"                    <button type=\"button\" class=\"btn btn-primary btn-lg\">Añadir producto</button>\n" +
"                </div> -->\n" +
"                <!-- Cierre div tabla -->\n" +
"            </div>\n" +
"\n" +
"        </section>";
        
        for (Cita ci : citas) {
            rta+= 
                        "<div class=\"modal fade\" id=\"modal1"+ci.getId()+"\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n" +
                "            <div class=\"modal-dialog \">\n" +
                "                <div class=\"modal-content\">\n" +
                "                    <div class=\"modal-header\">\n" +
                "                        <h5 class=\"modal-title\" id=\"exampleModalLabel\">Detalles Servicio</h5>\n" +
                "                        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n" +
                "                    </div>\n" +
                "                    <div class=\"modal-body\">\n" +
                "                        Servicio "+ci.getDescripcion()+" para el vehículo con placa ";
                if(ci.getEstado().equals("ATENDIDO")){
                    AtencionServicioDAO a = new AtencionServicioDAO();
                    AtencionServicio aten = a.getServicio(ci.getId());
                    FichaTecnica f = aten.getIdFichaTecnica();
                    Vehiculo v = f.getIdVehiculo();
                    
                    rta+=v.getPlaca();
                }        
                        rta+=" ESTADO DEL SERVICIO: "+ci.getEstado()+".\n" +
                "                    </div>\n" +
                "                    <div class=\"modal-footer\">\n" +
                "                        <button type=\"button\" class=\"boton3 \" data-bs-dismiss=\"modal\">Aceptar</button>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";
        }
        
        
        return rta;
    }
}
