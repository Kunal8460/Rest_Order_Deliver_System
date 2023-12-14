/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import Client.PreprationClient;
import Entity.OrderMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import utilities.Enums;

/**
 *
 * @author Bhatt Jaimin
 */
@Stateless
public class PreprationEJB implements PreprationEJBLocal {

    @Inject
    @RestClient PreprationClient client;
    @PersistenceContext(unitName = "orderpu")
    EntityManager em;
    @Override
    public List<OrderMaster> getOrderInPrepration(String outlet_id) {
      return (List<OrderMaster>) em.createQuery("SELECT o FROM OrderMaster o Where o.outletId =:outlet_id and o.orderStatus =:orderStatus").setParameter("outlet_id", outlet_id).setParameter("orderStatus", Enums.OrderStatus.PREPARING.toString());
    }

    @Override
    public boolean sendOrderToDelivery(String orderid) {
      try{
          OrderMaster order=em.find(OrderMaster.class, orderid);
          order.setOrderStatus(Enums.OrderStatus.IN_TRANSIT.toString());
          em.merge(order);
          client.updateDeliveryStatusToDelivered(orderid);
          return true;
      }catch(Exception ex){
          ex.printStackTrace();
          return false;
          
      }
    }
    

  
}
