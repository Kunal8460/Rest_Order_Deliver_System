
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HP Laptop
 */
@Entity
@Table(name = "address_master")
@NamedQueries({
    @NamedQuery(name = "AddressMaster.findAll", query = "SELECT a FROM AddressMaster a"),
    @NamedQuery(name="AddressMaster.findByUsername",query = "SELECT a FROM AddressMaster a WHERE a.userId.username = :username"),
    @NamedQuery(name = "AddressMaster.findById", query = "SELECT a FROM AddressMaster a WHERE a.id = :id")})
public class AddressMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Lob
    @Size(max = 65535)
    @Column(name = "adderss")
    private String adderss;
    @JoinColumn(name = "pincode", referencedColumnName = "pincode")
    @ManyToOne
    private Pincodes pincode;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public AddressMaster() {
    }

    public AddressMaster(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
    }

    public Pincodes getPincode() {
        return pincode;
    }

    public void setPincode(Pincodes pincode) {
        this.pincode = pincode;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof AddressMaster)) {
            return false;
        }
        AddressMaster other = (AddressMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.AddressMaster[ id=" + id + " ]";
    }
    
}
