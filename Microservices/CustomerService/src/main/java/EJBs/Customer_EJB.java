/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBs;

import Entity.AddressMaster;
import Entity.Pincodes;
import Entity.UserRoles;

import Entity.Users;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import utilities.EmailUtil;
import utilities.GenerateToken;
import utilities.*;

/**
 *
 * @author Bhatt Jaimin
 */
@Stateless
public class Customer_EJB implements Customer_EJBLocal {
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("order_pu");
//    EntityManager em = emf.createEntityManager();

    @PersistenceContext(unitName = "order_pu")
    EntityManager em;

    @Override
    public PHResponseType register(JsonObject data) {
        PHResponseType response = new PHResponseType();
        try {
            String username = data.getString("username");
            String userid = Utils.getUUID();
            String name = data.getString("name");
            String password = data.getString("password");
            String email = data.getString("email");
            BigInteger contact = new BigInteger(data.getString("phone_no"));
            String user_role = data.getString("role");
            Double credits = Double.valueOf(data.getString("credits"));
            //user details
            Users newuser = new Users();
            newuser.setId(userid);
            newuser.setName(name);
            newuser.setUsername(username);
            Pbkdf2PasswordHashImpl pbk = new Pbkdf2PasswordHashImpl();
            String hash = pbk.generate(password.toCharArray());
            newuser.setPassword(hash);
            newuser.setEmail(email);
            newuser.setPhoneNo(contact);
            newuser.setCredits(credits);
            em.persist(newuser);

            //role detail
            UserRoles role = new UserRoles(username, user_role);
            role.setUsers(newuser);
            if (em.contains(newuser)) {
                em.persist(role);
                response.setStatus(200);
                response.setMessage("User Registration Successfull!!!");
            } else {
                return null;
            }
            return response;
        } catch (Exception ex) {
            System.out.println("Exception while register method!!!");
            return null;
        }

    }

    private String checkUserRole(String username) {
        try {
            UserRoles user = (UserRoles) em.createNamedQuery("UserRoles.findByUsername").setParameter("username", username).getSingleResult();
            return user.getUserRolesPK().getGroupname();
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public PHResponseType removeAddress(String addressid) {
        PHResponseType response = new PHResponseType();
        try {
            AddressMaster address = em.find(AddressMaster.class, addressid);
            em.remove(address);
            response.setStatus(200);
            response.setMessage("Address Removed Successfully!!!");
            return response;
           
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public PHResponseType addAddress(JsonObject data) {
        PHResponseType response = new PHResponseType();
        try {
            String addressId = Utils.getUUID();
            String address = data.getString("address");
            int pincode = Integer.parseInt(data.getString("pincode"));
            Users user = em.find(Users.class, data.getString("user_id"));
            if (em.contains(user)) {
                AddressMaster userAddress = new AddressMaster();
                userAddress.setId(addressId);
                userAddress.setAdderss(address);
                //Pincode
                Pincodes pincodes = new Pincodes();
                pincodes.setPincode(pincode);
                userAddress.setPincode(pincodes);
                userAddress.setUserId(user);
                em.persist(userAddress);
                response.setStatus(200);
                response.setMessage("Address Added Successfully!!!");
                return response;
            } else {
                return null;
            }

        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public PHResponseType updateAddress(JsonObject data) {
        PHResponseType response = new PHResponseType();
        try {
            String addressid = data.getString("addressid");
            AddressMaster address = em.find(AddressMaster.class, addressid);
            address.setAdderss(data.getString("address"));
            Pincodes pincodes = em.find(Pincodes.class, Integer.valueOf(data.getString("pincode")));
            address.setPincode(pincodes);
            em.merge(address);
            response.setStatus(200);
            response.setMessage("Address Updated Successfully!!!");
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public PHResponseType updateProfile(JsonObject data) {
        PHResponseType response = new PHResponseType();
        try {
            String userid = data.getString("id");
            String name = data.getString("name");
            BigInteger contact = new BigInteger(data.getString("phone_no"));

            //user details
            Users newuser = em.find(Users.class, userid);
            newuser.setName(name);
            newuser.setPhoneNo(contact);
            em.merge(newuser);
            response.setStatus(200);
            response.setMessage("Profile Updated Successfully!!!");
            return response;
        } catch (Exception ex) {
            System.out.println("Excepyion while profile updation!!!");
            return null;
        }

    }

    @Override
    public JsonObject login(JsonObject data) {
        JsonObject user_details = null;
        try {
            String email = data.getString("email");
            Users user = (Users) em.createNamedQuery("Users.findByEmail").setParameter("email", email).getSingleResult();
            String role = checkUserRole(user.getUsername());
            Pbkdf2PasswordHashImpl pbk = new Pbkdf2PasswordHashImpl();
            if (pbk.verify(data.getString("password").toCharArray(), user.getPassword())) {
                user_details = Json.createObjectBuilder()
                        .add("userid", user.getId())
                        .add("token", GenerateToken.generateJWT(role, constants.Constants.ONE_DAY_EXP_TOKEN))
                        .build();
            }
            return user_details;

        } catch (Exception ex) {
            return user_details;
        }

    }

    @Override
    public JsonObject getUserData(String id) {
        JsonObject user_details = null;
        try {

            Users user = (Users) em.createNamedQuery("Users.findById").setParameter("id", id).getSingleResult();

            List<AddressMaster> address = (List<AddressMaster>) em.createNamedQuery("AddressMaster.findByUsername").setParameter("username", user.getUsername()).getResultList();

            JsonArrayBuilder addresses = Json.createArrayBuilder(); // Empty array for "Address"
            for (AddressMaster item : address) {
                addresses.add(Json.createObjectBuilder()
                        .add("id", item.getId())
                        .add("address", item.getAdderss())
                        .add("pincode", item.getPincode().getPincode()) // Add null value for "Role"
                        .build());
            }

            user_details = Json.createObjectBuilder()
                    .add("userid", user.getId())
                    .add("name", user.getName())
                    .add("email", user.getEmail())
                    .add("username", user.getUsername())
                    .add("phoneNo", user.getPhoneNo())
                    .add("credits", user.getCredits())
                    .add("address", addresses)
                    .build();

            return user_details;

        } catch (Exception ex) {
            return user_details;
        }

    }

    @Override
    public int sendOTP(String email) {
        EmailUtil emailUtil = new EmailUtil();
        emailUtil.setSubject("PIZZAHUNT : Reset Password Request");
        try {
            int otp = emailUtil.sendSingleMailSync(email);
            return otp;

        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public double getUserCredits(String userid) {
        Users user = em.find(Users.class, userid);
        return user.getCredits();
    }

    @Override
    public PHResponseType updateCredits(JsonObject data) {
        PHResponseType response = new PHResponseType();
        try {
            Users user = em.find(Users.class, data.getString("userid"));
            user.setCredits(Double.valueOf(data.getString("credits")));
            em.merge(user);
            response.setStatus(200);
            response.setMessage("Credits Updated Successfully!!!");
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public PHResponseType changePassword(JsonObject data) {
        PHResponseType response = new PHResponseType();
        try {
            Users user = em.find(Users.class, (data.getString("id")));
            Pbkdf2PasswordHashImpl pbk = new Pbkdf2PasswordHashImpl();
            String newpass = pbk.generate(data.getString("password").toCharArray());
            user.setPassword(newpass);
            em.merge(user);
            response.setStatus(200);
            response.setMessage("Password Reseted Successfully!!!");
            return response;
        } catch (Exception ex) {
            return null;
        }
    }

}
