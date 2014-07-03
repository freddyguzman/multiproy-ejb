/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Asignatura;
import cl.usach.entities.SprintAsignatura;
import cl.usach.entities.SprintGrupos;
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
public class SprintGruposFacade extends AbstractFacade<SprintGrupos> implements SprintGruposFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SprintGruposFacade() {
        super(SprintGrupos.class);
    }

    @Override
    public List<SprintGrupos> buscarPorSprintAsignatura(SprintAsignatura idSprintAsignatura) {
        Query query;
        query = em.createNamedQuery("SprintGrupos.findBySprintAsigntura")
                .setParameter("idSprintAsignatura", idSprintAsignatura);
        return query.getResultList();
    }

    @Override
    public List<SprintGrupos> buscarPorUsuario(Usuario idUsuario) {
        Query query;
        query = em.createNamedQuery("SprintGrupos.findByUsuarioSMaster")
                .setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    @Override
    public Boolean existeSprintGrupoPorUsuario(Usuario idUsuario) {
        Query query;
        query = em.createNamedQuery("SprintGrupos.findByUsuarioSMaster")
                .setParameter("idUsuario", idUsuario);
        if(query.getResultList().isEmpty()) return false;
        return true;
    }

    @Override
    public SprintGrupos buscarPorId(int idSprintGrupo) {
        Query query;
        query = em.createNamedQuery("SprintGrupos.findByIdSprintGrupo")
                .setParameter("idSprintGrupo", idSprintGrupo);
        if(!query.getResultList().isEmpty())
            return (SprintGrupos) query.getSingleResult();
        return null;
    }

    @Override
    public List<SprintGrupos> buscarPorProfesor(Usuario idUsuario) {
        Query query;
        query = em.createNamedQuery("SprintGrupos.findByUsuarioProfesor")
                .setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }

    @Override
    public List<SprintGrupos> buscarPorUsuarioSMasterYAsignatura(Usuario idUsuario, Asignatura idAsignatura) {
        Query query;
        query = em.createNamedQuery("SprintGrupos.findByUsuarioSMasterYAsignatura")
                .setParameter("idUsuario", idUsuario)
                .setParameter("idAsignatura", idAsignatura);
        return query.getResultList();
    }

    @Override
    public Boolean existePorUsuarioSMasterYAsignatura(Usuario idUsuario, Asignatura idAsignatura) {
        Query query;
        query = em.createNamedQuery("SprintGrupos.findByUsuarioSMasterYAsignatura")
                .setParameter("idUsuario", idUsuario)
                .setParameter("idAsignatura", idAsignatura);
        if(query.getResultList().isEmpty()) return false;
        return true;
    }

    @Override
    public List<SprintGrupos> buscarPorAsignaturasGBSprintGrupo(Asignatura idAsignatura) {
        Query query;
        query = em.createNamedQuery("SprintGrupos.findByAsignaturaGBSprintGrupos")
                .setParameter("idAsignatura", idAsignatura);
        return query.getResultList();
    }
    
    
}
