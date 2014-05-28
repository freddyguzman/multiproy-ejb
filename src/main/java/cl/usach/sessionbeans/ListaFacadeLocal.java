/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Lista;
import cl.usach.entities.Tablero;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface ListaFacadeLocal {

    void create(Lista lista);

    void edit(Lista lista);

    void remove(Lista lista);

    Lista find(Object id);

    List<Lista> findAll();

    List<Lista> findRange(int[] range);

    int count();

    Lista buscarPorIdExt(String idListaExt);

    Boolean existePorIdExt(String idListaExt);

    List<Lista> buscarPorTablero(Tablero tablero);

    Lista buscarUltimaPorTablero(Tablero idTablero);

    List<Lista> buscarPrimeraYUltimaPorTablero(Tablero idTablero);
    
}
