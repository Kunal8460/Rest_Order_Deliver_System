/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import client.IClientPayment;
import entities.Items;
import entities.OrderLine;
import entities.OrderMaster;
import entities.Outlets;
import entities.Users;
import utilities.Enums.OrderStatus;
import utilities.Utils;
import java.util.Date;
import javax.ejb.Stateless;
//import com.mycompany.Modules.OrderStatus;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.json.JSONObject;
import utilities.PHResponseType;

/**
 *
 * @author krdmo
 *
 * JSON object Structure {
 *
 * }
 */
@Stateless
public class BillBean implements BillBeanLocal {

    @PersistenceContext(unitName = "orderpu")
    EntityManager em;

    @Inject
    @RestClient
    IClientPayment cli;

//    @Override
//    public PHResponseType addOrder(JsonObject data) {
//        PHResponseType phr1 = new PHResponseType();
//        try {
//            String jsonItems = data.getJsonArray("items").toString();
//            Gson gson = new Gson();
//            Type listType = new TypeToken<Collection<OrderLine>>() {
//            }.getType();
//            Collection<OrderLine> items = gson.fromJson(jsonItems, listType);
//
//            Double itemTotal = 0d;
//            OrderMaster order = new OrderMaster();
//
//            String uuid = Utils.getUUID();
//
//            order.setId(uuid);
//
//            order.setOrderStatus(OrderStatus.PLACED.toString());
//
//
//            order.setPaymentMethod(data.getString("paymentMethod"));
//            order.setDeliveryCharge(25d);
//            order.setPayableAmount(itemTotal + 25);
//            order.setOrderDate(new Date());
//            Users user = (Users) em.createNamedQuery("Users.findById").setParameter("id", data.getString("userId")).getSingleResult();
//            order.setUserId(user);
//
//            Outlets outlet = (Outlets) em.createNamedQuery("Outlets.findById").setParameter("id", data.getString("outletId")).getSingleResult();
//            order.setOutletId(outlet);
//
//            for (OrderLine i : items) {
//                i.setId(Utils.getUUID());
//                Double tax = i.getItemId().getPrice() * i.getQuantity() * (i.getItemId().getTaxSlabId().getPercentage() / 100);
//                itemTotal += i.getItemId().getPrice() * i.getQuantity();
//                itemTotal += tax;
//                i.setOrderId(order);
//                em.persist(i);
//            }
//            order.setAmount(itemTotal);
//
//            Response response = cli.doPaymentAndPlaceOrder(order);
//            PHResponseType phr = (PHResponseType) response.readEntity(PHResponseType.class);
//            if (phr.getStatus() == 200) {
//                order.setOrderLineCollection(items);
//                em.persist(order);
//            }
//            //Call payment service rest by sending order id 
////        ms.SendPaymentStatusInquiry(order.getId());
//
//            return phr;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            phr1.setStatus(405);
//            phr1.setMessage("Order Placing Failed");
//            return phr1;
//        }
//    }
    @Override
    public PHResponseType addOrder(JsonObject data) {
        PHResponseType phr1 = new PHResponseType();
        System.out.println(data);
        try {
            JsonArray jsonarr = data.getJsonArray("items");
            Double itemTotal = 0d;
            OrderMaster order = new OrderMaster();

            order.setId(Utils.getUUID());

            order.setOrderStatus(OrderStatus.PREPARING.toString());

            order.setPaymentMethod(data.getString("paymentMethod"));
            order.setDeliveryCharge(25d);

            order.setOrderDate(new Date());
            Users user = (Users) em.createNamedQuery("Users.findById").setParameter("id", data.getString("userId")).getSingleResult();
            order.setUserId(user);

            Outlets outlet = (Outlets) em.createNamedQuery("Outlets.findById").setParameter("id", data.getString("outletId")).getSingleResult();
            order.setOutletId(outlet);

            for (int i = 0; i < jsonarr.size(); i++) {
                OrderLine lineItem = new OrderLine();
                JSONObject object = new JSONObject(jsonarr.getJsonObject(i).toString());
                int quantity = object.getInt("quantity");
                
                lineItem.setId(Utils.getUUID());
                lineItem.setQuantity(quantity);
                Items item = em.find(Items.class,object.getString("itemId"));
                lineItem.setItemId(item);
               
                Double tax = item.getPrice() * quantity * (item.getTaxSlabId().getPercentage() / 100);
                itemTotal += item.getPrice() * quantity;
                itemTotal += tax;
                lineItem.setOrderId(order);
               
                em.persist(lineItem);

            }
            order.setAmount(itemTotal);
            order.setPayableAmount(itemTotal + 25);
            Response response = cli.doPaymentAndPlaceOrder(order);
            PHResponseType phr = (PHResponseType) response.readEntity(PHResponseType.class);
            if (phr.getStatus() == 200) {
//                order.setOrderLineCollection(items);
                em.persist(order);
            }
            return phr;
        } catch (Exception ex) {
            ex.printStackTrace();
            phr1.setStatus(405);
            phr1.setMessage("Order Placing Failed");
            return phr1;
        }
    }
}
