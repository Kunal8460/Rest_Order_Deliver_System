package com.mycompany.deliveryservice.service;

import EJB.DeliveryBeanLocal;
import entities.OrderMaster;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utilities.PHResponseType;

@Path("/delivery")
public class DeliveryService {

    @EJB
    DeliveryBeanLocal dlb;

    //This rest call is made by Preparation Service
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deliveryPersonAllocation/{orderid}/{outletid}")
    public String deliveryPersonAllocation(@PathParam("orderid") String orderid, @PathParam("outletid") String outletid) {
        try {
            return dlb.deliveryPersonAllocation(orderid, outletid);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //This rest call is made by Preparation Service
    @GET
    @Path("/updateDeliveryStatusToDelivered/{orderid}")
    public Response updateDeliveryStatusToDelivered(@PathParam("orderid") String orderid) {
         PHResponseType phr = dlb.updateDeliveryStatusToDelivered(orderid);
        if (phr.getStatus()==200) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405).entity(405).build();
        }
    }

    @GET
    @Path("/getAllocatedOrders/{deliverPersonId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<OrderMaster> getAllocatedOrders(@PathParam("deliverPersonId") String deliverPersonId) {
        return dlb.getAllocatedOrders(deliverPersonId);
    }
}
