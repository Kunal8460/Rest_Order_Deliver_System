/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import entities.Items;
import entities.OrderMaster;
import entities.Pincodes;
import java.util.Collection;
import javax.ejb.Local;
import javax.json.JsonArray;
import javax.json.JsonObject;
import org.json.JSONArray;

/**
 *
 * @author krdmo
 */
@Local
public interface OrderBeanLocal {
    public Collection<Items> getAllItems();
    public String getOrderHistory(String userId, String status); 
    public OrderMaster getOrderById(String id);
    public JsonObject getOutlets(int pinocode);
    public Pincodes getDistrictNameByPincode(int pincode);
    public Collection<Pincodes> getDistrictByName(String name);
    public JsonObject getOrdersByDateAndOutlet(String outletid);
    public JsonObject getOrdersByDeliveryPerson(String deliveryPerosnId,String status);
    
}
