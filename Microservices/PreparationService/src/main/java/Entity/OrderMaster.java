/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HP Laptop
 */
@Entity
@Table(name = "order_master")
@NamedQueries({
        @NamedQuery(name = "OrderMaster.findAll", query = "SELECT o FROM OrderMaster o"),
        @NamedQuery(name = "OrderMaster.findById", query = "SELECT o FROM OrderMaster o WHERE o.id = :id"),
        @NamedQuery(name = "OrderMaster.findByOrderStatus", query = "SELECT o FROM OrderMaster o WHERE o.orderStatus = :orderStatus"),
        @NamedQuery(name = "OrderMaster.findByAmount", query = "SELECT o FROM OrderMaster o WHERE o.amount = :amount"),
        @NamedQuery(name = "OrderMaster.findByPaymentMethod", query = "SELECT o FROM OrderMaster o WHERE o.paymentMethod = :paymentMethod"),
        @NamedQuery(name = "OrderMaster.findByDeliveryCharge", query = "SELECT o FROM OrderMaster o WHERE o.deliveryCharge = :deliveryCharge"),
        @NamedQuery(name = "OrderMaster.findByPayableAmount", query = "SELECT o FROM OrderMaster o WHERE o.payableAmount = :payableAmount"),
        @NamedQuery(name = "OrderMaster.findByOrderDate", query = "SELECT o FROM OrderMaster o WHERE o.orderDate = :orderDate"),
        @NamedQuery(name = "OrderMaster.findByCustomerId", query = "SELECT o FROM OrderMaster o WHERE o.userId = :userid AND o.orderStatus=:status ORDER BY o.orderDate DESC ")
})
public class OrderMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Size(max = 50)
    @Column(name = "order_status")
    private String orderStatus;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Size(max = 50)
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "delivery_charge")
    private Double deliveryCharge;
    @Column(name = "payable_amount")
    private Double payableAmount;
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @JoinColumn(name = "delivery_person_id", referencedColumnName = "id")
    @ManyToOne
    @JsonbTransient
    private DeliveryPerson deliveryPersonId;
    @JoinColumn(name = "outlet_id", referencedColumnName = "id")
    @ManyToOne
    @JsonbTransient
    private Outlets outletId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;
    @OneToMany(mappedBy = "orderId")
    private Collection<OrderLine> orderLineCollection;

    public OrderMaster() {
    }

    public OrderMaster(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public DeliveryPerson getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(DeliveryPerson deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }

    public Outlets getOutletId() {
        return outletId;
    }

    public void setOutletId(Outlets outletId) {
        this.outletId = outletId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @JsonbTransient
    public Collection<OrderLine> getOrderLineCollection() {
        return orderLineCollection;
    }

    public void setOrderLineCollection(Collection<OrderLine> orderLineCollection) {
        this.orderLineCollection = orderLineCollection;
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
        if (!(object instanceof OrderMaster)) {
            return false;
        }
        OrderMaster other = (OrderMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.OrderMaster[ id=" + id + " ]";
    }

}
