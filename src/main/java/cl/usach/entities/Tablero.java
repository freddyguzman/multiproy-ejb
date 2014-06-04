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
@Table(name = "tablero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablero.findAll", query = "SELECT t FROM Tablero t"),
    @NamedQuery(name = "Tablero.findByIdTablero", query = "SELECT t FROM Tablero t WHERE t.idTablero = :idTablero"),
    @NamedQuery(name = "Tablero.findByIdTableroExt", query = "SELECT t FROM Tablero t WHERE t.idTableroExt = :idTableroExt"),
    @NamedQuery(name = "Tablero.findByNombreTablero", query = "SELECT t FROM Tablero t WHERE t.nombreTablero = :nombreTablero"),
    @NamedQuery(name = "Tablero.findByUrlTablero", query = "SELECT t FROM Tablero t WHERE t.urlTablero = :urlTablero"),
    @NamedQuery(name = "Tablero.findByUsuarioProfesor", query = "SELECT t FROM Tablero t WHERE t.idSprintGrupo.idSprintAsignatura.idAsignatura.idUsuario = :idUsuario"),
    @NamedQuery(name = "Tablero.findByidSprintGrupo", query = "SELECT t FROM Tablero t WHERE t.idSprintGrupo = :idSprintGrupo")
})
public class Tablero implements Serializable {
    @JoinColumn(name = "ID_SPRINT_GRUPO", referencedColumnName = "ID_SPRINT_GRUPO")
    @ManyToOne
    private SprintGrupos idSprintGrupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTablero")
    private List<Miembro> miembroList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TABLERO")
    private Integer idTablero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ID_TABLERO_EXT")
    private String idTableroExt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_TABLERO")
    private String nombreTablero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "URL_TABLERO")
    private String urlTablero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTablero")
    private List<Equipo> equipoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTablero")
    private List<Lista> listaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTablero")
    private List<Tarjeta> tarjetaList;

    public Tablero() {
    }

    public Tablero(Integer idTablero) {
        this.idTablero = idTablero;
    }

    public Tablero(Integer idTablero, String idTableroExt, String nombreTablero, String urlTablero) {
        this.idTablero = idTablero;
        this.idTableroExt = idTableroExt;
        this.nombreTablero = nombreTablero;
        this.urlTablero = urlTablero;
    }

    public Tablero(String idTableroExt, String nombreTablero, String urlTablero) {
        this.idTableroExt = idTableroExt;
        this.nombreTablero = nombreTablero;
        this.urlTablero = urlTablero;
    }

    public Integer getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(Integer idTablero) {
        this.idTablero = idTablero;
    }

    public String getIdTableroExt() {
        return idTableroExt;
    }

    public void setIdTableroExt(String idTableroExt) {
        this.idTableroExt = idTableroExt;
    }

    public String getNombreTablero() {
        return nombreTablero;
    }

    public void setNombreTablero(String nombreTablero) {
        this.nombreTablero = nombreTablero;
    }

    public String getUrlTablero() {
        return urlTablero;
    }

    public void setUrlTablero(String urlTablero) {
        this.urlTablero = urlTablero;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    @XmlTransient
    public List<Lista> getListaList() {
        return listaList;
    }

    public void setListaList(List<Lista> listaList) {
        this.listaList = listaList;
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
        hash += (idTablero != null ? idTablero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablero)) {
            return false;
        }
        Tablero other = (Tablero) object;
        if ((this.idTablero == null && other.idTablero != null) || (this.idTablero != null && !this.idTablero.equals(other.idTablero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.Tablero[ idTablero=" + idTablero + " ]";
    }

    @XmlTransient
    public List<Miembro> getMiembroList() {
        return miembroList;
    }

    public void setMiembroList(List<Miembro> miembroList) {
        this.miembroList = miembroList;
    }

    public SprintGrupos getIdSprintGrupo() {
        return idSprintGrupo;
    }

    public void setIdSprintGrupo(SprintGrupos idSprintGrupo) {
        this.idSprintGrupo = idSprintGrupo;
    }
    
}
