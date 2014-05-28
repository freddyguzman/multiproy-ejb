/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FGT
 */
@Entity
@Table(name = "estado_tarjeta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoTarjeta.findAll", query = "SELECT e FROM EstadoTarjeta e"),
    @NamedQuery(name = "EstadoTarjeta.findByIdEstadoTarjeta", query = "SELECT e FROM EstadoTarjeta e WHERE e.idEstadoTarjeta = :idEstadoTarjeta"),
    @NamedQuery(name = "EstadoTarjeta.findByNombreEstadoTarjeta", query = "SELECT e FROM EstadoTarjeta e WHERE e.nombreEstadoTarjeta = :nombreEstadoTarjeta")})
public class EstadoTarjeta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTADO_TARJETA")
    private Integer idEstadoTarjeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE_ESTADO_TARJETA")
    private String nombreEstadoTarjeta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoTarjeta")
    private List<Tarjeta> tarjetaList;

    public EstadoTarjeta() {
    }

    public EstadoTarjeta(Integer idEstadoTarjeta) {
        this.idEstadoTarjeta = idEstadoTarjeta;
    }

    public EstadoTarjeta(Integer idEstadoTarjeta, String nombreEstadoTarjeta) {
        this.idEstadoTarjeta = idEstadoTarjeta;
        this.nombreEstadoTarjeta = nombreEstadoTarjeta;
    }

    public Integer getIdEstadoTarjeta() {
        return idEstadoTarjeta;
    }

    public void setIdEstadoTarjeta(Integer idEstadoTarjeta) {
        this.idEstadoTarjeta = idEstadoTarjeta;
    }

    public String getNombreEstadoTarjeta() {
        return nombreEstadoTarjeta;
    }

    public void setNombreEstadoTarjeta(String nombreEstadoTarjeta) {
        this.nombreEstadoTarjeta = nombreEstadoTarjeta;
    }

    @XmlTransient
    public List<Tarjeta> getTarjetaList() {
        return tarjetaList;
    }

    public void setTarjetaList(List<Tarjeta> tarjetaList) {
        this.tarjetaList = tarjetaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoTarjeta != null ? idEstadoTarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoTarjeta)) {
            return false;
        }
        EstadoTarjeta other = (EstadoTarjeta) object;
        if ((this.idEstadoTarjeta == null && other.idEstadoTarjeta != null) || (this.idEstadoTarjeta != null && !this.idEstadoTarjeta.equals(other.idEstadoTarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.EstadoTarjeta[ idEstadoTarjeta=" + idEstadoTarjeta + " ]";
    }
    
}
