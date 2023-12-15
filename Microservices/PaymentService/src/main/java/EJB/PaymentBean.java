/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import client.IClientOrder;
import entities.OrderLine;
import entities.OrderMaster;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    @Inject
    @RestClient
    IClientOrder cli;

    @Override
    public PHResponseType doPaymentAndPlaceOrder(OrderMaster order) {
        PHResponseType phr = new PHResponseType();
        try {
            if (order.getPaymentMethod().equals("CREDIT")) {

                if (order.getUserId().getCredits() < order.getPayableAmount()) {
                    phr.setStatus(405);
                    phr.setMessage("User Credits are less than the Payable amount");
                    return phr;
                } else {
                    phr.setStatus(200);
                    phr.setMessage("Payment Successfull");
                }
                return phr;
            } else {
                phr.setStatus(200);
                phr.setMessage("Payment Successfull");
                return phr;
            }
        } catch (Exception ex) {
            phr.setMessage("Payment Failed.");
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
