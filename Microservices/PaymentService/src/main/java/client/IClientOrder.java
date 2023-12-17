/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package client;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author HP Laptop
 */
@RegisterRestClient(baseUri = "http://localhost:8085/OrderingService/rest")
@Path("/ordering")
public interface IClientOrder {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addOrder")
    public Response addOrder(@RequestBody JsonObject json);

}
