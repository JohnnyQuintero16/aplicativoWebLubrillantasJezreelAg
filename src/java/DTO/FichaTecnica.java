/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "Ficha_Tecnica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FichaTecnica.findAll", query = "SELECT f FROM FichaTecnica f"),
    @NamedQuery(name = "FichaTecnica.findById", query = "SELECT f FROM FichaTecnica f WHERE f.id = :id")})
public class FichaTecnica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idVehiculo", referencedColumnName = "placa")
    @ManyToOne(optional = false)
    private Vehiculo idVehiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFichaTecnica")
    private List<AtencionServicio> atencionServicioList;

    public FichaTecnica() {
    }

    public FichaTecnica(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vehiculo getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Vehiculo idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @XmlTransient
    public List<AtencionServicio> getAtencionServicioList() {
        return atencionServicioList;
    }

    public void setAtencionServicioList(List<AtencionServicio> atencionServicioList) {
        this.atencionServicioList = atencionServicioList;
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
        if (!(object instanceof FichaTecnica)) {
            return false;
        }
        FichaTecnica other = (FichaTecnica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.FichaTecnica[ id=" + id + " ]";
    }
    
}
