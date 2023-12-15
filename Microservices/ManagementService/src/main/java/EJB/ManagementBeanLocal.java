/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import entities.DeliveryPerson;
import entities.ItemCategory;
import entities.Items;
import entities.Outlets;
import java.util.Collection;
import javax.ejb.Local;
import javax.json.JsonObject;
import utilities.PHResponseType;

/**
 *
 * @author HP Laptop
 */
@Local
public interface ManagementBeanLocal {

    public PHResponseType addItems(JsonObject data);

    public PHResponseType deleteItems(String itemId);

    public PHResponseType editItem(String id, JsonObject data);
    
    public Items getItemById(String itemId);

    public Collection<Items> getAllItems();

    public PHResponseType addItemCategory(JsonObject data);

    public PHResponseType deleteItemCategory(String id);

    public PHResponseType editItemCategory(String id, JsonObject data);
    
    public ItemCategory getItemCategoryById(String itemCategoryId);

    public Collection<ItemCategory> getAllItemCategory();

    public PHResponseType addDeliveryPerson(JsonObject data);

    public PHResponseType deleteDeliveryPerson(String id);

    public PHResponseType editDeliveryPerson(String id,JsonObject data);

    public Collection<DeliveryPerson> getAllDeliveryPerson();
    
    public DeliveryPerson getDeliveryPersonById(String deliveryPersonId);
    
    public PHResponseType addOutlet(JsonObject data);

    public PHResponseType deleteOutlet(String id);

    public PHResponseType editOutlet(String id,JsonObject data);

    public Collection<Outlets> getAllOutlets();
    
    public Outlets getOutletById(String outletId);
}
