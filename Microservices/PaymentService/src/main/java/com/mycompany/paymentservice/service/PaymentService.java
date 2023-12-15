package com.mycompany.paymentservice.service;

import EJB.PaymentBeanLocal;
import entities.OrderMaster;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import utilities.PHResponseType;

@Path("/payment")
public class PaymentService {

    @EJB
    PaymentBeanLocal pb;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/doPaymentAndPlaceOrder")
    public Response doPaymentAndPlaceOrder(@RequestBody OrderMaster order) {
        PHResponseType phr = pb.doPaymentAndPlaceOrder(order);
        if(phr.getStatus() == 200){
            return Response.status(200).entity(phr).build();
        }
            return Response.status(405,"Payment Failed").build();
    }

}
