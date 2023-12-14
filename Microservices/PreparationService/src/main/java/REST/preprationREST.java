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
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

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
    @Path("/getOrders/outletid")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderMaster> getOrderInPrepration(@PathParam("outletid")String outletid) {
      return ejb.getOrderInPrepration(outletid);
    }
    
    @POST
    @Path("/postApprovedOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean sendOrderToDelivery(@RequestBody JsonObject data) {
        String orderid=data.getString("orderid");
      return ejb.sendOrderToDelivery(orderid);
    }

}
