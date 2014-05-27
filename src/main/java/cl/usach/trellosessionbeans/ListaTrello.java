/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.trellosessionbeans;

import cl.usach.elements.ListElement;
import cl.usach.entities.Equipo;
import cl.usach.entities.Lista;
import cl.usach.gettrello.Trello;
import cl.usach.gettrello.TrelloMake;
import cl.usach.sessionbeans.ListaFacadeLocal;
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
public class ListaTrello implements ListaTrelloLocal {
    @EJB
    private ListaFacadeLocal listaFacade;

    @Override
    public void buscarListaPorTablero(Equipo equipo) {
        Trello trello = new TrelloMake();
        trello.setConfigTrello(equipo.getIdCuenta().getKeyCuenta(),
                equipo.getIdCuenta().getSecretCuenta(),
                equipo.getIdCuenta().getTokenCuenta());
        
        try {
            List<ListElement> listasElement = trello.getLists(equipo.getIdTablero().getIdTableroExt());
            List<Lista> listaActual = listaFacade.buscarPorTablero(equipo.getIdTablero());
            int i = 1;
            for (ListElement listElement : listasElement) {
                if(listaFacade.existePorIdExt(listElement.getId())){ //UPDATE
                    Lista lista = listaFacade.buscarPorIdExt(listElement.getId());
                    
                    if(listaActual.contains(lista)){
                        listaActual.remove(lista);
                    }
                    
                    lista.setNombreLista(listElement.getName());
                    lista.setPosicion(i);                   
                    listaFacade.edit(lista);
                }else{ //CREATE
                    Lista lista = new Lista(listElement.getId(), listElement.getName(), i, equipo.getIdTablero());
                    listaFacade.create(lista);
                }
                i++;
            }
            
            for (Lista listaA : listaActual) {
                listaFacade.remove(listaA);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ListaTrello.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}
