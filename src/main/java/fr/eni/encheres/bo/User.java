package fr.eni.encheres.bo;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column
    private String username;

    @Column
    private String familyName;

    @Column
    private String firstName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private String postalCode;

    @Column
    private String city;

    @Column
    private String password;

    @Column
    private int credit;

    @Column
    private boolean isAdmin;

    @Column
    private boolean isActive;

    public User(int idUser, String username, String familyName, String firstName, String email, String phone, String address, String postalCode, String city, String password, int credit, boolean isAdmin, boolean isActive) {
        this.idUser = idUser;
        this.username = username;
        this.familyName = familyName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.password = password;
        this.credit = credit;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    public User() {

    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isActive() {return isActive; }

    public void setActive(boolean isActive) {this.isActive = isActive; }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", familyName='" + familyName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", password='" + password + '\'' +
                ", credit=" + credit +
                ", isAdmin=" + isAdmin +
                ", isActive=" + isActive +
                '}';
    }
}



