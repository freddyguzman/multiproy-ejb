/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.entities;

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
 * @author FGT
 */
@Entity
@Table(name = "equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findByIdEquipo", query = "SELECT e FROM Equipo e WHERE e.idEquipo = :idEquipo"),
    @NamedQuery(name = "Equipo.findByCuentaAndIdTableroExt", query = "SELECT e FROM Equipo e WHERE e.idCuenta = :idCuenta AND e.idTablero.idTableroExt = :idTableroExt"),
    @NamedQuery(name = "Equipo.findByCuenta", query = "SELECT e FROM Equipo e WHERE e.idCuenta = :idCuenta"),
    @NamedQuery(name = "Equipo.findByTablero", query = "SELECT e FROM Equipo e WHERE e.idTablero = :idTablero"),
    @NamedQuery(name = "Equipo.findByUsuario" , query = "SELECT e FROM Equipo e WHERE e.idCuenta.idUsuario = :idUsuario"),
    @NamedQuery(name = "Equipo.findByIdSprintGrupo", query = "SELECT e FROM Equipo e WHERE e.idTablero.idSprintGrupo = :idSprintGrupo")
})
public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EQUIPO")
    private Integer idEquipo;
    @JoinColumn(name = "ID_CUENTA", referencedColumnName = "ID_CUENTA")
    @ManyToOne(optional = false)
    private Cuenta idCuenta;
    @JoinColumn(name = "ID_TABLERO", referencedColumnName = "ID_TABLERO")
    @ManyToOne(optional = false)
    private Tablero idTablero;

    public Equipo() {
    }

    public Equipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }
    
    public Equipo(Cuenta idCuenta, Tablero idTablero) {
        this.idCuenta = idCuenta;
        this.idTablero = idTablero;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Cuenta getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Cuenta idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Tablero getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(Tablero idTablero) {
        this.idTablero = idTablero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipo != null ? idEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.idEquipo == null && other.idEquipo != null) || (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.Equipo[ idEquipo=" + idEquipo + " ]";
    }
    
}
