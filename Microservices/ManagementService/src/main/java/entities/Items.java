/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HP Laptop
 */
@Entity
@Table(name = "items")
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i"),
    @NamedQuery(name = "Items.findById", query = "SELECT i FROM Items i WHERE i.id = :id"),
    @NamedQuery(name = "Items.findByName", query = "SELECT i FROM Items i WHERE i.name = :name"),
    @NamedQuery(name = "Items.findByPrice", query = "SELECT i FROM Items i WHERE i.price = :price"),
    @NamedQuery(name = "Items.findByIsVeg", query = "SELECT i FROM Items i WHERE i.isVeg = :isVeg")})
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Lob
    @Column(name = "item_image")
    private byte[] itemImage;
    @Column(name = "is_veg")
    private Boolean isVeg;
    @OneToMany(mappedBy = "itemId")
    private Collection<Ratings> ratingsCollection;
    @OneToMany(mappedBy = "itemId")
    private Collection<OrderLine> orderLineCollection;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private ItemCategory categoryId;
    @JoinColumn(name = "tax_slab_id", referencedColumnName = "id")
    @ManyToOne
    private TaxSlabs taxSlabId;

    public Items() {
    }

    public Items(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getItemImage() {
        return itemImage;
    }

    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }

    public Boolean getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(Boolean isVeg) {
        this.isVeg = isVeg;
    }
    
    @JsonbTransient
    public Collection<Ratings> getRatingsCollection() {
        return ratingsCollection;
    }

    public void setRatingsCollection(Collection<Ratings> ratingsCollection) {
        this.ratingsCollection = ratingsCollection;
    }

    @JsonbTransient
    public Collection<OrderLine> getOrderLineCollection() {
        return orderLineCollection;
    }

    public void setOrderLineCollection(Collection<OrderLine> orderLineCollection) {
        this.orderLineCollection = orderLineCollection;
    }

    public ItemCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ItemCategory categoryId) {
        this.categoryId = categoryId;
    }

    public TaxSlabs getTaxSlabId() {
        return taxSlabId;
    }

    public void setTaxSlabId(TaxSlabs taxSlabId) {
        this.taxSlabId = taxSlabId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Items[ id=" + id + " ]";
    }
    
}
