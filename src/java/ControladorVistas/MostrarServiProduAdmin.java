/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.CitaDAO;
import DAO.ProductoDAO;
import DAO.ServicioDAO;
import DTO.Cita;
import DTO.Persona;
import Negocio.Jezreel;
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
public class MostrarServiProduAdmin extends HttpServlet {

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
            ProductoDAO pro = new ProductoDAO();
            ServicioDAO ser = new ServicioDAO();
            Jezreel j = new Jezreel();
            CitaDAO cita = new CitaDAO();
            int idCita = Integer.parseInt((String)request.getSession().getAttribute("idCitaServicio"));
            String placa = request.getParameter("placa");
            String km = request.getParameter("km");
            System.out.println("PLACA "+placa + " - " + "km " + km);
            Cita user = cita.readCita(idCita);
            Persona per = user.getIdPersona();
            String nameUser = per.getNombres().split(" ")[0] + " " + per.getApellidos().split(" ")[0];
            request.getSession().setAttribute("usuarioCliente", nameUser);
            request.getSession().setAttribute("idCita", idCita);
            request.getSession().setAttribute("productos", pro.readProductosActivos());
            request.getSession().setAttribute("servicios", ser.readServiciosActivos());
            request.getSession().setAttribute("mecanicos", j.getMecanico());
            request.getSession().setAttribute("placa", placa);
            request.getSession().setAttribute("km", km);
            response.sendRedirect("./jsp/adminRegis.jsp");
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
