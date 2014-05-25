/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans.kanbanizebeans;

import cl.usach.entities.Cuenta;
import cl.usach.entities.Equipo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface TableroKanbanizeLocal {

    List<Equipo> buscarTableros(Cuenta cuenta);
    
    public Boolean checkCuenta(Cuenta cuenta);
    
}
