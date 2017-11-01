/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.repositories.Bill;



/**
 *
 * @author HP
 */
public class Text {
    private Bill currentBill;

    public Bill getCurrentBill() {
        return currentBill;
    }

    public void setCurrentBill(Bill currentBill) {
        this.currentBill = currentBill;
    }
}
