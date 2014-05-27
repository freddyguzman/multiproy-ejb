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
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByIdCuenta", query = "SELECT c FROM Cuenta c WHERE c.idCuenta = :idCuenta"),
    @NamedQuery(name = "Cuenta.findByNombreUsuarioCuenta", query = "SELECT c FROM Cuenta c WHERE c.nombreUsuarioCuenta = :nombreUsuarioCuenta"),
    @NamedQuery(name = "Cuenta.findByEmailCuenta", query = "SELECT c FROM Cuenta c WHERE c.emailCuenta = :emailCuenta"),
    @NamedQuery(name = "Cuenta.findByKeyCuenta", query = "SELECT c FROM Cuenta c WHERE c.keyCuenta = :keyCuenta"),
    @NamedQuery(name = "Cuenta.findBySecretCuenta", query = "SELECT c FROM Cuenta c WHERE c.secretCuenta = :secretCuenta"),
    @NamedQuery(name = "Cuenta.findByTokenCuenta", query = "SELECT c FROM Cuenta c WHERE c.tokenCuenta = :tokenCuenta"),
    @NamedQuery(name = "Cuenta.findByUsuario", query = "SELECT c FROM Cuenta c WHERE c.idUsuario = :idUsuario")
})
public class Cuenta implements Serializable {
    @OneToMany(mappedBy = "idCuenta")
    private List<Miembro> miembroList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CUENTA")
    private Integer idCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_USUARIO_CUENTA")
    private String nombreUsuarioCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL_CUENTA")
    private String emailCuenta;
    @Size(max = 70)
    @Column(name = "KEY_CUENTA")
    private String keyCuenta;
    @Size(max = 70)
    @Column(name = "SECRET_CUENTA")
    private String secretCuenta;
    @Size(max = 70)
    @Column(name = "TOKEN_CUENTA")
    private String tokenCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuenta")
    private List<Equipo> equipoList;
    @JoinColumn(name = "ID_TIPO_CUENTA", referencedColumnName = "ID_TIPO_CUENTA")
    @ManyToOne(optional = false)
    private TipoCuenta idTipoCuenta;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Cuenta() {
    }

    public Cuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cuenta(Integer idCuenta, String nombreUsuarioCuenta, String emailCuenta) {
        this.idCuenta = idCuenta;
        this.nombreUsuarioCuenta = nombreUsuarioCuenta;
        this.emailCuenta = emailCuenta;
    }

    public Cuenta(String nombreUsuarioCuenta, String emailCuenta, String keyCuenta, String secretCuenta, String tokenCuenta, TipoCuenta idTipoCuenta, Usuario idUsuario) {
        this.nombreUsuarioCuenta = nombreUsuarioCuenta;
        this.emailCuenta = emailCuenta;
        this.keyCuenta = keyCuenta;
        this.secretCuenta = secretCuenta;
        this.tokenCuenta = tokenCuenta;
        this.idTipoCuenta = idTipoCuenta;
        this.idUsuario = idUsuario;
    }
    
    

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombreUsuarioCuenta() {
        return nombreUsuarioCuenta;
    }

    public void setNombreUsuarioCuenta(String nombreUsuarioCuenta) {
        this.nombreUsuarioCuenta = nombreUsuarioCuenta;
    }

    public String getEmailCuenta() {
        return emailCuenta;
    }

    public void setEmailCuenta(String emailCuenta) {
        this.emailCuenta = emailCuenta;
    }

    public String getKeyCuenta() {
        return keyCuenta;
    }

    public void setKeyCuenta(String keyCuenta) {
        this.keyCuenta = keyCuenta;
    }

    public String getSecretCuenta() {
        return secretCuenta;
    }

    public void setSecretCuenta(String secretCuenta) {
        this.secretCuenta = secretCuenta;
    }

    public String getTokenCuenta() {
        return tokenCuenta;
    }

    public void setTokenCuenta(String tokenCuenta) {
        this.tokenCuenta = tokenCuenta;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    public TipoCuenta getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(TipoCuenta idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.idCuenta == null && other.idCuenta != null) || (this.idCuenta != null && !this.idCuenta.equals(other.idCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usach.entities.Cuenta[ idCuenta=" + idCuenta + " ]";
    }

    @XmlTransient
    public List<Miembro> getMiembroList() {
        return miembroList;
    }

    public void setMiembroList(List<Miembro> miembroList) {
        this.miembroList = miembroList;
    }
    
}
