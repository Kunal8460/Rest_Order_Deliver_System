/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import Client.PreprationClient;
import Entity.DeliveryPerson;
import Entity.OrderLine;
import Entity.OrderMaster;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import utilities.Enums;
import utilities.PHResponseType;

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
    public JsonObject  getOrdersByOutletandStatus(String outlet_id,String status) {
        try{
            List<OrderMaster> orders= (List<OrderMaster>) em.createQuery("SELECT o FROM OrderMaster o Where o.outletId.id =:outlet_id and o.orderStatus =:orderStatus").setParameter("outlet_id", outlet_id).setParameter("orderStatus",status).getResultList();
        
      JsonArrayBuilder ordersline = Json.createArrayBuilder();
      JsonArrayBuilder outletOrders = Json.createArrayBuilder();
            for (OrderMaster item : orders) {
                List<OrderLine> items = em.createNamedQuery("OrderLine.findByOrderId").setParameter("id", item.getId()).getResultList();
                for (OrderLine e : items) {
                    ordersline.add(Json.createObjectBuilder()
                        .add("name", e.getItemId().getName())
                        .add("quantity", e.getQuantity())
                        .build());
                }
               
               outletOrders.add(Json.createObjectBuilder()
               .add("id", item.getId())
                       .add("name",item.getUserId().getName())
                       .add("items",ordersline)
                       .add("payable_amount",item.getPayableAmount())
                       .add("order_status",item.getOrderStatus())
                       .build()
               );
                        
            }
            JsonObject obj = Json.createObjectBuilder()
                    .add("orders",outletOrders)
                    .build();
        return obj;
        }catch(Exception ex){
            return null;
        }
      
      
    }

    @Override
    public PHResponseType sendOrderToDelivery(String orderid,String outletid) {
        PHResponseType response = new PHResponseType();
      try{
          client.deliveryPersonAllocation(orderid, orderid);
          response.setStatus(200);
          response.setMessage("Order is sent to delivery person!!!");
          return response;
      }catch(Exception ex){
          ex.printStackTrace();
          response.setStatus(405);
          response.setMessage("failed!!!");
          return response;
      }
    }

    @Override
    public JsonObject getOrderStatusByCustomer(String customerid) {
    try{
            List<OrderMaster> orders= (List<OrderMaster>) em.createQuery("SELECT o FROM OrderMaster o Where o.userId.id =:id and o.orderStatus !=:status").setParameter("id", customerid).setParameter("status", Enums.OrderStatus.DELIVERED.toString()).getResultList();
        
      JsonArrayBuilder ordersline = Json.createArrayBuilder();
      JsonArrayBuilder outletOrders = Json.createArrayBuilder();
        JsonArrayBuilder deliveryPerson = Json.createArrayBuilder();
            for (OrderMaster item : orders) {
                List<OrderLine> items = em.createNamedQuery("OrderLine.findByOrderId").setParameter("id", item.getId()).getResultList();
                for (OrderLine e : items) {
                    ordersline.add(Json.createObjectBuilder()
                        .add("name", e.getItemId().getName())
                        .add("quantity", e.getQuantity())
                        .build());
                }
                DeliveryPerson person= em.find(DeliveryPerson.class,item.getDeliveryPersonId().getId());
               // String deliveryPersonJson = gson.toJson(person);
                deliveryPerson.add(Json.createObjectBuilder()
                         .add("name", person.getUsername().getName())
                       .add("contact", person.getUsername().getPhoneNo())
                       .add("adharNumber ", person.getAdhaarNumber())
                       .add("latitude", person.getLetitude())
                       .add("longitude", person.getLongitude())
                               .build());
                
                       
               outletOrders.add(Json.createObjectBuilder()
               .add("id", item.getId())
                       .add("name",item.getUserId().getName())
                       .add("items",ordersline)
                       .add("payable_amount",item.getPayableAmount())
                       .add("order_status",item.getOrderStatus())
                       .add("deliveryPerson",deliveryPerson)
                       .build()
               );
                      
            }
            JsonObject obj = Json.createObjectBuilder()
                    .add("orders",outletOrders)
                    .build();
        return obj;
        }catch(Exception ex){
            return null;
        }
      
      
    }
    
    
    

  
}
