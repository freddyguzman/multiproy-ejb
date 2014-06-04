/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Cuenta;
import cl.usach.entities.Equipo;
import cl.usach.entities.SprintGrupos;
import cl.usach.entities.Tablero;
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
public class EquipoFacade extends AbstractFacade<Equipo> implements EquipoFacadeLocal {
    @PersistenceContext(unitName = "MultiproyPU")
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

    @Override
    public List<Equipo> buscarPorCuenta(Cuenta cuenta) {
        Query query;
        query = em.createNamedQuery("Equipo.findByCuenta")
                .setParameter("idCuenta", cuenta);
        return query.getResultList();
    }

    @Override
    public Boolean existeEquipoPorTablero(Tablero tablero) {
        Query query;
        query = em.createNamedQuery("Equipo.findByTablero")
                .setParameter("idTablero", tablero);
        if(query.getResultList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Equipo buscarUnEquipoPoTablero(Tablero tablero) {
        Query query;
        query = em.createNamedQuery("Equipo.findByTablero")
                .setParameter("idTablero", tablero);
        return (Equipo) query.getResultList().get(0);
    }

    @Override
    public List<Equipo> buscarPorUsuario(Usuario usuario) {
        Query query;
        query = em.createNamedQuery("Equipo.findByUsuario")
                .setParameter("idUsuario", usuario);
        return query.getResultList();
    }

    @Override
    public List<Equipo> buscarPorTablero(Tablero idTablero) {
        Query query;
        query = em.createNamedQuery("Equipo.findByTablero")
                .setParameter("idTablero", idTablero);
        return query.getResultList();
    }   

    @Override
    public List<Equipo> buscarPorIdSprintGrupo(SprintGrupos idSprintGrupo) {
        Query query;
        query = em.createNamedQuery("Equipo.findByIdSprintGrupo")
                .setParameter("idSprintGrupo", idSprintGrupo);
        return query.getResultList();
    }
    
}
