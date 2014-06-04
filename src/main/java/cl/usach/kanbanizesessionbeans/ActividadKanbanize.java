/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.kanbanizesessionbeans;

import cl.usach.elements.ActivityElement;
import cl.usach.entities.Actividad;
import cl.usach.entities.Equipo;
import cl.usach.entities.EstadoTarjeta;
import cl.usach.entities.Lista;
import cl.usach.entities.Miembro;
import cl.usach.entities.Tarjeta;
import cl.usach.entities.TipoActividad;
import cl.usach.entities.TipoCuenta;
import cl.usach.kanbanizejava.Kanbanize;
import cl.usach.kanbanizejava.KanbanizeMake;
import cl.usach.sessionbeans.ActividadFacadeLocal;
import cl.usach.sessionbeans.EstadoTarjetaFacadeLocal;
import cl.usach.sessionbeans.ListaFacadeLocal;
import cl.usach.sessionbeans.MiembroFacadeLocal;
import cl.usach.sessionbeans.TarjetaFacadeLocal;
import cl.usach.sessionbeans.TipoActividadFacadeLocal;
import cl.usach.sessionbeans.TipoCuentaFacadeLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
public class ActividadKanbanize implements ActividadKanbanizeLocal {
    @EJB
    private TipoCuentaFacadeLocal tipoCuentaFacade;
    @EJB
    private TipoActividadFacadeLocal tipoActividadFacade;
    @EJB
    private ActividadFacadeLocal actividadFacade;
    @EJB
    private MiembroFacadeLocal miembroFacade;
    @EJB
    private TarjetaFacadeLocal tarjetaFacade;
    @EJB
    private ListaFacadeLocal listaFacade;
    @EJB
    private EstadoTarjetaFacadeLocal estadoTarjetaFacade;

