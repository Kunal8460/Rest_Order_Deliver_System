/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.Random;
import java.util.UUID;

/**
 *
 * @author HP Laptop
 */
public class Utils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
    }
    
    public static String generateOtp(Integer length) {
        String numbers = "0123456789";
        char[] otp = new char[length];
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(rand.nextInt(numbers.length()));
        }
        return new String(otp);
    }

}
