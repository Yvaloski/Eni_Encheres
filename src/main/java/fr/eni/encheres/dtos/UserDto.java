package fr.eni.encheres.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class UserDto {
    private Long id;
    @NotBlank(message = "Email cannot be blank")
    @Email(message="Email must be of email type")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Length(min = 3, max = 20, message = "Password must be between 3-20 characters")
    private String password;
    @NotBlank(message = "Username cannot be blank")
    @Length(min = 3, max = 20, message = "Username must be between 3-20 characters")
    private String username;
    @NotBlank(message = "Firstname cannot be blank")
    @Length(min = 3, max = 20, message = "Firstname must be between 3-20 characters")
    private String firstName;
    @NotBlank(message = "Lastname cannot be blank")
    @Length(min = 3, max = 20, message = "Lastname must be between 3-20 characters")
    private String familyName;
    @Pattern(regexp = "0[0-9]{9}", message = "The phone number must follow the standard phone number format : start with 0 and followed by 9 numbers")
    private String phone;
    @NotBlank(message = "Address cannot be blank")
    @Length(min = 5, max = 100, message = "Address must be between 5 and 100 characters")
    private String address;
    @NotBlank(message = "City cannot be blank")
    @Length(min = 3, max = 50, message = "City must be between 3-50 characters")
    private String city;
    private String state;
    @NotBlank(message = "Postal Code cannot be blank")
    @Pattern(regexp = "[0-9]{5,6}", message = "The postal code must be composed of 5 or 6 numbers")
    private String postalCode;
    @NotBlank(message = "Country cannot be blank")
    @Length(min = 3, max = 20, message = "Country must be between 3-20 characters")
    private String country;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String lastName) {
        this.familyName = lastName;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}