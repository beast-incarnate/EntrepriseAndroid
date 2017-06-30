package com.example.kunalsingh.entreprise.models;

/**
 * Created by kunalsingh on 28/06/17.
 */

public class Item {

    private String itemName;
    private int itemPrice;
    private int itemQuantity;
    private int itemId;

    public Item(String itemName, int itemPrice, int itemQuantity , int itemId) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {

        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
