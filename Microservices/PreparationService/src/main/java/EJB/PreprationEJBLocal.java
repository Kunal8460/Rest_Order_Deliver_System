/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import Entity.OrderMaster;
import java.util.List;
import javax.ejb.Local;
import javax.json.JsonObject;
import utilities.PHResponseType;

/**
 *
 * @author Bhatt Jaimin
 */
@Local
public interface PreprationEJBLocal {
    JsonObject getOrdersByOutletandStatus(String outlet_id,String status);
    PHResponseType sendOrderToDelivery(String orderid,String outletid);
    public JsonObject  getOrderStatusByCustomer(String customerid);
    
}
