/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import entities.DeliveryPerson;
import entities.OrderMaster;
import entities.Outlets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.json.JSONObject;
import utilities.Enums;
import utilities.Enums.OrderStatus;
import utilities.PHResponseType;
import utilities.Utils;

/**
 *
 * @author HP Laptop
 */
@Stateless
public class DeliveryBean implements DeliveryBeanLocal {

    @PersistenceContext(unitName = "orderpu")
    private EntityManager em;

    //This method is called by Preparation Service  
    @Override
    public String deliveryPersonAllocation(String orderId, String outletid) {
        try {
            Outlets outlet = em.find(Outlets.class, outletid);
            OrderMaster order = em.find(OrderMaster.class, orderId);

            List<DeliveryPerson> persons = new ArrayList<>();
            persons.addAll(outlet.getDeliveryPersonCollection());
            DeliveryPerson randomPerson = persons.get((int) (Math.random() * persons.size()));
            order.setDeliveryPersonId(randomPerson);
            order.setOrderStatus(Enums.OrderStatus.IN_TRANSIT.toString());
            em.merge(order);

            JSONObject json = new JSONObject();
            String OTP = Utils.generateOtp(4);
            json.put("OTP", OTP);
            return json.toString();
        } catch (Exception ex) {
            System.out.println("Exception occurred in Delivery Person Allocation");
            ex.printStackTrace();
            return null;
        }
    }

    //This method is called by Preparation Service
    @Override
    public PHResponseType updateDeliveryStatusToDelivered(String orderId) {
        PHResponseType response = new PHResponseType();
        try {
            OrderMaster order = em.find(OrderMaster.class, orderId);
            order.setOrderStatus(Enums.OrderStatus.DELIVERED.toString());
             response.setStatus(200);
          response.setMessage("Delivery status updated to DELIVERED!!!");
          return response;
        } catch (Exception ex) {
            System.out.println("Exceptuon occured in Updating delivery status to Delivered");
            ex.printStackTrace();
            response.setStatus(405);
          response.setMessage("failed!!!");
          return response;
        }
        
    }

    //To update any status via path parameter
    public boolean updateDeliveryStatus(String orderId, String status) {
        try {
            OrderMaster order = em.find(OrderMaster.class, orderId);
            for (OrderStatus o : OrderStatus.values()) {
                if (o.toString().equals(status)) {
                    order.setOrderStatus(o.toString());
                }
            }
        } catch (Exception ex) {
            System.out.println("Exceptuon occured in Updating delivery status to Delivered");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Collection<OrderMaster> getAllocatedOrders(String deliveryPersonId) {
        Collection<OrderMaster> allocatedOrders = em.createQuery("select o from OrderMaster o where o.deliveryPersonId.id = :id").setParameter("id", deliveryPersonId).getResultList();
        return allocatedOrders;
        
    }
    
    
}
