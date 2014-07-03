/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Cuenta;
import cl.usach.entities.Miembro;
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
public class MiembroFacade extends AbstractFacade<Miembro> implements MiembroFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MiembroFacade() {
        super(Miembro.class);
    }

    @Override
    public Miembro buscarPorIdMiembroExt(String idMiembroExt) {
        Query query;
        query = em.createNamedQuery("Miembro.findByIdMiembroExt")
                .setParameter("idMiembroExt", idMiembroExt);
        return (Miembro) query.getSingleResult();
    }

    @Override
    public Boolean existeMiembroPorIdMiembroExt(String idMiembroExt) {
        Query query;
        query = em.createNamedQuery("Miembro.findByIdMiembroExt")
                .setParameter("idMiembroExt", idMiembroExt);
        if(query.getResultList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<Miembro> buscarPorTablero(Tablero tablero) {
        Query query;
        query = em.createNamedQuery("Miembro.findByidTablero")
                .setParameter("idTablero", tablero);
        return query.getResultList();
    }

    @Override
    public Miembro buscarMiembroPorIdTableroYIdMiembroExt(Tablero tablero, String idMiembroExt) {
        Query query;
        query = em.createNamedQuery("Miembro.findByidTableroYIdMiembroExt")
                .setParameter("idTablero", tablero)
                .setParameter("idMiembroExt", idMiembroExt);
        return (Miembro) query.getSingleResult();
    }   

    @Override
    public Boolean existeMiembroPorIdTableroYIdMiembroExt(Tablero tablero, String idMiembroExt) {
        Query query;
        query = em.createNamedQuery("Miembro.findByidTableroYIdMiembroExt")
                .setParameter("idTablero", tablero)
                .setParameter("idMiembroExt", idMiembroExt);
        if(query.getResultList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Miembro buscarPorIdTableroYNombreUsuario(Tablero idTablero, String nombreUsuarioMiembro) {
        Query query;
        query = em.createNamedQuery("Miembro.findByIdTableroYNombreUsuario")
                .setParameter("idTablero", idTablero)
                .setParameter("nombreUsuarioMiembro", nombreUsuarioMiembro);
        return (Miembro) query.getSingleResult();
    }

    @Override
    public Boolean existeMiembroPorIdTableroYNombreUsuario(Tablero idTablero, String nombreUsuarioMiembro) {
        Query query;
        query = em.createNamedQuery("Miembro.findByIdTableroYNombreUsuario")
                .setParameter("idTablero", idTablero)
                .setParameter("nombreUsuarioMiembro", nombreUsuarioMiembro);
        
        if(query.getResultList().isEmpty()) return false;
        return true;
    }

    @Override
    public List<Miembro> buscarPoIdTableroYIdCuenta(Tablero idTablero, Cuenta idCuenta) {
        Query query;
        query = em.createNamedQuery("Miembro.findByIdTableroYIdCuenta")
                .setParameter("idTablero", idTablero)
                .setParameter("idCuenta", idCuenta);
        return query.getResultList();
    }
    
}
