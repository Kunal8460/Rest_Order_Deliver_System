package com.mycompany.managementservice.service;

import entities.Items;
import java.util.Collection;
import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import entities.ItemCategory;
import entities.Outlets;
import EJB.ManagementBeanLocal;
import entities.DeliveryPerson;
import utilities.PHResponseType;

@Path("/management")
public class ManagementService {

    @EJB
    ManagementBeanLocal adminBean;

    @POST
    @Path("/addItems")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItems(@RequestBody JsonObject data) {
        PHResponseType phr = adminBean.addItems(data);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "Item Adding failed!!").build();
        }
    }

    @DELETE
    @Path("/deleteItem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("id") String id) {
        PHResponseType phr = adminBean.deleteItems(id);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "User Registration failed!!").build();
        }
    }

    @POST
    @Path("/editItem/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editItem(@PathParam("id") String id, @RequestBody JsonObject data) {
        PHResponseType phr = adminBean.editItem(id, data);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "User Registration failed!!").build();
        }
    }

    @GET
    @Path("/getAllItems")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Items> getAllItems() {
        return adminBean.getAllItems();
    }

    @GET
    @Path("/getItemById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Items getItemById(@PathParam("id") String id) {
        return adminBean.getItemById(id);
    }

    @POST
    @Path("/addItemCategory")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItemCategory(@RequestBody JsonObject data) {
        PHResponseType phr = adminBean.addItemCategory(data);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "Item Category Adding failed!!").build();
        }
    }

    @DELETE
    @Path("/deleteItemCategory/{id}")
    public Response deleteItemCategory(@PathParam("id") String id) {
        PHResponseType phr = adminBean.deleteItemCategory(id);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "User Registration failed!!").build();
        }
    }

    @POST
    @Path("/editItemCategory/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editItemCategory(@PathParam("id") String id, @RequestBody JsonObject data) {
        PHResponseType phr = adminBean.editItemCategory(id, data);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "User Registration failed!!").build();
        }
    }

    @GET
    @Path("/getAllItemCategory")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ItemCategory> getAllItemCategory() {
        return adminBean.getAllItemCategory();
    }

    @GET
    @Path("/getItemCategoryById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ItemCategory getItemCategoryById(@PathParam("id") String id) {
        return adminBean.getItemCategoryById(id);
    }

    @POST
    @Path("/addOutlet")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOutlet(@RequestBody JsonObject data) {
        PHResponseType phr = adminBean.addOutlet(data);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "Outlet Adding failed!!").build();
        }
    }

    @DELETE
    @Path("/deleteOutlet/{id}")
    public Response deleteOutlet(@PathParam("id") String id) {
        PHResponseType phr = adminBean.deleteOutlet(id);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "User Registration failed!!").build();
        }
    }

    @POST
    @Path("/editOutlet/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editOutlet(@PathParam("id") String id, @RequestBody JsonObject data) {
        PHResponseType phr = adminBean.editOutlet(id, data);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "User Registration failed!!").build();
        }
    }

    @GET
    @Path("/getAllOutlets")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Outlets> getAllOutlets() {
        return adminBean.getAllOutlets();
    }

    @GET
    @Path("/getOutletById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Outlets getOutletById(@PathParam("id") String id) {
        return adminBean.getOutletById(id);
    }

    @POST
    @Path("/addDeliveryPerson")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDeliveryPerson(@RequestBody JsonObject data) {
        PHResponseType phr = adminBean.addDeliveryPerson(data);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "User Registration failed!!").build();
        }
    }

    @DELETE
    @Path("/deleteDeliveryPerson/{id}")
    public Response deleteDeliveryPerson(@PathParam("id") String id) {
        PHResponseType phr = adminBean.deleteDeliveryPerson(id);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "User Registration failed!!").build();
        }
    }

    @POST
    @Path("/editDeliveryPerson/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editDeliveryPerson(@PathParam("id") String id, @RequestBody JsonObject data) {
        PHResponseType phr = adminBean.editDeliveryPerson(id, data);
        if (phr != null) {
            return Response.status(200).entity(phr).build();
        } else {
            return Response.status(405, "User Registration failed!!").build();
        }
    }

    @GET
    @Path("/getAllDeliveryPerson")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DeliveryPerson> getAllDeliveryPerson() {
        return adminBean.getAllDeliveryPerson();
    }

    @GET
    @Path("/getDeliveryPersonById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DeliveryPerson getDeliveryPersonById(@PathParam("id") String id) {
        return adminBean.getDeliveryPersonById(id);
    }
}
