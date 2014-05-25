/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Miembro;
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
    
}
