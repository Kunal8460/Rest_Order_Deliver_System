/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package client;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author HP Laptop
 */
@RegisterRestClient(baseUri = "http://localhost:8083/CustomerService/rest/")
@Path("/customer")
public interface IClientCustomer {

//    default String generateToken(){
//        return "";
//    }
    
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @ClientHeaderParam(name = "Authorization" , value = "{generateToken}")
    public Response register(@RequestBody JsonObject data);

}
