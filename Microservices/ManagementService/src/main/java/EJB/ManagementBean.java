/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJB;

import entities.ItemCategory;
import entities.Items;
import entities.TaxSlabs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import utilities.Utils;
import entities.Outlets;
import entities.Pincodes;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import javax.json.JsonObject;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP Laptop
 */
@Stateless
public class ManagementBean implements ManagementBeanLocal {

    @PersistenceContext(unitName = "orderpu")
    private EntityManager em;

    @Override
    public boolean addItems(JsonObject data) {
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
//            newItem.setItemImage(itemImage);
            newItem.setIsVeg(isVeg);

            em.persist(newItem);
        } catch (Exception ex) {
            System.out.println("Exception found in AddItems ====> ");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteItems(String itemId) {
        try {
            Items item = em.find(Items.class, itemId);
            if (item != null) {
                em.remove(item);
            }
        } catch (Exception ex) {
            System.out.println("Exception  found in DeleteItems ====> ");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean editItem(String id, JsonObject data) {

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
//            newItem.setItemImage(itemImage);
            item.setIsVeg(isVeg);
            em.merge(item);
            return true;
        } catch (Exception ex) {
            System.out.println("Exception occured in EditItem =======>");
            ex.printStackTrace();
            return false;
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
    public boolean addItemCategory(JsonObject data) {
        try {
            String name = data.getString("name");
            Boolean isSizeVarient = Boolean.valueOf(data.getString("isSizeVarient"));

            ItemCategory itemCategory = new ItemCategory();
            itemCategory.setId(Utils.getUUID());
            itemCategory.setName(name);
            itemCategory.setIsSizeVarient(isSizeVarient);

            em.persist(itemCategory);

        } catch (Exception ex) {
            System.out.println("Exception found in Add Item Category");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteItemCategory(String id) {
        try {
            ItemCategory itemCategory = em.find(ItemCategory.class, id);
            if (itemCategory != null) {
                em.remove(itemCategory);
            }
        } catch (Exception ex) {
            System.out.println("Exception  found in DeleteItemCategory ====> ");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean editItemCategory(String id, JsonObject data) {
        try {
            String name = data.getString("name");
            Boolean isSizeVarient = Boolean.valueOf(data.getString("isSizeVarient"));

            ItemCategory itemCategory = em.find(ItemCategory.class, id);
            itemCategory.setName(name);
            itemCategory.setIsSizeVarient(isSizeVarient);

            em.merge(itemCategory);

        } catch (Exception ex) {
            System.out.println("Exception found in Delete Item Category");
            ex.printStackTrace();
            return false;
        }
        return true;
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
//    @Override
//    public boolean addDeliveryPerson(JsonObject data) {
//        try {
//
//        } catch (Exception ex) {
//            System.out.println("Exception in Adding delivering person");
//            ex.printStackTrace();
//            return false;
//        }
//        return true;
//    }

    @Override
    public boolean addOutlet(JsonObject data) {
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

        } catch (Exception ex) {
            System.out.println("Exception adding outlets");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteOutlet(String id) {
        try {
            Outlets outlet = em.find(Outlets.class, id);
            em.remove(outlet);
        } catch (Exception ex) {
            System.out.println("Exception found in Deleting outlet");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean editOutlet(String id, JsonObject data) {
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
        } catch (Exception ex) {
            System.out.println("Exception found in Editing outlet");
            ex.printStackTrace();
            return false;
        }
        return true;
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

}
