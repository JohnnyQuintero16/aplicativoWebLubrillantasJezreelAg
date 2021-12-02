/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.MarcaDAO;
import DAO.PersonaDAO;
import DAO.TipoDAO;
import DAO.VehiculoDAO;
import DTO.Marca;
import DTO.Persona;
import DTO.Tipo;
import DTO.Vehiculo;
import Negocio.Jezreel;
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
public class AddVehiculo extends HttpServlet {

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
        String modelo = request.getParameter("modelo");
        String color = request.getParameter("color");
        String ruedas = request.getParameter("ruedas");
        String cilindra = request.getParameter("cilindra");
        String kilom = request.getParameter("km");
        String carroce = request.getParameter("carroceria");
        String peso = request.getParameter("peso");
        String motor = request.getParameter("motor");
        String dimension = request.getParameter("dimension");
        String marca = request.getParameter("Marca");
        String tipo = request.getParameter("Tipo");
        VehiculoDAO ve = new VehiculoDAO();
        Vehiculo nuevo =  new Vehiculo();
        MarcaDAO m = new MarcaDAO();
        TipoDAO t = new TipoDAO();
        Jezreel j = new Jezreel();
        nuevo.setPlaca(placa);
        nuevo.setModelo(modelo);
        nuevo.setColor(color);
        nuevo.setRuedas(Short.parseShort(ruedas));
        nuevo.setCilindraje(Integer.parseInt(cilindra));
        nuevo.setKilometraje(Integer.parseInt(kilom));
        nuevo.setCarroceria(carroce);
        nuevo.setPeso(peso);
        nuevo.setNumeroMotor(motor);
        nuevo.setDimension(dimension);
        nuevo.setIdMarca(m.readMarca(Integer.parseInt(marca)));
        nuevo.setIdTipo(t.readTipo(Integer.parseInt(tipo)));
        Persona p = new Persona();
        PersonaDAO per = new PersonaDAO();
        p = per.readPersona((String) request.getSession().getAttribute("cedula"));
        nuevo.setIdPersona(p);
        ve.create(nuevo);
        j.crearFichaVehiculo(placa);
        request.getRequestDispatcher("./MostrarDatosAgendaConfirAdmin.do").forward(request,response);
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
