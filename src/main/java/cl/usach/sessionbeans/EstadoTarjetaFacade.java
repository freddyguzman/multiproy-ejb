/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.EstadoTarjeta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FGT
 */
@Stateless
public class EstadoTarjetaFacade extends AbstractFacade<EstadoTarjeta> implements EstadoTarjetaFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoTarjetaFacade() {
        super(EstadoTarjeta.class);
    }

    @Override
    public EstadoTarjeta buscarPorNombreEstadoTarjeta(String nombreEstadoTarjeta) {
        Query query;
        query = em.createNamedQuery("EstadoTarjeta.findByNombreEstadoTarjeta")
                .setParameter("nombreEstadoTarjeta", nombreEstadoTarjeta);
        return (EstadoTarjeta) query.getSingleResult();
    }
    
}
