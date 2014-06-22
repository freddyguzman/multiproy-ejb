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
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface SprintGruposFacadeLocal {

    void create(SprintGrupos sprintGrupos);

    void edit(SprintGrupos sprintGrupos);

    void remove(SprintGrupos sprintGrupos);

    SprintGrupos find(Object id);

    List<SprintGrupos> findAll();

    List<SprintGrupos> findRange(int[] range);

    int count();

    List<SprintGrupos> buscarPorSprintAsignatura(SprintAsignatura idSprintAsignatura);

    List<SprintGrupos> buscarPorUsuario(Usuario idUsuario);

    Boolean existeSprintGrupoPorUsuario(Usuario idUsuario);

    SprintGrupos buscarPorId(int idSprintGrupo);

    List<SprintGrupos> buscarPorProfesor(Usuario idUsuario);

    List<SprintGrupos> buscarPorUsuarioSMasterYAsignatura(Usuario idUsuario, Asignatura idAsignatura);

    Boolean existePorUsuarioSMasterYAsignatura(Usuario idUsuario, Asignatura idAsignatura);

    List<SprintGrupos> buscarPorAsignaturasGBSprintGrupo(Asignatura idAsignatura);
    
}
