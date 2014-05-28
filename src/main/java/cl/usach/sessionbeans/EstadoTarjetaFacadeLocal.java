/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.EstadoTarjeta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface EstadoTarjetaFacadeLocal {

    void create(EstadoTarjeta estadoTarjeta);

    void edit(EstadoTarjeta estadoTarjeta);

    void remove(EstadoTarjeta estadoTarjeta);

    EstadoTarjeta find(Object id);

    List<EstadoTarjeta> findAll();

    List<EstadoTarjeta> findRange(int[] range);

    int count();

    EstadoTarjeta buscarPorNombreEstadoTarjeta(String nombreEstadoTarjeta);
    
}
