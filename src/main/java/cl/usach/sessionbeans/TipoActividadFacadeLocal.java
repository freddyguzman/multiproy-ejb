/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.TipoActividad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface TipoActividadFacadeLocal {

    void create(TipoActividad tipoActividad);

    void edit(TipoActividad tipoActividad);

    void remove(TipoActividad tipoActividad);

    TipoActividad find(Object id);

    List<TipoActividad> findAll();

    List<TipoActividad> findRange(int[] range);

    int count();

    TipoActividad buscarPorNombre(String nombreTipoActividad);

    Boolean existeActividadPorNombre(String nombreTipoActividad);
    
}
