/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import entities.OrderMaster;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author HP Laptop
 */
@Local
public interface DeliveryBeanLocal {
    
    public String deliveryPersonAllocation(String orderId,String outletid);
    
    public boolean updateDeliveryStatusToDelivered(String orderId);
    
    public Collection<OrderMaster> getAllocatedOrders(String deliveryPersonId);
        
}
