package com.imdb.SimulacionCarnet;

/**
 * Created by Martin on 07/04/2017.
 */

public class Process {

    //Constructor
    public Process(int id, String description) {
        this.id = id;
        this.description = description;
    }

    //ID of each process
    private int id;

    public int getId() {
        return id;
    }

    private String description;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //stamp the time the process is created
    private long arrivalTime;
    public long getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(long arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    //Stamp the time when the client is attended.
    private long startServiceTime;
    public long getStartServiceTime() {
        return startServiceTime;
    }
    public void setStartServiceTime(long startServiceTime) {
        this.startServiceTime = startServiceTime;
    }

    //Waiting time
    private String waitTime;

    public String getWaitTime() {
        return waitTime;
    }
    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    //Service Time
    private String serviceTime;

    public String getServiceTime() {
        return serviceTime;
    }
    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    //Time since start to queue until client left
    private String totalTime;

    public String getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    //Register some incidentalEvent that cancel the process or delayed.
    private String incidentalEventDescription;

    public String getIncidentalEventDescription() {
        return incidentalEventDescription;
    }
    public void setIncidentalEventDescription(String incidentalEventDescription) {
        this.incidentalEventDescription = incidentalEventDescription;
    }

    //The process could finish?
    private boolean processCompleted;
    public boolean isProcessCompleted(){
        return this.processCompleted;
    }
    public void setProcessCompleted(boolean processCompleted){
        this.processCompleted = processCompleted;
    }

    public static String TimeConverter(long timeInMillis){
        long secs = (timeInMillis / 1000) % 60;
        long mins = (timeInMillis / 60000) % 60;
        long hours = (timeInMillis / 3600000);
        return String.format("%2d:%2d:%2d",hours,mins,secs);
    }
}
