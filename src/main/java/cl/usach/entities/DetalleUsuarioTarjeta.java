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
@Table(name = "detalle_usuario_tarjeta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleUsuarioTarjeta.findAll", query = "SELECT d FROM DetalleUsuarioTarjeta d"),
    @NamedQuery(name = "DetalleUsuarioTarjeta.findByIdDetalleUsuarioTarjeta", query = "SELECT d FROM DetalleUsuarioTarjeta d WHERE d.idDetalleUsuarioTarjeta = :idDetalleUsuarioTarjeta"),
    @NamedQuery(name = "DetalleUsuarioTarjeta.finByIdTarjetaYIdMiembro", query = "SELECT d FROM DetalleUsuarioTarjeta d WHERE d.idTarjeta = :idTarjeta and d.idMiembro = :idMiembro"),
    @NamedQuery(name = "DetalleUsuarioTarjeta.finByIdTarjeta", query = "SELECT d FROM DetalleUsuarioTarjeta d WHERE d.idTarjeta = :idTarjeta" ),
    @NamedQuery(name = "DetalleUsuarioTarjeta.findByIdMiembro", query = "SELECT d FROM DetalleUsuarioTarjeta d WHERE d.idMiembro = :idMiembro"),
    @NamedQuery(name = "DetalleUsuarioTarjeta.findByIdMiembroYIdTablero", 
            query = "SELECT d FROM DetalleUsuarioTarjeta d WHERE d.idMiembro = :idMiembro and d.idMiembro.idTablero = :idTablero"),
    @NamedQuery(name = "DetalleUsuarioTarjeta.findByIdMiembroYIdTableroYNoLista", 
            query = "SELECT d FROM DetalleUsuarioTarjeta d WHERE d.idMiembro = :idMiembro and d.idMiembro.idTablero = :idTablero and d.idTarjeta.idLista != :idLista")
})
public class DetalleUsuarioTarjeta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DETALLE_USUARIO_TARJETA")
    private Integer idDetalleUsuarioTarjeta;
    @JoinColumn(name = "ID_TARJETA", referencedColumnName = "ID_TARJETA")
    @ManyToOne(optional = false)
    private Tarjeta idTarjeta;
    @JoinColumn(name = "ID_MIEMBRO", referencedColumnName = "ID_MIEMBRO")
    @ManyToOne(optional = false)
    private Miembro idMiembro;

    public DetalleUsuarioTarjeta() {
    }

    public DetalleUsuarioTarjeta(Integer idDetalleUsuarioTarjeta) {
        this.idDetalleUsuarioTarjeta = idDetalleUsuarioTarjeta;
    }

    public DetalleUsuarioTarjeta(Tarjeta idTarjeta, Miembro idMiembro) {
        this.idTarjeta = idTarjeta;
        this.idMiembro = idMiembro;
    }

    public Integer getIdDetalleUsuarioTarjeta() {
        return idDetalleUsuarioTarjeta;
    }

    public void setIdDetalleUsuarioTarjeta(Integer idDetalleUsuarioTarjeta) {
        this.idDetalleUsuarioTarjeta = idDetalleUsuarioTarjeta;
    }

    public Tarjeta getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Tarjeta idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Miembro getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(Miembro idMiembro) {
        this.idMiembro = idMiembro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleUsuarioTarjeta != null ? idDetalleUsuarioTarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleUsuarioTarjeta)) {
            return false;
        }
        DetalleUsuarioTarjeta other = (DetalleUsuarioTarjeta) object;
        if ((this.idDetalleUsuarioTarjeta == null && other.idDetalleUsuarioTarjeta != null) || (this.idDetalleUsuarioTarjeta != null && !this.idDetalleUsuarioTarjeta.equals(other.idDetalleUsuarioTarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.DetalleUsuarioTarjeta[ idDetalleUsuarioTarjeta=" + idDetalleUsuarioTarjeta + " ]";
    }
    
}
