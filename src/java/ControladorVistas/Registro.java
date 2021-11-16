/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.PersonaDAO;
import DAO.RolDAO;
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
<<<<<<< HEAD:src/java/ControladorVistas/IniciarSesion.java
public class IniciarSesion extends HttpServlet {
=======
public class Registro extends HttpServlet {
>>>>>>> 2c843f8987a74614bc5637f8026a7a37f125a8f9:src/java/ControladorVistas/Registro.java

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
<<<<<<< HEAD:src/java/ControladorVistas/IniciarSesion.java
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String c = request.getParameter("cedula");
        String cla = request.getParameter("clave");
        if(c.equals("88243257") && cla.equals("1111")){
            request.getSession().setAttribute("rta", "Correcto");
            request.getRequestDispatcher("./html/nosotros.jsp");
            request.getRequestDispatcher("./html/nosotros.jsp").forward(request, response);
        }else{
            request.getSession().setAttribute("rta", "inCorrecto");
            request.getRequestDispatcher("./index.jsp").forward(request, response);
        }
    }
=======
            
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String contrasenia = request.getParameter("password");
            String cedula = request.getParameter("cedula");
            String correo = request.getParameter("correo");
            String telef = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            PersonaDAO p = new PersonaDAO();
            boolean existe = p.existePersona(cedula);
            
            if(existe){
                String esta = "existe";
                request.getSession().setAttribute("existe", esta);
                request.getRequestDispatcher("html/registrarse.jsp").forward(request, response);
            }
            else{
                RolDAO r = new RolDAO();
                p.crearPersona(nombre,apellido,contrasenia,cedula,correo,telef,direccion,r.readRol((short)2));
                request.getRequestDispatcher("html/iniciarsesion.jsp").forward(request, response);
            }
            
                
            }
            
        
    
>>>>>>> 2c843f8987a74614bc5637f8026a7a37f125a8f9:src/java/ControladorVistas/Registro.java

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
