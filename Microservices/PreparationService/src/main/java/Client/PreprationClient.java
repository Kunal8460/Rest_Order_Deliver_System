/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Client;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import utilities.GenerateToken;

/**
 *
 * @author Bhatt Jaimin
 */
@RegisterRestClient(baseUri = "http://localhost:8086/DeliveryService/rest/")
@Path("/delivery")
public interface PreprationClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deliveryPersonAllocation/{orderid}/{outletid}")
    public String deliveryPersonAllocation(@PathParam("orderid") String orderid, @PathParam("outletid") String outletid);

    @GET
    default String generateJWTToken() {
        String token = "Bearer " + GenerateToken.generateJWT("service", constants.Constants.SHORT_EXP_TOKEN);
        return token;
    }
}
