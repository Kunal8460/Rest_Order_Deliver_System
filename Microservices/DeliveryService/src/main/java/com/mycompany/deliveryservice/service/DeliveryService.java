package com.mycompany.deliveryservice.service;

import EJB.DeliveryBeanLocal;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        if (dlb.updateDeliveryStatusToDelivered(orderid)) {
            return Response.status(200, "Delivery status updated to DELIVERED").build();
        } else {
            return Response.status(405, "Delivery status update Falied !!").build();
        }
    }
}
