/**
 * Created by Alexander Shreyner on 05.11.2016.
 */

package ru.teamrocket.csrsysteamdesktop.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserProperty {

    private final StringProperty firstName;
    private final StringProperty middleName;
    private final StringProperty lastName;
    private final StringProperty phoneNumber;
    private final StringProperty address;

//    private final ObjectProperty<T>[] product;


    public UserProperty(
            String firstName,
            String middleName,
            String lastName,
            String phoneNumber,
            String address
    ) {
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName = new SimpleStringProperty(middleName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.address = new SimpleStringProperty(address);
    }

    public UserProperty(User user) {
        this.firstName = new SimpleStringProperty(user.getFirstName());
        this.middleName = new SimpleStringProperty(user.getMiddleName());
        this.lastName = new SimpleStringProperty(user.getLastName());
        this.phoneNumber = new SimpleStringProperty(user.getPhoneNumber());
        this.address = new SimpleStringProperty(user.getAddress());
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty getAllName(){
        return new SimpleStringProperty(this.firstName.get() + " " + this.middleName.get() + " " + this.lastName.get());
    }
}
