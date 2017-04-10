package com.imdb.SimulacionCarnet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by martin-ubuntu on 05/04/17.
 */

public class Client {

    //Constructor
    public Client(int id) {
        this.id = id;
        this.setState("");
        this.clientProcessesData = new ArrayList<>();
        setClientProcessesData();
        this.attendClicksCounter = 0;
        this.releaseClicksCounter = 0;
        finishedProcesses = "";
    }

    //ClicksCounter for delivery each click on the attend button to the especific process for the client.
    private int attendClicksCounter;
    public int getAttendClicksCounter() {
        return attendClicksCounter;
    }
    public void setAttendClicksCounter(int attendClicksCounter) {
        this.attendClicksCounter = attendClicksCounter;
    }

    private int releaseClicksCounter;
    public int getReleaseClicksCounter() {
        return releaseClicksCounter;
    }
    public void setReleaseClicksCounter(int releaseClicksCounter) {
        this.releaseClicksCounter = releaseClicksCounter;
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

    public Process getClientProcess(int position){
        return this.getClientProcessesData().get(position);
    }

    public String getFinishedProcesses() {
        return finishedProcesses;
    }

    public void setFinishedProcesses(String finishedProcesses) {
        this.finishedProcesses = finishedProcesses;
    }

    private String finishedProcesses;

}
