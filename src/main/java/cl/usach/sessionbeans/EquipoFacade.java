/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Cuenta;
import cl.usach.entities.Equipo;
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
public class EquipoFacade extends AbstractFacade<Equipo> implements EquipoFacadeLocal {
    @PersistenceContext(unitName = "cl.usach_Mutliproy-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoFacade() {
        super(Equipo.class);
    }

    @Override
    public Equipo buscarPorCuentaYTablero(Cuenta cuenta, String idTableroExt) {
        Query query;
        query = em.createNamedQuery("Equipo.findByCuentaAndIdTableroExt")
                .setParameter("idCuenta", cuenta)
                .setParameter("idTableroExt", idTableroExt);
        return (Equipo) query.getSingleResult();
    }

    @Override
    public Boolean existeEquipoPorCuentaYTablero(Cuenta cuenta, String idTableroExt) {
        Query query;
        query = em.createNamedQuery("Equipo.findByCuentaAndIdTableroExt")
                .setParameter("idCuenta", cuenta)
                .setParameter("idTableroExt", idTableroExt);
        if(query.getResultList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    
    
}
