/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.AtencionServicioDAO;
import DAO.CalificacionDAO;
import DAO.PersonaDAO;
import DTO.AtencionServicio;
import DTO.Calificacion;
import DTO.CalificacionPK;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jefersonrr
 */
public class CalificarServicio extends HttpServlet {

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
    
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC-5"));
        Date fechaActual = calendar.getTime();
        CalificacionDAO cadao = new CalificacionDAO();
        PersonaDAO per = new PersonaDAO();
        CalificacionPK cpk = new CalificacionPK(request.getSession().getAttribute("usuario").toString(), Integer.parseInt(request.getParameter("atencion")));
        AtencionServicioDAO adao = new AtencionServicioDAO();
        Calificacion ca = new Calificacion(cpk, fechaActual, Short.parseShort(request.getParameter("valor")), request.getParameter("comentario"));
        ca.setAtencionServicio(adao.readAtencionServicio(Integer.parseInt(request.getParameter("atencion"))));
        ca.setPersona(per.readPersona(request.getSession().getAttribute("usuario").toString()));
        cadao.create(ca);
        request.getRequestDispatcher("./MisServiciosUsu.do").forward(request, response);
       
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