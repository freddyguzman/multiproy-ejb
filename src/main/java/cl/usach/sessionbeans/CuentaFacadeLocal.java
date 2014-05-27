/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.Cuenta;
import cl.usach.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface CuentaFacadeLocal {

    void create(Cuenta cuenta);

    void edit(Cuenta cuenta);

    void remove(Cuenta cuenta);

    Cuenta find(Object id);

    List<Cuenta> findAll();

    List<Cuenta> findRange(int[] range);

    int count();    
    
    Cuenta buscarPorId(int idCuenta);

    List<Cuenta> buscarPorUsuario(Usuario usuario);

    Boolean existeCuentaPorUsuarioCuenta(String usuarioCuenta);

    Cuenta buscarPorNombreUsuarioCuenta(String nombreUsuarioCuenta);
    
}
