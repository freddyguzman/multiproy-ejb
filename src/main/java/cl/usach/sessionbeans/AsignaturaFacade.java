/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Asignatura;
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
public class AsignaturaFacade extends AbstractFacade<Asignatura> implements AsignaturaFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignaturaFacade() {
        super(Asignatura.class);
    }
    
    @Override
    public List<Asignatura> buscarPorIdUsuario(Usuario idUsuario) {
        Query query;
        query = em.createNamedQuery("Asignatura.findByIdUsuario")
                .setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    @Override
    public Asignatura buscarPorId(int idAsignatura) {
        Query query;
        query = em.createNamedQuery("Asignatura.findByIdAsignatura")
                .setParameter("idAsignatura", idAsignatura);
        return (Asignatura) query.getResultList().get(0);
    }

    @Override
    public Boolean existeAsignaturaPorUsuario(Usuario usuario) {
        Query query;
        query = em.createNamedQuery("Asignatura.findByIdUsuario")
                .setParameter("idUsuario", usuario);
        if(query.getResultList().size() > 0){
            return true;
        }else{
            return false;
        }
    }
    
}
