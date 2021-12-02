/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.CitaDAO;
import DAO.VehiculoDAO;
import DTO.Cita;
import DTO.Persona;
import DTO.Vehiculo;
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
 * @author johnny
 */
public class MostrarDatosAgendaConfirAdmin extends HttpServlet {

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
        CitaDAO cita = new CitaDAO();
        VehiculoDAO vehiculo = new VehiculoDAO();
        Jezreel j = new Jezreel();
        int idCita = Integer.parseInt((String) request.getSession().getAttribute("idCitaServicio"));
        Cita user = cita.readCita(idCita);
        Persona per = user.getIdPersona();
        String nameUser = per.getNombres().split(" ")[0] + " " + per.getApellidos().split(" ")[0];
        List<Vehiculo> vehiculos = vehiculo.findVehiculosUser(per.getCedula());
        String vehiculoss = "";
        for(Vehiculo ve : vehiculos){
            vehiculoss += ve.getPlaca()+",";
            vehiculoss += ve.getModelo()+",";
            vehiculoss += ve.getKilometraje()+",";
            vehiculoss += ve.getIdMarca().getNombre()+",";
            vehiculoss += ve.getIdTipo().getNombre()+";";
        }
        request.getSession().setAttribute("usuarioCliente", nameUser);
        request.getSession().setAttribute("IdCliente", per.getCedula());
        request.getSession().setAttribute("idCita", idCita);
        request.getSession().setAttribute("vehiculos", vehiculoss);
        request.getSession().setAttribute("getVehiculo", j.getVehiculoClienteAtencion(per.getCedula()));
        request.getRequestDispatcher("./jsp/datosAgendamiento.jsp").forward(request, response);
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
