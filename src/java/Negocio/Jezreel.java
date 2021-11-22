/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DAO.MarcaDAO;
import DAO.PersonaDAO;
import DAO.ProductoDAO;
import DAO.ServicioDAO;
import DAO.TipoDAO;
import DTO.Marca;
import DTO.Persona;
import DTO.Producto;
import DTO.Servicio;
import DTO.Tipo;
import DTO.Vehiculo;
import java.util.ArrayList;
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
                            + "						<h4 class=\"titulo-card\">" + pro.getNombre()+ " </h4>\n"
                            + "						<p  id=\"desc\">" + pro.getDescripcion() +"</p>\n"
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
                rta += "<form action=\"MisServicios.do\"><table class=\"tabla-vehiculo\">\n" +
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
}
