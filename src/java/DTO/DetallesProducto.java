/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "Detalles_Producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesProducto.findAll", query = "SELECT d FROM DetallesProducto d"),
    @NamedQuery(name = "DetallesProducto.findById", query = "SELECT d FROM DetallesProducto d WHERE d.id = :id"),
    @NamedQuery(name = "DetallesProducto.findByCosto", query = "SELECT d FROM DetallesProducto d WHERE d.costo = :costo"),
    @NamedQuery(name = "DetallesProducto.findByCantidad", query = "SELECT d FROM DetallesProducto d WHERE d.cantidad = :cantidad")})
public class DetallesProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "costo")
    private double costo;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private short cantidad;
    @JoinColumn(name = "idAtencionServicio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AtencionServicio idAtencionServicio;
    @JoinColumn(name = "idProducto", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Producto idProducto;

    public DetallesProducto() {
    }

    public DetallesProducto(Integer id) {
        this.id = id;
    }

    public DetallesProducto(Integer id, double costo, short cantidad) {
        this.id = id;
        this.costo = costo;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    public AtencionServicio getIdAtencionServicio() {
        return idAtencionServicio;
    }

    public void setIdAtencionServicio(AtencionServicio idAtencionServicio) {
        this.idAtencionServicio = idAtencionServicio;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesProducto)) {
            return false;
        }
        DetallesProducto other = (DetallesProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.DetallesProducto[ id=" + id + " ]";
    }
    
}
