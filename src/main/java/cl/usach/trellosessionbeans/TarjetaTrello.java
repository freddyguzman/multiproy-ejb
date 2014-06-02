/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.trellosessionbeans;

import cl.usach.elements.CardElement;
import cl.usach.entities.DetalleUsuarioTarjeta;
import cl.usach.entities.Equipo;
import cl.usach.entities.EstadoTarjeta;
import cl.usach.entities.Lista;
import cl.usach.entities.Miembro;
import cl.usach.entities.Tarjeta;
import cl.usach.gettrello.Trello;
import cl.usach.gettrello.TrelloMake;
import cl.usach.sessionbeans.DetalleUsuarioTarjetaFacadeLocal;
import cl.usach.sessionbeans.EstadoTarjetaFacadeLocal;
import cl.usach.sessionbeans.ListaFacadeLocal;
import cl.usach.sessionbeans.MiembroFacadeLocal;
import cl.usach.sessionbeans.TarjetaFacadeLocal;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author FGT
 */
@Stateless
public class TarjetaTrello implements TarjetaTrelloLocal {
    @EJB
    private EstadoTarjetaFacadeLocal estadoTarjetaFacade;
    @EJB
    private DetalleUsuarioTarjetaFacadeLocal detalleUsuarioTarjetaFacade;
    @EJB
    private MiembroFacadeLocal miembroFacade;
    @EJB
    private ListaFacadeLocal listaFacade;
    @EJB
    private TarjetaFacadeLocal tarjetaFacade;

    @Override
    public void buscarTarjetasPorLista(Equipo equipo) {
        Trello trello = new TrelloMake();
        trello.setConfigTrello(equipo.getIdCuenta().getKeyCuenta(),
                equipo.getIdCuenta().getSecretCuenta(),
                equipo.getIdCuenta().getTokenCuenta());        
        
        try {
            List<CardElement> cardsElement = trello.getCards(equipo.getIdTablero().getIdTableroExt());
            List<Tarjeta> tarjetaActual = tarjetaFacade.buscarPorTablero(equipo.getIdTablero());
            for (CardElement cardElement : cardsElement) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Calendar cal = Calendar.getInstance();

                Date dateDue = null;
                if(cardElement.getDue() != null){
                    cal.setTime(formatter.parse(cardElement.getDue()));
                    cal.add(Calendar.HOUR, -4);
                    dateDue = cal.getTime();
                }
                JSONArray jsona = new JSONArray(cardElement.getIdMembers());

                if(tarjetaFacade.existeTarjetaPorIdTarjetaExt(cardElement.getId())){
                    Tarjeta tarjeta = tarjetaFacade.buscarPorIdTarjetaExt(cardElement.getId());
                    Lista lista = listaFacade.buscarPorIdExt(cardElement.getIdList());
                    
                    if(tarjetaActual.contains(tarjeta)){
                        tarjetaActual.remove(tarjeta);
                    }
                    
                    //Editar Tarjeta
                    tarjeta.setNombreTarjeta(cardElement.getName());
                    tarjeta.setFechaLimiteTarjeta(dateDue);
                    tarjeta.setIdLista(lista);
                    tarjetaFacade.edit(tarjeta);
                    
                    //Editar Miembros asignados
                    List<DetalleUsuarioTarjeta> detallesActual = detalleUsuarioTarjetaFacade.buscarPorIdTarjeta(tarjeta);
                    if(jsona.length() > 0){
                        for(int i = 0; i < jsona.length(); i++){
                            if(miembroFacade.existeMiembroPorIdTableroYIdMiembroExt(equipo.getIdTablero(), jsona.getString(i))){
                                Miembro miembro = miembroFacade.buscarMiembroPorIdTableroYIdMiembroExt(equipo.getIdTablero(), jsona.getString(i));
                                if(detalleUsuarioTarjetaFacade.existeDetallePorIdTarjetaYIdMiembro(tarjeta, miembro)){
                                    DetalleUsuarioTarjeta detalleUsuarioTarjeta = detalleUsuarioTarjetaFacade.buscarPorIdTarjetaYIdMiembro(tarjeta, miembro);
                                    if(detallesActual.contains(detalleUsuarioTarjeta)){
                                        detallesActual.remove(detalleUsuarioTarjeta);
                                    }
                                }else{
                                    //Si no existe el usuario asignado para esta tarjeta, se agrega
                                    DetalleUsuarioTarjeta dut = new DetalleUsuarioTarjeta(tarjeta, miembro);
                                    detalleUsuarioTarjetaFacade.create(dut);
                                }
                            }
                        }                        
                    }
                    
                    for (DetalleUsuarioTarjeta detalleA : detallesActual) {
                        detalleUsuarioTarjetaFacade.remove(detalleA);
                    }
                }else{
                    //Agregar Tarjeta
                    Lista lista = listaFacade.buscarPorIdExt(cardElement.getIdList());
                    EstadoTarjeta estadoTarjeta = estadoTarjetaFacade.buscarPorNombreEstadoTarjeta("Agregada");
                    Tarjeta tarjeta = new Tarjeta(estadoTarjeta, cardElement.getId(), cardElement.getName()
                            , dateDue, lista, equipo.getIdTablero());                    
                    tarjetaFacade.create(tarjeta);
                    
                    //Agregar Miembros asignados a la tarjeta
                    if(jsona.length() > 0){                        
                        tarjeta = tarjetaFacade.buscarPorIdTarjetaExt(cardElement.getId());                        
                        for(int i = 0; i < jsona.length(); i++){
                            if(miembroFacade.existeMiembroPorIdTableroYIdMiembroExt(equipo.getIdTablero(), jsona.getString(i))){
                                Miembro miembro = miembroFacade.buscarMiembroPorIdTableroYIdMiembroExt(equipo.getIdTablero(), jsona.getString(i));
                                DetalleUsuarioTarjeta dut = new DetalleUsuarioTarjeta(tarjeta, miembro);
                                detalleUsuarioTarjetaFacade.create(dut);
                            }
                        }
                    }
                    
                }
            }
            
            for (Tarjeta tarjetaA : tarjetaActual) {
                tarjetaFacade.remove(tarjetaA);
            }
        } catch (IOException | ParseException | JSONException ex) {
            Logger.getLogger(TarjetaTrello.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
