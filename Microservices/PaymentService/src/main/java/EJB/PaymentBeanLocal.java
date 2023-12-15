/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import entities.OrderMaster;
import javax.ejb.Local;
import javax.json.JsonObject;
import utilities.PHResponseType;

/**
 *
 * @author krdmo
 */
@Local
public interface PaymentBeanLocal {
    OrderMaster getOrderById(String id);
    public Boolean updateOrderStatus(OrderMaster order, String status);
    public PHResponseType checkCreditsAndPlaceOrder(JsonObject data);
}
