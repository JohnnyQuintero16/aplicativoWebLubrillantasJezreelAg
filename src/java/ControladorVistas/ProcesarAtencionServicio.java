/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.CitaDAO;
import DTO.Factura;
import Negocio.Jezreel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author johnny
 */
public class ProcesarAtencionServicio extends HttpServlet {

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
        
        String [] productos = request.getParameterValues("idp");
        String [] servicios = request.getParameterValues("ids");
        String [] cntPro = request.getParameterValues("cantidadProducto");
        String km = request.getParameter("kilometraje");
        String descripcion = request.getParameter("descri");
        String idCita = (String) request.getSession().getAttribute("idCitaServicio");
        ArrayList<String> lista = new ArrayList<>();
        CitaDAO c = new CitaDAO();
        c.actualizarCita(Integer.parseInt(idCita));
        Jezreel j = new Jezreel();
        int n = cntPro.length;
        int i = 1;
        while(n>0){
            if(!(j.productoDisponible(productos[productos.length-i], Integer.parseInt(cntPro[n-1])))){
                request.setAttribute("error", "erroPro");
                request.getRequestDispatcher("./jsp/adminRegis.jsp").forward(request, response);
            }else{
                lista.add(productos[productos.length-i] + "," + cntPro[n-1]);
            }
            i++;
            n--;
        }
        int idcita = Integer.parseInt(idCita);
        Factura factura = j.crearFactura(lista, 0, Integer.parseInt(idCita));
        String placa = (String)request.getSession().getAttribute("placa");
        j.crearFichaVehiculo(placa);
        j.crearAtencionServicio(Integer.parseInt(km), "Esto es una prueba", idcita, placa, factura);
        
        j.registrarItemServicio(idcita, servicios, factura);
        
        response.sendRedirect("CitasAdmin.do");
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
