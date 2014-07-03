/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Tablero;
import cl.usach.entities.Tarjeta;
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
public class TarjetaFacade extends AbstractFacade<Tarjeta> implements TarjetaFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetaFacade() {
        super(Tarjeta.class);
    }

    @Override
    public Tarjeta buscarPorIdTarjetaExt(String idTarjetaExt) {
        Query query;
        query = em.createNamedQuery("Tarjeta.findByIdTarjetaExt")
                .setParameter("idTarjetaExt", idTarjetaExt);
        return (Tarjeta) query.getSingleResult();
    }

    @Override
    public Boolean existeTarjetaPorIdTarjetaExt(String idTarjetaExt) {
        Query query;
        query = em.createNamedQuery("Tarjeta.findByIdTarjetaExt")
                .setParameter("idTarjetaExt", idTarjetaExt);
        if(query.getResultList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<Tarjeta> buscarPorTablero(Tablero tablero) {
        Query query;
        query = em.createNamedQuery("Tarjeta.findByIdTablero")
                .setParameter("idTablero", tablero);
        return query.getResultList();
    }
    
}
