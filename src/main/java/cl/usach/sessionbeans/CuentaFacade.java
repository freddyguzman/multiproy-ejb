/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Cuenta;
import cl.usach.entities.Usuario;
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
public class CuentaFacade extends AbstractFacade<Cuenta> implements CuentaFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }
    
    @Override
    public Cuenta buscarPorId(int idCuenta) {
        Query query;
        query = em.createNamedQuery("Cuenta.findByIdCuenta")
                .setParameter("idCuenta", idCuenta);
        return (Cuenta) query.getSingleResult();
    }

    @Override
    public List<Cuenta> buscarPorUsuario(Usuario usuario) {
        Query query;
        query = em.createNamedQuery("Cuenta.findByUsuario")
                .setParameter("idUsuario", usuario);
        return query.getResultList();
    }

    @Override
    public Boolean existeCuentaPorUsuarioCuenta(String usuarioCuenta) {
        Query query;
        query = em.createNamedQuery("Cuenta.findByNombreUsuarioCuenta")
                .setParameter("nombreUsuarioCuenta", usuarioCuenta);
        if(query.getResultList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Cuenta buscarPorNombreUsuarioCuenta(String nombreUsuarioCuenta) {
        Query query;
        query = em.createNamedQuery("Cuenta.findByNombreUsuarioCuenta")
                .setParameter("nombreUsuarioCuenta", nombreUsuarioCuenta);
        return (Cuenta) query.getSingleResult();
    }
    
    
}
