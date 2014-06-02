/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.DetalleUsuarioTarjeta;
import cl.usach.entities.Lista;
import cl.usach.entities.Miembro;
import cl.usach.entities.Tablero;
import cl.usach.entities.Tarjeta;
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

    DetalleUsuarioTarjeta buscarPorIdTarjetaYIdMiembro(Tarjeta idTarjeta, Miembro idMiembro);

    Boolean existeDetallePorIdTarjetaYIdMiembro(Tarjeta idTarjeta, Miembro idMiembro);

    List<DetalleUsuarioTarjeta> buscarPorIdTarjeta(Tarjeta idTarjeta);

    List<DetalleUsuarioTarjeta> buscarPorIdMiembro(Miembro idMiembro);

    List<DetalleUsuarioTarjeta> buscarPorIdMiembroYIdTablero(Miembro idMiembro, Tablero idTablero);

    List<DetalleUsuarioTarjeta> buscarPorIdMiembroYIdTableroYNoLista(Miembro idMiembro, Tablero idTablero, Lista idLista);

    Boolean existeDetalleUsuarioTarjetaPorIdTarjeta(Tarjeta idTarjeta);
    
}
