/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.kanbanizesessionbeans;

import cl.usach.entities.Equipo;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface ListaKanbanizeLocal {

    void buscarListas(Equipo equipo);
    
}
