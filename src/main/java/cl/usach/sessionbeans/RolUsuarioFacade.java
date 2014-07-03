/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.RolUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FGT
 */
@Stateless
public class RolUsuarioFacade extends AbstractFacade<RolUsuario> implements RolUsuarioFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolUsuarioFacade() {
        super(RolUsuario.class);
    }
    
    @Override
    public RolUsuario buscarPorId(int idRolUsuario) {
        Query query;
        query = em.createNamedQuery("RolUsuario.findByIdRolUsuario")
                .setParameter("idRolUsuario", idRolUsuario);
        return (RolUsuario) query.getSingleResult();
    }

    @Override
    public RolUsuario buscarPorNombre(String nombreRolUsuario) {
        Query query;
        query = em.createNamedQuery("RolUsuario.findByNombreRolUsuario")
                .setParameter("nombreRolUsuario", nombreRolUsuario);
        return (RolUsuario) query.getSingleResult();
    }
    
}
