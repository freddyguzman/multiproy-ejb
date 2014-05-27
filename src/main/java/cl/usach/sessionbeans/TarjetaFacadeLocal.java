/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Tablero;
import cl.usach.entities.Tarjeta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface TarjetaFacadeLocal {

    void create(Tarjeta tarjeta);

    void edit(Tarjeta tarjeta);

    void remove(Tarjeta tarjeta);

    Tarjeta find(Object id);

    List<Tarjeta> findAll();

    List<Tarjeta> findRange(int[] range);

    int count();

    Tarjeta buscarPorIdTarjetaExt(String idTarjetaExt);

    Boolean existeTarjetaPorIdTarjetaExt(String idTarjetaExt);

    List<Tarjeta> buscarPorTablero(Tablero tablero);
    
}
