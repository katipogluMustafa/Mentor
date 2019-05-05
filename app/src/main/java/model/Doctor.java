package model;

import java.util.List;

public class Doctor extends User {
    private Speciality speciality;
    private double hourlyRate;
    private String about;


    public Doctor(Speciality speciality, double hourlyRate, String about, String uid, boolean isSpecialUser, String prioritizedName, double balance, int age, Blood blood, Gender gender, String name, String surname,
                  List<String> appointments, List<String> reviews, List<String> lastCalls){
        super(uid,isSpecialUser,prioritizedName,balance,age,blood,gender,name,surname, appointments, reviews,lastCalls);

        if( hourlyRate < 0 )
            hourlyRate = 0;

        if(about == null)
            about = "";
        this.hourlyRate = hourlyRate;
        this.about = about;
    }


    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}