    @Override
    public void buscarActividades(Equipo equipo) {
        Kanbanize kanbanize = new KanbanizeMake();
        kanbanize.setConfig(equipo.getIdCuenta().getKeyCuenta());
        
        String idTablero = equipo.getIdTablero().getIdTableroExt();
        String idTE = equipo.getIdTablero().getIdTableroExt().replace(equipo.getIdCuenta().getKeyCuenta(), "");        
        
        //Fechas de Filtro
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Actividad ultimaActividad = actividadFacade.buscarUltimaActividad(equipo.getIdTablero());
        String fromDate = "2010-01-01";  
        if(ultimaActividad != null){
            cal.setTime(ultimaActividad.getFechaActividad());
            cal.add(Calendar.DATE, -1);
            fromDate = formatter.format(cal.getTime());
        }
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        String toDate = formatter.format(cal.getTime());
        
        //Obtener Actividades
        List<ActivityElement> activityElements = kanbanize.getBoardActivities(idTE, fromDate, toDate);
        Collections.reverse(activityElements);        
        for (ActivityElement activityElement : activityElements) {
            
            TipoActividad tipoActividad;
            if(tipoActividadFacade.existeActividadPorNombre(activityElement.getEvent())){
                tipoActividad = tipoActividadFacade.buscarPorNombre(activityElement.getEvent());
            }else{
                TipoCuenta tipoCuenta = tipoCuentaFacade.buscarPorNombreTipoCuenta("Kanbanize");
                TipoActividad ta = new TipoActividad(activityElement.getEvent(),tipoCuenta);
                tipoActividadFacade.create(ta);
                tipoActividad = tipoActividadFacade.buscarPorNombre(activityElement.getEvent());
            }
                        
            String idActividadExt = idTablero + tipoActividad.getIdTipoActividad() 
                    + activityElement.getDate() + activityElement.getTaskid();
            
            if(!actividadFacade.existeActividadPorIdActividadExt(idActividadExt)){
                cal = Calendar.getInstance();
                Date date = null;
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if(activityElement.getDate() != null){
                    try {
                        cal.setTime(formatter.parse(activityElement.getDate()));                        
                        cal.add(Calendar.HOUR, -5);
                        date = cal.getTime();
                    } catch (ParseException ex) {
                        Logger.getLogger(ActividadKanbanize.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                Actividad actividad = new Actividad(idActividadExt, date, tipoActividad);
                //Asignar miembro si es que existe
                if(miembroFacade.existeMiembroPorIdTableroYNombreUsuario(equipo.getIdTablero(), activityElement.getAuthor())){
                    Miembro miembro = miembroFacade.buscarPorIdTableroYNombreUsuario(equipo.getIdTablero(), activityElement.getAuthor());
                    actividad.setIdMiembro(miembro);
                }
                //Asignar tarjeta si es que tiene
                if(activityElement.getTaskid() != null){
                    String idTarjetaExt = idTablero + activityElement.getTaskid();
                    
                    if(tarjetaFacade.existeTarjetaPorIdTarjetaExt(idTarjetaExt)){
                        Tarjeta tarjeta = tarjetaFacade.buscarPorIdTarjetaExt(idTarjetaExt);
                        actividad.setIdTarjeta(tarjeta);
                        
                        if(tipoActividad.getNombreTipoActividad().equals("Task moved")){
                            List<Lista> listaPU = listaFacade.buscarPrimeraYUltimaPorTablero(equipo.getIdTablero());
                            if(!listaPU.isEmpty()){
                                String text = activityElement.getText().replace("'", "").replace("From ", "").replace(" to ", "-");
                                
                                if(!text.contains("Backlog") && !text.contains("Archive")){                           
                                    
                                    String nombreListBefore = text.substring(0,text.indexOf("-"));
                                    String nombreListAfter = text.substring(text.indexOf("-")+1,text.length());

                                    //Da fecha de inicio de la tarjeta
                                    if(nombreListBefore != null 
                                            && listaPU.get(0) != null 
                                            && listaPU.get(0).getNombreLista().equals(nombreListBefore)){
                                        EstadoTarjeta estado = estadoTarjetaFacade.buscarPorNombreEstadoTarjeta("En proceso");
                                        tarjeta.setIdEstadoTarjeta(estado);
                                        tarjeta.setFechaInicioTarjeta(date);
                                        if(tarjeta.getFechaCreacionTarjeta() == null) tarjeta.setFechaCreacionTarjeta(date);
                                        tarjetaFacade.edit(tarjeta);
                                    }else{
                                        //Da fecha fin de la tarjeta
                                        if(nombreListAfter != null 
                                                && listaPU.get(1) != null 
                                                && listaPU.get(1).getNombreLista().equals(nombreListAfter)){
                                            EstadoTarjeta estado = estadoTarjetaFacade.buscarPorNombreEstadoTarjeta("Terminada");
                                            tarjeta.setIdEstadoTarjeta(estado);
                                            tarjeta.setFechaFinalTarjeta(date);
                                            if(tarjeta.getFechaCreacionTarjeta() == null) tarjeta.setFechaCreacionTarjeta(date);
                                            tarjetaFacade.edit(tarjeta);
                                        }else{
                                            //Eliminar fecha fin de la tarjeta
                                            if(nombreListBefore != null 
                                                    && listaPU.get(1) != null 
                                                    && listaPU.get(1).getNombreLista().equals(nombreListBefore)){
                                                EstadoTarjeta estado = estadoTarjetaFacade.buscarPorNombreEstadoTarjeta("En proceso");
                                                tarjeta.setIdEstadoTarjeta(estado);
                                                tarjeta.setFechaFinalTarjeta(null);
                                                if(tarjeta.getFechaCreacionTarjeta() == null) tarjeta.setFechaCreacionTarjeta(date);
                                                tarjetaFacade.edit(tarjeta);
                                            }
                                        }
                                    }    
                                }
                                
                            }
                        }else{
                            if(tipoActividad.getNombreTipoActividad().equals("Task created")){
                                //Agregar fecha de creacion
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
    }

    
}
