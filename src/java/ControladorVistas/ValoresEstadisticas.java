/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.AtencionServicioDAO;
import DAO.CalificacionDAO;
import DAO.CitaDAO;
import DAO.FacturaDAO;
import DAO.PersonaDAO;
import DTO.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jefersonrr
 */
public class ValoresEstadisticas extends HttpServlet {

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
    
        PersonaDAO per = new PersonaDAO();
        CitaDAO cdao = new CitaDAO();
        AtencionServicioDAO atendao = new AtencionServicioDAO();
        CalificacionDAO cadao = new CalificacionDAO();
        FacturaDAO fdao = new FacturaDAO();
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        formatoNumero.setMaximumFractionDigits(0);
      
       request.getSession().setAttribute("numeroCliente",per.cantidadClientes() );
       request.getSession().setAttribute("numeroUsuarios",per.cantidadUsuarios());
       request.getSession().setAttribute("numeroMecanicos",per.cantidadMecanicos());
       request.getSession().setAttribute("numeroCitas",cdao.cantidadCitas());
       request.getSession().setAttribute("numeroCitasCanceladas",cdao.cantidadCitasCanceladas());
       request.getSession().setAttribute("numeroAtencion",atendao.cantidadAtenciones());
       request.getSession().setAttribute("numeroCaificacion",cadao.cantidadCalificacion());
       request.getSession().setAttribute("totalIngresos",formatoNumero.format(fdao.ingresosTotales()));
       request.getRequestDispatcher("./jsp/reportesAdmin.jsp").forward(request, response);
       

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
