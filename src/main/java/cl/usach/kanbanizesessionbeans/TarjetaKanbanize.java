/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.usach.kanbanizesessionbeans;

import cl.usach.elements.TaskElement;
import cl.usach.entities.DetalleUsuarioTarjeta;
import cl.usach.entities.Equipo;
import cl.usach.entities.EstadoTarjeta;
import cl.usach.entities.Lista;
import cl.usach.entities.Miembro;
import cl.usach.entities.Tarjeta;
import cl.usach.kanbanizejava.Kanbanize;
import cl.usach.kanbanizejava.KanbanizeMake;
import cl.usach.sessionbeans.DetalleUsuarioTarjetaFacadeLocal;
import cl.usach.sessionbeans.EstadoTarjetaFacadeLocal;
import cl.usach.sessionbeans.ListaFacadeLocal;
import cl.usach.sessionbeans.MiembroFacadeLocal;
import cl.usach.sessionbeans.TarjetaFacadeLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class TarjetaKanbanize implements TarjetaKanbanizeLocal {
    @EJB
    private TarjetaFacadeLocal tarjetaFacade;
    @EJB
    private ListaFacadeLocal listaFacade;
    @EJB
    private MiembroFacadeLocal miembroFacade;
    @EJB
    private DetalleUsuarioTarjetaFacadeLocal detalleUsuarioTarjetaFacade;
    @EJB
    private EstadoTarjetaFacadeLocal estadoTarjetaFacade;

    @Override
    public void buscarTarjetas(Equipo equipo) {
        Kanbanize kanbanize = new KanbanizeMake();
        kanbanize.setConfig(equipo.getIdCuenta().getKeyCuenta());
        
        String idTablero = equipo.getIdTablero().getIdTableroExt();
        String idTE = equipo.getIdTablero().getIdTableroExt().replace(equipo.getIdCuenta().getKeyCuenta(), "");
        
        List<TaskElement> taskElements = kanbanize.getAllTask(idTE);
        List<Tarjeta> tarjetaActual = tarjetaFacade.buscarPorTablero(equipo.getIdTablero());
        for (TaskElement taskElement : taskElements) {
            String idTarjetaExt = idTablero + taskElement.getTaskid();
            String idListaExt = idTablero 
                    + taskElement.getColumnid().substring(taskElement.getColumnid().lastIndexOf("_")+1, taskElement.getColumnid().length());
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDue = null;
            if(taskElement.getDeadlineoriginalformat() != null){
                try {
                    dateDue = formatter.parse(taskElement.getDeadlineoriginalformat());
                } catch (ParseException ex) {
                    Logger.getLogger(TarjetaKanbanize.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
            
            if(tarjetaFacade.existeTarjetaPorIdTarjetaExt(idTarjetaExt)){
                Tarjeta tarjeta = tarjetaFacade.buscarPorIdTarjetaExt(idTarjetaExt);
                Lista lista = listaFacade.buscarPorIdExt(idListaExt);
                
                if(tarjetaActual.contains(tarjeta)){
                    tarjetaActual.remove(tarjeta);
                }
                
                //Editar Tarjeta
                tarjeta.setNombreTarjeta(taskElement.getTitle());
                tarjeta.setFechaLimiteTarjeta(dateDue);
                tarjeta.setIdLista(lista);
                tarjetaFacade.edit(tarjeta);
                
                //Editar Miembros asignados
                if(!taskElement.getAssignee().equals("None")){
                    if(miembroFacade.existeMiembroPorIdTableroYNombreUsuario(equipo.getIdTablero(), taskElement.getAssignee())){
                        Miembro miembro = miembroFacade.buscarPorIdTableroYNombreUsuario(equipo.getIdTablero(), taskElement.getAssignee());
                        if(!detalleUsuarioTarjetaFacade.existeDetallePorIdTarjetaYIdMiembro(tarjeta, miembro)){
                            if(detalleUsuarioTarjetaFacade.existeDetalleUsuarioTarjetaPorIdTarjeta(tarjeta)){
                                DetalleUsuarioTarjeta detalle = detalleUsuarioTarjetaFacade.buscarPorIdTarjeta(tarjeta).get(0);
                                detalle.setIdMiembro(miembro);
                                detalleUsuarioTarjetaFacade.edit(detalle);
                            }else{
                                DetalleUsuarioTarjeta detalle = new DetalleUsuarioTarjeta(tarjeta, miembro);
                                detalleUsuarioTarjetaFacade.edit(detalle);
                            }
                        }
                    }
                }else{
                    if(detalleUsuarioTarjetaFacade.existeDetalleUsuarioTarjetaPorIdTarjeta(tarjeta)){
                        DetalleUsuarioTarjeta detalle = detalleUsuarioTarjetaFacade.buscarPorIdTarjeta(tarjeta).get(0);
                        detalleUsuarioTarjetaFacade.remove(detalle);
                    }
                }
            }else{
                Lista lista = listaFacade.buscarPorIdExt(idListaExt);
                EstadoTarjeta estadoTarjeta = estadoTarjetaFacade.buscarPorNombreEstadoTarjeta("Agregada");
                Tarjeta tarjeta = new Tarjeta(estadoTarjeta, idTarjetaExt, taskElement.getTitle()
                            , dateDue, lista, equipo.getIdTablero());
                tarjetaFacade.create(tarjeta);
                
                if(!taskElement.getAssignee().equals("None")){
                    tarjeta = tarjetaFacade.buscarPorIdTarjetaExt(idTarjetaExt);
                    if(miembroFacade.existeMiembroPorIdTableroYNombreUsuario(equipo.getIdTablero(), taskElement.getAssignee())){
                        Miembro miembro = miembroFacade.buscarPorIdTableroYNombreUsuario(equipo.getIdTablero(), taskElement.getAssignee());
                        DetalleUsuarioTarjeta dut = new DetalleUsuarioTarjeta(tarjeta, miembro);
                        detalleUsuarioTarjetaFacade.create(dut);
                    }
                }
            }
        }
        
        for (Tarjeta tarjetaA : tarjetaActual) {
            tarjetaFacade.remove(tarjetaA);
        }
    }    
}
