package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private final String uid;
    private boolean isSpecialUser;

    private String prioritizedName;
    private double balance;
    private int age;
    private Blood blood;
    private Gender gender;
    private String name;
    private String surname;
    private List<String> appointments;          // stores UID of each appointment
    private List<String> reviews;               // stores UID of each review
    private List<String> lastCalls;             // stores UID of each call
    private boolean isOnline = false;

    public User(String uid, boolean isSpecialUser, String prioritizedName, double balance, int age, Blood blood, Gender gender, String name, String surname){
        this(uid,isSpecialUser,prioritizedName,balance,age,blood,gender,name,surname, null, null,null);
    }

    public User( String uid, boolean isSpecialUser, String prioritizedName, double balance, int age, Blood blood, Gender gender, String name, String surname,
                 List<String> appointments, List<String> reviews, List<String> lastCalls){
        this.uid = uid;
        this.isSpecialUser = isSpecialUser;
        this.prioritizedName = prioritizedName;
        this.balance = balance;
        this.age = age;
        this.blood = blood;
        this.gender = gender;
        this.name = name;
        this.surname = surname;

        if( appointments != null)
            this.appointments = appointments;
        else
            this.appointments = new ArrayList<>();

        if( reviews != null)
            this.reviews = reviews;
        else
            this.reviews = new ArrayList<>();

        if( lastCalls != null)
            this.lastCalls = lastCalls;
        else
            this.lastCalls = new ArrayList<>();

    }

    public Map<String, Object> getUserData(){
        Map<String, Object> data = new HashMap<>();

        data.put("uid", uid);
        data.put("isSpecialUser", isSpecialUser);
        data.put("prioritizedName", prioritizedName);
        data.put("balance", balance);
        data.put("age", age);
        data.put("blood", blood.getIntValue());
        data.put("gender", gender.getIntValue());
        data.put("name", name);
        data.put("surname", surname);
        data.put("appointments", appointments);
        data.put("reviews", reviews);
        data.put("lastCalls", lastCalls);
        data.put("isOnline", isOnline);

        return data;
    }

    public static User createUser(Map<String, Object> data){
        //TODO: Create and return user with given data
        return null;
    }


    public void uploadUserField(String field){

        switch (field){
            case "isSpecialUser":
                //
                break;
            case "prioritizedName":
                //
                break;
            case "balance":
                //
                break;
            case "blood":
                //
                break;
            case "gender":
                //
                break;
            case "name":
                //
                break;
            case "surname":
                //
                break;
            case "appointments":
                //
                break;
            case "reviews":
                //
                break;
            case "lastCalls":
                //
                break;
            case "isOnline":
                //
                break;
        }


    }

    public String getPrioritizedName() {
        return prioritizedName;
    }

    public void setPrioritizedName(String prioritizedName) {
        this.prioritizedName = prioritizedName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(String balance){
        if( balance == null)
            return;

        double actualBalance = Double.valueOf(balance);
        this.balance = actualBalance;
    }

    public void setBalance(double balance) {
        if( balance < 0.0)
            balance = 0.0;

        this.balance = balance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if( age > 120 || age < 0)
            age = 0;

        this.age = age;
    }

    public Blood getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = Blood.bloodFactory(blood);
    }

    public void setBlood(int intValue) {
        this.blood = Blood.bloodFactory(intValue);
    }

    public void setBlood(Blood blood) {
        this.blood = blood;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(String gender){
        this.gender = Gender.genderFactory(gender);
    }

    public void setGender(int intValue){
        this.gender = Gender.genderFactory(intValue);
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public void setSpecialUser(boolean specialUser) {
        isSpecialUser = specialUser;
    }

    public boolean isSpecialUser() {
        return isSpecialUser;
    }

    public String getUid() {
        return uid;
    }

    public List<String> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<String> appointments) {
        this.appointments = appointments;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public List<String> getLastCalls() {
        return lastCalls;
    }

    public void setLastCalls(List<String> lastCalls) {
        this.lastCalls = lastCalls;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}