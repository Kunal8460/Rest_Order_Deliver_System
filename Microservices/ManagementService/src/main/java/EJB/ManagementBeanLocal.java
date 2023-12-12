/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import entities.ItemCategory;
import entities.Items;
import entities.Outlets;
import java.util.Collection;
import javax.ejb.Local;
import javax.json.JsonObject;

/**
 *
 * @author HP Laptop
 */
@Local
public interface ManagementBeanLocal {

    public boolean addItems(JsonObject data);

    public boolean deleteItems(String itemId);

    public boolean editItem(String id, JsonObject data);
    
    public Items getItemById(String itemId);

    public Collection<Items> getAllItems();

    public boolean addItemCategory(JsonObject data);

    public boolean deleteItemCategory(String id);

    public boolean editItemCategory(String id, JsonObject data);
    
    public ItemCategory getItemCategoryById(String itemCategoryId);

    public Collection<ItemCategory> getAllItemCategory();

//    public boolean addDeliveryPerson(JsonObject data);

//    public boolean deleteDeliveryPerson(String id);
//
//    public boolean editDeliveryPerson(String id,JsonObject data);
//
//    public Collection<DeliveryPerson> getAllDeliveryPerson();
//    
//    public DeliveryPerson getDeliveryPersonById(String deliveryPersonId);
    
    public boolean addOutlet(JsonObject data);

    public boolean deleteOutlet(String id);

    public boolean editOutlet(String id,JsonObject data);

    public Collection<Outlets> getAllOutlets();
    
    public Outlets getOutletById(String outletId);
}
