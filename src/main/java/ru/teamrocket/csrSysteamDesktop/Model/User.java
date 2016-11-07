/**
 * Created by Alexander Shreyner on 05.11.2016.
 */

package main.java.ru.teamrocket.csrSysteamDesktop.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

public class User {

    private final StringProperty firstName;
    private final StringProperty middleName;
    private final StringProperty lastName;
    private final IntegerProperty phoneNumber;
    private final StringProperty address;

//    private final ObjectProperty<T>[] product;


    public User() {

        this.firstName = new SimpleStringProperty("Ivan");
        this.middleName = new SimpleStringProperty("Ivan");
        this.lastName = new SimpleStringProperty("Ivanov");
        this.phoneNumber = new SimpleIntegerProperty(123);
        this.address = new SimpleStringProperty("Street");
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

    public int getPhoneNumber() {
        return phoneNumber.get();
    }

    public IntegerProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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
}
