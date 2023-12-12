/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author HP Laptop
 */
public class Enums {

    public enum OrderStatus
    {
        IN_TRANSIT,
        DELIVERED,
        PREPARING,
        CANCELLED,
        PLACED
    }
    
    public enum DeliveryPersonStatus{
        ALLOCATED,
        NOT_ALLOCATED
    }
}