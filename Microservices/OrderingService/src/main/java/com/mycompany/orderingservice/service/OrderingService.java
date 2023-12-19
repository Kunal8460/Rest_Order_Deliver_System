package com.mycompany.orderingservice.service;

import EJB.BillBeanLocal;
import EJB.OrderBeanLocal;
import entities.Items;
import entities.OrderMaster;
import entities.Pincodes;
import java.util.Collection;
import javax.ejb.EJB;
import javax.json.JsonArray;
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
import org.json.JSONArray;
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
    @Path("/getOrderHistory/{userId}/{status}")
    public String getOrderHistory(@PathParam("userId") String userId,@PathParam("status") String status) {
        return odb.getOrderHistory(userId,status);
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
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOutletByDistrict/{pincode}")
    public Response getOutletByDistrict(@PathParam("pincode") int pincode) {
        JsonObject obj = odb.getOutlets(pincode);
       if(obj!=null){
           return Response.status(200).entity(obj).build();
       }else{
           return Response.status(405,"failed").build();
       }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTodaysOrders/{outletid}")
    public Response getTodaysOrders(@PathParam("outletid") String outletid) {
        JsonObject obj = odb.getOrdersByDateAndOutlet(outletid);
       if(obj!=null){
           return Response.status(200).entity(obj).build();
       }else{
           return Response.status(405,"failed").build();
       }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOrdersByDeliveryPerosn/{id}/{status}")
    public Response getOrdersByDeliveryPerosn(@PathParam("id") String id,@PathParam("status") String status) {
        JsonObject obj = odb.getOrdersByDeliveryPerson(id,status);
       if(obj!=null){
           return Response.status(200).entity(obj).build();
       }else{
           return Response.status(405,"failed").build();
       }
    }

}
