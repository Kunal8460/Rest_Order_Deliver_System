/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import javax.ejb.Local;
import org.json.JSONObject;
import utilities.PHResponseType;

/**
 *
 * @author HP Laptop
 */
@Local
public interface DeliveryBeanLocal {
    
    public String deliveryPersonAllocation(String orderId,String outletid);
    
    public PHResponseType updateDeliveryStatusToDelivered(String orderId);
        
}
