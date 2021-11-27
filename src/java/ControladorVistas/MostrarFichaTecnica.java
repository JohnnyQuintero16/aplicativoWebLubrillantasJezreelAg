/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.PersonaDAO;
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
 * @author USUARIO
 */
public class MostrarFichaTecnica extends HttpServlet {

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

        
        
        String cedula;
        if(request.getSession().getAttribute("cedula")==null){
            cedula= request.getParameter("cedula");
        
        }else{
        
            cedula = request.getSession().getAttribute("cedula").toString();
            request.getSession().setAttribute("cedula", null);
        }
        PersonaDAO pdao = new PersonaDAO();
        Persona p = pdao.readPersona(cedula);
        Jezreel je = new Jezreel();
        request.getSession().setAttribute("tCliente",je.tablaDatosClienteFicha(cedula));
         request.getSession().setAttribute("nombreCliente",(p.getNombres() + " " + p.getApellidos()));
        request.getSession().setAttribute("tVehiculo",je.tableDatosVehiculosFicha(cedula));
        request.getSession().setAttribute("tAtencion",je.atencionServiciosFicha(cedula));
        request.getSession().setAttribute("selectMarca",je.selectMarca());
        request.getSession().setAttribute("selectTipo",je.selectTipo());
        request.getRequestDispatcher("./jsp/fichaTecnica.jsp").forward(request, response);
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
