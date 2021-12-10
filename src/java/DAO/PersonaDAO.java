/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Persona;
import DTO.Rol;
import Persistencia.PersonaJpaController;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class PersonaDAO {

    PersonaJpaController per;

    public PersonaDAO() {
        Conexion con = Conexion.getConexion();
        per = new PersonaJpaController(con.getBd());
    }

    public void create(Persona persona) {
        try {
            per.create(persona);
        } catch (Exception ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Persona> read() {  //devuelve todos los permnos
        return per.findPersonaEntities();
    }

    public Persona readPersona(String id) {
        return per.findPersona(id);
    }

    public boolean existePersona(String cedula) {
        return this.readPersona(cedula) != null;
    }

    public boolean existeCorreo(String correo) {
        List<Persona> personas = this.read();
        boolean existe = false;
        for (Persona p : personas) {
            if (p.getEmail().equals(correo)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public boolean usuarioValido(String cedula, String clave) {
        Persona user = readPersona(cedula);
        return user.getContraseña().equals(clave);
    }

    public void update(Persona d) {
        try {
            per.edit(d);
        } catch (Exception ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String id) throws IllegalOrphanException {

        try {
            per.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void crearPersona(String nombre, String apellido, String contrasenia, String cedula, String correo, String telef, String direccion, Rol rol) {

        Persona p = new Persona(cedula, nombre, apellido, correo, telef, direccion);
        p.setContraseña(contrasenia);
        p.setIdRol(rol);
        p.setUrlFoto("https://i.postimg.cc/9Qys23M0/585e4bf3cb11b227491c339a.png");
        this.create(p);
    }

    public List<Persona> readClientes() {
        List<Persona> clientes = new ArrayList<>();
        List<Persona> p = read();
        for (Persona per : p) {
            System.out.println(per.getIdRol());
            if (per.getIdRol().getId().equals("2")) {
                clientes.add(per);
            }
        }
        return clientes;
    }

    public boolean existeCorreoActualizar(String correo, String cedula) {

        List<Persona> personas = read();
        for (Persona p : personas) {
            if (!p.getCedula().equals(cedula) && p.getEmail().equals(correo)) {
                return true;
            }

        }

        return false;
    }

    public List<Persona> Usuarios() {
        List<Persona> personas = read();
        List<Persona> rta = new ArrayList<Persona>();

        for (Persona p : personas) {
            if (p.getIdRol().getId()==2) {
                rta.add(p);
                System.out.println("USUARIOS !!! " + p.getNombres());
            }

        }
        return rta;
    }

    public List<Persona> Mecanicos() {
        List<Persona> personas = read();
        List<Persona> rta = new ArrayList<Persona>();

        for (Persona p : personas) {
            if (p.getIdRol().getId()==3) {
                rta.add(p);
            }

        }
        return rta;
    }

    public int cantidadUsuarios() {
        int cant = 0;
        List<Persona> personas = Usuarios();
        if (personas != null) {
            cant = personas.size();
        }
        return cant;
    }

    public int cantidadMecanicos() {
        int cant = 0;
        List<Persona> personas = Mecanicos();
        if (personas != null) {
            cant = personas.size();
        }
        return cant;
    }

    public int cantidadClientes() {
        int cant = 0;

        List<Persona> personas = Usuarios();
        for (Persona p : personas) {
            if (!p.getVehiculoList().isEmpty()) {
                cant++;
            }

        }
        return cant;
    }

    /*public List<Object[]> getCantidad(){
    
    return per.getPersonaClienteCount();
    }*/
}
