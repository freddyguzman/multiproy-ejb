/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Asignatura;
import cl.usach.entities.SprintAsignatura;
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
public class SprintAsignaturaFacade extends AbstractFacade<SprintAsignatura> implements SprintAsignaturaFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SprintAsignaturaFacade() {
        super(SprintAsignatura.class);
    }

    @Override
    public List<SprintAsignatura> buscarPorAsignatura(Asignatura idAsignatura) {
        Query query;
        query = em.createNamedQuery("SprintAsignatura.findByAsignatura")
                .setParameter("idAsignatura", idAsignatura);
        return query.getResultList();
    }
    
}
