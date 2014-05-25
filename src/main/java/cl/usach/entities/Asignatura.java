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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FGT
 */
@Entity
@Table(name = "asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a"),
    @NamedQuery(name = "Asignatura.findByIdAsignatura", query = "SELECT a FROM Asignatura a WHERE a.idAsignatura = :idAsignatura"),
    @NamedQuery(name = "Asignatura.findByNombreAsignatura", query = "SELECT a FROM Asignatura a WHERE a.nombreAsignatura = :nombreAsignatura"),
    @NamedQuery(name = "Asignatura.findByCreditoAsignatura", query = "SELECT a FROM Asignatura a WHERE a.creditoAsignatura = :creditoAsignatura"),
    @NamedQuery(name = "Asignatura.findByHorasDeTrabajoAsignatura", query = "SELECT a FROM Asignatura a WHERE a.horasDeTrabajoAsignatura = :horasDeTrabajoAsignatura"),
    @NamedQuery(name = "Asignatura.findBySemestreAsignatura", query = "SELECT a FROM Asignatura a WHERE a.semestreAsignatura = :semestreAsignatura"),
    @NamedQuery(name = "Asignatura.findByAnoAsignatura", query = "SELECT a FROM Asignatura a WHERE a.anoAsignatura = :anoAsignatura"),
    @NamedQuery(name = "Asignatura.findByCierreAsignatura", query = "SELECT a FROM Asignatura a WHERE a.cierreAsignatura = :cierreAsignatura"),
    @NamedQuery(name = "Asignatura.findByIdUsuario", query = "SELECT a FROM Asignatura a WHERE a.idUsuario = :idUsuario")
})
public class Asignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ASIGNATURA")
    private Integer idAsignatura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_ASIGNATURA")
    private String nombreAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREDITO_ASIGNATURA")
    private int creditoAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORAS_DE_TRABAJO_ASIGNATURA")
    private int horasDeTrabajoAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEMESTRE_ASIGNATURA")
    private int semestreAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANO_ASIGNATURA")
    private int anoAsignatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CIERRE_ASIGNATURA")
    private int cierreAsignatura;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "ID_EQUIPO", referencedColumnName = "ID_EQUIPO")
    @ManyToOne
    private Equipo idEquipo;

    public Asignatura() {
    }

    public Asignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Asignatura(Integer idAsignatura, String nombreAsignatura, int creditoAsignatura, int horasDeTrabajoAsignatura, int semestreAsignatura, int anoAsignatura, int cierreAsignatura) {
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.creditoAsignatura = creditoAsignatura;
        this.horasDeTrabajoAsignatura = horasDeTrabajoAsignatura;
        this.semestreAsignatura = semestreAsignatura;
        this.anoAsignatura = anoAsignatura;
        this.cierreAsignatura = cierreAsignatura;
    }

    public Asignatura(String nombreAsignatura, int creditoAsignatura, int horasDeTrabajoAsignatura, int semestreAsignatura, int anoAsignatura, int cierreAsignatura, Usuario idUsuario) {
        this.nombreAsignatura = nombreAsignatura;
        this.creditoAsignatura = creditoAsignatura;
        this.horasDeTrabajoAsignatura = horasDeTrabajoAsignatura;
        this.semestreAsignatura = semestreAsignatura;
        this.anoAsignatura = anoAsignatura;
        this.cierreAsignatura = cierreAsignatura;
        this.idUsuario = idUsuario;
    }

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public int getCreditoAsignatura() {
        return creditoAsignatura;
    }

    public void setCreditoAsignatura(int creditoAsignatura) {
        this.creditoAsignatura = creditoAsignatura;
    }

    public int getHorasDeTrabajoAsignatura() {
        return horasDeTrabajoAsignatura;
    }

    public void setHorasDeTrabajoAsignatura(int horasDeTrabajoAsignatura) {
        this.horasDeTrabajoAsignatura = horasDeTrabajoAsignatura;
    }

    public int getSemestreAsignatura() {
        return semestreAsignatura;
    }

    public void setSemestreAsignatura(int semestreAsignatura) {
        this.semestreAsignatura = semestreAsignatura;
    }

    public int getAnoAsignatura() {
        return anoAsignatura;
    }

    public void setAnoAsignatura(int anoAsignatura) {
        this.anoAsignatura = anoAsignatura;
    }

    public int getCierreAsignatura() {
        return cierreAsignatura;
    }

    public void setCierreAsignatura(int cierreAsignatura) {
        this.cierreAsignatura = cierreAsignatura;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignatura != null ? idAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.idAsignatura == null && other.idAsignatura != null) || (this.idAsignatura != null && !this.idAsignatura.equals(other.idAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.Asignatura[ idAsignatura=" + idAsignatura + " ]";
    }
    
}
