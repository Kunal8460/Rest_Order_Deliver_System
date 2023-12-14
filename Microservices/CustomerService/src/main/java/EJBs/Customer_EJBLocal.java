/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package EJBs;

import Entity.AddressMaster;
import Entity.Users;
import javax.ejb.Local;
import javax.json.JsonObject;

/**
 *
 * @author Bhatt Jaimin
 */
import utilities.PHResponseType;
@Local
public interface Customer_EJBLocal {
    public PHResponseType register(JsonObject data);
    public PHResponseType removeAddress(String addressid);
    public PHResponseType addAddress(JsonObject data);
    public PHResponseType updateAddress(JsonObject data);
    public PHResponseType updateProfile(JsonObject data);
    public JsonObject login(JsonObject data);
    public JsonObject getUserData(String id);
    public int sendOTP(String email);
    public double getUserCredits(String userid);
    public PHResponseType updateCredits(JsonObject data);
    public PHResponseType changePassword(JsonObject data);
}
