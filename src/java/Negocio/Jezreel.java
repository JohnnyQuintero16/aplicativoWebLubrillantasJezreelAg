/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import DAO.ProductoDAO;
import DAO.ServicioDAO;
import DAO.TipoDAO;
import DTO.Producto;
import DTO.Servicio;
import DTO.Tipo;
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
}
