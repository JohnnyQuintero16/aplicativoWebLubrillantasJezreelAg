/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.ServicioDAO;
import DTO.Servicio;
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
public class ActualizarServicio extends HttpServlet {

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

        try {
            ServicioDAO serdao = new ServicioDAO();
            String tipo = request.getParameter("tipo");
            switch (tipo) {
                case "1":
                    tipo = "ACEITES";
                    break;
                case "2":
                    tipo = "FILTROS";
                    break;
                case "3":
                    tipo = "VALVULINAS";
                    break;
                case "4":
                    tipo = "ADITIVOS";
                    break;
                case "5":
                    tipo = "LLANTAS";
                    break;
                case "6":
                    tipo = "BUJIAS";
                    break;
                case "7":
                    tipo = "LUCES";
                    break;
            }
            Servicio ser = serdao.readServicio(Integer.parseInt(request.getParameter("id").toString()));
            ser.setDescripcion(request.getParameter("descripcion").toString());
            ser.setDuracion((Short.parseShort(request.getParameter("duracion").toString())));
            ser.setNombre(request.getParameter("nombre").toString());
            ser.setImgUrl(request.getParameter("img").toString());
            ser.setTipoProdcuto(tipo);
            serdao.update(ser);
            request.getRequestDispatcher("./MostrarServiciosAdmin.do").forward(request, response);
        } catch (Exception e) {

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
