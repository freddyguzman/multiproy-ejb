/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.TipoActividad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FGT
 */
@Stateless
public class TipoActividadFacade extends AbstractFacade<TipoActividad> implements TipoActividadFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoActividadFacade() {
        super(TipoActividad.class);
    }

    @Override
    public TipoActividad buscarPorNombre(String nombreTipoActividad) {
        Query query;
        query = em.createNamedQuery("TipoActividad.findByNombreTipoActividad")
                .setParameter("nombreTipoActividad", nombreTipoActividad);
        
        return (TipoActividad) query.getSingleResult();
    }

    @Override
    public Boolean existeActividadPorNombre(String nombreTipoActividad) {
        Query query;
        query = em.createNamedQuery("TipoActividad.findByNombreTipoActividad")
                .setParameter("nombreTipoActividad", nombreTipoActividad);
        if(query.getResultList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
}
