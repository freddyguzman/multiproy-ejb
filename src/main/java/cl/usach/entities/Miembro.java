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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "miembro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miembro.findAll", query = "SELECT m FROM Miembro m"),
    @NamedQuery(name = "Miembro.findByIdMiembro", query = "SELECT m FROM Miembro m WHERE m.idMiembro = :idMiembro"),
    @NamedQuery(name = "Miembro.findByIdMiembroExt", query = "SELECT m FROM Miembro m WHERE m.idMiembroExt = :idMiembroExt"),
    @NamedQuery(name = "Miembro.findByNombreUsuarioMiembro", query = "SELECT m FROM Miembro m WHERE m.nombreUsuarioMiembro = :nombreUsuarioMiembro"),
    @NamedQuery(name = "Miembro.findByidTablero", query = "SELECT m FROM Miembro m WHERE m.idTablero = :idTablero"),
    @NamedQuery(name = "Miembro.findByidTableroYIdMiembroExt", query = "SELECT m FROM Miembro m WHERE m.idTablero = :idTablero and m.idMiembroExt = :idMiembroExt"),
    @NamedQuery(name = "Miembro.findByIdTableroYNombreUsuario", query = "SELECT m FROM Miembro m WHERE m.idTablero = :idTablero and m.nombreUsuarioMiembro = :nombreUsuarioMiembro"),
    @NamedQuery(name = "Miembro.findByIdTableroYIdCuenta", query = "SELECT m FROM Miembro m WHERE m.idTablero = :idTablero and m.idCuenta = :idCuenta")
})
public class Miembro implements Serializable {
    @JoinColumn(name = "ID_TABLERO", referencedColumnName = "ID_TABLERO")
    @ManyToOne(optional = false)
    private Tablero idTablero;
    @JoinColumn(name = "ID_CUENTA", referencedColumnName = "ID_CUENTA")
    @ManyToOne
    private Cuenta idCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ID_MIEMBRO_EXT")
    private String idMiembroExt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MIEMBRO")
    private Integer idMiembro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_USUARIO_MIEMBRO")
    private String nombreUsuarioMiembro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMiembro")
    private List<DetalleUsuarioTarjeta> detalleUsuarioTarjetaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMiembro")
    private List<Actividad> actividadList;

    public Miembro() {
    }

    public Miembro(Integer idMiembro) {
        this.idMiembro = idMiembro;
    }

    public Miembro(Integer idMiembro, String idMiembroExt, String nombreUsuarioMiembro) {
        this.idMiembro = idMiembro;
        this.idMiembroExt = idMiembroExt;
        this.nombreUsuarioMiembro = nombreUsuarioMiembro;
    }

    public Miembro(Tablero idTablero, String idMiembroExt, String nombreUsuarioMiembro) {
        this.idTablero = idTablero;
        this.idMiembroExt = idMiembroExt;
        this.nombreUsuarioMiembro = nombreUsuarioMiembro;
    }

    public Integer getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(Integer idMiembro) {
        this.idMiembro = idMiembro;
    }

    public String getIdMiembroExt() {
        return idMiembroExt;
    }

    public void setIdMiembroExt(String idMiembroExt) {
        this.idMiembroExt = idMiembroExt;
    }

    public String getNombreUsuarioMiembro() {
        return nombreUsuarioMiembro;
    }

    public void setNombreUsuarioMiembro(String nombreUsuarioMiembro) {
        this.nombreUsuarioMiembro = nombreUsuarioMiembro;
    }

    @XmlTransient
    public List<DetalleUsuarioTarjeta> getDetalleUsuarioTarjetaList() {
        return detalleUsuarioTarjetaList;
    }

    public void setDetalleUsuarioTarjetaList(List<DetalleUsuarioTarjeta> detalleUsuarioTarjetaList) {
        this.detalleUsuarioTarjetaList = detalleUsuarioTarjetaList;
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMiembro != null ? idMiembro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miembro)) {
            return false;
        }
        Miembro other = (Miembro) object;
        if ((this.idMiembro == null && other.idMiembro != null) || (this.idMiembro != null && !this.idMiembro.equals(other.idMiembro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.Miembro[ idMiembro=" + idMiembro + " ]";
    }

    public Tablero getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(Tablero idTablero) {
        this.idTablero = idTablero;
    }

    public Cuenta getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Cuenta idCuenta) {
        this.idCuenta = idCuenta;
    }
 
}
