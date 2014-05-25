/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.TipoCuenta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author FGT
 */
@Stateless
public class TipoCuentaFacade extends AbstractFacade<TipoCuenta> implements TipoCuentaFacadeLocal {
    @PersistenceContext(unitName = "cl.usach_Mutliproy-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCuentaFacade() {
        super(TipoCuenta.class);
    }
    
    @Override
    public TipoCuenta buscarPorId(int idTipoCuenta) {
        Query query;
        query = em.createNamedQuery("TipoCuenta.findByIdTipoCuenta")
                .setParameter("idTipoCuenta", idTipoCuenta);
        return (TipoCuenta) query.getSingleResult();
    }
    
}
