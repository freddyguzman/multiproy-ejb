/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Asignatura;
import cl.usach.entities.Cuenta;
import cl.usach.entities.Equipo;
import cl.usach.entities.SprintGrupos;
import cl.usach.entities.Tablero;
import cl.usach.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface EquipoFacadeLocal {

    void create(Equipo equipo);

    void edit(Equipo equipo);

    void remove(Equipo equipo);

    Equipo find(Object id);

    List<Equipo> findAll();

    List<Equipo> findRange(int[] range);

    int count();

    Equipo buscarPorCuentaYTablero(Cuenta cuenta, String idTableroExt);

    Boolean existeEquipoPorCuentaYTablero(Cuenta cuenta, String idTableroExt);

    List<Equipo> buscarPorCuenta(Cuenta cuenta);

    Boolean existeEquipoPorTablero(Tablero tablero);

    Equipo buscarUnEquipoPoTablero(Tablero tablero);

    List<Equipo> buscarPorUsuario(Usuario usuario);

    List<Equipo> buscarPorTablero(Tablero idTablero);

    List<Equipo> buscarPorIdSprintGrupo(SprintGrupos idSprintGrupo);

    Equipo buscarPorId(int idEquipo);

    List<Equipo> buscarPorUsuarioGBYAsignatura(Usuario idUsuario);

    List<Equipo> buscarPorUsuarioyAsignatura(Usuario idUsuario, Asignatura idAsignatura);

    List<Usuario> buscarUsuariosPorAsignatura(Asignatura idAsignatura);

    List<Equipo> buscarPorNombreSprintGrupo(String nombreSprintGrupo);
    
}
