package com.example.mhchatbot;

public class User {
    private String profilePicture;
    private String fullName;
    private String mobileNumber;
    private int age;
    private String email;

    public User() {}

    public User(String profilePicture, String fullName, String mobileNumber, int age, String email) {
        this.profilePicture = profilePicture;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
