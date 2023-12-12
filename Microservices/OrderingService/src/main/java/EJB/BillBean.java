/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import entities.OrderLine;
import entities.OrderMaster;
import entities.Outlets;
import entities.Users;
import utilities.Enums.OrderStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utilities.Utils;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
//import com.mycompany.Modules.OrderStatus;
import java.lang.reflect.Type;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    @Override
    public Boolean addOrder(JsonObject data) {
        String jsonItems = data.getJsonArray("items").toString();
        Gson gson = new Gson();
        Type listType = new TypeToken<Collection<OrderLine>>() {
        }.getType();
        Collection<OrderLine> items = gson.fromJson(jsonItems, listType);

        Double itemTotal = 0d;
        OrderMaster order = new OrderMaster();

        String uuid = Utils.getUUID();

        order.setId(uuid);

        order.setOrderStatus(OrderStatus.PLACED.toString());

        for (OrderLine i : items) {
            i.setId(Utils.getUUID());
            Double tax = i.getItemId().getPrice() * i.getQuantity() * (i.getItemId().getTaxSlabId().getPercentage() / 100);
            itemTotal += i.getItemId().getPrice() * i.getQuantity();
            itemTotal += tax;
        }
        order.setAmount(itemTotal);

        order.setPaymentMethod(data.getString("paymentMethod"));
        order.setDeliveryCharge(25d);
        order.setPayableAmount(itemTotal + 25);
        order.setOrderDate(new Date());
        Users user = (Users) em.createNamedQuery("Users.findById").setParameter("Id", data.getString("userId")).getSingleResult();
        order.setUserId(user);

        Outlets outlet = (Outlets) em.createNamedQuery("Outlets.findById").setParameter("Id", data.getString("outletId")).getSingleResult();
        order.setOutletId(outlet);

        order.setOrderLineCollection(items);
        em.persist(order);

        return em.contains(order);
    }

}
