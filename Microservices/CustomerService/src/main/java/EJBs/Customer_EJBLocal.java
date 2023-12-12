/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJBs;

import Entity.Users;
import javax.ejb.Local;
import javax.json.JsonObject;

/**
 *
 * @author Bhatt Jaimin
 */
@Local
public interface Customer_EJBLocal {
    public boolean register(JsonObject data);
    public boolean removeAddress(String addressid);
    public boolean addAddress(JsonObject data);
    public boolean updateAddress(JsonObject data);
    public boolean updateProfile(JsonObject data);
    public JsonObject login(JsonObject data);
    public JsonObject getUserData(String id);
    public int sendOTP(String email);
    public double getUserCredits(String userid);
    public boolean updateCredits(JsonObject data);
    public boolean changePassword(JsonObject data);
}
