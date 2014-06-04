/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Tablero;
import cl.usach.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface TableroFacadeLocal {

    void create(Tablero tablero);

    void edit(Tablero tablero);

    void remove(Tablero tablero);

    Tablero find(Object id);

    List<Tablero> findAll();

    List<Tablero> findRange(int[] range);

    int count();
    
    public Tablero buscarPorId(int idTablero);
    
    public Boolean existeTableroPorIdTableroExt(String idTableroExt);
    
    public Tablero buscarPorIdTableroExt(String idTableroExt);

    List<Tablero> buscarPorProfesor(Usuario idUsuario);
    
}
