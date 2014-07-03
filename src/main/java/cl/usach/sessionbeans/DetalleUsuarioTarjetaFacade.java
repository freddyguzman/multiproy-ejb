/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.DetalleUsuarioTarjeta;
import cl.usach.entities.Lista;
import cl.usach.entities.Miembro;
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
public class DetalleUsuarioTarjetaFacade extends AbstractFacade<DetalleUsuarioTarjeta> implements DetalleUsuarioTarjetaFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleUsuarioTarjetaFacade() {
        super(DetalleUsuarioTarjeta.class);
    }

    @Override
    public DetalleUsuarioTarjeta buscarPorIdTarjetaYIdMiembro(Tarjeta idTarjeta, Miembro idMiembro) {
        Query query;
        query = em.createNamedQuery("DetalleUsuarioTarjeta.finByIdTarjetaYIdMiembro")
                .setParameter("idTarjeta", idTarjeta)
                .setParameter("idMiembro", idMiembro);
        return (DetalleUsuarioTarjeta) query.getSingleResult();
    }

    @Override
    public Boolean existeDetallePorIdTarjetaYIdMiembro(Tarjeta idTarjeta, Miembro idMiembro) {
        Query query;
        query = em.createNamedQuery("DetalleUsuarioTarjeta.finByIdTarjetaYIdMiembro")
                .setParameter("idTarjeta", idTarjeta)
                .setParameter("idMiembro", idMiembro);
        if(query.getResultList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<DetalleUsuarioTarjeta> buscarPorIdTarjeta(Tarjeta idTarjeta) {
        Query query;
        query = em.createNamedQuery("DetalleUsuarioTarjeta.finByIdTarjeta")
                .setParameter("idTarjeta", idTarjeta);
        return query.getResultList();
    }   

    @Override
    public List<DetalleUsuarioTarjeta> buscarPorIdMiembro(Miembro idMiembro) {
        Query query;
        query = em.createNamedQuery("DetalleUsuarioTarjeta.findByIdMiembro")
                .setParameter("idMiembro", idMiembro);
        return query.getResultList();
    }

    @Override
    public List<DetalleUsuarioTarjeta> buscarPorIdMiembroYIdTablero(Miembro idMiembro, Tablero idTablero) {
        Query query;
        query = em.createNamedQuery("DetalleUsuarioTarjeta.findByIdMiembroYIdTablero")
                .setParameter("idMiembro", idMiembro)
                .setParameter("idTablero", idTablero);
        return query.getResultList();
    }

    @Override
    public List<DetalleUsuarioTarjeta> buscarPorIdMiembroYIdTableroYNoLista(Miembro idMiembro, Tablero idTablero, Lista idLista) {
        Query query;
        query = em.createNamedQuery("DetalleUsuarioTarjeta.findByIdMiembroYIdTableroYNoLista")
                .setParameter("idMiembro", idMiembro)
                .setParameter("idTablero", idTablero)
                .setParameter("idLista", idLista);
        return query.getResultList();
    }

    @Override
    public Boolean existeDetalleUsuarioTarjetaPorIdTarjeta(Tarjeta idTarjeta) {
        Query query;
        query = em.createNamedQuery("DetalleUsuarioTarjeta.finByIdTarjeta")
                .setParameter("idTarjeta", idTarjeta);
        if(query.getResultList().isEmpty()) return false;
        else return true;
    }
    
}
