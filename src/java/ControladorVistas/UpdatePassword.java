/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.PersonaDAO;
import DTO.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jefersonrr
 */
public class UpdatePassword extends HttpServlet {

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
    
        PersonaDAO pdao = new PersonaDAO();
        Persona p = pdao.readPersona(request.getSession().getAttribute("usuario").toString());
        
        String pactual = request.getParameter("actual");
        String newPassword = request.getParameter("newpassword");
        String newPassword2 = request.getParameter("newpassword2");
        if(!newPassword.equals(newPassword2)){
        request.getSession().setAttribute("iguales","no");
        request.getRequestDispatcher("./jsp/editarContraseña.jsp").forward(request, response);
        
        }else if(!p.getContraseña().equals(request.getParameter("actual"))){
        
            request.getSession().setAttribute("passwordcorrecta","no");
            request.getRequestDispatcher("./jsp/editarContraseña.jsp").forward(request, response);
        }else{
        
        p.setContraseña(request.getParameter("newpassword"));
        pdao.update(p);
        request.getSession().setAttribute("passwordcambiada","si");
        request.getRequestDispatcher("./jsp/datosCliente.jsp").forward(request, response);
            
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
