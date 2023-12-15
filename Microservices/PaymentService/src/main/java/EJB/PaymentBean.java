/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import client.IClientOrder;
import entities.OrderMaster;
import entities.Users;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import utilities.PHResponseType;

/**
 *
 * @author krdmo
 */
@Stateless
public class PaymentBean implements PaymentBeanLocal {

    @PersistenceContext(unitName = "orderpu")
    EntityManager em;
    
    @Inject @RestClient IClientOrder cli;

    @Override
    public PHResponseType checkCreditsAndPlaceOrder(JsonObject data) {
        PHResponseType phr = new PHResponseType();
        try {
            String userId = data.getString("userId");
            Users user = em.find(Users.class, userId);
            if (user.getCredits() < 1000) {
                cli.addOrder(data);
                phr.setMessage("OrderService/addOrder Rest called");
                phr.setStatus(200);
            } else {
                phr.setMessage("OrderService/addOrder Rest called");
                phr.setStatus(405);
            }
            return phr;
        } catch (Exception ex) {
            phr.setMessage("OrderService/addOrder Rest called");
            phr.setStatus(405);
            return phr;
        }
    }

    @Override
    public OrderMaster getOrderById(String id) {
        OrderMaster order = (OrderMaster) em.createNamedQuery("OrderMaster.findById").setParameter("id", id).getSingleResult();
        return order;

    }

    @Override
    public Boolean updateOrderStatus(OrderMaster order, String status) {
        if (em.contains(order)) {
            order.setOrderStatus(status);
            em.merge(order);
            return true;
        }
        return false;
    }
}
