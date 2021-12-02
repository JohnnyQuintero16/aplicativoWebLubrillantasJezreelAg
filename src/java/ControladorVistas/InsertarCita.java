/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.CitaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cristian
 */
public class InsertarCita extends HttpServlet {

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
        
        String placa = request.getParameter("placa");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String anio = request.getParameter("año");
        String Kilometraje = request.getParameter("marca");
        String fecha[] = request.getParameter("fecha").split("-");
        String hora = request.getParameter("hora");
        String servicio = request.getParameter("servicio");
        
        String horaDia[] = hora.split(",");   //VIERNES,9:00 a.m.
        String horaFormat[] = horaDia[1].split(" "); //9:00,p.m
        int num = Integer.parseInt(horaFormat[1].split(":")[0]);   //9
        String ampm = horaFormat[2]; // a,m
        
        int horaMil = ampm.charAt(0)=='p'?num+12:num;
        String descripcion = placa+","+modelo+","+marca+","+anio+","+Kilometraje+","+servicio;
      
        CitaDAO c = new CitaDAO();
        c.insertarCita(fecha[0],fecha[1],fecha[2], horaMil, request.getSession().getAttribute("usuario").toString(), descripcion);
        request.getRequestDispatcher("MostrarCitasUsu.do").forward(request, response);
        
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
