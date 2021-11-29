/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorVistas;

import DAO.MarcaDAO;
import DAO.TipoDAO;
import DAO.VehiculoDAO;
import DTO.Vehiculo;
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
public class UpdateVehiculo extends HttpServlet {

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
       
            VehiculoDAO vedao = new VehiculoDAO();
            Vehiculo ve = vedao.readVehiculo(request.getParameter("placa"));
            System.out.println("VEHICULO @!!!!!!!: " + ve+ ve.getIdPersona().getNombres()+ ve.getIdPersona().getCedula());
            MarcaDAO m = new MarcaDAO();
            TipoDAO t = new TipoDAO();
            
             ve.setIdMarca(m.readMarca(Integer.parseInt(request.getParameter("marca"))));
             System.out.println("MARCA : " + ve.getIdMarca());
             ve.setIdTipo(t.readTipo(Integer.parseInt(request.getParameter("tipo"))));
             System.out.println("MARCA : " + ve.getIdTipo());
             ve.setCarroceria(request.getParameter("carroseria"));
             ve.setCilindraje(Integer.parseInt(request.getParameter("cilindraje")));
             ve.setColor(request.getParameter("color"));
             ve.setModelo(request.getParameter("modelo"));
             ve.setKilometraje(Integer.parseInt(request.getParameter("kilometraje")));
             System.out.println("VOY A REDIRECCIONAR");
             vedao.update(ve);
             System.out.println("VOY YA ACTUALICE");

            request.getSession().setAttribute("cedula",ve.getIdPersona().getCedula());
          
            request.getRequestDispatcher("./MostrarFichaTecnica.do").forward(request, response);
       
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
