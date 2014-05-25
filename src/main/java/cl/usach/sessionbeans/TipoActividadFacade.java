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

/**
 *
 * @author FGT
 */
@Stateless
public class TipoActividadFacade extends AbstractFacade<TipoActividad> implements TipoActividadFacadeLocal {
    @PersistenceContext(unitName = "cl.usach_Mutliproy-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoActividadFacade() {
        super(TipoActividad.class);
    }
    
}
