package com.mycompany.orderingservice.service;

import EJB.BillBeanLocal;
import EJB.OrderBeanLocal;
import entities.Items;
import entities.OrderMaster;
import entities.Pincodes;
import java.util.Collection;
import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import utilities.PHResponseType;

@Path("/ordering")
public class OrderingService {

    @EJB
    OrderBeanLocal odb;

    @EJB
    BillBeanLocal bb;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllItems")
    public Collection<Items> getAllIems() {
        return odb.getAllItems();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOrderById/{orderid}")
    public OrderMaster getOrderById(@PathParam("orderid") String orderid) {
        return odb.getOrderById(orderid);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOrderHistory/{username}/{status}")
    public Collection<OrderMaster> getOrderHistory(@PathParam("username") String username, @PathParam("username") String status) {
        return odb.getOrderHistory(username, status);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getDistrictByPincode/{pincode}")
    public Pincodes getDistrictByPincode(@PathParam("pincode") String pc) {
        int pincode = Integer.parseInt(pc);
        return odb.getDistrictNameByPincode(pincode);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getDistricByName/{name}")
    public Collection<Pincodes> getDistrictByName(@PathParam("name") String name) {
        return odb.getDistrictByName(name);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addOrder")
    public Response addOrder(@RequestBody JsonObject json) {
        PHResponseType phr = bb.addOrder(json);
        if (phr!=null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "Failed to Create Order").build();
        }
    }

}
