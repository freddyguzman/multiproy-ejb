/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Lista;
import cl.usach.entities.Tablero;
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
public class ListaFacade extends AbstractFacade<Lista> implements ListaFacadeLocal {
    @PersistenceContext(unitName = "MultiproyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaFacade() {
        super(Lista.class);
    }

    @Override
    public Lista buscarPorIdExt(String idListaExt) {
        Query query;
        query = em.createNamedQuery("Lista.findByIdListaExt")
                .setParameter("idListaExt", idListaExt);
        return (Lista) query.getSingleResult();
    }

    @Override
    public Boolean existePorIdExt(String idListaExt) {
        Query query;
        query = em.createNamedQuery("Lista.findByIdListaExt")
                .setParameter("idListaExt", idListaExt);
        if(query.getResultList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<Lista> buscarPorTablero(Tablero tablero) {
        Query query;
        query = em.createNamedQuery("Lista.findByIdTablero")
                .setParameter("idTablero", tablero);
        return query.getResultList();
    }
    
}
