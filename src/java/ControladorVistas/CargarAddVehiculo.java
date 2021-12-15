/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.CitaDAO;
import DTO.Cita;
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
public class CargarAddVehiculo extends HttpServlet {

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
        String cedulaCliente = request.getParameter("cedula");
        int idCita = Integer.parseInt((String) request.getSession().getAttribute("idCitaServicio"));
        CitaDAO c = new CitaDAO();
        Jezreel j = new Jezreel();
        Cita cita = c.readCita(idCita);
        
        String [] citas = cita.getDescripcion().split(",");
        request.getSession().setAttribute("placaAdd", citas[0]);
        request.getSession().setAttribute("modeloAdd", citas[1]);
        request.getSession().setAttribute("marcaAdd", citas[2]);
        request.getSession().setAttribute("kmAdd", citas[4]);
        request.getSession().setAttribute("tipo", j.selectTipo());
        request.getSession().setAttribute("marca", j.selectMarca());
        request.getSession().setAttribute("cedula", cedulaCliente);
        response.sendRedirect("./jsp/FichaTecnicaAdd.jsp");
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
