/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package REST;

import EJBs.Customer_EJBLocal;
import Entity.AddressMaster;
import Entity.Users;
import static constants.Constants.ROLE_ADMIN;
import static constants.Constants.ROLE_CUSTOMER;
import static constants.Constants.ROLE_DELIVERY_PERSON;
import javax.annotation.security.RolesAllowed;
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
import javax.ws.rs.DELETE;
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
@Path("/customer")
@RequestScoped
public class CustomerREST {

    @EJB Customer_EJBLocal ejb;
    
    @Context
    private UriInfo context;

    public CustomerREST() {
    }

   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@RequestBody JsonObject data){
       PHResponseType phr = ejb.register(data);
       if(phr!=null){
           return Response.status(200).entity(phr).build();
       }else{
           return Response.status(405,"User Registration failed!!").build();
       }
       
    }
    @DELETE
    @RolesAllowed(ROLE_CUSTOMER)
    @Path("/removeAddress/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeAddress(@PathParam("id") String id){
       PHResponseType phr= ejb.removeAddress(id);
        if(phr!=null){
           return Response.status(200).entity(phr).build();
       }else{
           return Response.status(405,"Address deletion failed!!").build();
       }
    }

   
    @POST
    @RolesAllowed(ROLE_CUSTOMER)
    @Path("/addAddress")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAddress(@RequestBody JsonObject data){
        PHResponseType phr = ejb.addAddress(data);
       if(phr!=null){
           return Response.status(200).entity(phr).build();
       }else{
           return Response.status(405,"Address creation failed!!").build();
       }
    }
    
    @POST
    @RolesAllowed(ROLE_CUSTOMER)
    @Path("/updateAddress")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAddress(@RequestBody JsonObject data){
         PHResponseType phr = ejb.updateAddress(data);
       if(phr!=null){
           return Response.status(200).entity(phr).build();
       }else{
           return Response.status(405,"Address Updation failed!!").build();
       }
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@RequestBody JsonObject data){
        JsonObject obj= ejb.login(data);
        if(obj==null){
            return Response.status(404).build();
        }else{
           return Response.status(200).entity(obj).build(); 
        }
        
    }
    
    @POST
     @RolesAllowed(ROLE_CUSTOMER)
    @Path("/sendOTP/{email}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public int sendOTP(@PathParam("email")String email){
        return ejb.sendOTP(email);
    }
    
    @GET
    @Path("/user/{id}")
    @RolesAllowed({ROLE_CUSTOMER,ROLE_ADMIN,ROLE_DELIVERY_PERSON})
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserData(@PathParam("id")String id){
        JsonObject obj=ejb.getUserData(id);
        if(obj==null){
            return Response.status(404).build();
        }else{
           return Response.status(200).entity(obj).build(); 
        }
    }
    
    @POST
     @RolesAllowed(ROLE_CUSTOMER)
    @Path("/updateProfile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProfile(@RequestBody JsonObject data){
         PHResponseType phr = ejb.updateProfile(data);
       if(phr!=null){
           return Response.status(200).entity(phr).build();
       }else{
           return Response.status(405,"User Update failed!!").build();
       }
    }
    
    @GET
     @RolesAllowed(ROLE_CUSTOMER)
    @Path("/getCredits/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public double getUserCredits(@PathParam("id")String id){
        
        return ejb.getUserCredits(id);
    }
    
    @POST
    @RolesAllowed(ROLE_CUSTOMER)
    @Path("/updateCredits")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCredits(@RequestBody JsonObject data){
         PHResponseType phr = ejb.updateCredits(data);
       if(phr!=null){
           return Response.status(200).entity(phr).build();
       }else{
           return Response.status(405,"Credits Update failed!!").build();
       }
    }
    
    @POST
     @RolesAllowed(ROLE_CUSTOMER)
    @Path("/changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(@RequestBody JsonObject data){
         PHResponseType phr = ejb.changePassword(data);
       if(phr!=null){
           return Response.status(200).entity(phr).build();
       }else{
           return Response.status(405,"Couldn't Reset Password!!").build();
       }
    }
}
