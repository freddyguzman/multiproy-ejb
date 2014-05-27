/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.trellosessionbeans;

import cl.usach.entities.Equipo;
import cl.usach.entities.Lista;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface ListaTrelloLocal {

    void buscarListaPorTablero(Equipo equipo);
    
}
