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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jefersonrr
 */
public class FiltrarReporteCitas extends HttpServlet {

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
       
        Jezreel j = new Jezreel();
        CitaDAO cdao = new CitaDAO();
        List<Cita> citas = cdao.read(); ;
        if(request.getParameter("inicial")!="" && request.getParameter("final")!="" ){

           String [] fecha = request.getParameterValues("inicial")[0].split("-");
           String [] fecha2 = request.getParameterValues("final")[0].split("-");
            request.getSession().setAttribute("seleFecha1", request.getParameter("inicial"));
            request.getSession().setAttribute("seleFecha2", request.getParameter("final"));
                    
          citas = j.filtrarCitaFechas(citas, j.formatofechaInt(fecha),j.formatofechaInt(fecha2) );
        }
        if(!request.getParameter("tipo").equals("0")){
            citas = j.filtrarCitaTipo(citas, request.getParameter("tipo") );
            request.getSession().setAttribute("reporte","Reporte Citas "+  request.getParameter("tipo"));
            request.getSession().setAttribute("seleTipo", request.getParameter("tipo"));
        }else{
        request.getSession().setAttribute("reporte","Reporte Citas");
        }
         if(request.getParameter("inicial")=="" && request.getParameter("final")=="" && request.getParameter("tipo").equals("0") ){
         request.getRequestDispatcher("./MostrarReportesCitas.do").forward(request, response);
         }
         
       Collections.sort(citas);
                
        request.getSession().setAttribute("citas", citas);      
        request.getRequestDispatcher("./jsp/reportesCitas.jsp").forward(request, response);
        
        
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
