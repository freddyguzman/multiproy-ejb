/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.RolUsuario;
import cl.usach.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FGT
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario buscarPorId(int idUsuario){
        Query query;
        query = em.createNamedQuery("Usuario.findByIdUsuario")
                .setParameter("idUsuario", idUsuario);
        return (Usuario) query.getSingleResult();
    }
    
    @Override
    public List<Usuario> buscarPorNombre(String nombreUsuario){
        Query query;
        query = em.createNamedQuery("Usuario.findByNombreUsuario")
                .setParameter("nombreUsuario", nombreUsuario);
        return query.getResultList();
    }
    
    @Override
    public Usuario buscarPorLogin(String loginUsuario){
        Query query;
        query = em.createNamedQuery("Usuario.findByLoginUsuario")
                .setParameter("loginUsuario", loginUsuario);
        return (Usuario) query.getSingleResult();
    }

    @Override
    public List<Usuario> buscarPorIdRolUsuario(RolUsuario idRolUsuario) {
        Query query;
        query = em.createNamedQuery("Usuario.findByRolUsuario")
                .setParameter("idRolUsuario", idRolUsuario);
        return query.getResultList();
    }
}
