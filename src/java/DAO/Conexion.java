/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Cristian
 */
public class Conexion {
    private static Conexion conexion;
    private EntityManagerFactory bd; 
 
    private Conexion() {
        this.bd=Persistence.createEntityManagerFactory("LubrillantasJezreelPU");
         
    }
     
     
    public static Conexion getConexion()
    {
        if(conexion==null)
        {
            conexion=new Conexion();
        }
    return conexion;
    }
 
    public EntityManagerFactory getBd() {
        return bd;
    }

}
