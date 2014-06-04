/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.trellosessionbeans;

import cl.usach.elements.ActionElement;
import cl.usach.entities.Actividad;
import cl.usach.entities.Equipo;
import cl.usach.entities.EstadoTarjeta;
import cl.usach.entities.Lista;
import cl.usach.entities.Miembro;
import cl.usach.entities.Tarjeta;
import cl.usach.entities.TipoActividad;
import cl.usach.entities.TipoCuenta;
import cl.usach.gettrello.Trello;
import cl.usach.gettrello.TrelloMake;
import cl.usach.sessionbeans.ActividadFacadeLocal;
import cl.usach.sessionbeans.EstadoTarjetaFacadeLocal;
import cl.usach.sessionbeans.ListaFacadeLocal;
import cl.usach.sessionbeans.MiembroFacadeLocal;
import cl.usach.sessionbeans.TarjetaFacadeLocal;
import cl.usach.sessionbeans.TipoActividadFacadeLocal;
import cl.usach.sessionbeans.TipoCuentaFacadeLocal;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.json.JSONException;

/**
 *
 * @author FGT
 */
@Stateless
public class ActividadTrello implements ActividadTrelloLocal {
    @EJB
    private EstadoTarjetaFacadeLocal estadoTarjetaFacade;
    @EJB
    private ListaFacadeLocal listaFacade;
    @EJB
    private TarjetaFacadeLocal tarjetaFacade;
    @EJB
    private MiembroFacadeLocal miembroFacade;
    @EJB
    private ActividadFacadeLocal actividadFacade;
    @EJB
    private TipoCuentaFacadeLocal tipoCuentaFacade;
    @EJB
    private TipoActividadFacadeLocal tipoActividadFacade;

