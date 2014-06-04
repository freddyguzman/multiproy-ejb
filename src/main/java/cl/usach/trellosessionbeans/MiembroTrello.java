/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.trellosessionbeans;

import cl.usach.elements.MemberElement;
import cl.usach.entities.Cuenta;
import cl.usach.entities.Equipo;
import cl.usach.entities.Miembro;
import cl.usach.gettrello.Trello;
import cl.usach.gettrello.TrelloMake;
import cl.usach.sessionbeans.CuentaFacadeLocal;
import cl.usach.sessionbeans.MiembroFacadeLocal;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FGT
 */
@Stateless
public class MiembroTrello implements MiembroTrelloLocal {
    @EJB
    private CuentaFacadeLocal cuentaFacade;
    @EJB
    private MiembroFacadeLocal miembroFacade;

    @Override
    public void buscarMiembros(Equipo equipo) {
        Trello trello = new TrelloMake();
        trello.setConfigTrello(equipo.getIdCuenta().getKeyCuenta(),
                equipo.getIdCuenta().getSecretCuenta(),
                equipo.getIdCuenta().getTokenCuenta());
        
        try {
            List<MemberElement> membersElement = trello.getMembers(equipo.getIdTablero().getIdTableroExt());
            List<Miembro> miembrosActual = miembroFacade.buscarPorTablero(equipo.getIdTablero());
            for (MemberElement memberElement : membersElement) {
                if(miembroFacade.existeMiembroPorIdTableroYIdMiembroExt(equipo.getIdTablero(), memberElement.getId())){
                    Miembro miembro = miembroFacade.buscarMiembroPorIdTableroYIdMiembroExt(equipo.getIdTablero(), memberElement.getId());
                    if(cuentaFacade.existeCuentaPorUsuarioCuenta(memberElement.getUsername())){
                        Cuenta cuenta = cuentaFacade.buscarPorNombreUsuarioCuenta(memberElement.getUsername());
                        miembro.setIdCuenta(cuenta);
                    }
                    miembroFacade.edit(miembro);
                    
                    if(miembrosActual.contains(miembro)){
                        miembrosActual.remove(miembro);
                    }                   
                }else{
                    Miembro miembro = new Miembro(equipo.getIdTablero(), memberElement.getId(), memberElement.getUsername());
                    if(cuentaFacade.existeCuentaPorUsuarioCuenta(memberElement.getUsername())){
                        Cuenta cuenta = cuentaFacade.buscarPorNombreUsuarioCuenta(memberElement.getUsername());
                        miembro.setIdCuenta(cuenta);
                    }                    
                    miembroFacade.create(miembro);
                }
            }
            
            for (Miembro miembroA : miembrosActual) {
                miembroFacade.remove(miembroA);
            }
        } catch (IOException ex) {
            Logger.getLogger(MiembroTrello.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
