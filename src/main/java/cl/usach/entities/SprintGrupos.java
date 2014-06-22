/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.entities;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "sprint_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SprintGrupos.findAll", query = "SELECT s FROM SprintGrupos s"),
    @NamedQuery(name = "SprintGrupos.findByIdSprintGrupo", query = "SELECT s FROM SprintGrupos s WHERE s.idSprintGrupo = :idSprintGrupo"),
    @NamedQuery(name = "SprintGrupos.findByNombreSprintGrupo", query = "SELECT s FROM SprintGrupos s WHERE s.nombreSprintGrupo = :nombreSprintGrupo"),
    @NamedQuery(name = "SprintGrupos.findByObjetivoTecnicoSprintGrupo", query = "SELECT s FROM SprintGrupos s WHERE s.objetivoTecnicoSprintGrupo = :objetivoTecnicoSprintGrupo"),
    @NamedQuery(name = "SprintGrupos.findByObjetivoUsuarioSprintGrupo", query = "SELECT s FROM SprintGrupos s WHERE s.objetivoUsuarioSprintGrupo = :objetivoUsuarioSprintGrupo"),
    @NamedQuery(name = "SprintGrupos.findBySprintAsigntura" , query = "SELECT s FROM SprintGrupos s WHERE s.idSprintAsignatura = :idSprintAsignatura"),
    @NamedQuery(name = "SprintGrupos.findByUsuarioSMaster", query = "SELECT s FROM SprintGrupos s WHERE s.idUsuario = :idUsuario"),
    @NamedQuery(name = "SprintGrupos.findByUsuarioProfesor", query = "SELECT s FROM SprintGrupos s WHERE s.idSprintAsignatura.idAsignatura.idUsuario = :idUsuario"),
    @NamedQuery(name = "SprintGrupos.findByUsuarioSMasterYAsignatura" , query = "SELECT s FROM SprintGrupos s WHERE s.idUsuario = :idUsuario and s.idSprintAsignatura.idAsignatura = :idAsignatura" ),
    @NamedQuery(name = "SprintGrupos.findByAsignaturaGBSprintGrupos", query = "SELECT s FROM SprintGrupos s WHERE s.idSprintAsignatura.idAsignatura = :idAsignatura GROUP BY s.nombreSprintGrupo")
})
public class SprintGrupos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SPRINT_GRUPO")
    private Integer idSprintGrupo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_SPRINT_GRUPO")
    private String nombreSprintGrupo;
    @Size(max = 100)
    @Column(name = "OBJETIVO_TECNICO_SPRINT_GRUPO")
    private String objetivoTecnicoSprintGrupo;
    @Size(max = 100)
    @Column(name = "OBJETIVO_USUARIO_SPRINT_GRUPO")
    private String objetivoUsuarioSprintGrupo;
    @OneToMany(mappedBy = "idSprintGrupo")
    private List<Tablero> tableroList;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "ID_SPRINT_ASIGNATURA", referencedColumnName = "ID_SPRINT_ASIGNATURA")
    @ManyToOne(optional = false)
    private SprintAsignatura idSprintAsignatura;

    public SprintGrupos() {
    }

    public SprintGrupos(Integer idSprintGrupo) {
        this.idSprintGrupo = idSprintGrupo;
    }

    public SprintGrupos(Integer idSprintGrupo, String nombreSprintGrupo) {
        this.idSprintGrupo = idSprintGrupo;
        this.nombreSprintGrupo = nombreSprintGrupo;
    }

    public SprintGrupos(String nombreSprintGrupo, String objetivoTecnicoSprintGrupo, String objetivoUsuarioSprintGrupo, Usuario idUsuario, SprintAsignatura idSprintAsignatura) {
        this.nombreSprintGrupo = nombreSprintGrupo;
        this.objetivoTecnicoSprintGrupo = objetivoTecnicoSprintGrupo;
        this.objetivoUsuarioSprintGrupo = objetivoUsuarioSprintGrupo;
        this.idUsuario = idUsuario;
        this.idSprintAsignatura = idSprintAsignatura;
    }

    

    public Integer getIdSprintGrupo() {
        return idSprintGrupo;
    }

    public void setIdSprintGrupo(Integer idSprintGrupo) {
        this.idSprintGrupo = idSprintGrupo;
    }

    public String getNombreSprintGrupo() {
        return nombreSprintGrupo;
    }

    public void setNombreSprintGrupo(String nombreSprintGrupo) {
        this.nombreSprintGrupo = nombreSprintGrupo;
    }

    public String getObjetivoTecnicoSprintGrupo() {
        return objetivoTecnicoSprintGrupo;
    }

    public void setObjetivoTecnicoSprintGrupo(String objetivoTecnicoSprintGrupo) {
        this.objetivoTecnicoSprintGrupo = objetivoTecnicoSprintGrupo;
    }

    public String getObjetivoUsuarioSprintGrupo() {
        return objetivoUsuarioSprintGrupo;
    }

    public void setObjetivoUsuarioSprintGrupo(String objetivoUsuarioSprintGrupo) {
        this.objetivoUsuarioSprintGrupo = objetivoUsuarioSprintGrupo;
    }

    @XmlTransient
    public List<Tablero> getTableroList() {
        return tableroList;
    }

    public void setTableroList(List<Tablero> tableroList) {
        this.tableroList = tableroList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public SprintAsignatura getIdSprintAsignatura() {
        return idSprintAsignatura;
    }

    public void setIdSprintAsignatura(SprintAsignatura idSprintAsignatura) {
        this.idSprintAsignatura = idSprintAsignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSprintGrupo != null ? idSprintGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SprintGrupos)) {
            return false;
        }
        SprintGrupos other = (SprintGrupos) object;
        if ((this.idSprintGrupo == null && other.idSprintGrupo != null) || (this.idSprintGrupo != null && !this.idSprintGrupo.equals(other.idSprintGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.SprintGrupos[ idSprintGrupo=" + idSprintGrupo + " ]";
    }
    
}
