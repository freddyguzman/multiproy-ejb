/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.kanbanizesessionbeans;

import cl.usach.elements.BoardElement;
import cl.usach.elements.ProjectElement;
import cl.usach.entities.Cuenta;
import cl.usach.entities.Equipo;
import cl.usach.entities.Tablero;
import cl.usach.kanbanizejava.Kanbanize;
import cl.usach.kanbanizejava.KanbanizeMake;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author FGT
 */
@Stateless
public class TableroKanbanize implements TableroKanbanizeLocal {

    @Override
    public List<Equipo> buscarTableros(Cuenta cuenta) {
        List<Equipo> equipos = new ArrayList<>();
        Kanbanize kanbanize = new KanbanizeMake();
        kanbanize.setConfig(cuenta.getKeyCuenta());
        List<ProjectElement> projects = kanbanize.getProjectsAndBoards();
        for (ProjectElement projectElement : projects) {
            List<BoardElement> boards = projectElement.getBoardElements();
            for (BoardElement boardElement : boards) {
                Tablero tablero = new Tablero(cuenta.getKeyCuenta()+boardElement.getId(),
                        boardElement.getName(), "https://kanbanize.com/ctrl_board/" + boardElement.getId());
                Equipo equipo = new Equipo(cuenta, tablero);
                equipos.add(equipo);
            }
        }
        return equipos;
    }

    @Override
    public Boolean checkCuenta(Cuenta cuenta) {
        Kanbanize kanbanize = new KanbanizeMake();
        kanbanize.setConfig(cuenta.getKeyCuenta());
        
        return kanbanize.checkConf();
    }
}
