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
@Table(name = "sprint_asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SprintAsignatura.findAll", query = "SELECT s FROM SprintAsignatura s"),
    @NamedQuery(name = "SprintAsignatura.findByIdSprintAsignatura", query = "SELECT s FROM SprintAsignatura s WHERE s.idSprintAsignatura = :idSprintAsignatura"),
    @NamedQuery(name = "SprintAsignatura.findByNombreSprintAsignatura", query = "SELECT s FROM SprintAsignatura s WHERE s.nombreSprintAsignatura = :nombreSprintAsignatura"),
    @NamedQuery(name = "SprintAsignatura.findByDescripcionSprintAsignatura", query = "SELECT s FROM SprintAsignatura s WHERE s.descripcionSprintAsignatura = :descripcionSprintAsignatura"),
    @NamedQuery(name = "SprintAsignatura.findByFechaInicioSprintAsignatura", query = "SELECT s FROM SprintAsignatura s WHERE s.fechaInicioSprintAsignatura = :fechaInicioSprintAsignatura"),
    @NamedQuery(name = "SprintAsignatura.findByFechaTerminoSprintAsignatura", query = "SELECT s FROM SprintAsignatura s WHERE s.fechaTerminoSprintAsignatura = :fechaTerminoSprintAsignatura"),
    @NamedQuery(name = "SprintAsignatura.findByAsignatura", query = "SELECT s FROM SprintAsignatura s WHERE s.idAsignatura = :idAsignatura")
})
public class SprintAsignatura implements Serializable,Comparable<SprintAsignatura> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SPRINT_ASIGNATURA")
    private Integer idSprintAsignatura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_SPRINT_ASIGNATURA")
    private String nombreSprintAsignatura;
    @Size(max = 200)
    @Column(name = "DESCRIPCION_SPRINT_ASIGNATURA")
    private String descripcionSprintAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO_SPRINT_ASIGNATURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioSprintAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_TERMINO_SPRINT_ASIGNATURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTerminoSprintAsignatura;
    @JoinColumn(name = "ID_ASIGNATURA", referencedColumnName = "ID_ASIGNATURA")
    @ManyToOne(optional = false)
    private Asignatura idAsignatura;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSprintAsignatura")
    private List<SprintGrupos> sprintGruposList;

    public SprintAsignatura() {
    }

    public SprintAsignatura(Integer idSprintAsignatura) {
        this.idSprintAsignatura = idSprintAsignatura;
    }

    public SprintAsignatura(Integer idSprintAsignatura, String nombreSprintAsignatura, Date fechaInicioSprintAsignatura, Date fechaTerminoSprintAsignatura) {
        this.idSprintAsignatura = idSprintAsignatura;
        this.nombreSprintAsignatura = nombreSprintAsignatura;
        this.fechaInicioSprintAsignatura = fechaInicioSprintAsignatura;
        this.fechaTerminoSprintAsignatura = fechaTerminoSprintAsignatura;
    }

    public SprintAsignatura(String nombreSprintAsignatura, String descripcionSprintAsignatura, Date fechaInicioSprintAsignatura, Date fechaTerminoSprintAsignatura, Asignatura idAsignatura) {
        this.nombreSprintAsignatura = nombreSprintAsignatura;
        this.descripcionSprintAsignatura = descripcionSprintAsignatura;
        this.fechaInicioSprintAsignatura = fechaInicioSprintAsignatura;
        this.fechaTerminoSprintAsignatura = fechaTerminoSprintAsignatura;
        this.idAsignatura = idAsignatura;
    }

    public Integer getIdSprintAsignatura() {
        return idSprintAsignatura;
    }

    public void setIdSprintAsignatura(Integer idSprintAsignatura) {
        this.idSprintAsignatura = idSprintAsignatura;
    }

    public String getNombreSprintAsignatura() {
        return nombreSprintAsignatura;
    }

    public void setNombreSprintAsignatura(String nombreSprintAsignatura) {
        this.nombreSprintAsignatura = nombreSprintAsignatura;
    }

    public String getDescripcionSprintAsignatura() {
        return descripcionSprintAsignatura;
    }

    public void setDescripcionSprintAsignatura(String descripcionSprintAsignatura) {
        this.descripcionSprintAsignatura = descripcionSprintAsignatura;
    }

    public Date getFechaInicioSprintAsignatura() {
        return fechaInicioSprintAsignatura;
    }

    public void setFechaInicioSprintAsignatura(Date fechaInicioSprintAsignatura) {
        this.fechaInicioSprintAsignatura = fechaInicioSprintAsignatura;
    }

    public Date getFechaTerminoSprintAsignatura() {
        return fechaTerminoSprintAsignatura;
    }

    public void setFechaTerminoSprintAsignatura(Date fechaTerminoSprintAsignatura) {
        this.fechaTerminoSprintAsignatura = fechaTerminoSprintAsignatura;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    @XmlTransient
    public List<SprintGrupos> getSprintGruposList() {
        return sprintGruposList;
    }

    public void setSprintGruposList(List<SprintGrupos> sprintGruposList) {
        this.sprintGruposList = sprintGruposList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSprintAsignatura != null ? idSprintAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SprintAsignatura)) {
            return false;
        }
        SprintAsignatura other = (SprintAsignatura) object;
        if ((this.idSprintAsignatura == null && other.idSprintAsignatura != null) || (this.idSprintAsignatura != null && !this.idSprintAsignatura.equals(other.idSprintAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.SprintAsignatura[ idSprintAsignatura=" + idSprintAsignatura + " ]";
    }
    
    @Override
    public int compareTo(SprintAsignatura sprintA){
        if (this.fechaInicioSprintAsignatura.after(sprintA.getFechaInicioSprintAsignatura()))
            return 1;
        else if (this.fechaInicioSprintAsignatura.equals(sprintA.getFechaInicioSprintAsignatura()))
            return 0;
        else 
            return -1;
    }    
}
