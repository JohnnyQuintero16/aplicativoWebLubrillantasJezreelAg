/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.PersonaDAO;
import DAO.RolDAO;
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
public class adminAddCliente extends HttpServlet {

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
        PersonaDAO p = new PersonaDAO();
        String nombres = "", apellidos = "";
        String [] nombreSplit;
        nombreSplit = nombre.split(" ");
        if(nombreSplit.length == 2){
            nombres = nombreSplit[0];
            apellidos = nombreSplit[1];
        }else if(nombreSplit.length == 3){
            nombres = nombreSplit[0] + " " + nombreSplit[1];
            apellidos = nombreSplit[2];
        }else{
            nombres = nombreSplit[0] + " " + nombreSplit[1];
            apellidos = nombreSplit[2] + " " + nombreSplit[3];
        }
        boolean existePersona = p.existePersona(cedula);
        boolean existeCorreo = p.existeCorreo(correo);
        String esta = "existe";
        if (existePersona || existeCorreo) {
            if(existePersona) esta += " Usuario";
            if(existeCorreo) esta += " Correo";
            request.getSession().setAttribute("existe", esta);
        } else {
            RolDAO r = new RolDAO();
            p.crearPersona(nombres, apellidos, contrasenia, cedula, correo, telef, direccion, r.readRol((short) 2));
            esta = "exito";
        }
        //request.getRequestDispatcher("jsp/adminClientes.jsp").forward(request, response);
        response.sendRedirect("jsp/adminClientes.jsp");
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
