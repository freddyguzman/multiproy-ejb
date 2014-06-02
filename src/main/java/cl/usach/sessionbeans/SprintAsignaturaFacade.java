/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.SprintAsignatura;
import cl.usach.kanbanizesessionbeans.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author FGT
 */
@Stateless
public class SprintAsignaturaFacade extends AbstractFacade<SprintAsignatura> implements SprintAsignaturaFacadeLocal {
    @PersistenceContext(unitName = "MultiproyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SprintAsignaturaFacade() {
        super(SprintAsignatura.class);
    }
    
}
