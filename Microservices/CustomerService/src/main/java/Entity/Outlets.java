/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "outlets")
@NamedQueries({
    @NamedQuery(name = "Outlets.findAll", query = "SELECT o FROM Outlets o"),
    @NamedQuery(name = "Outlets.findById", query = "SELECT o FROM Outlets o WHERE o.id = :id"),
    @NamedQuery(name = "Outlets.findByName", query = "SELECT o FROM Outlets o WHERE o.name = :name"),
    @NamedQuery(name = "Outlets.findByPhoneNo", query = "SELECT o FROM Outlets o WHERE o.phoneNo = :phoneNo"),
    @NamedQuery(name = "Outlets.findByLatitude", query = "SELECT o FROM Outlets o WHERE o.latitude = :latitude"),
    @NamedQuery(name = "Outlets.findByLongitude", query = "SELECT o FROM Outlets o WHERE o.longitude = :longitude")})
public class Outlets implements Serializable {

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
    @Column(name = "address")
    private String address;
    @Column(name = "phone_no")
    private BigInteger phoneNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @OneToMany(mappedBy = "outletId")
    private Collection<OrderMaster> orderMasterCollection;
    @JoinColumn(name = "pincode", referencedColumnName = "pincode")
    @ManyToOne
    private Pincodes pincode;
    @OneToMany(mappedBy = "outletId")
    private Collection<DeliveryPerson> deliveryPersonCollection;

    public Outlets() {
    }

    public Outlets(String id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(BigInteger phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonbTransient
    public Collection<OrderMaster> getOrderMasterCollection() {
        return orderMasterCollection;
    }

    public void setOrderMasterCollection(Collection<OrderMaster> orderMasterCollection) {
        this.orderMasterCollection = orderMasterCollection;
    }

    public Pincodes getPincode() {
        return pincode;
    }

    public void setPincode(Pincodes pincode) {
        this.pincode = pincode;
    }

    @JsonbTransient
    public Collection<DeliveryPerson> getDeliveryPersonCollection() {
        return deliveryPersonCollection;
    }

    public void setDeliveryPersonCollection(Collection<DeliveryPerson> deliveryPersonCollection) {
        this.deliveryPersonCollection = deliveryPersonCollection;
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
        if (!(object instanceof Outlets)) {
            return false;
        }
        Outlets other = (Outlets) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Outlets[ id=" + id + " ]";
    }

}
