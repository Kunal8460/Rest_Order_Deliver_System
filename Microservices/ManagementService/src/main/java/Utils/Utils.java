/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.UUID;

/**
 *
 * @author HP Laptop
 */
public class Utils {

    public static final String DELIVERY_PERSON_ALLOCATED = "Allocated";
    public static final String DELIVERY_PERSON_NOT_ALLOCATED = "Not Allocated";

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
    }
}
