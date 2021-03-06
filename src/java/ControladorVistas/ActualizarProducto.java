/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.MarcaProductoDAO;
import DAO.ProductoDAO;
import DTO.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author johnny
 */
public class ActualizarProducto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Producto nuevo = new Producto();
            ProductoDAO pro = new ProductoDAO();
            String tipo = request.getParameter("tipo");
            switch (tipo) {
                case "1":
                    tipo = "ACEITES";
                    break;
                case "2":
                    tipo = "FILTROS";
                    break;
                case "3":
                    tipo = "VALVULINAS";
                    break;
                case "4":
                    tipo = "ADITIVOS";
                    break;
                case "5":
                    tipo = "LLANTAS";
                    break;
                case "6":
                    tipo = "BUJIAS";
                    break;
                case "7":
                    tipo = "LUCES";
                    break;
            }
            nuevo.setCodigo(request.getParameter("codigo"));
            nuevo.setNombre(request.getParameter("nombre"));
            nuevo.setReferencia(request.getParameter("referencia"));
            MarcaProductoDAO mar = new MarcaProductoDAO();
            nuevo.setIdMarca(mar.readMarcaProducto(Integer.parseInt(request.getParameter("marca"))));
            String precioUnitario = request.getParameter("precioUnitario").replaceAll("[-+.^:,$]","");;
            nuevo.setPrecioUnitario(Double.parseDouble(precioUnitario));
            String precioVenta = request.getParameter("precioVenta").replaceAll("[-+.^:,$]","");;
            nuevo.setPrecioVenta(Double.parseDouble(precioVenta));
            nuevo.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            nuevo.setTipo(tipo);
            nuevo.setImgUrl(request.getParameter("url"));
            nuevo.setDescripcion(request.getParameter("descripcion"));
            nuevo.setEstado("ACTIVO");
            pro.update(nuevo);
            response.sendRedirect("MostrarProductosAdmin.do");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
