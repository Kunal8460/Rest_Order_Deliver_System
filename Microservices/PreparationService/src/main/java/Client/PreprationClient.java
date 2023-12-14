/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Client;

import java.util.Date;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import utilities.GenerateToken;

/**
 *
 * @author Bhatt Jaimin
 */
@RegisterRestClient(baseUri = "")
public interface PreprationClient {
    
    @GET
    @Path("/updateDeliveryStatusToDelivered/{orderid}")
    public Response updateDeliveryStatusToDelivered(@PathParam("orderid") String orderid);
    default String generateJWTToken()
    {
      
     
        String token = "Bearer "+ GenerateToken.generateJWT("service",constants.Constants.SHORT_EXP_TOKEN);
       
        return token;  
    }
}
