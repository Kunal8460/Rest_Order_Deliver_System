/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.Random;

/**
 *
 * @author Bhatt Jaimin
 */
public class OTPUtils {
   public static String generateOTP() {
       String randomNum = String.format("%06d", (int) (Math.random() * 900000) + 100000);
       return randomNum;
  }
}
