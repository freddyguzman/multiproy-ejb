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
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface MiembroFacadeLocal {

    void create(Miembro miembro);

    void edit(Miembro miembro);

    void remove(Miembro miembro);

    Miembro find(Object id);

    List<Miembro> findAll();

    List<Miembro> findRange(int[] range);

    int count();

    Miembro buscarPorIdMiembroExt(String idMiembroExt);

    Boolean existeMiembroPorIdMiembroExt(String idMiembroExt);

    List<Miembro> buscarPorTablero(Tablero tablero);

    Miembro buscarMiembroPorIdTableroYIdMiembroExt(Tablero tablero, String idMiembroExt);

    Boolean existeMiembroPorIdTableroYIdMiembroExt(Tablero tablero, String idMiembroExt);

    Miembro buscarPorIdTableroYNombreUsuario(Tablero idTablero, String nombreUsuarioMiembro);

    Boolean existeMiembroPorIdTableroYNombreUsuario(Tablero idTablero, String nombreUsuarioMiembro);

    List<Miembro> buscarPoIdTableroYIdCuenta(Tablero idTablero, Cuenta idCuenta);
    
}
