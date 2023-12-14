/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Bhatt Jaimin
 */
@Entity
@Table(name = "delivery_person")
@NamedQueries({
    @NamedQuery(name = "DeliveryPerson.findAll", query = "SELECT d FROM DeliveryPerson d"),
    @NamedQuery(name = "DeliveryPerson.findById", query = "SELECT d FROM DeliveryPerson d WHERE d.id = :id"),
    @NamedQuery(name = "DeliveryPerson.findByCurrentStatus", query = "SELECT d FROM DeliveryPerson d WHERE d.currentStatus = :currentStatus"),
    @NamedQuery(name = "DeliveryPerson.findByAdhaarNumber", query = "SELECT d FROM DeliveryPerson d WHERE d.adhaarNumber = :adhaarNumber"),
    @NamedQuery(name = "DeliveryPerson.findByLetitude", query = "SELECT d FROM DeliveryPerson d WHERE d.letitude = :letitude"),
    @NamedQuery(name = "DeliveryPerson.findByLongitude", query = "SELECT d FROM DeliveryPerson d WHERE d.longitude = :longitude")})
public class DeliveryPerson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Size(max = 50)
    @Column(name = "current_status")
    private String currentStatus;
    @Column(name = "adhaar_number")
    private BigInteger adhaarNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "letitude")
    private Double letitude;
    @Column(name = "longitude")
    private Double longitude;
    @OneToMany(mappedBy = "deliveryPersonId")
    private Collection<OrderMaster> orderMasterCollection;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private Users username;
    @JoinColumn(name = "outlet_id", referencedColumnName = "id")
    @ManyToOne
    private Outlets outletId;

    public DeliveryPerson() {
    }

    public DeliveryPerson(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public BigInteger getAdhaarNumber() {
        return adhaarNumber;
    }

    public void setAdhaarNumber(BigInteger adhaarNumber) {
        this.adhaarNumber = adhaarNumber;
    }

    public Double getLetitude() {
        return letitude;
    }

    public void setLetitude(Double letitude) {
        this.letitude = letitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Collection<OrderMaster> getOrderMasterCollection() {
        return orderMasterCollection;
    }

    public void setOrderMasterCollection(Collection<OrderMaster> orderMasterCollection) {
        this.orderMasterCollection = orderMasterCollection;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    public Outlets getOutletId() {
        return outletId;
    }

    public void setOutletId(Outlets outletId) {
        this.outletId = outletId;
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
        if (!(object instanceof DeliveryPerson)) {
            return false;
        }
        DeliveryPerson other = (DeliveryPerson) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DeliveryPerson[ id=" + id + " ]";
    }
    
}
