package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Controller.DatabaseController.UserQuery;

@SuppressWarnings("unchecked")
public class User {
    private  UserQuery userQuery;

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

        if( uid != null)
            userQuery = new UserQuery( uid );
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
        if( data == null)
            return null;

        User user;
        user = new User(
                (String)data.get("uid"),
                (boolean)data.get("isSpecialUser"),
                (String)data.get("prioritizedName"),
                (double)data.get("balance"),
                (int)data.get("age"),
                (Blood)data.get("blood"),
                (Gender)data.get("gender"),
                (String)data.get("name"),
                (String)data.get("surname"),
                (List<String>)data.get("appointments"),
                (List<String>)data.get("reviews"),
                (List<String>)data.get("lastCalls"),
                );
        user.setOnline( (boolean)data.get("isOnline") );

        return user;
    }

    public boolean uploadUser(User user) throws Exception {
        if( user == null )
            return false;

        if( !uploadUser(user) )
            throw userQuery.getException();

        return true;
    }

    public boolean uploadUserField(String field){
        if( uid == null || userQuery == null)
            return false;

        switch (field){
            case "isOnline":
                userQuery.uploadUserData("isOnline",isOnline);
                break;
            case "isSpecialUser":
                userQuery.uploadUserData("isSpecialUser",isSpecialUser);
                break;
            case "balance":
                userQuery.uploadUserData("balance",balance);
                break;
            case "appointments":
                userQuery.uploadUserData("appointments", appointments);
                break;
            case "reviews":
                userQuery.uploadUserData("reviews", reviews);
                break;
            case "lastCalls":
                userQuery.uploadUserData("lastCalls", lastCalls);
                break;
            case "prioritizedName":
                userQuery.uploadUserData("prioritizedName", prioritizedName);
                break;
            case "blood":
                userQuery.uploadUserData("blood",blood);
                break;
            case "gender":
                userQuery.uploadUserData("gender", gender);
                break;
            case "name":
                userQuery.uploadUserData("name", name);
                break;
            case "surname":
                userQuery.uploadUserData("surname", surname);
                break;
        }

        return true;
    }

    /* Getters */

    public double getBalance() {
        return balance;
    }

    public int getAge() {
        return age;
    }

    public Blood getBlood() {
        return blood;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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


    public List<String> getReviews() {
        return reviews;
    }

    public List<String> getLastCalls() {
        return lastCalls;
    }


    public boolean isOnline() {
        return isOnline;
    }

    public String getPrioritizedName() {
        return prioritizedName;
    }

    /* Setters */

    public void setPrioritizedName(String prioritizedName) {
        this.prioritizedName = prioritizedName;
        uploadUserField("prioritizedName");
    }

    public void setBalance(String balance){
        if( balance == null)
            return;

        double actualBalance = Double.valueOf(balance);
        this.balance = actualBalance;
        uploadUserField("balance");
    }

    public void setBalance(double balance) {
        if( balance < 0.0)
            balance = 0.0;

        this.balance = balance;
        uploadUserField("balance");
    }

    public void setAge(int age) {
        if( age > 120 || age < 0)
            age = 0;

        this.age = age;
        uploadUserField("age");
    }

    public void setBlood(String blood) {
        this.blood = Blood.bloodFactory(blood);
        uploadUserField("blood");
    }

    public void setBlood(int intValue) {
        this.blood = Blood.bloodFactory(intValue);
        uploadUserField("blood");
    }

    public void setBlood(Blood blood) {
        this.blood = blood;
        uploadUserField("blood");
    }

    public void setGender(String gender){
        this.gender = Gender.genderFactory(gender);
        uploadUserField("gender");
    }

    public void setGender(int intValue){
        this.gender = Gender.genderFactory(intValue);
        uploadUserField("gender");
    }

    public void setGender(Gender gender) {
        this.gender = gender;
        uploadUserField("gender");
    }

    public void setName(String name) {
        this.name = name;
        uploadUserField("name");
    }

    public void setSurname(String surname) {
        this.surname = surname;
        uploadUserField("surname");
    }


    public void setSpecialUser(boolean isSpecialUser) {
        this.isSpecialUser = isSpecialUser;
        uploadUserField("isSpecialUser");
    }

    public void setAppointments(List<String> appointments) {
        this.appointments = appointments;
        uploadUserField("appointments");
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
        uploadUserField("reviews");
    }

    public void setLastCalls(List<String> lastCalls) {
        this.lastCalls = lastCalls;
        uploadUserField("lastCalls");
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
        uploadUserField("isOnline");
    }
}