package com.imdb.SimulacionCarnet;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by martin-ubuntu on 05/04/17.
 */

public class Client {
//    private int arrivalTime_process1;
//    private int releaseTime_process1;
//
//    private int arrivalTime_process2;
//    private int releaseTime_process2;
//
//    private int arrivalTime_process3;
//    private int releaseTime_process3;
//
//    private int arrivalTime_process4;
//    private int releaseTime_process4;

    //Constructor
    public Client(int id) {
        this.id = id;
        this.clientArrivalTime = new Date().getTime();
        this.clientDismissed = false;
        this.setState("");
    }

    //ID
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //clientArrivalTime
    private long clientArrivalTime;
    public long getClientArrivalTime() {
        return clientArrivalTime;
    }

    //elapsedTime
    private String elapsedTime;
    public String getElapsedTime() {
        return elapsedTime;
    }
    public void setElapsedTime(long elapsedTime) {
        long secs = (elapsedTime/1000)%60;
        long mins = (elapsedTime/60000)%60;
        long hours = mins/60;
        this.elapsedTime = hours + ":" + mins + ":" + secs;
    }

    //State of client in Queue
    private String state;
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }

    //The client left?
    private boolean clientDismissed;
    public boolean isClientDismissed() {
        return clientDismissed;
    }
    public void ClientDismissed(boolean clientDismissed) {
        this.clientDismissed = clientDismissed;
    }
}
