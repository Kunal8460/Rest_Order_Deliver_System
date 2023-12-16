/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import client.IClientCustomer;
import entities.DeliveryPerson;
import entities.ItemCategory;
import entities.Items;
import entities.TaxSlabs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import utilities.Utils;
import entities.Outlets;
import entities.Pincodes;
import entities.UserRoles;
import entities.Users;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.PersistenceContext;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import utilities.PHResponseType;

/**
 *
 * @author HP Laptop
 */
@Stateless
public class ManagementBean implements ManagementBeanLocal {

    @PersistenceContext(unitName = "orderpu")
    private EntityManager em;

    @Inject
    @RestClient
    IClientCustomer cli;

    @Override
    public PHResponseType addItems(JsonObject data) {
        PHResponseType phr = new PHResponseType();
        try {
            String itemname = data.getString("name");
            String categoryId = data.getString("categoryId");
            String description = data.getString("description");
            String taxSlabId = data.getString("taxSlabId");
            Double price = Double.parseDouble(data.getString("price"));
            Boolean isVeg = data.getBoolean("isVeg");

            Items newItem = new Items();
            newItem.setId(Utils.getUUID());
            newItem.setName(itemname);
//            ItemCategory itemCategory = (ItemCategory)em.find(ItemCategory.class,categoryId);
            ItemCategory itemCategory = (ItemCategory) em.createNamedQuery("ItemCategory.findById").setParameter("id", categoryId).getSingleResult();
            newItem.setCategoryId(itemCategory);

            newItem.setDescription(description);
            TaxSlabs taxSlab = (TaxSlabs) em.createNamedQuery("TaxSlabs.findById").setParameter("id", taxSlabId).getSingleResult();
            newItem.setTaxSlabId(taxSlab);
            newItem.setPrice(price);
            byte[] itemImage = Base64.getDecoder().decode(data.getString("itemImage").getBytes());
            newItem.setItemImage(itemImage);
            newItem.setIsVeg(isVeg);

            phr.setStatus(200);
            phr.setMessage("Item Added successfully");
            em.persist(newItem);
        } catch (Exception ex) {
            System.out.println("Exception found in AddItems ====> ");
            phr.setStatus(405);
            phr.setMessage("Item Adding Failed");
            return phr;
        }
        return phr;
    }

    @Override
    public PHResponseType deleteItems(String itemId) {
        PHResponseType phr = new PHResponseType();
        try {
            Items item = em.find(Items.class, itemId);
            if (item != null) {
                em.remove(item);
                phr.setStatus(200);
                phr.setMessage("Item Deleted successfully");
            }
        } catch (Exception ex) {
            System.out.println("Exception  found in DeleteItems ====> ");
            ex.printStackTrace();
            phr.setStatus(405);
            phr.setMessage("Item deleting Failed");
            return phr;
        }
        return phr;
    }

    @Override
    public PHResponseType editItem(String id, JsonObject data) {
        PHResponseType phr = new PHResponseType();
        try {
            String itemname = data.getString("name");
            String categoryId = data.getString("categoryId");
            String description = data.getString("description");
            String taxSlabId = data.getString("taxSlabId");
            Double price = Double.parseDouble(data.getString("price"));
            Boolean isVeg = data.getBoolean("isVeg");

            Items item = em.find(Items.class, id);
            item.setName(itemname);
            ItemCategory itemCategory = (ItemCategory) em.createNamedQuery("ItemCategory.findById").setParameter("id", categoryId).getSingleResult();
            item.setCategoryId(itemCategory);

            item.setDescription(description);
            TaxSlabs taxSlab = (TaxSlabs) em.createNamedQuery("TaxSlabs.findById").setParameter("id", taxSlabId).getSingleResult();
            item.setTaxSlabId(taxSlab);
            item.setPrice(price);
            byte[] itemImage = Base64.getDecoder().decode(data.getString("itemImage").getBytes());
            item.setItemImage(itemImage);
            item.setIsVeg(isVeg);

            phr.setStatus(200);
            phr.setMessage("Item Updated successfully");
            em.merge(item);
            return phr;
        } catch (Exception ex) {
            System.out.println("Exception occured in EditItem =======>");
            ex.printStackTrace();
            phr.setStatus(405);
            phr.setMessage("Item Editing Failed");
            return phr;
        }
    }

    @Override
    public Collection<Items> getAllItems() {
        Collection<Items> allItems = new ArrayList<>();
        try {
            allItems = em.createNamedQuery("Items.findAll").getResultList();
        } catch (Exception ex) {
            System.out.println("Exception occured in GetAllItems");
            ex.printStackTrace();
            return allItems;
        }
        return allItems;
    }

