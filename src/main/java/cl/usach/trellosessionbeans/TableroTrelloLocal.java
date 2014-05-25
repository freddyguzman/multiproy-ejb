/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.trellosessionbeans;

import cl.usach.entities.Cuenta;
import cl.usach.entities.Equipo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface TableroTrelloLocal {

    List<Equipo> buscarTableros(Cuenta cuenta);

    Boolean checkCuenta(Cuenta cuenta);
    
}
