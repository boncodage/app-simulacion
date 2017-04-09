package com.imdb.SimulacionCarnet;

import java.util.ArrayList;
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
        this.setState("");
        this.clientProcessesData = new ArrayList<>();
        setClientProcessesData();
    }

    //ID
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //State of client in Queue
    private String state;
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }

    //List of Processes of the Total Queue.
    private ArrayList<Process> clientProcessesData;

    public ArrayList<Process> getClientProcessesData() {
        return this.clientProcessesData;
    }
    public void setClientProcessesData() {
        try{
            for (Process process: Repository.getProcesses()) {
                Process newProcess = new Process(process.getId(),process.getDescription());
                this.clientProcessesData.add(newProcess);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
