package ru.teamrocket.csrsysteamdesktop.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

/**
 * Created by Alexander Shreyner on 07.11.2016.
 */
public class User implements SimpleModel{

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private Product products;

    public User(String firstName, String middleName, String lastName, String phoneNumber, String address, Product products) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.products = products;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Product getProduct() {
        return products;
    }

    public void setProduct(Product product) {
        this.products = product;
    }

    public void setProducts(Product product) {
        this.products = product;
    }

    public UserProperty composUserToProppery(){
        return new UserProperty(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                '}';
    }
}
