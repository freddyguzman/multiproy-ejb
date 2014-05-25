/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.DetalleUsuarioTarjeta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface DetalleUsuarioTarjetaFacadeLocal {

    void create(DetalleUsuarioTarjeta detalleUsuarioTarjeta);

    void edit(DetalleUsuarioTarjeta detalleUsuarioTarjeta);

    void remove(DetalleUsuarioTarjeta detalleUsuarioTarjeta);

    DetalleUsuarioTarjeta find(Object id);

    List<DetalleUsuarioTarjeta> findAll();

    List<DetalleUsuarioTarjeta> findRange(int[] range);

    int count();
    
}
