package model;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Appointment {
    private User consumerUser;
    private User hostUser;
    private Date timeStamp;
    private Status status;

    enum Status{
        CREATED, CANCELED,COMPLETED
    }

    public Appointment(User consumerUser, User hostUser, Date timeStamp) {
        this.consumerUser = consumerUser;
        this.hostUser = hostUser;
        this.timeStamp = timeStamp;
        status = Status.CREATED;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("consumerUser", consumerUser);
        map.put("hostUser", hostUser);
        map.put("timeStamp", timeStamp);
        map.put("status", status);

        return map;
    }

    public User getConsumerUser() {
        return consumerUser;
    }

    public void setConsumerUser(User consumerUser) {
        this.consumerUser = consumerUser;
    }

    public User getHostUser() {
        return hostUser;
    }

    public void setHostUser(User hostUser) {
        this.hostUser = hostUser;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void signAsCompleted(){
        this.status = Status.COMPLETED;
    }

    public void cancelAppointment(){
        this.status = Status.CANCELED;
    }
}
