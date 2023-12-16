/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJB;

import javax.ejb.Local;
import javax.json.JsonObject;
import org.json.JSONObject;
import utilities.PHResponseType;

/**
 *
 * @author krdmo
 */
@Local
public interface BillBeanLocal {
    PHResponseType addOrder(JsonObject data);
}
