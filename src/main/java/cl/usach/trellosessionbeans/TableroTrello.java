/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.trellosessionbeans;

import cl.usach.elements.BoardElem;
import cl.usach.entities.Cuenta;
import cl.usach.entities.Equipo;
import cl.usach.entities.Tablero;
import cl.usach.gettrello.Trello;
import cl.usach.gettrello.TrelloMake;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author FGT
 */
@Stateless
public class TableroTrello implements TableroTrelloLocal {

    @Override
    public List<Equipo> buscarTableros(Cuenta cuenta) {
        List<Equipo> equipos = new ArrayList<>();
        List<Tablero> auxTableros = new ArrayList<>();
        
        Trello trello = new TrelloMake();
        trello.setConfigTrello(cuenta.getKeyCuenta(), cuenta.getSecretCuenta(), cuenta.getTokenCuenta());
        
        try {
            List<BoardElem> boardsTrello = trello.getBoardsByIdMember(cuenta.getNombreUsuarioCuenta());
            for (BoardElem boardTrello : boardsTrello) {
                Tablero auxtablero = new Tablero(boardTrello.getId(),
                        boardTrello.getName(), boardTrello.getShortUrl()+".html");
                Equipo equipo = new Equipo(cuenta, auxtablero);
                equipos.add(equipo);
            }            
        } catch (IOException ex) {
            Logger.getLogger(TableroTrello.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipos;        
    }

    @Override
    public Boolean checkCuenta(Cuenta cuenta) {
        Trello trello = new TrelloMake();
        trello.setConfigTrello(cuenta.getKeyCuenta(), cuenta.getSecretCuenta(), cuenta.getTokenCuenta());
        
        try {
            return trello.checkConf(cuenta.getNombreUsuarioCuenta());
        } catch (IOException ex) {
            Logger.getLogger(TableroTrello.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
