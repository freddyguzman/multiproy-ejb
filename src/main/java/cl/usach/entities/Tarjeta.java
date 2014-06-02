/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FGT
 */
@Entity
@Table(name = "tarjeta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarjeta.findAll", query = "SELECT t FROM Tarjeta t"),
    @NamedQuery(name = "Tarjeta.findByIdTarjeta", query = "SELECT t FROM Tarjeta t WHERE t.idTarjeta = :idTarjeta"),
    @NamedQuery(name = "Tarjeta.findByIdTarjetaExt", query = "SELECT t FROM Tarjeta t WHERE t.idTarjetaExt = :idTarjetaExt"),
    @NamedQuery(name = "Tarjeta.findByNombreTarjeta", query = "SELECT t FROM Tarjeta t WHERE t.nombreTarjeta = :nombreTarjeta"),
    @NamedQuery(name = "Tarjeta.findByFechaLimiteTarjeta", query = "SELECT t FROM Tarjeta t WHERE t.fechaLimiteTarjeta = :fechaLimiteTarjeta"),
    @NamedQuery(name = "Tarjeta.findByIdTablero", query = "SELECT t FROM Tarjeta t WHERE t.idTablero = :idTablero")
})
public class Tarjeta implements Serializable,Comparable<Tarjeta> {
    @Column(name = "FECHA_CREACION_TARJETA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacionTarjeta;
    @JoinColumn(name = "ID_ESTADO_TARJETA", referencedColumnName = "ID_ESTADO_TARJETA")
    @ManyToOne(optional = false)
    private EstadoTarjeta idEstadoTarjeta;
    @Column(name = "FECHA_INICIO_TARJETA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioTarjeta;
    @Column(name = "FECHA_FINAL_TARJETA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalTarjeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ID_TARJETA_EXT")
    private String idTarjetaExt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TARJETA")
    private Integer idTarjeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_TARJETA")
    private String nombreTarjeta;
    @Column(name = "FECHA_LIMITE_TARJETA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimiteTarjeta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarjeta")
    private List<DetalleUsuarioTarjeta> detalleUsuarioTarjetaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTarjeta")
    private List<Actividad> actividadList;
    @JoinColumn(name = "ID_LISTA", referencedColumnName = "ID_LISTA")
    @ManyToOne(optional = false)
    private Lista idLista;
    @JoinColumn(name = "ID_TABLERO", referencedColumnName = "ID_TABLERO")
    @ManyToOne(optional = false)
    private Tablero idTablero;

    public Tarjeta() {
    }

    public Tarjeta(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Tarjeta(Integer idTarjeta, String idTarjetaExt, String nombreTarjeta) {
        this.idTarjeta = idTarjeta;
        this.idTarjetaExt = idTarjetaExt;
        this.nombreTarjeta = nombreTarjeta;
    }

    public Tarjeta(String idTarjetaExt, String nombreTarjeta, Date fechaLimiteTarjeta, Lista idLista, Tablero idTablero) {
        this.idTarjetaExt = idTarjetaExt;
        this.nombreTarjeta = nombreTarjeta;
        this.fechaLimiteTarjeta = fechaLimiteTarjeta;
        this.idLista = idLista;
        this.idTablero = idTablero;
    }

    public Tarjeta(EstadoTarjeta idEstadoTarjeta, String idTarjetaExt, String nombreTarjeta, Date fechaLimiteTarjeta, Lista idLista, Tablero idTablero) {
        this.idEstadoTarjeta = idEstadoTarjeta;
        this.idTarjetaExt = idTarjetaExt;
        this.nombreTarjeta = nombreTarjeta;
        this.fechaLimiteTarjeta = fechaLimiteTarjeta;
        this.idLista = idLista;
        this.idTablero = idTablero;
    }

    public Integer getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getIdTarjetaExt() {
        return idTarjetaExt;
    }

    public void setIdTarjetaExt(String idTarjetaExt) {
        this.idTarjetaExt = idTarjetaExt;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public Date getFechaLimiteTarjeta() {
        return fechaLimiteTarjeta;
    }

    public void setFechaLimiteTarjeta(Date fechaLimiteTarjeta) {
        this.fechaLimiteTarjeta = fechaLimiteTarjeta;
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

    public Lista getIdLista() {
        return idLista;
    }

    public void setIdLista(Lista idLista) {
        this.idLista = idLista;
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
        hash += (idTarjeta != null ? idTarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarjeta)) {
            return false;
        }
        Tarjeta other = (Tarjeta) object;
        if ((this.idTarjeta == null && other.idTarjeta != null) || (this.idTarjeta != null && !this.idTarjeta.equals(other.idTarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.Tarjeta[ idTarjeta=" + idTarjeta + " ]";
    }

    public Date getFechaInicioTarjeta() {
        return fechaInicioTarjeta;
    }

    public void setFechaInicioTarjeta(Date fechaInicioTarjeta) {
        this.fechaInicioTarjeta = fechaInicioTarjeta;
    }

    public Date getFechaFinalTarjeta() {
        return fechaFinalTarjeta;
    }

    public void setFechaFinalTarjeta(Date fechaFinalTarjeta) {
        this.fechaFinalTarjeta = fechaFinalTarjeta;
    }

    public EstadoTarjeta getIdEstadoTarjeta() {
        return idEstadoTarjeta;
    }

    public void setIdEstadoTarjeta(EstadoTarjeta idEstadoTarjeta) {
        this.idEstadoTarjeta = idEstadoTarjeta;
    }

    public Date getFechaCreacionTarjeta() {
        return fechaCreacionTarjeta;
    }

    public void setFechaCreacionTarjeta(Date fechaCreacionTarjeta) {
        this.fechaCreacionTarjeta = fechaCreacionTarjeta;
    }
    
    @Override
    public int compareTo(Tarjeta tarjeta){
        if (this.fechaCreacionTarjeta.after(tarjeta.getFechaCreacionTarjeta()))
            return 1;
        else if (this.fechaCreacionTarjeta.equals(tarjeta.getFechaCreacionTarjeta()))
            return 0;
        else 
            return -1;
    }
}
