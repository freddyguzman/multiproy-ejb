/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FGT
 */
@Entity
@Table(name = "actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a"),
    @NamedQuery(name = "Actividad.findByIdActividad", query = "SELECT a FROM Actividad a WHERE a.idActividad = :idActividad"),
    @NamedQuery(name = "Actividad.findByIdActividadExt", query = "SELECT a FROM Actividad a WHERE a.idActividadExt = :idActividadExt"),
    @NamedQuery(name = "Actividad.findByFechaActividad", query = "SELECT a FROM Actividad a WHERE a.fechaActividad = :fechaActividad"),
    @NamedQuery(name = "Actividad.findByTableroYDesc", query = "SELECT a FROM Actividad a WHERE a.idMiembro.idTablero = :idTablero ORDER BY a.idActividad DESC")
})
public class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ACTIVIDAD")
    private Integer idActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ID_ACTIVIDAD_EXT")
    private String idActividadExt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTIVIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActividad;
    @JoinColumn(name = "ID_MIEMBRO", referencedColumnName = "ID_MIEMBRO")
    @ManyToOne(optional = false)
    private Miembro idMiembro;
    @JoinColumn(name = "ID_TARJETA", referencedColumnName = "ID_TARJETA")
    @ManyToOne
    private Tarjeta idTarjeta;
    @JoinColumn(name = "ID_TIPO_ACTIVIDAD", referencedColumnName = "ID_TIPO_ACTIVIDAD")
    @ManyToOne(optional = false)
    private TipoActividad idTipoActividad;

    public Actividad() {
    }

    public Actividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Actividad(Integer idActividad, String idActividadExt, Date fechaActividad) {
        this.idActividad = idActividad;
        this.idActividadExt = idActividadExt;
        this.fechaActividad = fechaActividad;
    }
    
    public Actividad(String idActividadExt, Date fechaActividad, TipoActividad idTipoActividad) {
        this.idActividadExt = idActividadExt;
        this.fechaActividad = fechaActividad;
        this.idTipoActividad = idTipoActividad;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getIdActividadExt() {
        return idActividadExt;
    }

    public void setIdActividadExt(String idActividadExt) {
        this.idActividadExt = idActividadExt;
    }

    public Date getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(Date fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public Miembro getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(Miembro idMiembro) {
        this.idMiembro = idMiembro;
    }

    public Tarjeta getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Tarjeta idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public TipoActividad getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(TipoActividad idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.Actividad[ idActividad=" + idActividad + " ]";
    }
    
}
