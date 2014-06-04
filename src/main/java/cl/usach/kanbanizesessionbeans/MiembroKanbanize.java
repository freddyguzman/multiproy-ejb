/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.kanbanizesessionbeans;

import cl.usach.entities.Cuenta;
import cl.usach.entities.Equipo;
import cl.usach.entities.Miembro;
import cl.usach.kanbanizejava.Kanbanize;
import cl.usach.kanbanizejava.KanbanizeMake;
import cl.usach.sessionbeans.CuentaFacadeLocal;
import cl.usach.sessionbeans.MiembroFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FGT
 */
@Stateless
public class MiembroKanbanize implements MiembroKanbanizeLocal {
    @EJB
    private CuentaFacadeLocal cuentaFacade;
    @EJB
    private MiembroFacadeLocal miembroFacade;

    @Override
    public void buscarMiembos(Equipo equipo) {
        Kanbanize kanbanize = new KanbanizeMake();
        kanbanize.setConfig(equipo.getIdCuenta().getKeyCuenta());
        
        String idTablero = equipo.getIdTablero().getIdTableroExt();
        String idTE = equipo.getIdTablero().getIdTableroExt().replace(equipo.getIdCuenta().getKeyCuenta(), "");
        
        List<String> miembrosNuevos = kanbanize.getSettingsBoard(idTE).getUsernames();
        List<Miembro> miembrosActual = miembroFacade.buscarPorTablero(equipo.getIdTablero());
        for (String miembroNuevo : miembrosNuevos) {
            String idMiembroExt = idTablero + miembroNuevo;
            
            if(miembroFacade.existeMiembroPorIdTableroYIdMiembroExt(equipo.getIdTablero(), idMiembroExt)){
                Miembro miembro = miembroFacade.buscarMiembroPorIdTableroYIdMiembroExt(equipo.getIdTablero(), idMiembroExt);
                if(cuentaFacade.existeCuentaPorUsuarioCuenta(miembroNuevo)){
                    Cuenta cuenta = cuentaFacade.buscarPorNombreUsuarioCuenta(miembroNuevo);
                    miembro.setIdCuenta(cuenta);
                }
                miembroFacade.edit(miembro);
                
                if(miembrosActual.contains(miembro)){
                    miembrosActual.remove(miembro);
                } 
            }else{
                Miembro miembro = new Miembro(equipo.getIdTablero(), idMiembroExt, miembroNuevo);
                if(cuentaFacade.existeCuentaPorUsuarioCuenta(miembroNuevo)){
                    Cuenta cuenta = cuentaFacade.buscarPorNombreUsuarioCuenta(miembroNuevo);
                    miembro.setIdCuenta(cuenta);
                }                    
                miembroFacade.create(miembro);
            }
        }
        
        for (Miembro miembroA : miembrosActual) {
            miembroFacade.remove(miembroA);
        }   
    }

    
}
