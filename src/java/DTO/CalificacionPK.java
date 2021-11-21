/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
<<<<<<< HEAD
 * @author USUARIO
=======
 * @author Cristian
>>>>>>> Test
 */
@Embeddable
public class CalificacionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "idPersona")
    private String idPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAtencion")
    private int idAtencion;

    public CalificacionPK() {
    }

    public CalificacionPK(String idPersona, int idAtencion) {
        this.idPersona = idPersona;
        this.idAtencion = idAtencion;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        hash += (int) idAtencion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalificacionPK)) {
            return false;
        }
        CalificacionPK other = (CalificacionPK) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        if (this.idAtencion != other.idAtencion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.CalificacionPK[ idPersona=" + idPersona + ", idAtencion=" + idAtencion + " ]";
    }
    
}
