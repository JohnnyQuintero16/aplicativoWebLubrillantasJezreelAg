/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.AtencionServicioDAO;
import DAO.CitaDAO;
import DTO.AtencionServicio;
import DTO.Cita;
import Negocio.Jezreel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cristian
 */
public class CitasAdmin extends HttpServlet {

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
        
        Jezreel j = new Jezreel();
        CitaDAO c = new CitaDAO();
        
        AtencionServicioDAO a = new AtencionServicioDAO();
        
        List<Cita> ci = c.read();
        String citasNoAtendidas ="";
        String citasAtendidas="";
        for (Cita aten: ci) {
            if(!aten.getEstado().equals("ATENDIDO")){
                citasNoAtendidas+=aten.getId()+",";
                citasNoAtendidas+=aten.getDescripcion()+";";
            }else{
                citasAtendidas+=aten.getId()+",";
                AtencionServicio s = a.getServicio(aten.getId());
                citasAtendidas+=s.getDescripcion()+",";
                citasAtendidas+=s.getIdFichaTecnica().getIdVehiculo().getPlaca()+",";
                citasAtendidas+=aten.getEstado()+";";
            }
            
        }
        
        
        request.getSession().removeAttribute("citas");
        request.getSession().removeAttribute("atendida");
        request.getSession().removeAttribute("noatendida");
        
        request.getSession().setAttribute("atendida", citasAtendidas);
        request.getSession().setAttribute("noatendida", citasNoAtendidas);
        request.getSession().setAttribute("citas", j.getCitas());
        
        
//        request.getSession().setAttribute("cita", new CitaDAO());
        request.getRequestDispatcher("jsp/citasAdmin.jsp").forward(request, response);
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
