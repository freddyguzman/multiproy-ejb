/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Asignatura;
import cl.usach.entities.SprintAsignatura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface SprintAsignaturaFacadeLocal {

    void create(SprintAsignatura sprintAsignatura);

    void edit(SprintAsignatura sprintAsignatura);

    void remove(SprintAsignatura sprintAsignatura);

    SprintAsignatura find(Object id);

    List<SprintAsignatura> findAll();

    List<SprintAsignatura> findRange(int[] range);

    int count();

    List<SprintAsignatura> buscarPorAsignatura(Asignatura idAsignatura);
    
}
