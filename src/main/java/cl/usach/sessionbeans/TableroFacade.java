/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

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
public class TableroFacade extends AbstractFacade<Tablero> implements TableroFacadeLocal {
    @PersistenceContext(unitName = "TodoAgilPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TableroFacade() {
        super(Tablero.class);
    }
    
    @Override
    public Tablero buscarPorId(int idTablero) {
        Query query;
        query = em.createNamedQuery("Tablero.findByIdTablero")
                .setParameter("idTablero", idTablero);
        return (Tablero) query.getSingleResult();
    }
    
    @Override
    public Boolean existeTableroPorIdTableroExt(String idTableroExt) {
        Query query;
        query = em.createNamedQuery("Tablero.findByIdTableroExt")
                .setParameter("idTableroExt", idTableroExt);
        if(query.getResultList().size() > 0){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public Tablero buscarPorIdTableroExt(String idTableroExt) {
        Query query;
        query = em.createNamedQuery("Tablero.findByIdTableroExt")
                .setParameter("idTableroExt", idTableroExt);
        return (Tablero)query.getSingleResult();
    }

    @Override
    public List<Tablero> buscarPorProfesor(Usuario idUsuario) {
        Query query;
        query = em.createNamedQuery("Tablero.findByUsuarioProfesor")
                .setParameter("idUsuario", idUsuario);
        return query.getResultList();
    }
    
}
