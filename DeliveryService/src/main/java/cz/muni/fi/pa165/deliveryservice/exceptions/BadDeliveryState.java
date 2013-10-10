/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.deliveryservice.exceptions;

/**
 * Thrown in case operation is illegal because of delivery state
 * @author Jan Vorcak
 */
public class BadDeliveryState extends Exception {

    /**
     * Creates a new instance of <code>BadDeliveryState</code> without detail
     * message.
     */
    public BadDeliveryState() {
    }

    /**
     * Constructs an instance of <code>BadDeliveryState</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BadDeliveryState(String msg) {
        super(msg);
    }
}
