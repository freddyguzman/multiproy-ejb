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
@Table(name = "lista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lista.findAll", query = "SELECT l FROM Lista l"),
    @NamedQuery(name = "Lista.findByIdLista", query = "SELECT l FROM Lista l WHERE l.idLista = :idLista"),
    @NamedQuery(name = "Lista.findByIdListaExt", query = "SELECT l FROM Lista l WHERE l.idListaExt = :idListaExt"),
    @NamedQuery(name = "Lista.findByNombreLista", query = "SELECT l FROM Lista l WHERE l.nombreLista = :nombreLista"),
    @NamedQuery(name = "Lista.findByPosicion", query = "SELECT l FROM Lista l WHERE l.posicion = :posicion"),
    @NamedQuery(name = "Lista.findByIdTablero", query = "SELECT l FROM Lista l WHERE l.idTablero = :idTablero"),
    @NamedQuery(name = "Lista.findByIdTableroPorPos", query = "SELECT l FROM Lista l WHERE l.idTablero = :idTablero ORDER BY l.posicion"),
    @NamedQuery(name = "Lista.findByIdTableroYPrimera", query = "SELECT l FROM Lista l WHERE l.idTablero = :idTablero AND l.posicion = 1")
})
public class Lista implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSICION")
    private int posicion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ID_LISTA_EXT")
    private String idListaExt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LISTA")
    private Integer idLista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_LISTA")
    private String nombreLista;
    @JoinColumn(name = "ID_TABLERO", referencedColumnName = "ID_TABLERO")
    @ManyToOne(optional = false)
    private Tablero idTablero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLista")
    private List<Tarjeta> tarjetaList;

    public Lista() {
    }

    public Lista(Integer idLista) {
        this.idLista = idLista;
    }

    public Lista(Integer idLista, String idListaExt, String nombreLista, int posicion) {
        this.idLista = idLista;
        this.idListaExt = idListaExt;
        this.nombreLista = nombreLista;
        this.posicion = posicion;
    }

    public Lista(String idListaExt, String nombreLista, int posicion, Tablero idTablero) {
        this.idListaExt = idListaExt;
        this.nombreLista = nombreLista;
        this.posicion = posicion;
        this.idTablero = idTablero;
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public String getIdListaExt() {
        return idListaExt;
    }

    public void setIdListaExt(String idListaExt) {
        this.idListaExt = idListaExt;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }


    public Tablero getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(Tablero idTablero) {
        this.idTablero = idTablero;
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
        hash += (idLista != null ? idLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lista)) {
            return false;
        }
        Lista other = (Lista) object;
        if ((this.idLista == null && other.idLista != null) || (this.idLista != null && !this.idLista.equals(other.idLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.Lista[ idLista=" + idLista + " ]";
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
}
