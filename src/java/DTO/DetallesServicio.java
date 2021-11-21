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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "Detalles_Servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesServicio.findAll", query = "SELECT d FROM DetallesServicio d")
    , @NamedQuery(name = "DetallesServicio.findById", query = "SELECT d FROM DetallesServicio d WHERE d.id = :id")
    , @NamedQuery(name = "DetallesServicio.findByCosto", query = "SELECT d FROM DetallesServicio d WHERE d.costo = :costo")})
public class DetallesServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private int costo;
    @JoinColumn(name = "idAtencionServicio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AtencionServicio idAtencionServicio;
    @JoinColumn(name = "idServicio", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Servicio idServicio;

    public DetallesServicio() {
    }

    public DetallesServicio(Integer id) {
        this.id = id;
    }

    public DetallesServicio(Integer id, int costo) {
        this.id = id;
        this.costo = costo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public AtencionServicio getIdAtencionServicio() {
        return idAtencionServicio;
    }

    public void setIdAtencionServicio(AtencionServicio idAtencionServicio) {
        this.idAtencionServicio = idAtencionServicio;
    }

    public Servicio getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Servicio idServicio) {
        this.idServicio = idServicio;
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
        if (!(object instanceof DetallesServicio)) {
            return false;
        }
        DetallesServicio other = (DetallesServicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.DetallesServicio[ id=" + id + " ]";
    }
    
}
