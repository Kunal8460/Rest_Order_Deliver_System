/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import entities.AddressMaster;
import entities.Items;
import entities.OrderLine;
import entities.OrderMaster;
import entities.Outlets;
import entities.Pincodes;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.json.JSONArray;
import org.json.JSONObject;
import utilities.Enums.OrderStatus;

/**
 *
 * @author krdmo
 */
@Stateless
public class OrderBean implements OrderBeanLocal {

    @PersistenceContext(unitName = "orderpu")
    EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Collection<Items> getAllItems() {
        Collection<Items> items = em.createNamedQuery("Items.findAll").getResultList();
        return items;
    }

    @Override
    public String getOrderHistory(String userId, String status) {
//        Users user = (Users) em.find(Users.class,userId);
        JSONArray orderHistory = new JSONArray();
        try {
            Collection<OrderMaster> orders = em.createNamedQuery("OrderMaster.findByCustomerId").setParameter("userid", userId).setParameter("status", status).getResultList();

            for (OrderMaster order : orders) {
                Collection<OrderLine> orderLineItems = order.getOrderLineCollection();
                JSONArray ordersline = new JSONArray();
                for (OrderLine ol : orderLineItems) {
                    JSONObject lineItem = new JSONObject();
                    lineItem.put("name", ol.getItemId().getName());
                    lineItem.put("quantity", ol.getQuantity());
                    ordersline.put(lineItem);
                }

                JSONObject outlets = new JSONObject();
                outlets.put("id", order.getOutletId().getId());
                outlets.put("address", order.getOutletId().getAddress());
                outlets.put("name", order.getOutletId().getName());
                outlets.put("phoneNo", order.getOutletId().getPhoneNo());
                outlets.put("pincode", order.getOutletId().getPincode().getPincode());

                JSONObject deliveryPerson = new JSONObject();

                deliveryPerson.put("name", order.getDeliveryPersonId().getUsername().getName());
                deliveryPerson.put("phoneNo", order.getDeliveryPersonId().getUsername().getPhoneNo());

                JSONObject orderObject = new JSONObject();
                orderObject.put("id", order.getId());
                orderObject.put("amount", order.getAmount());
                orderObject.put("payableAmount", order.getPayableAmount());
                orderObject.put("payableAmount", order.getPayableAmount());
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                
                orderObject.put("orderDate",format.format(order.getOrderDate()) );
                orderObject.put("orderStatus", order.getOrderStatus());
                orderObject.put("paymentMethod", order.getPaymentMethod());
                orderObject.put("outletId", outlets);
                orderObject.put("deliveryPersonId", deliveryPerson);
                orderObject.put("items", ordersline);
                orderHistory.put(orderObject);
            }
                return orderHistory.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new JSONArray().toString();
        }
    }

    @Override
    public OrderMaster getOrderById(String id) {
        OrderMaster order = (OrderMaster) em.createNamedQuery("OrderMaster.findById").setParameter("id", id).getSingleResult();
        return order;
    }

    @Override
    public Pincodes getDistrictNameByPincode(int pincode) {
        Pincodes pin = (Pincodes) em.createNamedQuery("Pincodes.findByPincode").setParameter("pincode", pincode).getSingleResult();
        return pin;
    }

    @Override
    public Collection<Pincodes> getDistrictByName(String name) {
        Collection<Pincodes> pins = em.createNamedQuery("Pincodes.findByDistrict").setParameter("district", name + "%").getResultList();
        return pins;
    }

    @Override
    public JsonObject getOutlets(int pincode) {
        try {
            Pincodes pin = getDistrictNameByPincode(pincode);
            Collection<Outlets> outlets = em.createQuery("SELECT o FROM Outlets o WHERE o.pincode.district = :district").setParameter("district", pin.getDistrict()).getResultList();
            JsonArrayBuilder jsonarray = Json.createArrayBuilder();
            for (Outlets item : outlets) {
                jsonarray.add(Json.createObjectBuilder()
                        .add("outletid", item.getId())
                        .add("name", item.getName())
                        .build()
                );

            }
            JsonObject obj = Json.createObjectBuilder()
                    .add("outlets", jsonarray)
                    .build();
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public JsonObject getOrdersByDateAndOutlet(String outletid) {
        try {
            List<OrderMaster> todaysOrder = (List<OrderMaster>) em.createQuery("SELECT o FROM OrderMaster o WHERE o.outletId.id =:outletid AND o.orderDate = :orderDate AND o.orderStatus!= :status").setParameter("outletid", outletid).setParameter("orderDate", new Date()).setParameter("status", OrderStatus.PLACED.toString()).getResultList();
            JsonArrayBuilder ordersline = Json.createArrayBuilder();

            JsonArrayBuilder outletOrders = Json.createArrayBuilder();
            for (OrderMaster item : todaysOrder) {

                List<OrderLine> items = em.createQuery("SELECT o FROM OrderLine o WHERE o.orderId.id = :orderid").setParameter("orderid", item.getId()).getResultList();

                for (OrderLine e : items) {
                    ordersline.add(Json.createObjectBuilder()
                            .add("name", e.getItemId().getName())
                            .add("quantity", e.getQuantity())
                            .build());
                }

                outletOrders.add(Json.createObjectBuilder()
                        .add("id", item.getId())
                        .add("name", item.getUserId().getName())
                        .add("totalAmount", item.getPayableAmount())
                        .add("status", item.getOrderStatus())
                        .add("deliveryPerson", item.getDeliveryPersonId().getUsername().getName())
                        .add("items", ordersline)
                        .build()
                );

            }
            JsonObject obj = Json.createObjectBuilder()
                    .add("orders", outletOrders)
                    .build();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public JsonObject getOrdersByDeliveryPerson(String deliveryPerosnId, String status) {
        String useraddress = null;
        try {

            List<OrderMaster> todaysOrder = (List<OrderMaster>) em.createQuery("SELECT o FROM OrderMaster o WHERE o.deliveryPersonId.id =:id AND o.orderStatus = :status").setParameter("id", deliveryPerosnId).setParameter("status", status).getResultList();
            JsonArrayBuilder ordersline = Json.createArrayBuilder();
            JsonArrayBuilder outletOrders = Json.createArrayBuilder();
            for (OrderMaster item : todaysOrder) {
                List<AddressMaster> address = (List<AddressMaster>) item.getUserId().getAddressMasterCollection();
                JsonArrayBuilder addresses = Json.createArrayBuilder(); // Empty array for "Address"

                useraddress = address.get(0).getAdderss() + "," + address.get(0).getPincode().getDistrict() + "," + address.get(0).getPincode().getState() + "-" + address.get(0).getPincode().getPincode();

                List<OrderLine> items = em.createQuery("SELECT o FROM OrderLine o WHERE o.orderId.id = :orderid").setParameter("orderid", item.getId()).getResultList();
                for (OrderLine e : items) {
                    ordersline.add(Json.createObjectBuilder()
                            .add("name", e.getItemId().getName())
                            .add("quantity", e.getQuantity())
                            .build());
                }

                outletOrders.add(Json.createObjectBuilder()
                        .add("id", item.getId())
                        .add("userid", item.getUserId().getId())
                        .add("name", item.getUserId().getName())
                        .add("payable_amount", item.getPayableAmount())
                        .add("phoneNo", item.getUserId().getPhoneNo())
                        .add("address", useraddress)
                        .add("items", ordersline)
                        .add("order_status", item.getOrderStatus())
                        .build()
                );

            }
            JsonObject obj = Json.createObjectBuilder()
                    .add("orders", outletOrders)
                    .build();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
