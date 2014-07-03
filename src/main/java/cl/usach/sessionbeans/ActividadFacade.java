/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Actividad;
import cl.usach.entities.Tablero;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FGT
 */
@Stateless
public class ActividadFacade extends AbstractFacade<Actividad> implements ActividadFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActividadFacade() {
        super(Actividad.class);
    }

    @Override
    public Actividad buscarPorIdActividadExt(String idActividadExt) {
        Query query;
        query = em.createNamedQuery("Actividad.findByIdActividadExt")
                .setParameter("idActividadExt", idActividadExt);
        return (Actividad) query.getSingleResult();
    }

    @Override
    public Boolean existeActividadPorIdActividadExt(String idActividadExt) {
        Query query;
        query = em.createNamedQuery("Actividad.findByIdActividadExt")
                .setParameter("idActividadExt", idActividadExt);
        if(query.getResultList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Actividad buscarUltimaActividad(Tablero tablero) {
        Query query;
        query = em.createNamedQuery("Actividad.findByTableroYDesc")
                .setParameter("idTablero", tablero)
                .setMaxResults(1);
        if(!query.getResultList().isEmpty()){
            return (Actividad) query.getResultList().get(0);
        }
        return null;
    }

    @Override
    public Boolean existeActividadPorTablero(Tablero tablero) {
        Query query;
        query = em.createNamedQuery("Actividad.findByTableroYDesc")
                .setParameter("idTablero", tablero)
                .setMaxResults(1);
        if(query.getResultList().isEmpty()){
            return false; 
        }
        return true;
    }
    
    
}