    @Override
    public void buscarActividades(Equipo equipo) {
        Trello trello = new TrelloMake();
        trello.setConfigTrello(equipo.getIdCuenta().getKeyCuenta(),
                equipo.getIdCuenta().getSecretCuenta(),
                equipo.getIdCuenta().getTokenCuenta());
                
        try {                        
            List<ActionElement> actionElements =  trello.getActions(equipo.getIdTablero().getIdTableroExt());
            Collections.reverse(actionElements);
            
            if(actividadFacade.existeActividadPorTablero(equipo.getIdTablero())){                
                Actividad ultimaActividad = actividadFacade.buscarUltimaActividad(equipo.getIdTablero());
                List<ActionElement> auxLista = new ArrayList<>();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Calendar cal = Calendar.getInstance();
                for (ActionElement actionElement : actionElements) {
                    Date date = null;
                    if(actionElement.getDate() != null){
                        cal.setTime(formatter.parse(actionElement.getDate()));
                        cal.add(Calendar.HOUR, -4);
                        date = cal.getTime();
                    }
                    if(ultimaActividad.getFechaActividad().after(date)){
                        auxLista.add(actionElement);
                    }
                }
                actionElements.removeAll(auxLista);
            }
            
            for (ActionElement actionElement : actionElements) {
                
                TipoActividad tipoActividad;
                if(tipoActividadFacade.existeActividadPorNombre(actionElement.getType())){
                    tipoActividad = tipoActividadFacade.buscarPorNombre(actionElement.getType());
                }else{
                    TipoCuenta tipoCuenta = tipoCuentaFacade.buscarPorNombreTipoCuenta("Trello");
                    TipoActividad ta = new TipoActividad(actionElement.getType(),tipoCuenta);
                    tipoActividadFacade.create(ta);
                    tipoActividad = tipoActividadFacade.buscarPorNombre(actionElement.getType());
                }
                
                if(!actividadFacade.existeActividadPorIdActividadExt(actionElement.getId())){
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    Calendar cal = Calendar.getInstance();

                    Date date = null;
                    if(actionElement.getDate() != null){
                        cal.setTime(formatter.parse(actionElement.getDate()));
                        cal.add(Calendar.HOUR, -4);
                        date = cal.getTime();
                    }
                    Actividad actividad = new Actividad(actionElement.getId(), date, tipoActividad);
                    //Asignar miembro si es que existe
                    if(miembroFacade.existeMiembroPorIdTableroYIdMiembroExt(equipo.getIdTablero(), actionElement.getIdMemberCreator())){
                        Miembro miembro = miembroFacade.buscarMiembroPorIdTableroYIdMiembroExt(equipo.getIdTablero(), actionElement.getIdMemberCreator());
                        actividad.setIdMiembro(miembro);
                    }
                    //Asignar tarjeta si es que tiene
                    if(actionElement.getCardId() != null){
                        if(tarjetaFacade.existeTarjetaPorIdTarjetaExt(actionElement.getCardId())){
                            Tarjeta tarjeta = tarjetaFacade.buscarPorIdTarjetaExt(actionElement.getCardId());
                            actividad.setIdTarjeta(tarjeta);
                            
                            if(tipoActividad.getNombreTipoActividad().equals("updateCard")){
                                List<Lista> listaPU = listaFacade.buscarPrimeraYUltimaPorTablero(equipo.getIdTablero());
                                if(!listaPU.isEmpty()){
                                    
                                    //Da fecha de inicio de la tarjeta
                                    if(actionElement.getIdListBefore() != null 
                                            && listaPU.get(0) != null 
                                            && listaPU.get(0).getIdListaExt().equals(actionElement.getIdListBefore())){
                                        EstadoTarjeta estado = estadoTarjetaFacade.buscarPorNombreEstadoTarjeta("En proceso");
                                        tarjeta.setIdEstadoTarjeta(estado);
                                        tarjeta.setFechaInicioTarjeta(date);
                                        if(tarjeta.getFechaCreacionTarjeta() == null) tarjeta.setFechaCreacionTarjeta(date);
                                        tarjetaFacade.edit(tarjeta);
                                    }else{
                                        //Da fecha fin de la tarjeta
                                        if(actionElement.getIdListAfter()!= null 
                                                && listaPU.get(1) != null 
                                                && listaPU.get(1).getIdListaExt().equals(actionElement.getIdListAfter())){
                                            EstadoTarjeta estado = estadoTarjetaFacade.buscarPorNombreEstadoTarjeta("Terminada");
                                            tarjeta.setIdEstadoTarjeta(estado);
                                            tarjeta.setFechaFinalTarjeta(date);
                                            if(tarjeta.getFechaCreacionTarjeta() == null) tarjeta.setFechaCreacionTarjeta(date);
                                            tarjetaFacade.edit(tarjeta);
                                        }else{
                                            //Eliminar fecha fin de la tarjeta
                                            if(actionElement.getIdListBefore()!= null 
                                                    && listaPU.get(1) != null 
                                                    && listaPU.get(1).getIdListaExt().equals(actionElement.getIdListBefore())){
                                                EstadoTarjeta estado = estadoTarjetaFacade.buscarPorNombreEstadoTarjeta("En proceso");
                                                tarjeta.setIdEstadoTarjeta(estado);
                                                tarjeta.setFechaFinalTarjeta(null);
                                                if(tarjeta.getFechaCreacionTarjeta() == null) tarjeta.setFechaCreacionTarjeta(date);
                                                tarjetaFacade.edit(tarjeta);
                                            }
                                        }
                                    }                                 
                                }
                            }else{
                                //Instrucciones si la tarjeta no se crea en la primera lista del tablero
                                if(tipoActividad.getNombreTipoActividad().equals("createCard")){
                                    //Agregar Fecha de creacion
                                    tarjeta.setFechaCreacionTarjeta(date);
                                    
                                    List<Lista> listaPU = listaFacade.buscarPrimeraYUltimaPorTablero(equipo.getIdTablero());
                                    if(!listaPU.isEmpty()){
                                        //Si la tarjeta se crea en la ultima lista
                                        if(listaPU.get(1) != null 
                                                && listaPU.get(1).getIdLista().equals(tarjeta.getIdLista().getIdLista())){
                                            EstadoTarjeta estado = estadoTarjetaFacade.buscarPorNombreEstadoTarjeta("Terminada");
                                            tarjeta.setIdEstadoTarjeta(estado);
                                            tarjeta.setFechaInicioTarjeta(date);
                                            tarjeta.setFechaFinalTarjeta(date);
                                            tarjetaFacade.edit(tarjeta);
                                        }else{
                                            //Si la tarjeta se crea en cualquier otra lista que no sea la primera ni la ultima
                                            if(listaPU.get(0) != null 
                                                    && !listaPU.get(0).getIdLista().equals(tarjeta.getIdLista().getIdLista())){
                                                EstadoTarjeta estado = estadoTarjetaFacade.buscarPorNombreEstadoTarjeta("En proceso");
                                                tarjeta.setIdEstadoTarjeta(estado);
                                                tarjeta.setFechaInicioTarjeta(date);
                                                tarjetaFacade.edit(tarjeta);
                                            }
                                        }
                                    }
                                }
                            }
                            
                        }
                    }
                    actividadFacade.create(actividad);
                }
                
            }
        } catch (IOException | JSONException | ParseException ex) {
            Logger.getLogger(ActividadTrello.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
