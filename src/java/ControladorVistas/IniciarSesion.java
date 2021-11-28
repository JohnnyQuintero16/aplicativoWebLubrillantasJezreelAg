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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author johnny
 */
public class IniciarSesion extends HttpServlet {

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
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
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

        String cedula = request.getParameter("cedula");
        String clave = request.getParameter("clave");
        PersonaDAO p = new PersonaDAO();

        try {
            HttpSession sesion = request.getSession(true);
            String page = "jsp/iniciarsesion.jsp";
            String msg = "check";
            String nameUser = " ";
            if (p.existePersona(cedula) && !cedula.equals("") && !clave.equals("")) {
                if (p.usuarioValido(cedula, clave)) {
                    sesion.setAttribute("usuario", cedula);
                    Persona perso = p.readPersona(cedula);
                    nameUser = perso.getNombres().split(" ")[0] + " " + perso.getApellidos().split(" ")[0];
                    if(perso.getIdRol().getId() == 1){
                        page = "jsp/adminregis.jsp";
                    }else{
                        page = "index.jsp";
                    }
                    sesion.setAttribute("nameUser", nameUser);
                    response.sendRedirect(page);
                } else {
                    msg = "err"; //El usuario digito mal la clave
                    sesion.setAttribute("mensaje", msg);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                    dispatcher.forward(request, response);
                }
            }else{
                msg = "err"; //El usuario digito mal la clave
                    sesion.setAttribute("mensaje", msg);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                    dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
