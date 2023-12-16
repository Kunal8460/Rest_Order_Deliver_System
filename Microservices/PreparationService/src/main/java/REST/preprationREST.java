/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package REST;

import EJB.PreprationEJBLocal;
import Entity.OrderMaster;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import utilities.PHResponseType;

/**
 * REST Web Service
 *
 * @author Bhatt Jaimin
 */
@Path("/prepration")
@RequestScoped
public class preprationREST {

    @EJB PreprationEJBLocal ejb;
    public preprationREST() {
    }

    @GET
    @Path("/getOrders/{outletid}/{status}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getOrdersByOutletandStatus(@PathParam("outletid")String outletid,@PathParam("status")String status) {
        
      return ejb.getOrdersByOutletandStatus(outletid,status);
    }
    
    @GET
    @Path("/getOrders/{customerid}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getOrderStatusByCustomer(@PathParam("customerid")String customerid) {
        
      return ejb.getOrderStatusByCustomer(customerid);
    }
    
    @POST
    @Path("/postApprovedOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendOrderToDelivery(@RequestBody JsonObject data) {
        String orderid=data.getString("orderid");
        String outletid=data.getString("outletid");
        PHResponseType phr = ejb.sendOrderToDelivery(orderid,outletid);
      if(phr.getStatus()==200){
          return Response.status(200).entity(phr).build();
      }else{
          return Response.status(405).entity(phr).build();
      }
    }

}
