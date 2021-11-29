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
 * @author johnny
 */
public class adminUpdateCliente extends HttpServlet {

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
        String nombre = request.getParameter("nombre");
        String cedula = request.getParameter("cedula");
        String correo = request.getParameter("email");
        String telef = request.getParameter("celular");
        String direccion = request.getParameter("direccion");
        String contrasenia = request.getParameter("clave");

        try {
            PersonaDAO p = new PersonaDAO();
            Persona perEditar = p.readPersona(cedula);
            String nombres = "", apellidos = "";
            String[] nombreSplit;
            nombreSplit = nombre.split(" ");            
            if (nombreSplit.length == 2) {
                nombres = nombreSplit[0];
                apellidos = nombreSplit[1];
            } else if (nombreSplit.length == 3) {
                nombres = nombreSplit[0] + " " + nombreSplit[1];
                apellidos = nombreSplit[2];
            } else {
                nombres = nombreSplit[0] + " " + nombreSplit[1];
                apellidos = nombreSplit[2] + " " + nombreSplit[3];
            }
            perEditar.setNombres(nombres);
            perEditar.setApellidos(apellidos);
            perEditar.setCedula(cedula);
            perEditar.setEmail(correo);
            perEditar.setCelular(telef);
            perEditar.setContraseña(contrasenia);
            perEditar.setDirecccion(direccion);
            p.update(perEditar);
            System.out.println(perEditar.getEmail());
            response.sendRedirect("jsp/adminClientes.jsp");
        } catch (Exception e) {
            System.err.println(e.getMessage());
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
