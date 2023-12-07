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
import EJB.AdminBeanLocal;
import entities.ItemCategory;
import entities.Outlets;

@Path("/management")
public class ManagementService {

    @EJB
    AdminBeanLocal adminBean;

    @POST
    @Path("/addItems")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItems(@RequestBody JsonObject data) {
        boolean status = adminBean.addItems(data);

        if (status) {
            return Response.status(200, "Item Added Successfully").build();
        } else {
            return Response.status(405, "Item Adding Failed").build();
        }
    }

    @DELETE
    @Path("/deleteItem/{id}")
    public Response deleteItem(@PathParam("id") String id) {

        if (adminBean.deleteItems(id)) {
            return Response.status(200, "Item Deleted Successfully").build();
        } else {
            return Response.status(405, "Item Deleting  Failed").build();
        }
    }

    @POST
    @Path("/editItem/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editItem(@PathParam("id") String id, @RequestBody JsonObject data) {

        if (adminBean.editItem(id, data)) {
            return Response.status(200, "Item Edited Successfully").build();
        } else {
            return Response.status(405, "Failed to edit items").build();
        }
    }

    @GET
    @Path("/getAllItems")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Items> getAllItems() {
        return adminBean.getAllItems();
    }

    @POST
    @Path("/addItemCategory")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItemCategory(@RequestBody JsonObject data) {
        boolean status = adminBean.addItemCategory(data);

        if (status) {
            return Response.status(200, "Item Category Added Successfully").build();
        } else {
            return Response.status(405, "Item Category Adding Failed").build();
        }
    }

    @DELETE
    @Path("/deleteItemCategory/{id}")
    public Response deleteItemCategory(@PathParam("id") String id) {

        if (adminBean.deleteItemCategory(id)) {
            return Response.status(200, "Item Category Deleted Successfully").build();
        } else {
            return Response.status(405, "Item Category Deleting  Failed").build();
        }
    }

    @POST
    @Path("/editItemCategory/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editItemCategory(@PathParam("id") String id, @RequestBody JsonObject data) {

        if (adminBean.editItemCategory(id, data)) {
            return Response.status(200, "Item Category Edited Successfully").build();
        } else {
            return Response.status(405, "Failed to edit item category").build();
        }
    }

    @GET
    @Path("/getAllItemCategory")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ItemCategory> getAllItemCategory() {
        return adminBean.getAllItemCategory();
    }

    @POST
    @Path("/addOutlet")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOutlet(@RequestBody JsonObject data) {
        boolean status = adminBean.addOutlet(data);
        if (status) {
            return Response.status(200, "Outlet Added Successfully").build();
        } else {
            return Response.status(405, "Outlet Adding Failed").build();
        }
    }

    @DELETE
    @Path("/deleteOutlet/{id}")
    public Response deleteOutlet(@PathParam("id") String id) {

        if (adminBean.deleteOutlet(id)) {
            return Response.status(200, "Outlet Deleted Successfully").build();
        } else {
            return Response.status(405, "Outlet Deleting  Failed").build();
        }
    }

    @POST
    @Path("/editOutlet/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editOutlet(@PathParam("id") String id, @RequestBody JsonObject data) {

        if (adminBean.editOutlet(id, data)) {
            return Response.status(200, "Outlet Edited Successfully").build();
        } else {
            return Response.status(405, "Failed to edit Outlet").build();
        }
    }

    @GET
    @Path("/getAllOutlets")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Outlets> getAllOutlets() {
        return adminBean.getAllOutlets();
    }

}
