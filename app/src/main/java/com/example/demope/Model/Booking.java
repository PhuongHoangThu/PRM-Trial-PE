package com.example.demope.Model;

public class Booking {
    private int id;
    private String customerName;
    private String serviceName;
    private double price;
    private int quantity;
    private double totalValue;

    public Booking() {
    }

    public Booking(String customerName, String serviceName, double price, int quantity, double totalValue) {
        this.customerName = customerName;
        this.serviceName = serviceName;
        this.price = price;
        this.quantity = quantity;
        this.totalValue = totalValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
}