    @Override
    public Items getItemById(String itemId) {
        try {
            return (Items) em.createNamedQuery("Items.findById").setParameter("id", itemId).getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception found in getItemById");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public PHResponseType addItemCategory(JsonObject data) {
        PHResponseType phr = new PHResponseType();
        try {
            String name = data.getString("name");
            Boolean isSizeVarient = Boolean.valueOf(data.getString("isSizeVarient"));

            ItemCategory itemCategory = new ItemCategory();
            itemCategory.setId(Utils.getUUID());
            itemCategory.setName(name);
            itemCategory.setIsSizeVarient(isSizeVarient);

            em.persist(itemCategory);

            phr.setMessage("Item Category Added Successfully");
            phr.setStatus(200);

        } catch (Exception ex) {
            System.out.println("Exception found in Add Item Category");
            ex.printStackTrace();
            phr.setStatus(405);
            phr.setMessage("Item Category Adding Failed");
            return phr;
        }
        return phr;
    }

    @Override
    public PHResponseType deleteItemCategory(String id) {
        PHResponseType phr = new PHResponseType();
        try {
            ItemCategory itemCategory = em.find(ItemCategory.class, id);
            if (itemCategory != null) {
                em.remove(itemCategory);
                phr.setMessage("Item Category Deleted Successfully");
                phr.setStatus(200);
            }
        } catch (Exception ex) {
            System.out.println("Exception  found in DeleteItemCategory ====> ");
            ex.printStackTrace();
            phr.setStatus(405);
            phr.setMessage("Item Category Deleting Failed");
            return phr;
        }
        return phr;
    }

    @Override
    public PHResponseType editItemCategory(String id, JsonObject data) {
        PHResponseType phr = new PHResponseType();
        try {
            String name = data.getString("name");
            Boolean isSizeVarient = Boolean.valueOf(data.getString("isSizeVarient"));

            ItemCategory itemCategory = em.find(ItemCategory.class, id);
            itemCategory.setName(name);
            itemCategory.setIsSizeVarient(isSizeVarient);

            em.merge(itemCategory);

            phr.setMessage("Item Category Edited Successfully");
            phr.setStatus(200);
        } catch (Exception ex) {
            System.out.println("Exception found in Delete Item Category");
            ex.printStackTrace();
            phr.setStatus(405);
            phr.setMessage("Item Category Editing Failed");
            return phr;
        }
        return phr;
    }

    @Override
    public Collection<ItemCategory> getAllItemCategory() {
        try {
            return em.createNamedQuery("ItemCategory.findAll").getResultList();
        } catch (Exception ex) {
            System.out.println("Exception found in getAllItemCategory");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ItemCategory getItemCategoryById(String itemCategoryId) {
        try {
            return (ItemCategory) em.createNamedQuery("ItemCategory.findById").setParameter("id", itemCategoryId).getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception found in getItemCategoryById");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public PHResponseType addOutlet(JsonObject data) {
        PHResponseType phr = new PHResponseType();
        try {
            String name = data.getString("name");
            String address = data.getString("address");
            BigInteger phoneno = new BigInteger(data.getString("phoneNo"));
            Double latitude = Double.parseDouble(data.getString("latitude"));
            Double longitude = Double.parseDouble(data.getString("longitude"));
            Integer pincode = data.getInt("pincode");

            Outlets outlets = new Outlets();
            outlets.setId(Utils.getUUID());
            outlets.setName(name);
            outlets.setAddress(address);
            outlets.setPhoneNo(phoneno);
            outlets.setLatitude(latitude);
            outlets.setLongitude(longitude);
            Pincodes pc = em.find(Pincodes.class, pincode);
            outlets.setPincode(pc);

            em.persist(outlets);

            phr.setMessage("Outlet Added Successfully");
            phr.setStatus(200);
        } catch (Exception ex) {
            System.out.println("Exception adding outlets");
            ex.printStackTrace();
            phr.setStatus(405);
            phr.setMessage("Outlet Adding Failed");
            return phr;
        }
        return phr;
    }

    @Override
    public PHResponseType deleteOutlet(String id) {
        PHResponseType phr = new PHResponseType();
        try {
            Outlets outlet = em.find(Outlets.class, id);
            if (outlet != null) {
                em.remove(outlet);
                phr.setMessage("Outlet Deleted Successfully");
                phr.setStatus(200);
            }
        } catch (Exception ex) {
            System.out.println("Exception found in Deleting outlet");
            ex.printStackTrace();
            phr.setStatus(405);
            phr.setMessage("Outlet Deleting Failed");
            return phr;
        }
        return phr;
    }

    @Override
    public PHResponseType editOutlet(String id, JsonObject data) {
        PHResponseType phr = new PHResponseType();
        try {
            Outlets outlet = em.find(Outlets.class, id);
            outlet.setName(data.getString("name"));
            outlet.setAddress(data.getString("address"));
            outlet.setLatitude(Double.parseDouble(data.getString("latitude")));
            outlet.setLongitude(Double.parseDouble(data.getString("longitude")));
            outlet.setPhoneNo(new BigInteger(data.getString("phoneNo")));
            Integer pincode = data.getInt("pincode");
            Pincodes pc = em.find(Pincodes.class, pincode);
            outlet.setPincode(pc);

            em.merge(outlet);
            phr.setMessage("Outlet Edited Successfully");
            phr.setStatus(200);
        } catch (Exception ex) {
            System.out.println("Exception found in Editing outlet");
            ex.printStackTrace();
            phr.setStatus(405);
            phr.setMessage("Outlet Editing Failed");
            return phr;
        }
        return phr;
    }

    @Override
    public Collection<Outlets> getAllOutlets() {
        try {
            Collection<Outlets> outlets = em.createNamedQuery("Outlets.findAll").getResultList();
            return outlets;
        } catch (Exception ex) {
            System.out.println("Exception found in GetAllOutlets");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Outlets getOutletById(String outletId) {
        try {
            return (Outlets) em.createNamedQuery("Outlets.findById").setParameter("id", outletId).getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception found in getOutletById");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public PHResponseType addDeliveryPerson(JsonObject data) {
        PHResponseType phr = new PHResponseType();
        try {
            cli.register(data);
            DeliveryPerson dp = new DeliveryPerson();
            dp.setId(Utils.getUUID());
            String username = data.getString("username");
            Users user = (Users) em.createNamedQuery("Users.findByUsername").setParameter("username", username).getSingleResult();
            dp.setUsername(user);
            dp.setAdhaarNumber(new BigInteger(data.getString("aadharNumber")));
            Outlets outlet = em.find(Outlets.class, data.getString("outletId"));
            dp.setOutletId(outlet);
            em.persist(dp);

            phr.setStatus(200);
            phr.setMessage("Delivery Person added Successfully");
            return phr;

        } catch (Exception ex) {
            System.out.println("Exception found in AddDeliveryPerson ");
            phr.setStatus(405);
            phr.setMessage("Failed to add Delivery Person");
            return phr;
        }
    }

    @Override
    public PHResponseType deleteDeliveryPerson(String id) {
        PHResponseType phr = new PHResponseType();
        try {
            DeliveryPerson dp = em.find(DeliveryPerson.class, id);
            if (dp != null) {
                UserRoles role = (UserRoles) em.createNamedQuery("UserRoles.findByUsername").setParameter("username", dp.getUsername().getUsername()).getSingleResult();
                em.remove(role);
                em.remove(dp.getUsername());
                em.remove(dp);

                phr.setStatus(200);
                phr.setMessage("Delivery Person added Successfully");
            }
            return phr;
        } catch (Exception ex) {
            System.out.println("Exception found in deleting deliveryperson");
            ex.printStackTrace();
            phr.setStatus(405);
            phr.setMessage("Failed to Delete Delivery Person");
            return phr;
        }
    }

    @Override
    public PHResponseType editDeliveryPerson(String id, JsonObject data) {
        PHResponseType phr = new PHResponseType();
        try {
//            cli.register(data);
            DeliveryPerson dp = em.find(DeliveryPerson.class, id);
            String username = data.getString("username");

            Users user = (Users) em.createNamedQuery("Users.findByUsername").setParameter("username", username).getSingleResult();
            user.setName(data.getString("name"));
            user.setPhoneNo(new BigInteger(data.getString("phone_no")));
            em.merge(user);

            dp.setUsername(user);
            dp.setAdhaarNumber(new BigInteger(data.getString("aadharNumber")));
            Outlets outlet = em.find(Outlets.class, data.getString("outletId"));
            dp.setOutletId(outlet);
            em.merge(dp);
            phr.setStatus(200);
            phr.setMessage("Delivery Person edited Successfully");
            return phr;

        } catch (Exception ex) {
            System.out.println("Exception found in AddDeliveryPerson ");
            ex.printStackTrace();
            phr.setStatus(405);
            phr.setMessage("Failed to Edit Delivery Person");
            return phr;
        }
    }

    @Override
    public Collection<DeliveryPerson> getAllDeliveryPerson() {
        return em.createNamedQuery("DeliveryPerson.findAll").getResultList();
    }

    @Override
    public DeliveryPerson getDeliveryPersonById(String deliveryPersonId) {
        return em.find(DeliveryPerson.class, deliveryPersonId);
    }

}
