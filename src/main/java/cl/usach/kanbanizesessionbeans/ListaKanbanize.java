/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.kanbanizesessionbeans;

import cl.usach.elements.ColumnElement;
import cl.usach.entities.Equipo;
import cl.usach.entities.Lista;
import cl.usach.kanbanizejava.Kanbanize;
import cl.usach.kanbanizejava.KanbanizeMake;
import cl.usach.sessionbeans.ListaFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FGT
 */
@Stateless
public class ListaKanbanize implements ListaKanbanizeLocal {
    @EJB
    private ListaFacadeLocal listaFacade;

    @Override
    public void buscarListas(Equipo equipo) {
        Kanbanize kanbanize = new KanbanizeMake();
        kanbanize.setConfig(equipo.getIdCuenta().getKeyCuenta());
        
        String idTablero = equipo.getIdTablero().getIdTableroExt();
        String idTE = equipo.getIdTablero().getIdTableroExt().replace(equipo.getIdCuenta().getKeyCuenta(), "");
        
        List<ColumnElement> columnElements = kanbanize.getFullBoardStructure(idTE).getColumnElements();
        columnElements.remove(0); //Eliminar Backlog
        columnElements.remove(columnElements.size()-1); //Eliminar Archive
        List<Lista> listaActual = listaFacade.buscarPorTablero(equipo.getIdTablero());
        for (ColumnElement columnElement : columnElements) {
            String idListaExt = idTablero + columnElement.getLcid();
            
            if(listaFacade.existePorIdExt(idListaExt)){ //UPDATE
                Lista lista = listaFacade.buscarPorIdExt(idListaExt);

                if(listaActual.contains(lista)){
                    listaActual.remove(lista);
                }

                lista.setNombreLista(columnElement.getLcname());
                lista.setPosicion(columnElement.getPosition()+1);                   
                listaFacade.edit(lista);
            }else{ //CREATE
                Lista lista = new Lista(idListaExt, columnElement.getLcname(), columnElement.getPosition()+1, equipo.getIdTablero());
                listaFacade.create(lista);
            }
        }
        for (Lista listaA : listaActual) {
            listaFacade.remove(listaA);
        }
    }

    
}
