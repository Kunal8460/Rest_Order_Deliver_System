/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "pincodes")
@NamedQueries({
    @NamedQuery(name = "Pincodes.findAll", query = "SELECT p FROM Pincodes p"),
    @NamedQuery(name = "Pincodes.findByPincode", query = "SELECT p FROM Pincodes p WHERE p.pincode = :pincode"),
    @NamedQuery(name = "Pincodes.findByDistrict", query = "SELECT p FROM Pincodes p WHERE p.district = :district"),
    @NamedQuery(name = "Pincodes.findByState", query = "SELECT p FROM Pincodes p WHERE p.state = :state")})
public class Pincodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pincode")
    private Integer pincode;
    @Size(max = 50)
    @Column(name = "district")
    private String district;
    @Size(max = 50)
    @Column(name = "state")
    private String state;
    @OneToMany(mappedBy = "pincode")
    private Collection<AddressMaster> addressMasterCollection;
    @OneToMany(mappedBy = "pincode")
    private Collection<Outlets> outletsCollection;

    public Pincodes() {
    }

    public Pincodes(Integer pincode) {
        this.pincode = pincode;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Collection<AddressMaster> getAddressMasterCollection() {
        return addressMasterCollection;
    }

    public void setAddressMasterCollection(Collection<AddressMaster> addressMasterCollection) {
        this.addressMasterCollection = addressMasterCollection;
    }

    public Collection<Outlets> getOutletsCollection() {
        return outletsCollection;
    }

    public void setOutletsCollection(Collection<Outlets> outletsCollection) {
        this.outletsCollection = outletsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pincode != null ? pincode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pincodes)) {
            return false;
        }
        Pincodes other = (Pincodes) object;
        if ((this.pincode == null && other.pincode != null) || (this.pincode != null && !this.pincode.equals(other.pincode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Pincodes[ pincode=" + pincode + " ]";
    }
    
}
