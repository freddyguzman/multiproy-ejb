/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.sessionbeans;

import cl.usach.entities.RolUsuario;
import cl.usach.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author FGT
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    public Usuario buscarPorId(int idUsuario);
    
    public List<Usuario> buscarPorNombre(String nombreUsuario);
    
    public Usuario buscarPorLogin(String loginUsuario);
    
    public List<Usuario> buscarPorIdRolUsuario(RolUsuario idRolUsuario);
    
}
